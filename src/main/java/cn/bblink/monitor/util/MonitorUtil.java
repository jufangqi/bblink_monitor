package cn.bblink.monitor.util;

import java.util.*;

/**
 * Created by Administrator on 2015/12/2.
 */
public class MonitorUtil {
    public static final String PREFIX_URL = "192.168.0.145:8081/monitor/";

    public static List<Map<String,Object>> getProjectStatusNameAndValueList(){
        MonitorEnum.PROJECT_STATUS[] values = MonitorEnum.PROJECT_STATUS.values();
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i=0;i<values.length;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("value",values[i].getCode());
            map.put("name",values[i].getCnName());
            list.add(map);
        }
        return list;
    }

    public static String getRandomString(int number){
        String str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String re = "";
        for (int i = 0; i < number; i++) {
            re+=str.charAt(new Random().nextInt(str.length()));
        }
        return re;
    }

    public static void main(String[] args) {

    }
}
