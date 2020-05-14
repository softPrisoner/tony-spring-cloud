package com.tony.eureka.client1.server.common;


import java.io.Serializable;

/**
 * @author tony
 * @describe SystemLogInfo
 * @date 2019-09-17
 */
public class SystemLogInfo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -784577634453005443L;
    /**
     * ��־���
     */
    private String id;
    /**
     * �̺߳�
     */
    private String threadCode;
    /**
     * ҵ���
     */
    private String businessId;
    /**
     * ��������
     */
    private String businessDate;
    /**
     * ����ʱ��
     */
    private String businessTime;
    /**
     * ��������
     */
    private String organCode;
    /**
     * ��������
     */
    private String organName;
    /**
     * ���ű���
     */
    private String deptCode;
    /**
     * ��������
     */
    private String deptName;
    /**
     * ����Ա����
     */
    private String operCode;
    /**
     * ����Ա����
     */
    private String operName;
    /**
     * ��������
     */
    private String functionName;
    /**
     * ���ܱ���
     */
    private String functionCode;
    /**
     * ���׺�ʱ(ms)
     */
    private String businessConsumingTime;
    /**
     * ִ�з���
     */
    private String invokingMethod;
    /**
     * ����IP
     */
    private String requestIp;
    /**
     * ִ�н��
     */
    private String invokingResult;
    /**
     * ������
     */
    private String serviceCode;
    /**
     * ��������������log4J�����
     */
    private String methodArg;
    /**
     * �쳣��Ϣ
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
