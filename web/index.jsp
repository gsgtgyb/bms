<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!doctype html>
<html class="x-admin-sm">
<head>
  <meta charset="UTF-8">
  <title>账务管理系统</title>
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="stylesheet" href="./css/font.css">
  <link rel="stylesheet" href="./css/xadmin.css">
  <!-- <link rel="stylesheet" href="./css/theme5.css"> -->
  <script src="./lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="./js/xadmin.js"></script>
</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
  <div class="logo">
    <a href="./index.jsp">账务管理系统1.0</a></div>
  <div class="left_open">
    <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
  </div>
  <ul class="layui-nav right" lay-filter="">
    <li class="layui-nav-item">
      <a href="javascript:;">admin</a>
      <dl class="layui-nav-child">
        <!-- 二级菜单 -->
        <a onclick="xadmin.open('切换帐号','http://www.baidu.com')">切换帐号</a>
        <a href="./login.jsp">退出</a></dd>
      </dl>
    </li>
    <li class="layui-nav-item to-index">
  </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
  <div id="side-nav">
    <ul id="nav">
      <li>
        <ul class="iconfont left-nav-li">
          <li>
            <a onclick="xadmin.add_tab('充值页面','recharge.jsp')">
              <cite>充值</cite></a>
          </li>
          <li>
            <a onclick="xadmin.add_tab('提现页面','./web/cash-withdrawal.jsp')">
              <cite>提现</cite></a>
          </li>
          <li>
            <a onclick="xadmin.add_tab('转账页面','transfer-accounts.jsp',true)">
              <cite>转账</cite></a>
          </li>
          <li>
            <a onclick="xadmin.add_tab('流水页面','flowing-water.jsp',true)">
              <cite>流水</cite></a>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
  <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
    <ul class="layui-tab-title">
      <li class="home">
        <i class="layui-icon">&#xe68e;</i>我的桌面</li>
    </ul>
    <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
      <dl>
        <dd data-type="this">关闭当前</dd>
        <dd data-type="other">关闭其它</dd>
        <dd data-type="all">关闭全部</dd>
      </dl>
    </div>
    <div class="layui-tab-content">
      <div class="layui-tab-item layui-show">
        <iframe src='./welcome.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
      </div>
    </div>
    <div id="tab_show"></div>
  </div>
</div>
<div class="page-content-bg"></div>
<style id="theme_style"></style>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
</body>
</html>
