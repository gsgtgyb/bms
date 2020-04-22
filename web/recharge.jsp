<%@ page import="java.util.List" %>
<%@ page import="com.fengshen.pojo.Bank" %>
<%@ page import="com.fengshen.service.BindBankCardImpl" %>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="x-admin-sm">

	<head>
		<meta charset="UTF-8">
		<title>欢迎页面-X-admin2.2</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
		<link rel="stylesheet" href="./css/font.css">
		<link rel="stylesheet" href="./css/xadmin.css">
		<script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
		<script type="text/javascript" src="./js/xadmin.js"></script>
		<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
		<!--[if lt IE 9]>
            <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
            <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
	</head>
	<body>
		<div class="layui-fluid">
			<div class="layui-row">
				<form class="layui-form" action="EchargeServlet" method="post">

					<%--<div class="layui-form-item">
						<label for="bankCode" class="layui-form-label">
							<span class="x-red">*</span>银行卡号
						</label>
						<div class="layui-input-inline">
							<select autofocus id="bankList">
								<option value="请选择"name="bankCode"id="bankCode">请选择</option>
								<c:forEach items="${list}" var="bank">
									<option value="&{bank.getBankCode()}">
										&{bank.getBankCode()}
									</option>
								</c:forEach>
							</select>
						</div>
					</div>--%>
					<div class="layui-form-item">
						<label for="bankCode" class="layui-form-label">
							<span class="x-red">*</span>银行卡号
						</label>
						<div class="layui-input-inline">
							<input type="text" id="bankCode" name="bankCode" required="" lay-verify="required"
								   autocomplete="off" class="layui-input">
						</div>
						<div class="layui-form-mid layui-word-aux">
							<span class="x-red">*</span>${codeError}
						</div>
					</div>
					<div class="layui-form-item">
						<label for="amount" class="layui-form-label">
							<span class="x-red">*</span>充值金额
						</label>
						<div class="layui-input-inline">
							<input type="text" id="amount" name="amount" required="" lay-verify="amount" autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label for="defrayPasswd" class="layui-form-label">
							<span class="x-red">*</span>支付密码
						</label>
						<div class="layui-input-inline">
							<input type="password" id="defrayPasswd" name="defrayPasswd" required="" lay-verify="defrayPasswd" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label for="L_repass" class="layui-form-label">
						</label>
						<button type="submit" class="layui-btn"id="L_repass">
							提交
						</button>
					</div>
				</form>
			</div>
		</div>
		<script>
			var msg = '${msg}';
			if(msg != ''){
				alert(msg);
			}
			/**
			 * 获取银行卡号
			 */
			/*$(document).ready(function(){
				$.ajax({
					url:'/BankCardServlet',
					dataType:'json',
					type:'post',
					success:function(data){
						if(data.code==0){
							var list=data.list;
							$.each(list,function(i,item){
								<!-- 向商品详情表中进行数据注入 -->
								$("#bankList").append("<option value='"+item.getBankCode()+"'>"+item.getBankCode()+"</option>");
								i++;
							});
						}else{
							layer.alert(data.msg, {icon: 5, offset: '0px'});
						}

					}
				});
			});*/

			layui.use(['form', 'layer'],
				function() {
					$ = layui.jquery;
					var form = layui.form,
						layer = layui.layer;

					//监听提交
					form.on('submit(add)',
						function(data) {
							console.log(data);
							//发异步，把数据提交给php
							layer.alert("增加成功", {
									icon: 6
								},
								function() {
									//关闭当前frame
									xadmin.close();

									// 可以对父窗口进行刷新 
									xadmin.father_reload();
								});
							return false;
						});

				});
		</script>
		<script>
			var _hmt = _hmt || [];
			(function() {
				var hm = document.createElement("script");
				hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
				var s = document.getElementsByTagName("script")[0];
				s.parentNode.insertBefore(hm, s);
			})();
		</script>
	</body>

</html>
