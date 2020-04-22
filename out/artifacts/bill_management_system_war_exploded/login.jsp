<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!doctype html>
<html class="x-admin-sm">
<head>
	<meta charset="UTF-8">
	<title>账务管理系统登录界面</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="stylesheet" href="css/font.css">
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="css/xadmin.css">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script src="./lib/layui/layui.js" charset="utf-8"></script>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/font.css">
	<link rel="stylesheet" href="css/xadmin.css">
	<script src="./lib/layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/xadmin.js"></script>
</head>
<body class="login-bg">
${msg}
<div class="login layui-anim layui-anim-up">
	<div class="message">账务管理系统登录界面</div>
	<div id="darkbannerwrap"></div>
	<div align="center">
		<form method="post" class="layui-form"  action="loginServlet">
			<input name="username" placeholder="用户名" type="text" lay-verify="required" class="layui-input">
			<hr class="hr15">
			<input name="password" lay-verify="required" placeholder="密码" type="password" class="layui-input">
			<hr class="hr15">
			<button type="submit" class="btn btn-default btn-lg" style="color: white; border: 0; width: 80% ;background-color: #189F92 ">登录</button>
			<hr class="hr15">
		</form>
		<button type="button" class="btn btn-default btn-lg" onclick="xadmin.open('添加用户','./admin-add.jsp',700,500)" style=" color: white;border: 0; width: 80% ;background-color: #189F92">注册</button>
	</div>
</div>
<script>
	$(function() {
		layui.use('form', function() {
			var form = layui.form;
			// layer.msg('玩命卖萌中', function(){
			//   //关闭后的操作
			//   });
			//监听提交
			form.on('submit(login)', function(data) {
				// alert(888)
				layer.msg(JSON.stringify(data.field), function() {
					location.href = 'index.html'
				});
				return false;
			});
		});
	})

	function button() {
		window.location.href = "http://www.baidu.com"
	}
</script>
<!-- 底部结束 -->

</body>
</html>
