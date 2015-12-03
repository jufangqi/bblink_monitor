package cn.bblink.monitor.web;

import cn.bblink.common.utils.DateUtils;
import cn.bblink.common.utils.json.JSONOutput;
import cn.bblink.monitor.util.GenerateShortUrlUtil;
import cn.bblink.monitor.util.MonitorUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/3.
 */
@Controller
public class MonitorAction extends AbstractAction {

    public String test(HttpServletRequest request,@PathVariable String randomCode){
        Map<String, Object> paramMap = super.getParamMap(request);
        paramMap.put("project_link_url", MonitorUtil.PREFIX_URL+randomCode);
        paramMap.put("count_date", DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
        paramMap.put("ip_address",request.getRemoteAddr());
        //得到
        Map<String,Object> map = super.daoTemplate.selectOne("project_monitor.getMonitorByLinkUrl",paramMap);
        Map<String,Object> puMap = super.daoTemplate.selectOne("pv_uv.getPvUvList",paramMap);
        Map<String,Object> linkIpMap = super.daoTemplate.selectOne("link_ip.getLinkIp",paramMap);
        if (linkIpMap == null){
            super.daoTemplate.insert("link_ip.insertLinkIp",paramMap);
        }
        if (puMap == null){
            puMap = new HashMap<>();
            puMap.put("url_pv",1);
        }else{
            puMap.put("url_pv",(long)puMap.get("url_pv")+1);
        }
        puMap.put("url_uv",super.daoTemplate.selectOne("link_ip.getLinkIpCount",paramMap));
        puMap.put("project_link_url",paramMap.get("project_link_url"));
        puMap.put("count_date",paramMap.get("count_date"));
        int n = super.daoTemplate.update("pv_uv.updatePu",puMap);
        if (n == 0){
            super.daoTemplate.insert("pv_uv.insertPu",puMap);
        }
        String project_arrive_url = "";
        if (map == null){
            project_arrive_url = "http://www.yabibuy.com/";
        }else {
            project_arrive_url = (String) map.get("project_arrive_url");
        }
        return "redirect:"+project_arrive_url;
    }

    @RequestMapping("test_third")
    public void testThirdParty(HttpServletRequest request) throws IOException {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod("http://192.168.0.145:8081/bblink_monitor/test22");
        httpClient.executeMethod(getMethod);
        String callback = request.getParameter("callback");
        if(StringUtils.isEmpty(callback)){
            JSONOutput.writeJSON(response, 1000);
        }else{
            JSONOutput.writeJSONP(response, 0,callback);
        }
    }
    @RequestMapping("test22")
    public void test(){
        System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjj");
    }

    @RequestMapping("/monitor/{randomCode}")
    public String monitor(HttpServletRequest request,@PathVariable String randomCode){
        Map<String, Object> paramMap = super.getParamMap(request);
        String url = GenerateShortUrlUtil.PREFIX_URL;
        url += randomCode;
        url += "&tag=monitor";
        paramMap.put("short_link_url", GenerateShortUrlUtil.generateShortUrl(url));
        Map<String,Object> map = super.daoTemplate.selectOne("project_monitor.getMonitorByLinkUrl",paramMap);
        String third_party_monitor_url = (String) map.get("third_party_monitor_url");
        if (third_party_monitor_url != null){
            try {
                HttpClient httpClient = new HttpClient();
                GetMethod getMethod = new GetMethod(third_party_monitor_url);
                httpClient.executeMethod(getMethod);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String project_arrive_url = "";
        if (map == null){
            project_arrive_url = "http://www.yabibuy.com/";
        }else {
            project_arrive_url = (String) map.get("project_arrive_url");
        }
        return "redirect:"+project_arrive_url;
    }
}
