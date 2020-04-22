<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="x-admin-sm">
	<head>
		<meta charset="UTF-8">
		<title>欢迎页面-X-admin2.2</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
		<link rel="stylesheet" href="./css/font.css">
		<link rel="stylesheet" href="./css/xadmin.css">
		<script src="./lib/layui/layui.js" charset="utf-8"></script>
		<script type="text/javascript" src="./js/xadmin.js"></script>
	</head>
	<body>
		<div class="x-nav">
			<span class="layui-breadcrumb">
				<a href="">首页</a>
				<a href="">流水</a>
			</span>
			<a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" onclick="location.reload()"
			 title="刷新">
				<i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
		</div>
		<div class="layui-fluid">
			<div class="layui-row layui-col-space15">
				<div class="layui-col-md12">
					<div class="layui-card">
						<div class="layui-card-body ">
							<form method="get" class="layui-form layui-col-space5" action="${pageContext.request.contextPath}/queryPayBillServlet">
								<div class="layui-inline layui-show-xs-block">
									<input type="date" class="layui-input" autocomplete="off" placeholder="开始日" name="startDate" id="startDate" max="" min="">
								</div>
								<div class="layui-inline layui-show-xs-block">
									<input type="date" class="layui-input" autocomplete="off" placeholder="截止日" name="endDate" id="endDate" max="" min="">
								</div>
								<select name="selectComment">
                                    <option value="所有交易类型" selected="selected">所有交易类型</option>
                                    <option value="充值">充值</option>
                                    <option value="提现">提现</option>
                                    <option value="转入">收款</option>
                                    <option value="转出">付款</option>
								</select>
                                <td>转账对象：<input type="text" name="cusName" size="8" value="null"> </td>
                                <td>最小金额：<input type="number" name="minAmount" min="0.00" max="10000.00" step="0.01" size="8" value="0.00"></td>
                                <td>最大金额：<input type="number" name="maxAmount" min="0.00" max="10000.00" step="0.01" size="8" value="10000.00"> </td>
								<div class="layui-inline layui-show-xs-block">
									<div class="layui-inline layui-show-xs-block">
										<button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
									</div>
								</div>
							</form>
						</div>
						<div class="layui-card-header">
							<button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
						</div>
						<div class="layui-card-body layui-table-body layui-table-main">
							<table class="layui-table layui-form" >
								<thead>
									<tr>
										<th>选取</th>
										<th>流水号</th>
										<th>交易时间</th>
										<th>交易金额</th>
										<th>交易类型</th>
										<th>交易对象</th>
										<th>交易状态</th>
										<th>刪除交易</th>
									</tr>
								</thead>
								<tbody>
								    <c:forEach items="${list}" var="payBill">
                                        <tr>
                                            <td>
                                            </td>
                                            <td>${payBill.moneyID}</td>
                                            <td>${payBill.moneyDate}</td>
                                            <td>${payBill.moneyManipulate}</td>
                                            <td>${payBill.moneyComment}</td>
                                            <td>${payBill.customer}</td>
                                            <td>${payBill.moneyState}</td>
                                            <td class="td-status">
                                                <span class="layui-btn layui-btn-normal layui-btn-mini">已启用</span></td>
                                            <td class="td-manage">
                                                <a title="删除" onclick="member_del(this,'要删除的id')" href="javascript:;">
                                                    <i class="layui-icon">&#xe640;</i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
								</tbody>
							</table>
						</div>
						<div class="layui-card-body ">
							<div class="page">
								<div>

                                    <a class="prev" href="">&lt;&lt;</a>
                                    <a class="num" href="">${page}</a>

                                    <a class="next" href="">&gt;&gt;</a>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script language="javascript">
		    function time(){
                var date = new Date();
                var year = date.getFullYear();
                var month = ('0'+ (date.getMonth() + 1)).slice(-2);
                var day = ('0' + date.getDate()).slice(-2);
                // var day = date.getDay();
                var time = year +'-'+ month +'-'+ day;
                console.log(time);
                document.getElementById('startDate').value = time;
                document.getElementById('endDate').value = time;
                //限制不能选择今天之回后的日期（加上属性答max）
                document.getElementById('startDate').setAttribute('max', time);
                document.getElementById('endDate').setAttribute('max', time);
                document.getElementById('startDate').setAttribute('min', getLast3Month(Date.now()));
                document.getElementById('endDate').setAttribute('min', getLast3Month(Date.now()));
            }
            function getLast3Month() {
                var now = new Date();
                var year = now.getFullYear();
                var month = now.getMonth() + 1;//0-11表示1-12月
                var day = now.getDate();
                var dateObj = {};
                if (parseInt(month) < 10) {
                    month = "0" + month;
                }
                if (parseInt(day) < 10) {
                    day = "0" + day;
                }

                dateObj.now = year + '-' + month + '-' + day;

                if (parseInt(month) == 1) {//如果是1月份，则取上一年的10月份
                    dateObj.last = (parseInt(year) - 1) + '-10-' + day;
                    return dateObj.last;
                }
                if (parseInt(month) == 2) {//如果是2月份，则取上一年的11月份
                    dateObj.last = (parseInt(year) - 1) + '-11-' + day;
                    return dateObj.last;
                }
                if (parseInt(month) == 3) {//如果是3月份，则取上一年的12月份
                    dateObj.last = (parseInt(year) - 1) + '-12-' + day;
                    return dateObj.last;
                }

                var preSize = new Date(year, parseInt(month) - 3, 0).getDate();//开始时间所在月的总天数
                if (preSize < parseInt(day)) {
            　　　// 开始时间所在月的总天数<本月总天数，比如当前是5月30日，在2月中没有30，则取下个月的第一天(3月1日)为开始时间
                    let resultMonth = parseInt(month) - 2 < 10 ? ('0' + parseInt(month) - 2) : (parseInt(month) - 2);
                    dateObj.last = year + '-' + resultMonth + '-01';
                    return dateObj.last;
                }

                if (parseInt(month) <= 10) {
                    dateObj.last = year + '-0' + (parseInt(month) - 3) + '-' + day;
                    return dateObj.last;
                } else {
                    dateObj.last = year + '-' + (parseInt(month) - 3) + '-' + day;
                    return dateObj.last;
                }
            }
            time();
            layui.use('form', function(){
              var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
              form.render();
            });
        </script>
	</body>

</html>
