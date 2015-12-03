<!DOCTYPE html>
<html>
<head>
<#include "/base/head_meta.ftl"/>
<#import "/base/spring.ftl" as s/>
    <script>
        $(function(){
            $("#project_status option[value='${paramMap.project_status}']").attr("selected", "selected");
            $("#export_button").click(function(){
                window.location = "preedit.do?id=0";
            })
        });
        function formSubmit(){
            $('#searchForm').attr("action","list.do").submit();
        }

        function changeStatus(project_status,id){
            var flag = confirm("确认更改状态吗？");
            if(flag){
                window.location = "update?project_status="+project_status+"&id="+id;
            }

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
               <td width="100px"  align="left">&nbsp;&nbsp;&nbsp;&nbsp;项目名称</td>
               <td><input type="text" name="project_name" id="project_name" value="${paramMap.project_name!''}" /></td>
               <td>
                   <table>
                       <tr>
                           <td>评论发布日期：</td>
                           <td> <input type="text" name="startPublishDate" onFocus="var endDate=$dp.$('endPublishDate');WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){endPublishDate.focus();},maxDate:'#F{$dp.$D(\'endPublishDate\')}'})" id="startPublishDate" value="${paramMap.startPublishDate}"/></td>
                           <td>-</td>
                           <td><input type="text" name="endPublishDate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startPublishDate\')}'})" id="endPublishDate" value="${paramMap.endPublishDate}"/></td>
                       </tr>
                   </table>
               </td>
               <td width="80px"  align="left">&nbsp;&nbsp;状态</td>
               <td class="s_label">
                   <div id="selectDiv">
                       <select id="project_status" name="project_status" style="width:200px;height:27px;">
                           <option value="">全部</option>
                       <#if projectStatusList??  &&  projectStatusList?size &gt; 0>
                           <#list projectStatusList as project_status>
                               <option value="${project_status.value}" <#if key="${project_status.value}">selected="selected"</#if> >${project_status.name}</option>
                           </#list>
                       </#if>
                       </select>
                   </div>
               </td>
           </tr>
            <tr>
                <td class="s_label operate" style="width:100px;" colspan="5"> <a class="btn btn_cc1" onclick="formSubmit()">查询</a></td>
                <td class="s_label operate" style="width:100px;" colspan="5"> <a class="btn btn_cc1" id="export_button">新增</a></td>
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
                <th nowrap="nowrap" width="65">序号</th>
                <th nowrap="nowrap" width="150">项目名称</th>
                <th nowrap="nowrap" width="100">描述</th>
                <th nowrap="nowrap" width="100">状态</th>
                <th nowrap="nowrap" width="100">短链接</th>
                <th nowrap="nowrap" width="100">到达页地址</th>
                <th nowrap="nowrap" width="100">第三方监测链接</th>
                <th nowrap="nowrap" width="100">创建日期</th>
                <th nowrap="nowrap" width="100">操作</th>
                </thead>
                <#list pageParam.items as item>
                    <tr>
                        <td nowrap="nowrap" width="65">${item.id}</td>
                        <td nowrap="nowrap" width="150" title="">${item.project_name}</td>
                        <td nowrap="nowrap" width="100" title="">${item.project_description}</td>
                        <td nowrap="nowrap" width="100" title="">
                        <#if item.project_status == 'N'>
                            <a href="#" onclick="changeStatus('Y','${item.id}')">无效</a>
                        <#else >
                            <a href="#" onclick="changeStatus('N','${item.id}')">有效</a>
                        </#if>
                       </td>
                        <td nowrap="nowrap" width="100" title=""><a href="${item.short_link_url}">${item.short_link_url}</a></td>
                        <td nowrap="nowrap" width="100" title=""><a href="${item.project_arrive_url}">${item.project_arrive_url}</a></td>
                        <td nowrap="nowrap" width="100" title=""><a href="${item.third_party_monitor_url}">${item.third_party_monitor_url}</a></td>
                        <td nowrap="nowrap" width="100" title="">${item.create_time?number_to_datetime}</td>
                        <td nowrap="nowrap" width="100" title="">
                            <a href="preedit.do?id=${item.id}">编辑</a>|
                            <a href="${rc.contextPath}/pu/list.do?short_link_url=${item.short_link_url}">查看数据</a>
                        </td>
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