<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="js/themes/demo.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.1.2.6.min.js"></script>
<!-- 使用日期控件步骤1: 导入js和css -->
	<link rel="stylesheet" href="js/datepicker/jquery.datepick.css" type="text/css">
	<script type="text/javascript" src="js/datepicker/jquery.datepick.js"></script>
	<script type="text/javascript" src="js/datepicker/jquery.datepick-zh-CN.js"></script>
	<script type="text/javascript">
	$(function(){
		//使用日期控件步骤2: 当页面加载完成时,调用 datepick方法指定需要应用的文本框  
		$('#reg_date').datepick({dateFormat: 'yy年mm月dd日'}); 
		$('#beginDate').datepick({dateFormat: 'yy年mm月dd日'}); 
		$('#endDate').datepick({dateFormat: 'yy年mm月dd日'}); 
	})
	function loadData(){
		var name = $("#reg_patientName").val();
		var card = $("#reg_card").val();
		var date = $("#reg_date").val();
		var number = $("#reg_serialNumber").val();
		var doctor = $("#dis_doctor").val();
		var beginDate = $("#beginDate").val();
		var endDate = $("#endDate").val();
		window.parent.frames["rightframe"].window.loadData(name,card,date,number,doctor,beginDate,endDate);
	}
	</script>
<body>
	<div>
		<form method="post" action="">
			<table align="center">
				<tr><td rowspan="2" align="right">患者名字：</td></tr><tr><td align="left"><input type="text" id="reg_patientName" /></td></tr>
				<tr><td rowspan="2" align="right">诊疗卡号：</td></tr><tr><td align="left"><input type="text" id="reg_card" /></td></tr>
				<tr><td rowspan="2" align="right">挂号日期：</td></tr><tr><td align="left"><input readOnly="readonly" id="reg_date"></input></td></tr>
				<tr><td rowspan="2" align="right">流水号：</td></tr><tr><td align="left"><input type="text" id="reg_serialNumber" /></td></tr>
				<tr><td rowspan="2" align="right">接诊医生：</td></tr><tr><td align="left"><input type="text" id="dis_doctor" /></td></tr>
				<tr><td rowspan="2" align="right">接诊日期：</td></tr><tr><td align="left"><input readOnly="readonly" id="beginDate" /></td></tr>
				<tr><td rowspan="2" align="right">至：</td></tr><tr><td align="left"><input readOnly="readonly" id="endDate" /></td></tr>
			</table>
			<div><input style="margin-left:10px" type="submit" onclick="loadData()" value="搜索" id="send" /> <input type="reset" value="重置" id="reset" align="middle" /></div>
		</form>
	</div>
</body>
</html>