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
<script>
	
	$(function(){
			$("#tb1").datagrid({
				title:'西药',
				width:700,
				height:350,
				nowrap: false,
				striped: true,
				remoteSort: false,
				columns:[[
	                {field:'reg_patientName',title:'姓名',width:80,sortable:true,editor:{type:'text',options:{textField:'editor_reg_patientName',valueField:'editor_reg_patientName',data:regAndDis}}},
					{field:'reg_sex',title:'性别',width:60,align:'center', rowspan:2,editor:{type:'text',options:{textField:'editor_reg_sex',valueField:'editor_reg_sex',data:regAndDis}}},
					{field:'reg_age',title:'年龄',width:60,editor:{type:'text',options:{textField:'editor_reg_age',valueField:'editor_reg_age',data:regAndDis}}},
					{field:'reg_card',title:'诊疗卡号',width:110,rowspan:1,sortable:true,editor:{type:'text',options:{textField:'editor_reg_card',valueField:'editor_reg_card',data:regAndDis}}},
					{field:'reg_date',title:'挂号日期',width:110,rowspan:2,editor:{type:'text',options:{textField:'editor_reg_date',valueField:'editor_reg_date',data:regAndDis}}},
					{field:'reg_serialNumber',title:'流水号',width:80,rowspan:2,editor:{type:'text',options:{textField:'editor_reg_serialNumber',valueField:'editor_reg_serialNumber',data:regAndDis}}},
					{field:'dis_time',title:'接诊时间',width:110,rowspan:2,editor:{type:'text',options:{textField:'editor_dis_time',valueField:'editor_dis_time',data:regAndDis}}},
					{field:'dis_doctor',title:'接诊医生',width:80,rowspan:1,editor:{type:'text',options:{textField:'editor_dis_doctor',valueField:'editor_dis_doctor',data:regAndDis}}}
				]],
				pagination:true,
				rownumbers:true
			});
	})
	var regAndDis = [];
	function loadData(name,card,date,number,doctor,beginDate,endDate){
		//alert(name+","+card+","+date+","+number+","+doctor+","+beginDate+","+endDate);
		$.ajax({
			url:'${pageContext.request.contextPath}/PatientMessageAction_execute',
			data:{"reg_patientName":name,"reg_card":card,"reg_date":date,"reg_serialNumber":number,"diseaseTypes.dis_doctor":doctor,"beginDate":beginDate,"endDate":endDate},
			async:false,
			cache:false,
			success:function(response){
				console.log(JSON.stringify(response));
				/* alert(response.list[0][0].dis_doctor);*/
				for(var i = 0 ;i<response.rows.length;i++){
					regAndDis.push({
			    		dis_time:response.rows[i].dis_time,
				        dis_doctor:response.rows[i].dis_doctor,
				        reg_patientName:response.rows[i].reg_patientName,
				        reg_sex:response.rows[i].reg_sex,
				        reg_age:response.rows[i].reg_age,
				        reg_card:response.rows[i].reg_card,
				        reg_date:response.rows[i].reg_date,
				        reg_serialNumber:response.rows[i].reg_serialNumber
		  			})
				} 
				//var data = $.parseJSON(regAndDis);  
				$("#tb1").datagrid('loadData',regAndDis); 
				//加载完数据后清空数组
				regAndDis.splice(0,regAndDis.length);
			}
		});
		//alert(JSON.stringify(regAndDis));
		
	}
</script>
<body>
	<table id="tb1"></table>
</body>
</html>