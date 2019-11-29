<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Complex Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="js/themes/demo.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.1.2.6.min.js"></script>
	<script>
		$(function(){
			//这个是数据表单，tt2是西药表单
			var lastIndex2;
			var lastIndex3;
			$('#tt2').datagrid({
				title:'',
				iconCls:'icon-save',
				width:600,
				height:350,
				nowrap: false,
				striped: true,
				fit: true,
				url:'datagrid_data.json',
				/* sortName: 'ck', 
				sortOrder: 'desc',
				idField:'ck',*/
				frozenColumns:[[
	                {field:'ck'},
	                {title:'名称',field:'name',width:80,sortable:true}
				]],
				columns:[[
					{field:'standard',title:'规格',width:100,align:'center', rowspan:2,
						formatter:function(value,rec){
							return '<span style="color:red">Edit Delete</span>';
						}
					}
				],[
					{field:'frequency',title:'每次量',width:120},
					{field:'unit',title:'单位',width:80,rowspan:1,sortable:true},
					{field:'way',title:'用药途径',width:140,rowspan:2},
					{field:'time',title:'用药时间',width:140,rowspan:2},
					{field:'days',title:'用药天数',width:140,rowspan:2},
					{field:'total',title:'总量',width:120,rowspan:1},
					{field:'price',title:'金额',width:120,rowspan:1}
				]],
				pagination:true,
				rownumbers:true,
				singleSelect:false,//只允许单选
				//工具栏
				toolbar:[{
					text:'append',
					iconCls:'icon-add',
					handler:function(){
						//如果有添加一行，那么结束上一行的编辑
						$('#tt2').datagrid('endEdit', lastIndex2);
						//追加一个新行。新行将被添加到最后的位置。
						$('#tt2').datagrid('appendRow',{
							//下面的是这一行的key和值，相当于默认初始化
							ck:'C',
							name:'',
							standard:'',
							frequency:'',
							unit:'',
							way:'',
							time:'',
							days:'',
							total:'',
							price:''
						});
						//返回当前页的所有行。的长度-1  就是最后一行的索引了
						lastIndex2 = $('#tt2').datagrid('getRows').length-1;
						//新增一行的时候取消选中上一行
						$('#tt2').datagrid('unselectRow',lastIndex2-1);
						//选择一行，行索引从lastIndex开始。
						$('#tt2').datagrid('selectRow', lastIndex2);
						//开始编辑行。从lastIndex开始
						$('#tt2').datagrid('beginEdit', lastIndex2);
					}
				},'-',{
					text:'delete',
					iconCls:'icon-remove',
					handler:function(){
						var row = $('#tt2').datagrid('getSelected');
						if (row){
							var index = $('#tt2').datagrid('getRowIndex', row);
							$('#tt2').datagrid('deleteRow', index);
						} 
						//删除完一行后，重新分配序号
						for(var i = 0 ; i<$('#tt2').datagrid('getRows').length;i++){
							//更新操作，index是索引，row是行数据
							$('#tt2').datagrid('updateRow',{
								index:i,
								row: {
									itemid: i+1
								}
							});
						}
					}
				},'-',{
					text:'deleteAll',
					iconCls:'icon-remove',
					handler:function(){
						if(confirm("确认删除所有记录行吗？")){
							var row = $('#tt2').datagrid('getRows');
							if (row){
								//获取数组长度
								var length = row.length;
								for(var i=0;i<length;i++){
									/* var index = $('#tt').datagrid('getRowIndex', row[i]);
									alert(index); */
									//每次循环删除掉一个行后，索引都重新分配，所以只需要每次循环删除第一个即可
									$('#tt2').datagrid('deleteRow',0);
								}
							} 
						}
					}
				},'-',{
					text:'accept',
					iconCls:'icon-save',
					handler:function(){
						$('#tt2').datagrid('acceptChanges');
					}
				},'-',{
					text:'reject',
					iconCls:'icon-undo',
					handler:function(){
						$('#tt2').datagrid('rejectChanges');
					}
				},'-',{
					text:'GetChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = $('#tt2').datagrid('getChanges');
						alert('changed rows: ' + rows.length + ' lines');
					}
				}]
				/* toolbar:[{
					text:'append',
					iconCls:'icon-add',
					handler:function(){
						//如果有添加一行，那么结束上一行的编辑
						$('#tt2').datagrid('endEdit', lastIndex2);
						//追加一个新行。新行将被添加到最后的位置。
						$('#tt2').datagrid('appendRow',{
							//下面的是这一行的key和值，相当于默认初始化
							ck:'',
							itemid:'',
							productid:'',
							listprice:'',
							unitprice:'',
							attr1:'',
							status:'P'
						});
						//返回当前页的所有行。的长度-1  就是最后一行的索引了
						lastIndex2 = $('#tt2').datagrid('getRows').length-1;
						//选择一行，行索引从lastIndex开始。
						$('#tt2').datagrid('selectRow', lastIndex2);
						//开始编辑行。从lastIndex开始
						$('#tt2').datagrid('beginEdit', lastIndex2);
					}
				},'-',{
					text:'delete',
					iconCls:'icon-remove',
					handler:function(){
						//返回第一行被选中的，如果没有被选中返回null
						var row = $('#tt2').datagrid('getSelections');
						alert("类型"+typeof row);
						alert("是不是空"+row==null);
						alert(JSON.stringify(row));
						if (row){
							for(var i=0;i<row.length;i++){
								var id = row[i].id;
								//获取选中的id
								//var index = $('#tt2').datagrid('getRowIndex', row);
								$('#tt2').datagrid('deleteRow', id);
							}
						}
					}
				},'-',{
					text:'accept',
					iconCls:'icon-save',
					handler:function(){
						$('#tt2').datagrid('acceptChanges');
					}
				},'-',{
					text:'reject',
					iconCls:'icon-undo',
					handler:function(){
						$('#tt2').datagrid('rejectChanges');
					}
				},'-',{
					text:'GetChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = $('#tt2').datagrid('getChanges');
						alert('changed rows: ' + rows.length + ' lines');
					}
				}] */
			});
			//这个是数据表单，tt3是中药药表单
			$('#tt3').datagrid({
				title:'',
				iconCls:'icon-save',
				width:600,
				height:350,
				nowrap: false,
				striped: true,
				fit: true,
				url:'datagrid_data.json',
				sortName: 'code',
				sortOrder: 'desc',
				idField:'code',
				columns:[[
					{field:'ck',title:'C',width:20,rowspan:1},
	                {field:'name',title:'名称',width:80,sortable:false},
	                {field:'standard',title:'规格',width:80, rowspan:2},
					{field:'frequency',title:'每次量',width:120},
					{field:'unit',title:'单位',width:80,rowspan:1,sortable:true},
					{field:'way',title:'用药途径',width:140,rowspan:2},
					{field:'time',title:'用药时间',width:140,rowspan:2},
					{field:'days',title:'用药天数',width:140,rowspan:2},
					{field:'total',title:'总量',width:120,rowspan:1},
					{field:'price',title:'金额',width:120,rowspan:1}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					text:'append',
					iconCls:'icon-add',
					handler:function(){
						//如果有添加一行，那么结束上一行的编辑
						$('#tt3').datagrid('endEdit', lastIndex3);
						//追加一个新行。新行将被添加到最后的位置。
						$('#tt3').datagrid('appendRow',{
							//下面的是这一行的key和值，相当于默认初始化
							ck:'C',
							itemid:'默认值',
							productid:'',
							listprice:'',
							unitprice:'',
							attr1:'',
							status:'P'
						});
						//返回当前页的所有行。的长度-1  就是最后一行的索引了
						lastIndex3 = $('#tt3').datagrid('getRows').length-1;
						//新增一行的时候取消选中上一行
						$('#tt3').datagrid('unselectRow',lastIndex3-1);
						//选择一行，行索引从lastIndex开始。
						$('#tt3').datagrid('selectRow', lastIndex3);
						//开始编辑行。从lastIndex3开始
						$('#tt3').datagrid('beginEdit', lastIndex3);
					}
				},'-',{
					text:'delete',
					iconCls:'icon-remove',
					handler:function(){
						var row = $('#tt3').datagrid('getSelected');
						if (row){
							var index = $('#tt3').datagrid('getRowIndex', row);
							$('#tt3').datagrid('deleteRow', index);
						} 
						//删除完一行后，重新分配序号
						for(var i = 0 ; i<$('#tt3').datagrid('getRows').length;i++){
							//更新操作，index是索引，row是行数据
							$('#tt3').datagrid('updateRow',{
								index:i,
								row: {
									itemid: i+1
								}
							});
						}
					}
				},'-',{
					text:'deleteAll',
					iconCls:'icon-remove',
					handler:function(){
						if(confirm("确认删除所有记录行吗？")){
							var row = $('#tt3').datagrid('getRows');
							if (row){
								//获取数组长度
								var length = row.length;
								for(var i=0;i<length;i++){
									/* var index = $('#tt').datagrid('getRowIndex', row[i]);
									alert(index); */
									//每次循环删除掉一个行后，索引都重新分配，所以只需要每次循环删除第一个即可
									$('#tt3').datagrid('deleteRow',0);
								}
							}
						}
					}
				},'-',{
					text:'accept',
					iconCls:'icon-save',
					handler:function(){
						$('#tt3').datagrid('acceptChanges');
					}
				},'-',{
					text:'reject',
					iconCls:'icon-undo',
					handler:function(){
						$('#tt3').datagrid('rejectChanges');
					}
				},'-',{
					text:'GetChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = $('#tt3').datagrid('getChanges');
						alert('changed rows: ' + rows.length + ' lines');
					}
				}]
			});
			
			
			//这个是east异步ajax测试
			var returnStr = "<thead style='font-size: 13px'>"+
			"<th>流水号</th>"+			
			"<th>姓名</th>"+				
			"<th>操作</th>"+
			"</thead>";
			var returnStr1 = "<thead style='font-size: 13px'>"+
			"<th>流水号</th>"+			
			"<th>姓名</th>"+				
			"<th>操作</th>"+
			"</thead>";
			$.ajax({
				type:"POST",
				url:"${pageContext.request.contextPath}/AjaxRegistration_list",
				contentType:"application/x-www-form-urlencoded",
				async:false,
				data:{"content":"noCured"},
				dataType:"json",
				success:function(data){
					if(data.length>0){
						for(var i=0;i<data.length;i++){
							//未就诊的循环输出到noCuredTable
							if(data[i].reg_state==false)
							returnStr += "<tr><td>"+data[i].reg_id+"</td><td>"+data[i].reg_phone+"</td><td><a href='${pageContext.request.contextPath}/RegistrationAction_regmessage?reg_id="+data[i].reg_id+"'>"+data[i].reg_patientName+"</a></td></tr>";
							//已就诊的循环输出到isCuredTable
							if(data[i].reg_state==true)
							returnStr1 += "<tr><td>"+data[i].reg_id+"</td><td>"+data[i].reg_phone+"</td><td><a href='${pageContext.request.contextPath}/RegistrationAction_regmessage?reg_id="+data[i].reg_id+"'>"+data[i].reg_patientName+"</a></td></tr>";
						} 
						$("#noCuredTable").html(returnStr);
						$("#isCuredTable").html(returnStr1);
						/* $("#div").html(returnStr);
						$("#div").css("display","block"); */
					}else{
						/* $("#div").css("display","none"); */
					}
				}
			}); 
		});
		
	</script>
	<script>
		//将对象转换成字符串
		function obj2string(o){ 
			 var r=[]; 
			 if(typeof o=="string"){ 
			 return "\""+o.replace(/([\'\"\\])/g,"\\$1").replace(/(\n)/g,"\\n").replace(/(\r)/g,"\\r").replace(/(\t)/g,"\\t")+"\""; 
			 } 
			 if(typeof o=="object"){ 
			 if(!o.sort){ 
			  for(var i in o){ 
			  r.push(i+":"+obj2string(o[i])); 
			  } 
			  if(!!document.all&&!/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)){ 
			  r.push("toString:"+o.toString.toString()); 
			  } 
			  r="{"+r.join()+"}"; 
			 }else{ 
			  for(var i=0;i<o.length;i++){ 
			  r.push(obj2string(o[i])) 
			  } 
			  r="["+r.join()+"]"; 
			 } 
			 return r; 
			 } 
			 return o.toString(); 
			}
		
		function productFormatter(value){
			for(var i=0; i<products.length; i++){
				if (products[i].editor_disdiagnose == value) return products[i].editor_disdiagnose;
			}
			return value;
		}
		
		var deleteArr = [];
		var products =[];
		//这是诊断记录查询返回结果
		$(function(){
			var reg_id = $("input[name='reg_id']").val();
			$.ajax({
				url:'${pageContext.request.contextPath}/AjaxDiseaseType_list',
				data:{"reg_id":reg_id},
				dataType:"json",
				async:false,
				cache:false
			})
			.done(function(response){
				console.log("success"+JSON.stringify(response));
				console.log(response);
				for(var i=0;i<response.length;i++){
					//这个是访问后台查询id的出来的结果
				    var diagnose=response[i].dis_diagnose;
				    var id=response[i].dis_id;
				    var description=response[i].dis_description;
				    var doctor=response[i].dis_doctor;
				    var time=response[i].dis_time_s;
				    //往表里仍如内容
				    products.push({
				    		editor_disid:id,
					        editor_disdiagnose:diagnose,
					        editor_disdescription:description,
					        editor_disdoctor:doctor,
					        editor_distime:time
				    })
				}
				/* alert(products); */
			})
			.fail(function(){
				console.log("error")
			}); 
			var lastIndex;
			$('#tt').datagrid({
				/* columns:[[
					{field:'ck',checkbox:true},
					{field:'user_name',title:'用户名',width:120},
					{field:'user_code',title:'登录名',width:220,rowspan:2}
				]], */
				singleSelect:false,//只允许单选
				//工具栏
				toolbar:[{
					text:'append',
					iconCls:'icon-add',
					handler:function(){
						//用i定义序号变量，因为先添加了一行，所以当前行的长度+1即是序号
						var i = $('#tt').datagrid('getRows').length+1;
						//如果有添加一行，那么结束上一行的编辑
						$('#tt').datagrid('endEdit', lastIndex);
						//追加一个新行。新行将被添加到最后的位置。
						$('#tt').datagrid('appendRow',{
							//下面的是这一行的key和值，相当于默认初始化
							ck:'C',
							itemid:i,
							dis_diagnose:'',
							dis_description:'',
							dis_doctor:'',
							dis_time:''
						});
						//返回当前页的所有行。的长度-1  就是最后一行的索引了
						lastIndex = $('#tt').datagrid('getRows').length-1;
						//新增一行的时候取消选中上一行
						$('#tt').datagrid('unselectRow',lastIndex-1);
						//$('#tt').datagrid('acceptChanges');
						//选择一行，行索引从lastIndex开始。
						$('#tt').datagrid('selectRow', lastIndex);
						//开始编辑行。从lastIndex开始
						$('#tt').datagrid('beginEdit', lastIndex);
					}
				},'-',{
					text:'delete',
					iconCls:'icon-remove',
					handler:function(){
						var row = $('#tt').datagrid('getSelected');
						if (row){
							var index = $('#tt').datagrid('getRowIndex', row);
							//定义的deleteArr再509行，这个数组用于accept的时候提交给后台根据id删除选择项
							if(row.dis_id!=null){
								deleteArr.push({
									"dis_id" : row.dis_id
								})
							}
							
							$('#tt').datagrid('deleteRow', index);
						} 
						//删除完一行后，重新分配序号
						for(var i = 0 ; i<$('#tt').datagrid('getRows').length;i++){
							//更新操作，index是索引，row是行数据
							$('#tt').datagrid('updateRow',{
								index:i,
								row: {
									itemid: i+1
								}
							});
						}
					}
				},'-',{
					text:'deleteAll',
					iconCls:'icon-remove',
					handler:function(){
						if(confirm("确认删除所有记录行吗？")){
							var row = $('#tt').datagrid('getRows');
							if (row){
								//获取数组长度
								var length = row.length;
								for(var i=0;i<length;i++){
									//定义的deleteArr再509行，这个数组用于accept的时候提交给后台根据id删除选择项
									if(row[i].dis_id!=null){
										deleteArr.push({
											"dis_id" : row[i].dis_id
										})
									}
								}
								for(var i=0;i<length;i++){
									/* var index = $('#tt').datagrid('getRowIndex', row[i]);
									alert(index); */
									//每次循环删除掉一个行后，索引都重新分配，所以只需要每次循环删除第一个即可
									$('#tt').datagrid('deleteRow',0);
								}
							}
							
						};
					}
				},'-',{
					text:'accept',
					iconCls:'icon-save',
					handler:function(){
						$('#tt').datagrid('acceptChanges');
						//获取当前页面所有行
						var object = $('#tt').datagrid('getRows');
						var arr = new Array(object.length);
						//创建二维数组
						for(var i=0;i<arr.length;i++){
							arr[i] = [];
						}
						
						if(object!=null){
							for(var i =0;i<object.length;i++){
								//循环把对象属性封装到一个数组中
								//这里的数据来源是：--------><th field="" 的值
								arr[i].push({"dis_diagnose":object[i].dis_diagnose});
								arr[i].push({"dis_description":object[i].dis_description});
								arr[i].push({"dis_doctor":object[i].dis_doctor});
								arr[i].push({"dis_time":object[i].dis_time.replace(/年/g,"-").replace(/月/g,"-").replace(/日/g,"")});
								arr[i].push({"dis_id":object[i].dis_id});
								alert(JSON.stringify(object[i]))
							}
						}
						//alert(JSON.stringify(arr[0]));
						//alert(JSON.stringify(arr));
						//把list同过ajax发送到后台保存函数包存
						$.ajax({
							url:'${pageContext.request.contextPath}/AjaxDiseaseType_save',
							data:{"arr":JSON.stringify(arr),"reg_id":reg_id,"deleteArr":JSON.stringify(deleteArr)},
							async:false,
							cache:false,
							success:function(){
								alert("保存成功");
								diagnoseReload();
								//清空deleteArr的内容
								deleteArr.splice(0,deleteArr.length);
								alert('reload了吗')
								//window.location.href="${pageContext.request.contextPath}/RegistrationAction_regmessage?reg_id="+reg_id; 
							},
							error:function(result){
								//请求失败后的操作
								throw 'error';
							}
						})
						
						/* $.post(
								"${pageContext.request.contextPath}/AjaxDiseaseType_save",
								{
								 "arr":JSON.stringify(arr)
								},
								function(){
									alert("保存成功");
								}
							);  */
						
					}
				},'-',{
					text:'reject',
					iconCls:'icon-undo',
					handler:function(){
						if(deleteArr!=null){
							//返回操作那么要把之前delete操作放进去的数组都清空，因为这些数组不需要删除了
							deleteArr.splice(0,deleteArr.length);
						}
						$('#tt').datagrid('rejectChanges');
					}
				},'-',{
					text:'GetChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = $('#tt').datagrid('getChanges');
						alert('changed rows: ' + rows.length + ' lines');
					}
				}],
				onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');
				},
				onClickRow:function(rowIndex){
					if (lastIndex != rowIndex){
						$('#tt').datagrid('endEdit', lastIndex);
						$('#tt').datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
				
			});
			
			//保证进入页面就创建一行编辑行=====================================
				/*
					回显的数据输出到------><th field="dis_diagnose"
					回显的数据来源：------>products
					products来源   ：------>url:'${pageContext.request.contextPath}/AjaxDiseaseType_list'
				*/
				
				//products是获取后台得来的数据，如果病人是已经接诊病人，那么
				//那么也要回显id 
				if(products!=null){
					for(var i = 0 ; i<products.length ; i++){
						$('#tt').datagrid('appendRow',{
							//下面的是这一行的key和值，相当于默认初始化
							dis_id:products[i].editor_disid,
							ck:'',
							itemid:i+1,
							dis_diagnose:products[i].editor_disdiagnose,
							dis_description:products[i].editor_disdescription,
							dis_doctor:products[i].editor_disdoctor,
							dis_time:products[i].editor_distime
						});
					}
				}
			//隐藏这一列id值的列
			$('#tt').datagrid('hideColumn','dis_id');
			//先要提交一次修改，否则点击rejectChanges的时候触发清空效果
			$('#tt').datagrid('acceptChanges');
			//=====================================
		});
		//获得被选中
		function getSelected(){
			var selected = $('#tt').datagrid('getSelected');
			if (selected){
				/* alert(selected.user_id); */
				return selected.user_id;
			}
		}
		function getSelections(){
			var ids = [];
			var rows = $('#tt').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].code);
			}
			alert(ids.join(':'));
		}
		function clearSelections(){
			$('#tt').datagrid('clearSelections');
		}
		function selectRow(){
			$('#tt').datagrid('selectRow',2);
		}
		function selectRecord(){
			$('#tt').datagrid('selectRecord','002');
		}
		function unselectRow(){
			$('#tt').datagrid('unselectRow',2);
		}
		function mergeCells(){
			$('#tt').datagrid('mergeCells',{
				index:2,
				field:'addr',
				rowspan:2,
				colspan:2
			});
		}
		function diagnoseReload(){
			//第一步：先删除现在所有记录
			var row = $('#tt').datagrid('getRows');
			if (row){
				//获取数组长度
				var length = row.length;
				for(var i=0;i<length;i++){
					/* var index = $('#tt').datagrid('getRowIndex', row[i]);
					alert(index); */
					//每次循环删除掉一个行后，索引都重新分配，所以只需要每次循环删除第一个即可
					$('#tt').datagrid('deleteRow',0);
				}
			}
			//第二步：再重新添加所有查出来的记录
			products = [];
			var reg_id = $("input[name='reg_id']").val();
			$.ajax({
				url:'${pageContext.request.contextPath}/AjaxDiseaseType_list',
				data:{"reg_id":reg_id},
				dataType:"json",
				async:false,
				cache:false
			})
			.done(function(response){
				console.log("success"+JSON.stringify(response));
				console.log(response);
				for(var i=0;i<response.length;i++){
					//这个是访问后台查询id的出来的结果
				    var diagnose=response[i].dis_diagnose;
				    var id=response[i].dis_id;
				    var description=response[i].dis_description;
				    var doctor=response[i].dis_doctor;
				    var time=response[i].dis_time_s;
				    //往表里仍如内容
				    products.push({
				    		editor_disid:id,
					        editor_disdiagnose:diagnose,
					        editor_disdescription:description,
					        editor_disdoctor:doctor,
					        editor_distime:time
				    })
				}
				/* alert(products); */
			})
			.fail(function(){
				console.log("error")
			}); 
			
			//第三步：添加内容进去
			if(products!=null){
				for(var i = 0 ; i<products.length ; i++){
					$('#tt').datagrid('appendRow',{
						//下面的是这一行的key和值，相当于默认初始化
						dis_id:products[i].editor_disid,
						ck:'',
						itemid:i+1,
						dis_diagnose:products[i].editor_disdiagnose,
						dis_description:products[i].editor_disdescription,
						dis_doctor:products[i].editor_disdoctor,
						dis_time:products[i].editor_distime
					});
				}
			}
		//隐藏这一列id值的列
		$('#tt').datagrid('hideColumn','dis_id');
		}
	</script>
</head>
<body class="easyui-layout">
	<!-- 第二，开发这个 -->
	<div region="north" title="病人基本信息" split="true" style="height:100px;padding:10px;">
		<!--病人基本信息-->
		<div id="sickerMessage" style="position: relative;">
			<!--预约信息-->
			<ul style="display: flex;list-style-type:none;">
				<li id="bookMessage" style="width: 20%;">
					<span>挂号日期</span><span id="registrationDate" name="reg_date"><s:property value="#registration.reg_date" /></span>
					<br />
					<span>流水号</span><input id="serialNumber" name="reg_serialNumber" type="text" value="<s:property value="#registration.reg_serialNumber" />" />
					<span><a href="#">查找</a></span>
				</li>
				<div style="display:inline;position:absolute;margin-left:270px;height:50px;width:1px;background-color:black"></div>
				<!--个人信息-->
				<li id="personalMessage" style="width: 20%;">
					<!--姓名-->
					<span>姓名</span><span id="name" name="reg_patientName"><s:property value="#registration.reg_patientName" /></span>
					<br />
					<!--性别-->
					<span>性别</span><span id="sex" name="reg_sex"><s:property value="#registration.reg_sex==true?'男':'女'" /></span>
					<br />
					<!--年龄-->
					<span>年龄</span><span id="age" name="reg_age"><s:property value="#registration.reg_age" /></span>
					
				</li>
				<div style="display:inline;position:absolute;margin-left:560px;height:50px;width:1px;background-color:black"></div>
				<!--所属病人类型-->
				<li id="sickerType" style="width: 20%;">
					<span>疾病类型</span><span id="sick" name=""></span>
					<br />
					<span>药物限额</span><span id="drag" name=""></span>
					<br />
					<span>剩余限额</span><span id="extraMoney" name=""></span>
				</li>
				<div style="display:inline;position:absolute;margin-left:850px;height:50px;width:1px;background-color:black"></div>
				<!--费用详情-->
				<li id="fareMessage" style="width: 20%;">
					<input type="hidden" name="reg_card">
					<span>诊疗单总额</span><br />
					<span>自负比例</span><br />
					<span>自负额</span>
				</li>
				<div style="display:inline;position:absolute;margin-left:1140px;height:50px;width:1px;background-color:black"></div>
				<!--过敏记录-->
				<li id="allergyRecord" style="width: 20%;">
					<span>过敏记录</span>
				</li>
			</ul>
		</div>
	</div>
	
	
	<div region="south" title="South Title" split="true" style="height:100px;padding:10px;background:#efefef;">
		<div class="easyui-layout" fit="true" style="background:#ccc;">
			<div region="center">sub center</div>
			<div region="east" split="true" style="width:200px;">sub center</div>
		</div>
	</div>
	
	<!-- 先开发这个 -->
	<div region="east" iconCls="icon-reload" title="候诊列表" split="true" style="width:180px;">
		<ul class="easyui-tree" url="tree_data.json"></ul>
		<div class="easyui-tabs" fit="true" border="false">
		
		
			<div id="notCured" title="未接诊" style="padding:20px;overflow:hidden;"> 
				<div data-options="href:'user.json'" >
					
				
				</div>  
				<div style="margin-top:-20px;">
					<table id="noCuredTable">
						<thead style="font-size: 13px">
							<th>流水号</th>				
							<th>姓名</th>				
							<th>操作</th>
						</thead>
						<%-- <c:forEach items="${list }" var="customer">
							<TR
								style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
								<TD>${customer.cust_name }</TD>
								<TD>${customer.cust_level }</TD>
								<TD>
								<a href="${pageContext.request.contextPath }/customerServlet?method=edit&custId=${customer.cust_id}">修改</a>
								&nbsp;&nbsp;
								<a href="${pageContext.request.contextPath }/customerServlet?method=delete&custId=${customer.cust_id}">删除</a>
								</TD>
							</TR>
						</c:forEach> --%>
						<tr>
							<th>1</th>				
							<th>陈</th>				
							<th><a href="#">接诊</a></th>				
						</tr>
						<tr>
							<th>1</th>				
							<th>陈</th>				
							<th><a href="#">接诊</a></th>				
						</tr>
					</table>
				</div>
			</div>
			<div title="已就诊" closable="false" style="margin-top:-20px;padding:20px;">
				<table id="isCuredTable">
					<thead style="font-size: 13px;">
						<th>流水号</th>				
						<th>姓名</th>				
					</thead>
					<tr>
						<th>1</th>				
						<th>陈</th>				
					</tr>
					<tr>
						<th>1</th>				
						<th>陈</th>				
					</tr>
				</table>
			</div>
			<!-- <div title="Tab3" iconCls="icon-reload" closable="true" style="overflow:hidden;padding:5px;">
				<table id="tt2"></table> 
			</div> -->
		</div>
	</div>
	
	
	
	
	
	<div region="west" split="true" title="West Menu" style="width:280px;padding1:1px;overflow:hidden;">
		<div class="easyui-accordion" fit="true" border="false">
			<div title="Title1" style="padding:10px;overflow:auto;">
				<p>content1</p>
				<p>content1</p>
				<p>content1</p>
				<p>content1</p>
				<p>content1</p>
				<p>content1</p>
				<p>content1</p>
				<p>content12</p>
			</div>
			<div title="Title2" selected="true" style="padding:10px;">
				content2
			</div>
			<div title="Title3" style="padding:10px">
				content3
			</div>
		</div>
	</div>
	
	<!-- 第三开发这个 -->
	<div region="center" title="" style="overflow:hidden;">
		<div class="easyui-tabs" fit="true" border="false">
		
		
			<input type="hidden" name="reg_id" value="<s:property value="#registration.reg_id" />" />
			<!--  =============================================================================-->
			<div title="诊断信息" style="padding:20px;overflow:hidden;">
				<table id="tt" style="width:1000px;height:auto"
						title="门诊诊断" iconCls="icon-edit" singleSelect="true"
						idField="itemid" url="datagrid_data2.json">
					<thead>
						<tr>
						<!-- field是字段 -->
							<input type="hidden" name="dis_id" value="products.editor_disid"  />
							<th field="dis_id" width="60"  align="center" editor="{type:'text',options:{valueField:'editor_disid',textField:'editor_disid',data:products}}"></th>
							<th field="ck" width="60" align="center" editor="{type:'checkbox',options:{on:'',off:''}}"></th>
							<th field="itemid" width="80">序号</th>
							<!-- required：校验，valueField：值域，用于提交后台，textField：前台显示域，data：一个数组对象-->
							<th field="dis_diagnose" width="100" formatter="productFormatter" editor="{type:'combobox',options:{valueField:'editor_disdiagnose',textField:'editor_disdiagnose',data:products,required:true}}" >诊断</th>
							<th field="dis_description" width="80"  align="right" editor="{type:'text',options:{valueField:'editor_disdescription',textField:'editor_disdescription',precision:1}}">描述</th>
							<th field="dis_doctor" width="80"  align="right" editor="{type:'text',options:{valueField:'editor_disdoctor',textField:'editor_disdoctor',data:products}}">诊断人</th>
							<th field="dis_time" width="250"  editor="{type:'text',options:{valueField:'editor_distime',textField:'editor_distime',data:products}}">诊断时间</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--  =============================================================================-->
			
			
			<div title="处方" closable="true" style="padding:20px;" class="easyui-layout">
				<div region="center" title="" style="overflow:hidden;height:500px" >
					<div class="easyui-tabs" fit="true" border="false">
						<div title="西药1" closable="false" style="overflow:hidden;padding:5px;">
							<table id="tt2"></table> 
						</div>
						<div title="中药2" closable="false" style="overflow:hidden;padding:5px;">
							<table id="tt3"></table> 
						</div>
					</div>
				</div>
			</div>
			<div title="检查" iconCls="icon-reload" closable="true" style="overflow:scroll;padding:5px;">
				<s:debug></s:debug>
			</div>
		</div>
	</div>
	
</body>
</html>