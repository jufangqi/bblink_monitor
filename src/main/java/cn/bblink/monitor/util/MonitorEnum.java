package cn.bblink.monitor.util;

/**
 * Created by Administrator on 2015/12/2.
 */
public class MonitorEnum {
    public static enum PROJECT_STATUS{
        Y("有效"),
        N("无效");
        private String cnName;
        private PROJECT_STATUS(String name) {
            this.cnName = name;
        }

        public String getCode() {
            return this.name();
        }

        public String getCnName() {
            return this.cnName;
        }

        public static String getCnName(String code) {
            for (PROJECT_STATUS item : PROJECT_STATUS.values()) {
                if (item.getCode().equals(code)) {
                    return item.getCnName();
                }
            }
            return code;
        }

        public String toString() {
            return this.name();
        }
    }
}
