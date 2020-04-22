<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
                <form class="layui-form" action="BindBankCardServlet" method="post">
                  <div class="layui-form-item">
                      <label for="bankCode" class="layui-form-label">
                          <span class="x-red">*</span>银行卡号
                      </label>
                      <div class="layui-input-inline">
                          <input type="text" id="bankCode" name="bankCode" required="" lay-verify="required"
                          autocomplete="off" class="layui-input">
                      </div>
                      <div class="layui-form-mid layui-word-aux">
                          <span class="x-red">*</span>必填
                      </div>
                  </div>
                  
				<div class="layui-form-item">
				    <label for="bankPasswd0" class="layui-form-label">
				        <span class="x-red">*</span>银行卡密码
				    </label>
				    <div class="layui-input-inline">
				        <input type="password" id="bankPasswd0" name="bankPasswd" required="" lay-verify="pass"
				        autocomplete="off" class="layui-input">
				    </div>
				    <div class="layui-form-mid layui-word-aux">
				        <span class="x-red">*</span>6位数字
				    </div>
				</div>
                 
                  <div class="layui-form-item">
                      <label for="bankPasswd1" class="layui-form-label">
                          <span class="x-red">*</span>确认密码
                      </label>
                      <div class="layui-input-inline">
                          <input type="password" id="bankPasswd1" name="bankPasswd" required="" lay-verify="repass"
                          autocomplete="off" class="layui-input">
                      </div>
					  <div class="layui-form-mid layui-word-aux">
					      <span class="x-red">*</span>6位数字
					  </div>
                  </div>
                  <div class="layui-form-item">
                      <label for="bankCode" class="layui-form-label">
                      </label>
                      <button type="submit" class="layui-btn" >
                          提交
                      </button>
                  </div>
              </form>
            </div>
        </div>
        <script>
            var msg = '${msg}';
            if(msg != ''){
                alert(msg)
            }
            layui.use(['form', 'layer'],
            function() {
                $ = layui.jquery;
                var form = layui.form,
                layer = layui.layer;

                //自定义验证规则
                form.verify({
                    nikename: function(value) {
                        if (value.length < 5) {
                            return '昵称至少得5个字符啊';
                        }
                    },
                    pass: [/(.+){6,12}$/, '密码必须6到12位'],
                    repass: function(value) {
                        if ($('#bankPasswd1').val() != $('#bankPasswd0').val()) {
                            return '两次密码不一致';
                        }
                    }
                });

                //监听提交
               

            });</script>
        <script>var _hmt = _hmt || []; (function() {
                var hm = document.createElement("script");
                hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
                var s = document.getElementsByTagName("script")[0];
                s.parentNode.insertBefore(hm, s);
            })();</script>
    </body>

</html>
