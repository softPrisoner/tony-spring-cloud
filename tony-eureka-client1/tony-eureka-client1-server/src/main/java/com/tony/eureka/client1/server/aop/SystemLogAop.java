package com.tony.eureka.client1.server.aop;

import com.alibaba.fastjson.JSON;
import com.tony.eureka.client1.server.annotation.SystemLog;
import com.tony.eureka.client1.server.common.BaseEntity;
import com.tony.eureka.client1.server.common.Dictionary;
import com.tony.eureka.client1.server.common.SystemLogInfo;
import com.tony.eureka.client1.server.exception.SystemLogException;
import com.tony.eureka.client1.server.util.IPUtil;
import com.tony.eureka.client1.server.util.MixUtils;
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
 * ��Ȩ����û��Ծ��幦�ܽ��ж��μ�Ȩ
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

    // ҵ����־�е�
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
     * ҵ������־
     *
     * @param point �е�
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

        LOGGER.debug("===========FUNCTION[" + map.get("functionName") + "]Ȩ��У�� START===========");
        if (!checkPermission(map)) {
            throw new SystemLogException("w101");
        }
        LOGGER.debug("===========FUNCTION[" + map.get("functionName") + "]Ȩ��У�� END===========");
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
     * ҵ���쳣��¼
     *
     * @param joinPoint ���ӵ�
     * @param e         �쳣��Ϣ
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
        LOGGER.info("######################ģ��[" + map.get("businessName") + "]-����[" + map.get("functionCode") + "]-���[" + map.get("functionCode") + "]����######################");
    }

    /**
     * TODO �ж�Ȩ��
     */
    private boolean checkPermission(Map<String, Object> map) throws Exception {
        if (map.get("functionCode") == null) {
            return false;
        } else if ("system".equalsIgnoreCase(map.get("functionCode").toString())) {
            return true;
        }
        //todo ��ɫȨ��У��
        return false;
    }

    /**
     * ������־��
     *
     * @param systemLogInfo ϵͳ��־��Ϣ
     * @param accessType    ��������
     */
    private void insertLog(SystemLogInfo systemLogInfo, Dictionary.AccessType accessType) {
        if (Dictionary.AccessType.WRITE == accessType) {
            LOGGER.debug("==========Record Log START==========");
            systemLogInfo.setId(MixUtils.getNowDate() + MixUtils.getNowTime() + MixUtils.getLastSixNum());
            try {
                //todo ������־��Ϣ
            } catch (Exception ex) {
                throw new SystemLogException("insert system log error", ex);
            }
            LOGGER.debug("==========Record Log END==========");
        }
    }

    /**
     * ��ȡҵ����־ע����Ϣ.
     *
     * @param joinPoint ֯���
     * @return ������������
     */
    public Map<String, Object> getMethodDescribe(JoinPoint joinPoint) {
        Map<String, Object> map = new HashMap<>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        map.put("invokingMethod", targetName + "." + methodName + "()");
        // ��ȡ�û����󷽷��Ĳ��������л�ΪJSON��ʽ�ַ���
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
                        //�ӿ���־����
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
     * ����ע��Map
     *
     * @param map    ����
     * @param method ���÷���
     * @return ������
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
     * ͨ��ע����Ϣ����ҵ����־����.
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
