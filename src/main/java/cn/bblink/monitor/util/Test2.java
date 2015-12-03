package cn.bblink.monitor.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/2.
 */
public class Test2 {
    public static void main(String[] args) {
        String sql = "insert into link_url_pv_uv(project_link_url,url_pv,url_uv,count_date)\n" +
                "        values(?,?,?,?)";
        for (int i = 0; i < 100; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("project_link_url","link_url"+i);
            map.put("url_pv",i);
            map.put("url_uv",i);
            map.put("count_date", new Date());
            Object[] params = new Object[]{
                    map.get("project_link_url"),map.get("url_pv"),map.get("url_uv"),map.get("count_date")
            };
            new SqlService().update(sql,params);
        }
    }
}
