package com.tony.eureka.client1.aop;

import com.alibaba.fastjson.JSON;
import com.tony.eureka.client1.annotation.SystemLog;
import com.tony.eureka.client1.common.BaseEntity;
import com.tony.eureka.client1.common.Dictionary;
import com.tony.eureka.client1.common.SystemLogInfo;
import com.tony.eureka.client1.exception.SystemLogException;
import com.tony.eureka.client1.util.IPUtil;
import com.tony.eureka.client1.util.MixUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 鉴权后的用户对具体功能进行二次鉴权
 *
 * @author tony
 * @description SystemLogAop
 * @date 2019-08-17
 */
@Aspect
@Configuration
public class SystemLogAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemLogAop.class);

    ThreadLocal<Long> start = ThreadLocal.withInitial(() -> 0L);

    // 业务日志切点
    @Pointcut("execution(* com.tony.eureka.client1.*.controller.*.*(..))")
    public void logAspect() {
        LOGGER.debug("logAspect");
    }

    @Before("logAspect()")
    public void before() {
        LOGGER.debug("before logAspect()");
    }

    @AfterReturning("logAspect()")
    public void after() {
        LOGGER.debug("after logAspect()");
    }

    /**
     * 业务环绕日志
     *
     * @param point 切点
     */
    @Around("logAspect()")
    public Object doController(ProceedingJoinPoint point) throws Throwable {
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Map<String, Object> map = getMethodDescribe(point);
        long startTime;
        long endTime;
        long consumeTime;
        Object result;
        LOGGER.info("######################MODULE[" + map.get("businessName") + "]-FUNCTION[" +
                map.get("functionName") + "]-NO[" + map.get("functionCode") + "]START######################");

        LOGGER.debug("===========FUNCTION[" + map.get("functionName") + "]权限校验 START===========");
        if (!checkPermission(map)) {
            throw new SystemLogException("w101");
        }
        LOGGER.debug("===========FUNCTION[" + map.get("functionName") + "]权限校验 END===========");
        startTime = System.currentTimeMillis();
        start.set(startTime);
        result = point.proceed();
        endTime = System.currentTimeMillis();
        consumeTime = startTime - endTime;

        LOGGER.info("invoke pointcut method take {} ms,totally", consumeTime);
        LOGGER.debug("######################MODULE[" + map.get("businessName") + "]-FUNCTION[" +
                map.get("functionName") + "]-No[" + map.get("functionCode") + "]END######################");
        return result;
    }

    /**
     * 业务异常记录
     *
     * @param joinPoint 连接点
     * @param e         异常信息
     */
    @AfterThrowing(pointcut = "logAspect()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        Long eEnd = System.currentTimeMillis();
        Long consume = eEnd - start.get();
        Map<String, Object> map = getMethodDescribe(joinPoint);
        LOGGER.info("######################MOUDULE[" + map.get("businessName") + "]-FUNCTION[" + map.get("functionName") + "]-NO[" + map.get("functionCode") + "]EXCEPTION######################");
        SystemLogInfo systemLogInfo = buildSystemLog(map);
        systemLogInfo.setException(e);
        systemLogInfo.setBusinessConsumingTime(consume.toString());
        String msg;
        if (e instanceof SystemLogException) {
            String code = ((SystemLogException) e).getCode();
            msg = code + ":" + e.getClass();
        } else {
            msg = e.getClass().getCanonicalName() + ":" + e.getLocalizedMessage();
        }
        systemLogInfo.setInvokingResult(msg);
        LOGGER.error(systemLogInfo.toString());
        insertLog(systemLogInfo, Dictionary.AccessType.WRITE);
        LOGGER.info("######################模块[" + map.get("businessName") + "]-功能[" + map.get("functionCode") + "]-编号[" + map.get("functionCode") + "]结束######################");
    }

    /**
     * TODO 判断权限
     */
    private boolean checkPermission(Map<String, Object> map) throws Exception {
        if (map.get("functionCode") == null) {
            return false;
        } else if ("system".equalsIgnoreCase(map.get("functionCode").toString())) {
            return true;
        }
        //todo 角色权限校验
        return false;
    }

    /**
     * 插入日志表
     *
     * @param systemLogInfo 系统日志信息
     * @param accessType    访问类型
     */
    private void insertLog(SystemLogInfo systemLogInfo, Dictionary.AccessType accessType) {
        if (Dictionary.AccessType.WRITE == accessType) {
            LOGGER.debug("==========Record Log START==========");
            systemLogInfo.setId(MixUtils.getNowDate() + MixUtils.getNowTime() + MixUtils.getLastSixNum());
            try {
                //todo 插入日志信息
            } catch (Exception ex) {
                throw new SystemLogException("insert system log error", ex);
            }
            LOGGER.debug("==========Record Log END==========");
        }
    }

    /**
     * 获取业务日志注解信息.
     *
     * @param joinPoint 织入点
     * @return 方法描述集合
     */
    public Map<String, Object> getMethodDescribe(JoinPoint joinPoint) {
        Map<String, Object> map = new HashMap<>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        map.put("invokingMethod", targetName + "." + methodName + "()");
        // 获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                if (joinPoint.getArgs()[i] instanceof BaseEntity) {
                    params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
                } else {
                    params += joinPoint.getArgs()[i] + ";";
                }
            }
        }

        map.put("arguments", params);
        try {
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzList = method.getParameterTypes();
                    if (clazzList.length == joinPoint.getArgs().length) {
                        //接口日志切面
                        if (method.getAnnotation(SystemLog.class) == null) {
                            LOGGER.error("not detect this annotation!");
                            throw new SystemLogException("not detect this annotation!");
                        }
                        buildAnnotationMap(map, method);
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("resolve annotation error:", e);
            throw new SystemLogException("", e);
        }
        return map;
    }

    /**
     * 构建注解Map
     *
     * @param map    集合
     * @param method 调用方法
     * @return 结果结合
     */
    public static Map<String, Object> buildAnnotationMap(Map<String, Object> map, Method method) {
        map.put("businessName", method.getAnnotation(SystemLog.class).businessName());
        map.put("functionCode", method.getAnnotation(SystemLog.class).functionCode());
        map.put("functionName", method.getAnnotation(SystemLog.class).functionName());
        map.put("accessType", method.getAnnotation(SystemLog.class).accessType());
        map.put("logLevel", method.getAnnotation(SystemLog.class).logLevel());
        map.put("result", "success");
        return map;
    }

    /**
     * 通过注解信息生成业务日志对象.
     */
    private SystemLogInfo buildSystemLog(Map<String, Object> map) {
        SystemLogInfo systemLogInfo = new SystemLogInfo();
        String ip = IPUtil.getRequestIpAddr();
        if (StringUtils.isBlank(ip)) {
            ip = "no-ip-info";
        }

        systemLogInfo.setThreadCode(Thread.currentThread().getName());
        systemLogInfo.setBusinessDate(MixUtils.getNowDate());
        systemLogInfo.setBusinessTime(MixUtils.getNowTime());

        systemLogInfo.setOrganCode("----");
        systemLogInfo.setOrganName("----");
        systemLogInfo.setOperCode("----");
        systemLogInfo.setDeptCode("----");
        systemLogInfo.setDeptName("----");
        systemLogInfo.setOperName("----");

        systemLogInfo.setInvokingMethod(map.get("invokingMethod") + "");
        systemLogInfo.setRequestIp(ip);
        systemLogInfo.setInvokingResult(map.get("result") + "");
        systemLogInfo.setMethodArg(map.get("arguments").toString());
        systemLogInfo.setServiceCode(IPUtil.getLocalHostIpAddr());
        return systemLogInfo;
    }
}
