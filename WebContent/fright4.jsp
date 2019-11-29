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
	                {field:'drug_name',title:'药物名',width:80,sortable:true,editor:{type:'text',options:{textField:'editor_reg_patientName',valueField:'editor_reg_patientName',data:regAndDis}}},
					{field:'drug_message',title:'使用信息',width:60,align:'center', rowspan:2,editor:{type:'text',options:{textField:'editor_reg_sex',valueField:'editor_reg_sex',data:regAndDis}}},
					{field:'drug_repertory',title:'药物库存',width:60,editor:{type:'text',options:{textField:'editor_reg_age',valueField:'editor_reg_age',data:regAndDis}}},
					{field:'drug_price',title:'药物价格',width:110,rowspan:1,sortable:true,editor:{type:'text',options:{textField:'editor_reg_card',valueField:'editor_reg_card',data:regAndDis}}},
					{field:'drug_type',title:'医保类型',width:110,rowspan:2,editor:{type:'text',options:{textField:'editor_reg_date',valueField:'editor_reg_date',data:regAndDis}}},
					{field:'drug_standard',title:'药物规格',width:80,rowspan:2,editor:{type:'text',options:{textField:'editor_reg_serialNumber',valueField:'editor_reg_serialNumber',data:regAndDis}}},
				]],
				pagination:true,
				rownumbers:true
			});
	})
	var regAndDis = [];
	function loadData(name,drugType,drug_type,beginPrice,endPrice){
		//alert(name+","+card+","+date+","+number+","+doctor+","+beginDate+","+endDate);
		$.ajax({
			url:'${pageContext.request.contextPath}/DrugMessageAction_execute',
			data:{"drugMessage.drug_name":name,"drugType":drugType,"drugMessage.drug_type":drug_type,"beginPrice":beginPrice,"endPrice":endPrice},
			async:false,
			cache:false,
			success:function(response){
				console.log(JSON.stringify(response));
				/* alert(response.list[0][0].dis_doctor);*/
				for(var i = 0 ;i<response.rows.length;i++){
					regAndDis.push({
						drug_name:response.rows[i].drug_name,
						drug_message:response.rows[i].drug_message,
						drug_repertory:response.rows[i].drug_repertory,
						drug_price:response.rows[i].drug_price,
						drug_type:response.rows[i].drug_type?'是':'否',
						drug_standard:response.rows[i].drug_standard
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