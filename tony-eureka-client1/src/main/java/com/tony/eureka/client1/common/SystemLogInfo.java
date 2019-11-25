package com.tony.eureka.client1.common;


import java.io.Serializable;

/**
 * @author tony
 * @describe SystemLogInfo
 * @date 2019-09-17
 */
public class SystemLogInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -784577634453005443L;
    /**
     * 日志编号
     */
    private String id;
    /**
     * 线程号
     */
    private String threadCode;
    /**
     * 业务号
     */
    private String businessId;
    /**
     * 交易日期
     */
    private String businessDate;
    /**
     * 交易时间
     */
    private String businessTime;
    /**
     * 机构名称
     */
    private String organCode;
    /**
     * 机构名称
     */
    private String organName;
    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 操作员代码
     */
    private String operCode;
    /**
     * 操作员姓名
     */
    private String operName;
    /**
     * 功能名称
     */
    private String functionName;
    /**
     * 功能编码
     */
    private String functionCode;
    /**
     * 交易耗时(ms)
     */
    private String businessConsumingTime;
    /**
     * 执行方法
     */
    private String invokingMethod;
    /**
     * 请求IP
     */
    private String requestIp;
    /**
     * 执行结果
     */
    private String invokingResult;
    /**
     * 机器号
     */
    private String serviceCode;
    /**
     * 方法参数，仅在log4J中输出
     */
    private String methodArg;
    /**
     * 异常信息
     */
    private Throwable exception;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThreadCode() {
        return threadCode;
    }

    public void setThreadCode(String threadCode) {
        this.threadCode = threadCode;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getBusinessConsumingTime() {
        return businessConsumingTime;
    }

    public void setBusinessConsumingTime(String businessConsumingTime) {
        this.businessConsumingTime = businessConsumingTime;
    }

    public String getInvokingMethod() {
        return invokingMethod;
    }

    public void setInvokingMethod(String invokingMethod) {
        this.invokingMethod = invokingMethod;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getInvokingResult() {
        return invokingResult;
    }

    public void setInvokingResult(String invokingResult) {
        this.invokingResult = invokingResult;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getMethodArg() {
        return methodArg;
    }

    public void setMethodArg(String methodArg) {
        this.methodArg = methodArg;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
