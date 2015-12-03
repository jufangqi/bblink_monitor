package cn.bblink.monitor.web;

import cn.bblink.common.utils.DateUtils;
import cn.bblink.common.vo.Page;
import cn.bblink.monitor.util.GenerateShortUrlUtil;
import cn.bblink.monitor.util.MonitorUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2015/12/2.
 */
@Controller
@RequestMapping("project")
public class ProjectAction extends AbstractAction{
    @RequestMapping("/list")
    public String list(HttpServletRequest request){
        Map<String, Object> paramMap = super.getParamMap(request);
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.putAll(paramMap);
        try {
            String startDateStr = (String) paramMap.get("startPublishDate");
            String endDateStr = (String) paramMap.get("endPublishDate");
            if (startDateStr != null){
                long startPublishDate = DateUtils.parseDate(startDateStr).getTime();
                paramMap.put("startPublishDate", startPublishDate);
            }
            if (endDateStr != null){
                long endPublishDate = DateUtils.parseDate(endDateStr).getTime();
                paramMap.put("endPublishDate", endPublishDate);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        Page page = super.daoTemplate.paging("project_monitor.getProjectMonitorList", paramMap);
        List<HashMap<String,Object>> list = page.getItems();
        List<HashMap<String,Object>> result = new ArrayList<>();
        for(HashMap<String,Object> map : list){
            result.add(map);
        }
        page.setItems(result);
        page.buildUrl(request);
        request.setAttribute("paramMap", parameterMap);
        request.setAttribute("pageParam", page);
        //项目状态列表
        request.setAttribute("projectStatusList", MonitorUtil.getProjectStatusNameAndValueList());
        return "project_monitor/list";
    }

    @RequestMapping("preedit")
    public String preedit(HttpServletRequest request){
        Map<String, Object> paramMap = super.getParamMap(request);
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.putAll(paramMap);
        Map<String,Object> map = daoTemplate.selectOne("project_monitor.getMonitorById",paramMap);
        request.setAttribute("projectMap",map);
        request.setAttribute("paramMap", parameterMap);
        return "project_monitor/edit";
    }

    @RequestMapping("update")
    public String update(HttpServletRequest request){
        Map<String, Object> paramMap = super.getParamMap(request);
        int n = super.daoTemplate.update("project_monitor.updateMonitorProject",paramMap);
        if (n == 0){
            paramMap.put("project_status","Y");
            paramMap.put("create_time",new Date().getTime());
            String url = GenerateShortUrlUtil.PREFIX_URL;
            url += MonitorUtil.getRandomString(10);
            url += "&tag=monitor";
            paramMap.put("short_link_url", GenerateShortUrlUtil.generateShortUrl(url));
            super.daoTemplate.insert("project_monitor.insertMonitorProject",paramMap);
        }
        return "redirect:list.do";
    }


    @RequestMapping("{randomcode}")
    public String monitor(@PathVariable String randomcode){
        System.out.println(randomcode);
        return "";
    }
}
