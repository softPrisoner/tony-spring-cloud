package com.tony.eureka.client1.server.common;

/**
 * ��־����ҵ��ö����
 *
 * @author tony
 * @description Dictionary
 * @date 2019-09-11
 */
public class Dictionary {
    public enum LogLevel {
        DEBUG("debug", 4),
        INFO("info", 3),
        WARN("warn", 2),
        ERROR("error", 1);
        private final String name;
        private final int value;

        LogLevel(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return this.name;
        }

        public int getValue() {
            return this.value;
        }
    }

    /**
     * �������ݿⷽʽ,Read��ʽֻ�����־�ļ�����¼��־��Write��ʽ�����־�ļ��Ҽ�¼��־��.
     */
    public enum AccessType {
        READ, WRITE
    }
}
