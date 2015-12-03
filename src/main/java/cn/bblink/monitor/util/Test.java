package cn.bblink.monitor.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/2.
 */
public class Test {
    public static void main(String[] args) {
        String sql = "insert into project_monitor(project_name,project_description,\n" +
                "        project_status,project_link_url,project_arrive_url,third_party_monitor_url,create_time)\n" +
                "        values(?,?,?,?,?,?,?)";
        for (int i = 0; i < 100; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("project_name","name"+i);
            map.put("project_description","description"+i);
            map.put("project_status",i%2==0?"Y":"N");
            map.put("project_link_url","link_url"+i);
            map.put("project_arrive_url","arrive_url"+i);
            map.put("third_party_monitor_url","third_party_monitor_url"+i);
            map.put("create_time", new Date().getTime()+i*86400000);
            Object[] params = new Object[]{map.get("project_name"),map.get("project_description"),map.get("project_status"),
                    map.get("project_link_url"),map.get("project_arrive_url"),map.get("third_party_monitor_url"),map.get("create_time")
                    };
            new SqlService().update(sql,params);
        }
    }
}
