<!DOCTYPE html>
<html>
<head>
<#include "/base/head_meta.ftl"/>
<#import "/base/spring.ftl" as s/>
    <script>
        $(function(){
            $("#project_status option[value='${paramMap.project_status}']").attr("selected", "selected");
            $("#export_button").bind("click",function(){
                var path = "exportPU.do";
                $("#searchForm").attr("action",path).attr("method","get").submit();
            });
        });
        function formSubmit(){
            $('#searchForm').attr("action","list.do").submit();
        }
    </script>
</head>
<body>
<#--<@navigationBar name="MENU_HOS_PM"> </@navigationBar>-->
<div class='iframe_header'>
    <ul class='iframe_nav'>
        <li><a href='list.do'>页面监控管理</a></li>
        <li>&gt;页面链接配置</li>
    </ul>
</div>
<div class="iframe_search" id="iframe_search_do">
    <form method="post" action="list.do" id="searchForm">
        <table class="s_table">
           <tr>
               <td width="100px"  align="left">&nbsp;&nbsp;&nbsp;&nbsp;URL</td>
               <td><input type="text" name="project_link_url" id="project_link_url" style="width: 400px;" value="${paramMap.project_link_url!''}" /></td>
               <td>
                   <table>
                       <tr>
                           <td>评论发布日期：</td>
                           <td> <input type="text" name="startPublishDate" onFocus="var endDate=$dp.$('endPublishDate');WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){endPublishDate.focus();},maxDate:'#F{$dp.$D(\'endPublishDate\')}'})" id="startPublishDate" value="${paramMap.startPublishDate}"/></td>
                           <td>-</td>
                           <td><input type="text" name="endPublishDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startPublishDate\')}'})" id="endPublishDate" value="${paramMap.endPublishDate}" /></td>
                       </tr>
                   </table>
               </td>

           </tr>
            <tr>
                <td class="s_label operate" style="width:100px;" colspan="5"> <a class="btn btn_cc1" onclick="formSubmit()">查询</a></td>
                <td class="s_label operate" style="width:100px;" colspan="5"> <a class="btn btn_cc1" id="export_button">导出</a></td>
            </tr>
        </table>
    </form>
</div>

<div class="iframe-content">
<#if pageParam?? && pageParam.items?? &&  pageParam.items?size &gt; 0>
    <#setting datetime_format="yyyy-MM-dd HH:mm:ss"/>
    <div class="p_box">
        <div class="tableContent">
            <table class="p_table table_center freeze">
                <thead>
                <th nowrap="nowrap" width="65">日期</th>
                <th nowrap="nowrap" width="150">PV</th>
                <th nowrap="nowrap" width="100">UV</th>
                </thead>
                <#list pageParam.items as item>
                    <tr>
                        <td nowrap="nowrap" width="65">${(item.count_date?string('yyyy-MM-dd'))!''}</td>
                        <td nowrap="nowrap" width="150" title="">${item.url_pv}</td>
                        <td nowrap="nowrap" width="100" title="">${item.url_uv}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </div>
    <div class="paging">${pageParam.pagination(11)}</div>

<#else>
    <div class="no_data mt20"><i class="icon-warn32"></i>暂无相关数据，重新输入条件查询！</div>
</#if>
</div>
<#include "/base/foot.ftl"/>
<script>
    $(function(){
        $("#hospitalName").jsonSuggest({
                url:"${rc.contextPath}/pub/queryHospitalList.do",
                maxResults: 10,
                minCharacters:1,
                onSelect:function(item) {
                    $("#hospitalId").val(item.id);
                }
            });
    });
</script>
</body>
</html>