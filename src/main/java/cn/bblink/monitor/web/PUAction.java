package cn.bblink.monitor.web;

import cn.bblink.common.exception.BusinessException;
import cn.bblink.common.utils.DateUtils;
import cn.bblink.common.utils.ExportXlsUtils;
import cn.bblink.common.vo.Page;
import cn.bblink.common.vo.XlsExportBean;
import cn.bblink.monitor.util.MonitorUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/2.
 */
//pv ,uv
@Controller
@RequestMapping("pu")
public class PUAction extends AbstractAction{

    @RequestMapping("list")
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

        Page page = super.daoTemplate.paging("pv_uv.getPvUvList", paramMap);
        List<HashMap<String,Object>> list = page.getItems();
        List<HashMap<String,Object>> result = new ArrayList<>();
        for(HashMap<String,Object> map : list){
            result.add(map);
        }
        page.setItems(result);
        page.buildUrl(request);
        request.setAttribute("paramMap", parameterMap);
        request.setAttribute("pageParam", page);
        return "project_monitor/puList";
    }

    @RequestMapping(value = "exportPU",method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportPU(HttpServletRequest request){
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

        List<HashMap<String,Object>> list = super.daoTemplate.selectList("pv_uv.getPvUvList", paramMap);
        List<HashMap<String,Object>> result = new ArrayList<>();
        for(HashMap<String,Object> map : list){
            result.add(map);
        }
        XlsExportBean bean = new XlsExportBean(result,"pvuv");
        bean.buildColumn("日期", "count_date").
                buildColumn("pv", "url_pv").
                buildColumn("uv", "url_uv");
        return ExportXlsUtils.exportXls(bean);
    }
}
