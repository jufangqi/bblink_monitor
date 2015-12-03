<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta
	content="initial-scale=1.0,user-scalable=no,maximum-scale=1,width=device-width"
	name="viewport">
<meta content="initial-scale=1.0,user-scalable=no,maximum-scale=1"
	media="(device-height: 568px)" name="viewport">
<style>
* {
	margin: 0;
	padding: 0
}

.not404 {
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0;
	background: #589dc6 url(<%=request.getContextPath()%>/img/bk.jpg)
		no-repeat center;
	overflow: hidden
}

.not404_i {
	margin: -100px auto 0 auto;
	height: 200px;
	text-align: center;
	position: absolute;
	top: 50%;
	width: 70%
}

.notBox {
	margin: 0 auto;
	overflow: hidden;
	width: 70%
}

.not404_i .icon {
	border-right: #fff 1px solid;
	height: 180px;
	float: left;
	margin-right: 2%;
	text-align: right;
	padding: 5% 2% 0 0;
	width: 26%
}

.not404_i .font {
	float: right;
	width: 68%
}

.not404_i .font img {
	width: 100%
}

.not404_i .font h1,button {
	font-family: "Microsoft YaHei", 微软雅黑, "MicrosoftJhengHei", 华文细黑, STHeiti,
		MingLiu
}

.not404_i .font h1 {
	font-size: 4em;
	color: #fff;
	text-shadow: 1px 5px 5px #579ac5;
	line-height: 2.4em;;
	font-weight: normal;
}

.not404_i .font button {
	font-size: 2em;
	color: #fff;
	background: none;
	padding: 0 10px;
	cursor: pointer;
	border-radius: 5px;
	border: #fff 1px solid;
	height: 56px;
	line-height: 56px;
}

@media ( max-width :1000px) {
	.not404_i .icon {
		height: 90px;
		padding-top: 40px
	}
	.not404_i .icon img {
		width: 80%;
	}
	.not404_i .font h1 {
		font-size: 3em;
	}
	.not404_i .font button {
		font-size: 1.5em;
	}
}

@media screen and (min-width:640px) and (max-width:999px) {
	.not404_i {
		width: 80%
	}
	.not404_i .icon {
		height: 100px;
		padding: 20px 2% 0 0;
		width: 25%;
	}
	.not404_i .font {
		width: 70%;
		overflow: hidden;
	}
	.not404_i .font h1 {
		font-size: 2em;
	}
	.not404_i .font button {
		font-size: 1.2em;
		height: 40px;
		line-height: 40px
	}
}

@media screen and (max-width:639px) {
	.not404_i {
		width: 80%
	}
	.not404_i .icon {
		padding: 1% 2% 0 0;
		border: none;
		text-align: center;
		width: 100%;
	}
	.not404_i .icon img {
		width: 100px;
	}
	.not404_i .font {
		float: none;
		width: 100%;
	}
	.not404_i .font h1 {
		font-size: 1.4em;
		line-height: 1.4em;
		margin-bottom: 20px;
		text-shadow: 1px 1px 2px #579ac5;
	}
	.not404_i .font button {
		font-size: 1em;
		height: 40px;
		line-height: 40px
	}
}
</style>
<script type="text/javascript">
	function toBblink() {
		window.location.href = "http://www.bblink.cn";
	}
</script>
</head>
<body>
	<div class="not404">
		<div class="notBox">
			<div class="not404_i">
				<div class="icon">
					<img src="<%=request.getContextPath()%>/img/error.png" />
				</div>
				<div class="font">
					<h1>抱歉！网络出错了!</h1>
					<button onclick="toBblink()">点击访问贝联官网</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>