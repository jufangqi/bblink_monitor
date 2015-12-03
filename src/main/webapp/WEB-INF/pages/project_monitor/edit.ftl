<!DOCTYPE html>
<html>
<head>
<#include "/base/head_meta.ftl"/>
<#import "/base/spring.ftl" as s/>
    <script>
        $(function(){

        });
        function s(e,a)
        {
            if ( e && e.preventDefault )
                e.preventDefault();
            else
                window.event.returnValue=false;
            a.focus();

        }
        function trim(str)
        {
            return str.replace(/(^\s*)|(\s*$)/g, "");
        }
        function formSubmit(){
            var flag =false;
            var project_name = $("#project_name").val().trim();
            var project_description = $("#project_description").val().trim();
            var project_arrive_url = $("#project_arrive_url").val().trim();
            var third_party_monitor_url = $("#third_party_monitor_url").val().trim();
            if(project_name == ''){
                $("#project_name_msg").html('<span style="color:red;">项目名称不能为空</span>');
                flag = true;
            }else{
                $("#project_name_msg").html('');
            }
            if(project_description == ''){
                $("#project_description_msg").html('<span style="color:red;">描述不能为空</span>');
                flag = true;
            }else{
                $("#project_description_msg").html('');
            }
            if(project_arrive_url == ''){
                $("#project_arrive_url_msg").html('<span style="color:red;">到达页不能为空</span>');
                flag = true;
            }else{
                $("#project_arrive_url_msg").html('');
            }
            if(flag){
                return;
            }
            var flag2 = false;
            if(!isURL(project_arrive_url)){
                $("#project_arrive_url_msg").html('<span style="color:red;">到达页URL格式不正确，请以http://开头！</span>');
                flag2 = true;
            }
            if(!isURL(third_party_monitor_url)){

                $("#third_party_monitor_url_msg").html('<span style="color:red;">第三方监控URL格式不正确，请以http://开头！</span>');
                flag2 = true;
            }
            if(flag2){
                return;
            }
            $('#searchForm').submit();
        }
        function isURL(str_url) {// 验证url
            var strRegex = "((http|ftp|https)://)([a-zA-Z0-9_-]+\.)*";
            var re = new RegExp(strRegex);
            return re.test(str_url);
        }
    </script>
</head>
<body>
<#--<@navigationBar name="MENU_HOS_PM"> </@navigationBar>-->
<div class='iframe_header'>
    <ul class='iframe_nav'>
        <li><a href='list.do'>页面监控管理</a></li>
        <li>&gt;编辑监控链接配置</li>
    </ul>
</div>
<div class="iframe_search">
    <form method="post" action="update.do" id="searchForm">
        <table class="s_table">
            <input type="hidden" id="id" name="id" value="${paramMap.id}"/>
            <tr>
                <td width="200px"  align="left">*项目名称:</td>
                <td width="1000px"  align="left">
                    <input type="text" id="project_name" name="project_name"  style="width: 600px;"value="${projectMap.project_name!''}"/>
                    &nbsp;&nbsp;<span id="project_name_msg"></span>
                    <br/>
                    链接的使用项目名称，例如：美赞臣注册送奶粉活动
                </td>

            </tr>
            <tr>
                <td width="200px"  align="left">*描述:</td>
                <td  width="1000px" align="left">
                   <textarea name="project_description" id="project_description" cols="80" rows="10" style="width: 600px;" onmousedown="s(event,this)">
                    ${projectMap.project_description}
                   </textarea>
                    &nbsp;&nbsp;<span id="project_description_msg"></span>
                    <br/>
                    对链接的使用场景进行更为详尽的描述，例如：portal到达页的第2张广告图
                </td>

            </tr>

            <tr>
                <td width="200px"  align="left">*到达页地址:</td>
                <td width="1000px"  align="left">
                    <input type="text" id="project_arrive_url" name="project_arrive_url"  style="width: 600px;" value="${projectMap.project_arrive_url}"/>
                    &nbsp;&nbsp;<span id="project_arrive_url_msg"></span>
                    <br/>
                    该链接最终到达的页面地址
                </td>
            </tr>
            <tr>
                <td width="200px"  align="left">*监控链接:</td>
                <td width="1000px"  align="left">
                    <input type="text" id="third_party_monitor_url" name="third_party_monitor_url"  style="width: 600px;" value="${projectMap.third_party_monitor_url}"/>
                    &nbsp;&nbsp;<span id="third_party_monitor_url_msg"></span>
                    <br/>
                    录入第三方的监控地址
                </td>
            </tr>
            <tr>
                <td class="s_label operate" style="width:100px;" colspan="5"> <a class="btn btn_cc1" onclick="formSubmit()">保存</a></td>
                <td class="s_label operate" style="width:100px;" colspan="5"> <a class="btn btn_cc1" onclick="history.go(-1)">返回</a></td>
            </tr>
        </table>
    </form>
</div>

<div class="iframe-content">
    <div class="p_box">
        <div class="tableContent">

        </div>
    </div>
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
            },

        });
    });

</script>

</body>
</html>