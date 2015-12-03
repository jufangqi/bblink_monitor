<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
  <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="http://dac.bblink.cn/g.js?curl=192.168.0.145:8081/monitor/1234567"></script>
  <script>
    function jumpTo(){
      var image = new Image();
      image.src = "test_third";
      //window.location = "http://dac.bblink.cn/goto?_key={_key}&_version={_version}&uid={_uid}&adid=564c17d60cf2bf4560331534&_gotourl=http://192.168.0.145:8081/bblink_monitor/monitor/1234567?tag=monitor"
      window.location = "http://dwz.cn/2gCekM";
    }
  </script>
</head>
<body>
<h1>hello</h1>
<a href="http://192.168.0.145:8081/bblink_monitor/monitor/1234567">测试链接</a>
<a href="#" onclick="jumpTo()">测试链接2</a>
</body>
</html>
