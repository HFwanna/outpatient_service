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
		var model_arr = [];
		$("#tb1").datagrid({
			title:'西药',
			iconCls:'icon-save',
			url:"fright_model2.json",
			width:700,
			height:350,
			nowrap: false,
			striped: true,
			collapsible:true,
			/* url:'fright_model.json', */
			remoteSort: false,
			idField:'ck',
			columns:[[
                {field:'ck',checkbox:true,width:30},
                {field:'pre_id',width:"60"},
                {field:'pre_drug_id',width:"60"},
                {field:'pre_drug_name',title:'名称',width:80,sortable:true},
				{field:'pre_drug_standard',title:'规格',width:100,align:'center', rowspan:2},
				{field:'pre_mg',title:'每次量',width:120},
				{field:'pre_unit',title:'单位',width:80,rowspan:1,sortable:true},
				{field:'pre_pathway',title:'用药途径',width:140,rowspan:2},
				{field:'pre_frequency',title:'用药时间',width:140,rowspan:2},
				{field:'pre_days',title:'用药天数',width:140,rowspan:2},
				{field:'pre_totalCount',title:'总量',width:120,rowspan:1},
				{field:'pre_drug_price',title:'金额',width:120,rowspan:1}
			]],
			pagination:true,
			rownumbers:true,
			onSelect:function(rowIndex,rowData){
				//选中立即处方已选项目显示[{}],但是不能rowData[0]获取
				//alert("选中")
				getSelected(rowIndex,rowData);
			},
			onSelectAll:function(rows){
				//[{},{}]
				getSelectAll(rows);
			},
			onUnselect:function(rowIndex,rowData){
				//选中立即处方已选项目显示[{}],但是不能rowData[0]获取
				//alert("取消选中");
				getUnSelected(rowIndex,rowData);
			},
			onUnselectAll:function(rows){
				getUnSelectedAll(rows);
			}
		});
		
		$("#tb2").datagrid({
			title:'已选项目',
			iconCls:'icon-save',
			width:700,
			height:350,
			nowrap: false,
			striped: true,
			collapsible:true,
			remoteSort: false,
			idField:'ck',
			columns:[[
                {field:'ck',checkbox:true,width:30},
                {field:'pre_id',width:"60",editor:{type:'text',options:{valueField:'pre_id',textField:'pre_id',data:model_arr}}},
                {field:'pre_drug_id',width:"60",editor:{type:'text',options:{valueField:'pre_drug_id',textField:'pre_drug_id',data:model_arr}}},
                {field:'pre_drug_name',title:'名称',width:80,sortable:true,editor:{type:'text',options:{valueField:'pre_drug_name',textField:'pre_drug_name',data:model_arr}}},
				{field:'pre_drug_standard',title:'规格',width:100,align:'center', rowspan:2,editor:{type:'text',options:{valueField:'pre_drug_standard',textField:'pre_drug_standard',data:model_arr}}},
				{field:'pre_mg',title:'每次量',width:120,editor:{type:'text',options:{valueField:'pre_mg',textField:'pre_mg',data:model_arr}}},
				{field:'pre_unit',title:'单位',width:80,rowspan:1,sortable:true,editor:{type:'text',options:{valueField:'pre_unit',textField:'pre_unit',data:model_arr}}},
				{field:'pre_pathway',title:'用药途径',width:140,rowspan:2,editor:{type:'text',options:{valueField:'pre_pathway',textField:'pre_pathway',data:model_arr}}},
				{field:'pre_frequency',title:'用药时间',width:140,rowspan:2,editor:{type:'text',options:{valueField:'pre_frequency',textField:'pre_frequency',data:model_arr}}},
				{field:'pre_days',title:'用药天数',width:140,rowspan:2,editor:{type:'text',options:{valueField:'pre_days',textField:'pre_days',data:model_arr}}},
				{field:'pre_totalCount',title:'总量',width:120,rowspan:1,editor:{type:'text',options:{valueField:'pre_totalCount',textField:'pre_totalCount',data:model_arr}}},
				{field:'pre_drug_price',title:'金额',width:120,rowspan:1,editor:{type:'text',options:{valueField:'pre_drug_price',textField:'pre_drug_price',data:model_arr}}}
			]],
			pagination:true,
			rownumbers:true,
		});
		function loadData(){
			$("#tb1").datagrid({url:"fright_model.json"});
		}
		function getUnSelectedAll(rows){
			for(var i = 0;i<rows.length;i++){
				$('#tb2').datagrid('deleteRow',0);
			}
		}
		
		function getSelectAll(rows){
			var selected = rows;
			for(var i=0;i<rows.length;i++){
				model_arr[i] = [];
			}
			for(var i = 0;i<rows.length;i++){
				model_arr[i].push({
					"ck":selected[i].ck,
					"pre_id":selected[i].pre_id,
			        "pre_drug_id":selected[i].pre_drug_id,
			        "pre_drug_name":selected[i].pre_drug_name,
			        "pre_drug_standard":selected[i].pre_drug_standard,
			        "pre_mg":selected[i].pre_mg,
			        "pre_unit":selected[i].pre_unit,
			        "pre_pathway":selected[i].pre_pathway,
			        "pre_frequency":selected[i].pre_frequency,
			        "pre_days":selected[i].pre_days,
			        "pre_totalCount":selected[i].pre_totalCount,
			        "pre_drug_price":selected[i].pre_drug_price
				})
				//alert(model_arr[i][0].pre_drug_name);
			}
			if(model_arr!=null){
				for(var i = 0 ; i<model_arr.length ; i++){
					$('#tb2').datagrid('appendRow',{
						//下面的是这一行的key和值，相当于默认初始化
						ck:model_arr[i][0].ck,
						pre_id:model_arr[i][0].pre_id,
				        pre_drug_id:model_arr[i][0].pre_drug_id,
				        pre_drug_name:model_arr[i][0].pre_drug_name,
				        pre_drug_standard:model_arr[i][0].pre_drug_standard,
				        pre_mg:model_arr[i][0].pre_mg,
				        pre_unit:model_arr[i][0].pre_unit,
				        pre_pathway:model_arr[i][0].pre_pathway,
				        pre_frequency:model_arr[i][0].pre_frequency,
				        pre_days:model_arr[i][0].pre_days,
				        pre_totalCount:model_arr[i][0].pre_totalCount,
				        pre_drug_price:model_arr[i][0].pre_drug_price
					});
				}
				 model_arr.splice(0,model_arr.length); 
			}
		}
		function getUnSelected(rowIndex,rowData){
			var currentSelected = $('#tb2').datagrid('getRowIndex',rowData.ck);
			$('#tb2').datagrid('deleteRow',currentSelected);
		}
		function getSelected(rowIndex,rowData){
			var selected = rowData;
			if (selected){
				model_arr.push({
					ck:selected.ck,
					pre_id:selected.pre_id,
			        pre_drug_id:selected.pre_drug_id,
			        pre_drug_name:selected.pre_drug_name,
			        pre_drug_standard:selected.pre_drug_standard,
			        pre_mg:selected.pre_mg,
			        pre_unit:selected.pre_unit,
			        pre_pathway:selected.pre_pathway,
			        pre_frequency:selected.pre_frequency,
			        pre_days:selected.pre_days,
			        pre_totalCount:selected.pre_totalCount,
			        pre_drug_price:selected.pre_drug_price
				})
				if(model_arr!=null){
						$('#tb2').datagrid('appendRow',{
							//下面的是这一行的key和值，相当于默认初始化
							ck:model_arr[0].ck,
							pre_id:model_arr[0].pre_id,
					        pre_drug_id:model_arr[0].pre_drug_id,
					        pre_drug_name:model_arr[0].pre_drug_name,
					        pre_drug_standard:model_arr[0].pre_drug_standard,
					        pre_mg:model_arr[0].pre_mg,
					        pre_unit:model_arr[0].pre_unit,
					        pre_pathway:model_arr[0].pre_pathway,
					        pre_frequency:model_arr[0].pre_frequency,
					        pre_days:model_arr[0].pre_days,
					        pre_totalCount:model_arr[0].pre_totalCount,
					        pre_drug_price:model_arr[0].pre_drug_price
						});
						model_arr.splice(0,model_arr.length);
				}
			}
		}
	})
	function loadData(){
		alert("dfdf");
			$("#tb1").datagrid({url:"fright_model.json"});
		}
	function loadData2(){
		alert("dfdf");
			$("#tb1").datagrid({url:"fright_model2.json"});
		}
</script>
<body>
	<table id="tb1"></table>
	<table id="tb2"></table>
	<input id="ggg" onchange="loadData()" value="333" type="text">
	<input type="text" id="tyt" value="test"> 
</body>
</html>