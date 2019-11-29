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
	<script type="text/javascript" src="js/jquery.table2excel.js"></script>
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
	                {field:'dis_doctor',title:'医生姓名',width:80,sortable:true,editor:{type:'text',options:{textField:'editor_reg_patientName',valueField:'editor_reg_patientName',data:regAndDis}}},
					{field:'dis_time',title:'诊断时间',width:60,align:'center', rowspan:2,editor:{type:'text',options:{textField:'editor_reg_sex',valueField:'editor_reg_sex',data:regAndDis}}},
					{field:'reg_patientName',title:'病人姓名',width:60,editor:{type:'text',options:{textField:'editor_reg_age',valueField:'editor_reg_age',data:regAndDis}}},
					{field:'reg_serialNumber',title:'流水号',width:110,rowspan:1,sortable:true,editor:{type:'text',options:{textField:'editor_reg_card',valueField:'editor_reg_card',data:regAndDis}}},
					{field:'doc_fee',title:'看诊费',width:80,rowspan:2,editor:{type:'text',options:{textField:'editor_reg_serialNumber',valueField:'editor_reg_serialNumber',data:regAndDis}}},
					{field:'drug_price',title:'药物价格',width:110,rowspan:2,editor:{type:'text',options:{textField:'editor_dis_time',valueField:'editor_dis_time',data:regAndDis}}},
					{field:'totalPrice',title:'总价格',width:110,rowspan:2,editor:{type:'text',options:{textField:'editor_dis_time',valueField:'editor_dis_time',data:regAndDis}}}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					text:'导出excel表',
					iconCls:'icon-redo',
					handler:function(){
						$("#tb2").table2excel({  
				            exclude  : ".noExl", //过滤位置的 css 类名  
				            filename : "日报表" + new Date().getTime() + ".xls", //文件名称  
				            name: "Excel Document Name.xlsx",  
				            exclude_img: true,  
				            exclude_links: true,  
				            exclude_inputs: true});   
					}
				}]
			});
	})
	var regAndDis = [];
	function loadData(doctor,beginDate,endDate){
		//alert(name+","+card+","+date+","+number+","+doctor+","+beginDate+","+endDate);
		$.ajax({
			url:'${pageContext.request.contextPath}/DayMessageAction_execute',
			data:{"dis_doctor":doctor,"beginDate":beginDate,"endDate":endDate},
			async:false,
			cache:false,
			success:function(response){
				console.log(JSON.stringify(response));
				/* alert(response.list[0][0].dis_doctor);*/
				for(var i = 0 ;i<response.rows.length;i++){
					regAndDis.push({
						dis_doctor:response.rows[i].dis_doctor,
						dis_time:response.rows[i].dis_time,
			    		reg_patientName:response.rows[i].reg_patientName,
			    		reg_serialNumber:response.rows[i].reg_serialNumber,
				        doc_fee:response.rows[i].doc_fee,
				        drug_price:response.rows[i].drug_price,
				        totalPrice:response.rows[i].totalPrice
		  			})
				}
				//var data = $.parseJSON(regAndDis);
				$("#tb1").datagrid('loadData',regAndDis);
				$("#tb2").append("<tr><td colspan='7' align='center'>日报表</td></tr>");
				$("#tb2").append("<tr><td align='center'>医生姓名</td><td align='center'>诊断时间</td><td align='center'>病人姓名</td><td align='center'>流水号</td><td align='center'>看诊费</td><td align='center'>药物价格</td><td align='center'>总价格</td></tr>");
				for(var i = 0 ;i<response.rows.length;i++){
					$("#tb2").append("<tr><td>" + response.rows[i].dis_doctor + "</td><td>" + response.rows[i].dis_time + "</td><td>" + response.rows[i].reg_patientName + "</td><td>" + response.rows[i].reg_serialNumber + "</td><td>" + response.rows[i].doc_fee + "</td><td>" + response.rows[i].drug_price + "</td><td>" + response.rows[i].totalPrice + "</td></tr>");
				}
				//加载完数据后清空数组
				regAndDis.splice(0,regAndDis.length);
			}
		});
		//alert(JSON.stringify(regAndDis));
		
	}
	
</script>
<body>
	<table id="tb1"></table>
	<table id="tb2" style="display: none;"></table>
</body>
</html>