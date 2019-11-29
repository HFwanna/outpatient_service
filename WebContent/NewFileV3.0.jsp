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
	<script type="text/javascript" src="js/jquery.table2excel.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.1.2.6.min.js"></script>
	<script>
	//该方法是north区域的reg_id搜索功能
	function findRegid(){
		var reg_id = $("#serialNumber").val();
		window.location.href = "${pageContext.request.contextPath}/RegistrationAction_regmessage?reg_id="+reg_id;
		/* $.ajax({
			url:'${pageContext.request.contextPath}/RegistrationAction_regmessage',
			data:{"reg_id":reg_id},
			async:false,
			cache:false
		}) */
	}
	//该方法用于模板加载西药tt2数据
	function loadCenterData(arr){
		//$("#tt2").datagrid('loadData',arr); 
		if(arr!=null){
			for(var i = 0 ; i<arr.length ; i++){
				$('#tt2').datagrid('appendRow',{
					//下面的是这一行的key和值，相当于默认初始化
					ck:arr[i].ck,
					pre_id:arr[i].pre_id,
					pre_drug_id:arr[i].pre_drug_id,
			        pre_drug_name:arr[i].pre_drug_name,
			        pre_drug_standard:arr[i].pre_drug_standard,
			        pre_mg:arr[i].pre_mg,
			        pre_unit:arr[i].pre_unit,
			        pre_pathway:arr[i].pre_pathway,
			        pre_frequency:arr[i].pre_frequency,
			        pre_days:arr[i].pre_days,
			        pre_totalCount:arr[i].pre_totalCount,
			        pre_drug_price:arr[i].pre_drug_price
				});
			}
		}
		$("#w").window('close');
	}
		$(function(){
			//这个是west部分的tree结构
			$("#ezt3").tree({
					onDblClick: function (e) {
						//打开报表窗口
						if(e.text == "病人信息表"){
							$('#Regiframeset2').window('open',{
								width:1200,
								height:900,
								modal:false
							})
							//frameset3.jsp
						}else if(e.text == "查询日报表"){
							$('#Regiframeset3').window('open',{
								width:1200,
								height:900,
								modal:false
							})
							//frameset4.jsp
						}else if(e.text == "药物信息查询"){
							$('#Regiframeset4').window('open',{
								width:1200,
								height:900,
								modal:false
							})
						}
					},
					onContextMenu: function(e,node){
	                    //阻止冒泡事件，防止相同类型事件产生
						e.preventDefault();
						$(this).tree('select',node.target);
						$('#mm').menu('show',{
	                        //pageX() 属性是鼠标指针的位置，相对于文档的左边缘。
							left: e.pageX,
							top: e.pageY
						});
					}
				})
				$("#ezt2").tree({
					onDblClick: function (e) {
						$('#w').window('open',{
							width:1200,
							height:900,
							modal:false
							
						});
					},
					onContextMenu: function(e,node){
	                    //阻止冒泡事件，防止相同类型事件产生
						e.preventDefault();
						$(this).tree('select',node.target);
						$('#mm').menu('show',{
	                        //pageX() 属性是鼠标指针的位置，相对于文档的左边缘。
							left: e.pageX,
							top: e.pageY
						});
					}
				})
				$("#ezt1").tree({
					onDblClick: function (e) {
	                    //alert("我是"+e.text+"你点了我");
	                    if(e.text == "诊断信息"){
	                    	
	                    }else if(e.text == "处方"){
	                    	$('#w2').window('open',{
								width:1200,
								height:900,
								modal:false
							});
	                    }else{
	                    	$('#w3').window('open',{
								width:1200,
								height:900,
								modal:false
							});
	                    }
	                },
	                onContextMenu: function(e,node){
	                    //阻止冒泡事件，防止相同类型事件产生
						e.preventDefault();
						$(this).tree('select',node.target);
						$('#mm').menu('show',{
	                        //pageX() 属性是鼠标指针的位置，相对于文档的左边缘。
							left: e.pageX,
							top: e.pageY
						});
					}
				})
			//处方单和诊断都需要用到
			var reg_id = $("input[name='reg_id']").val();
			//这个是east异步ajax测试123
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
							returnStr += "<tr><td>"+data[i].reg_id+"</td><td>"+data[i].reg_patientName+"</td><td><a href='${pageContext.request.contextPath}/RegistrationAction_regmessage?reg_id="+data[i].reg_id+"'>"+"接诊"+"</a></td></tr>";
							//已就诊的循环输出到isCuredTable
							if(data[i].reg_state==true)
							returnStr1 += "<tr><td>"+data[i].reg_id+"</td><td>"+data[i].reg_patientName+"</td><td><a href='${pageContext.request.contextPath}/RegistrationAction_regmessage?reg_id="+data[i].reg_id+"'>"+"接诊"+"</a></td></tr>";
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
			
			
			
			
			//这个是数据表单，tt2是西药表单
			var lastIndex2;
			var lastIndex3;
			var tt2Premg = [{editor_premg:"0.02mg/次"},{editor_premg:"0.05mg/次"},{editor_premg:"2粒/次"},{editor_premg:"1粒/次"},{editor_premg:"1瓶/次"}];
			var tt2Preunit = [{editor_preunit:"10支/瓶"},{editor_preunit:"20粒/盒"},{editor_preunit:"100mg/瓶"},{editor_preunit:"10mg/片"},{editor_preunit:"10mg/包"},{editor_preunit:"10片/盒"},{}];
			var tt2Prepathway = [{editor_prepathway:"外敷"},{editor_prepathway:"外涂"},{editor_prepathway:"口服"},{editor_prepathway:"静脉注射"},{editor_prepathway:"熬制"}];
			var tt2Prefrequency = [{editor_prefrequency:"一日三次"},{editor_prefrequency:"一日四次"},{editor_prefrequency:"一日两次"},{editor_prefrequency:"一日一次"},{editor_prefrequency:"隔日一次"}];
			var tt2Predays = [{editor_predays:"3天"},{editor_predays:"7天"},{editor_predays:"30天"},{editor_predays:"14天"},{editor_predays:"两次"}];
			var tt2PretotalCount = [{editor_pretotalCount:"1盒"},{editor_pretotalCount:"1瓶"},{editor_pretotalCount:"1片"},{editor_pretotalCount:"2盒"},{editor_pretotalCount:"2瓶"},{editor_pretotalCount:"2片"}
			,{editor_pretotalCount:"3盒"},{editor_pretotalCount:"3瓶"},{editor_pretotalCount:"3片"}
			];
			var tt3Cmmg = [{editor_cmmg:"0.02mg/次"},{editor_cmmg:"0.05mg/次"},{editor_cmmg:"2粒/次"},{editor_cmmg:"1粒/次"},{editor_cmmg:"1瓶/次"}];
			var tt3Cmunit = [{editor_cmunit:"10支/瓶"},{editor_cmunit:"20粒/盒"},{editor_cmunit:"100mg/瓶"},{editor_cmunit:"10mg/片"},{editor_cmunit:"10mg/包"},{editor_cmunit:"10片/盒"}];
			var tt3Cmpathway = [{editor_cmpathway:"外敷"},{editor_cmpathway:"外涂"},{editor_cmpathway:"口服"},{editor_cmpathway:"静脉注射"},{editor_cmpathway:"熬制"}];
			var tt3Cmfrequency = [{editor_cmfrequency:"一日三次"},{editor_cmfrequency:"一日四次"},{editor_cmfrequency:"一日两次"},{editor_cmfrequency:"一日一次"},{editor_cmfrequency:"隔日一次"}];
			var tt3Cmdays = [{editor_cmdays:"3天"},{editor_cmdays:"7天"},{editor_cmdays:"30天"},{editor_cmdays:"14天"},{editor_cmdays:"两次"}];
			var tt3CmtotalCount = [{editor_cmtotalCount:"1盒"},{editor_cmtotalCount:"1瓶"},{editor_cmtotalCount:"1片"},{editor_cmtotalCount:"2盒"},{editor_cmtotalCount:"2瓶"},{editor_cmtotalCount:"2片"}
			,{editor_cmtotalCount:"3盒"},{editor_cmtotalCount:"3瓶"},{editor_cmtotalCount:"3片"}
			];
			var deleteArr2 = [];
			var deleteArr3 = [];
			//根据reg_id查出drugmessage.drug_name、drug_id、drug_price、drug_message的数组
			var drug_arr = [];
			var drug_arr3 = [];
			//思路：访问AjaxPrescription_*方法获取drugmessage有关的数据，
			/* 其中方法查询出来的list循环get出对象，用对象.getdrugMessage.get属性1，属性2,属性3,属性4
			获取值后，属性1放到一个list1里面，属性2放到一个list2（格式[{"key":"value"},{"k":"v"}]）里面....
			a.用JSON.toJSONString转换成json格式的string输出到浏览器，打印四次把四个数组输出到浏览器 
				应该不可行了
			b.再创建一个数组，把list1到4放进去，再用JSON.toJSONString转  这个数据格式是:
				[[{{"key":"value"},{"k":"v"}}],[{"key":"value"},{"k":"v"}]]
				循环 i response.length    for( j   response[i].length)    arr.push({response[i][j]})  
			
			
			返回数据形式：[[],[],[],[]]
			.done接受这4个数据， */
			//这是drugMessage要使用的属性
			$.ajax({
				url:'${pageContext.request.contextPath}/AjaxPrescription_drugMessageList',
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
				    var drug_message=response[i].drug_message;
				    var drug_name=response[i].drug_name;
				    var drug_price=response[i].drug_price;
				    var drug_id=response[i].drug_id;
				    var drug_standard=response[i].drug_standard;
				    //往表里仍如内容
				    drug_arr.push({
				    		editor_drugid:drug_id,
					        editor_drugprice:drug_price,
					        editor_drugname:drug_name,
					        editor_drugmessage:drug_message,
					        editor_drugstandard:drug_standard
				    })
				}
			})
			.fail(function(){
				console.log("error")
			});
			
			$.ajax({
				url:'${pageContext.request.contextPath}/AjaxCmedicine_drugMessageList',
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
				    var drug_message=response[i].drug_message;
				    var drug_name=response[i].drug_name;
				    var drug_price=response[i].drug_price;
				    var drug_id=response[i].drug_id;
				    var drug_standard=response[i].drug_standard;
				    //往表里仍如内容
				    drug_arr3.push({
				    		editor_drugid:drug_id,
					        editor_drugprice:drug_price,
					        editor_drugname:drug_name,
					        editor_drugmessage:drug_message,
					        editor_drugstandard:drug_standard
				    })
				}
			})
			.fail(function(){
				console.log("/AjaxCmedicine_drugMessageList.error")
			});  
			
			$('#tt2').datagrid({
				title:'',
				iconCls:'icon-save',
				width:600,
				height:350,
				nowrap: false,
				striped: true,
				fit: true,
				/* 
				sortName: 'user_id',//指定可以使用哪列进行排序
				sortOrder: 'desc',//指定默认排序规则asc/desc
				remoteSort: false,//是否支持远程排序
				idField:'user_id',//哪一列是id列，可以返回这列的id
				idField这个开启后选择多行，删除一行后无法再删除第二行，可能是因为返回id已被删除缘故，暂不清楚
				*/
				
				url:'${pageContext.request.contextPath}/AjaxPrescription_list',
				queryParams: {          
	                reg_id: reg_id            
	            },    
				/* sortName: 'ck', 
				sortOrder: 'desc',
				idField:'ck',*/
				frozenColumns:[[
	                {field:'ck',checkbox:true,width:30},
	                {field:'pre_id',width:"60"},
	                {field:'pre_drug_id',width:"60",editor:{type:'text',options:{valueField:'editor_drugid',data:drug_arr}}},
	                {field:'pre_drug_name',title:'名称',width:80,sortable:true,
	                	formatter:function(value){
							return value;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'editor_drugname',
								textField:'editor_drugname',
								data:drug_arr,
								required:true,
								//当combobox值改变时
								onChange:function(newVal,oldVal){
									 $.post(
											"${pageContext.request.contextPath}/AjaxPrescription_preMessage",
											{"content":newVal},
											function(data){
												console.log("给点反应啊大哥！！！！！！！！")
												$("#centerDrugMessage").html(data);
											},
											"text"
										);
								}
							}
						},
	                }
				]],
				columns:[[
					{field:'pre_drug_standard',title:'规格',width:100,align:'center', rowspan:2,editor:{type:'combobox',options:{valueField:'editor_drugstandard',textField:'editor_drugstandard',data:drug_arr}}
						/* formatter:function(value,rec){
							return '<span style="color:red">Edit Delete</span>';
						} */
					}
				],[
					{field:'pre_mg',title:'每次量',width:120,editor:{type:'combobox',options:{valueField:'editor_premg',textField:'editor_premg',data:tt2Premg}}},
					{field:'pre_unit',title:'单位',width:80,rowspan:1,sortable:true,editor:{type:'combobox',options:{valueField:'editor_preunit',textField:'editor_preunit',data:tt2Preunit}}},
					{field:'pre_pathway',title:'用药途径',width:100,rowspan:2,editor:{type:'combobox',options:{valueField:'editor_prepathway',textField:'editor_prepathway',data:tt2Prepathway}}},
					{field:'pre_frequency',title:'用药时间',width:100,rowspan:2,editor:{type:'combobox',options:{valueField:'editor_prefrequency',textField:'editor_prefrequency',data:tt2Prefrequency}}},
					{field:'pre_days',title:'用药天数',width:100,rowspan:2,editor:{type:'combobox',options:{valueField:'editor_predays',textField:'editor_predays',data:tt2Predays}}},
					{field:'pre_totalCount',title:'总量',width:100,rowspan:1,editor:{type:'combobox',options:{valueField:'editor_pretotalCount',textField:'editor_pretotalCount',data:tt2PretotalCount}}},
					{field:'pre_drug_price',title:'金额',width:120,rowspan:1,editor:'textReadonly'}
				]],
				/* onBeforeEdit:function(index,row){
					row.editing = true;
					updateActions(index);
				},
				onAfterEdit:function(index,row){
					row.editing = true;
					updateActions(index);
				},
				onCancelEdit:function(index,row){
					row.editing = true;
					updateActions(index);
				}, 
				onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');
				},*/
				onClickRow:function(rowIndex,rowData){
					//去除上一个编辑行，将现在选中行变成编辑行
					if (lastIndex2 != rowIndex){
						$('#tt2').datagrid('endEdit', lastIndex2);
						$('#tt2').datagrid('beginEdit', rowIndex);
					}
					lastIndex2 = rowIndex;
					//获得当前选中行的记录的
					/* $.post(
						"${pageContext.request.contextPath}/AjaxPrescription_preMessage",
						{"content":""},
						function(data){
							console.log(data);
							$("#centerDrugMessage").val(data);
						},
						"text"
					);
					console.log(rowData.pre_drug_id);
					console.log(JSON.stringify(rowData)); */
				},
				pagination:true,//分页
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
							ck:'',
							pre_id:'',
							pre_drug_id:'',
							pre_drug_name:'',
							pre_drug_standard:'',
							pre_mg:'',
							pre_unit:'',
							pre_pathway:'',
							pre_frequency:'',
							pre_days:'',
							pre_totalCount:'',
							pre_drug_price:''
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
							if(row.pre_id!=null){
								deleteArr2.push({
									"pre_id" : row.pre_id
								})
							}
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
									//定义的deleteArr2在script的最前面，这个数组用于accept的时候提交给后台根据id删除选择项
									if(row[i].pre_id!=null){
										deleteArr2.push({
											"pre_id" : row[i].pre_id
										})
									}
								}
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
						//获取当前页面所有行
						var object = $('#tt2').datagrid('getRows');
						var arr2 = new Array(object.length);
						//创建二维数组
						for(var i=0;i<arr2.length;i++){
							arr2[i] = [];
						}
						
						if(object!=null){
							for(var i =0;i<object.length;i++){
								//循环把对象属性封装到一个数组中
								//这里的数据来源是：--------><th field="" 的值
								arr2[i].push({"pre_id":object[i].pre_id});
								arr2[i].push({"pre_drug_id":object[i].pre_drug_id});
								arr2[i].push({"pre_drug_name":object[i].pre_drug_name});
								arr2[i].push({"pre_drug_standard":object[i].pre_drug_standard});
								arr2[i].push({"pre_mg":object[i].pre_mg});
								arr2[i].push({"pre_unit":object[i].pre_unit});
								arr2[i].push({"pre_pathway":object[i].pre_pathway});
								arr2[i].push({"pre_frequency":object[i].pre_frequency});
								arr2[i].push({"pre_days":object[i].pre_days});
								arr2[i].push({"pre_totalCount":object[i].pre_totalCount});
								arr2[i].push({"pre_drug_price":object[i].pre_drug_price});
								//alert(JSON.stringify(object[i]))
							}
						}
						//alert(JSON.stringify(arr[0]));
						//alert(JSON.stringify(arr));
						//把list同过ajax发送到后台保存函数包存
						$.ajax({
							url:'${pageContext.request.contextPath}/AjaxPrescription_save',
							data:{"arr2":JSON.stringify(arr2),"reg_id":reg_id,"deleteArr2":JSON.stringify(deleteArr2)},
							async:false,
							cache:false,
							type:'POST',
							success:function(){
								alert("保存成功");
								//刷新操作
								$('#tt2').datagrid('reload');
								//清空deleteArr的内容
								deleteArr2.splice(0,deleteArr2.length);
								//alert('reload了吗')
								//window.location.href="${pageContext.request.contextPath}/RegistrationAction_regmessage?reg_id="+reg_id; 
							},
							error:function(result){
								//请求失败后的操作
								throw 'tt2.error2';
							}
						})
					}
				},'-',{
					text:'reject',
					iconCls:'icon-undo',
					handler:function(){
						if(deleteArr2!=null){
							//返回操作那么要把之前delete操作放进去的数组都清空，因为这些数组不需要删除了
							deleteArr2.splice(0,deleteArr2.length);
						}
						$('#tt2').datagrid('rejectChanges');
					}
				},'-',{
					text:'GetChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = $('#tt2').datagrid('getChanges');
						//alert('changed rows: ' + rows.length + ' lines');
					}
				}]
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
				url:'${pageContext.request.contextPath}/AjaxCmedicine_list',
				queryParams: {          
	                reg_id: reg_id            
	            }, 
				/* sortName: 'code',
				sortOrder: 'desc',
				idField:'code', */
				columns:[[
	                {field:'ck',checkbox:true,width:30},
	                {field:'cm_id',width:"60"},
	                {field:'cm_drug_id',width:"60",editor:{type:'text',options:{valueField:'editor_drugid',textField:'editor_drugid',data:drug_arr3}}},
	                {field:'cm_drug_name',title:'名称',width:80,sortable:true,
	                	formatter:function(value){
							return value;
						},
						editor:{
							type:'combobox',
							options:{
								valueField:'editor_drugname',
								textField:'editor_drugname',
								data:drug_arr3,
								required:true,
								onChange:function(newVal,oldVal){
									 $.post(
											"${pageContext.request.contextPath}/AjaxCmedicine_cmMessage",
											{"content":newVal},
											function(data){
												$("#centerDrugMessage").html(data);
											},
											"text"
										);
								}
							}
						}
	                },
					{field:'cm_drug_standard',title:'规格',width:100,align:'center', rowspan:2,editor:{type:'combobox',options:{valueField:'editor_drugstandard',textField:'editor_drugstandard',data:drug_arr3}}},
					{field:'cm_mg',title:'每次量',width:120,editor:{type:'combobox',options:{valueField:'editor_cmmg',textField:'editor_cmmg',data:tt3Cmmg}}},
					{field:'cm_unit',title:'单位',width:80,rowspan:1,sortable:true,editor:{type:'combobox',options:{valueField:'editor_cmunit',textField:'editor_cmunit',data:tt3Cmunit}}},
					{field:'cm_pathway',title:'用药途径',width:140,rowspan:2,editor:{type:'combobox',options:{valueField:'editor_cmpathway',textField:'editor_cmpathway',data:tt3Cmpathway}}},
					{field:'cm_frequency',title:'用药时间',width:140,rowspan:2,editor:{type:'combobox',options:{valueField:'editor_cmfrequency',textField:'editor_cmfrequency',data:tt3Cmfrequency}}},
					{field:'cm_days',title:'用药天数',width:140,rowspan:2,editor:{type:'combobox',options:{valueField:'editor_cmdays',textField:'editor_cmdays',data:tt3Cmdays}}},
					{field:'cm_totalCount',title:'总量',width:120,rowspan:1,editor:{type:'combobox',options:{valueField:'editor_cmtotalCount',textField:'editor_cmtotalCount',data:tt3CmtotalCount}}},
					{field:'cm_drug_price',title:'金额',width:120,rowspan:1,editor:'textReadonly'}
				]],
				onClickRow:function(rowIndex){
					if (lastIndex3 != rowIndex){
						$('#tt3').datagrid('endEdit', lastIndex3);
						$('#tt3').datagrid('beginEdit', rowIndex);
					}
					lastIndex3 = rowIndex;
				},
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
							ck:'',
							cm_id:'',
							cm_drug_id:'',
							cm_drug_name:'',
							cm_drug_standard:'',
							cm_mg:'',
							cm_unit:'',
							cm_pathway:'',
							cm_frequency:'',
							cm_days:'',
							cm_totalCount:'',
							cm_drug_price:''
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
							if(row.cm_id!=null){
								deleteArr3.push({
									"cm_id" : row.cm_id
								})
							}
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
									//定义的deleteArr3在script的最前面，这个数组用于accept的时候提交给后台根据id删除选择项
									if(row[i].cm_id!=null){
										deleteArr3.push({
											"cm_id" : row[i].cm_id
										})
									}
								}
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
						//获取当前页面所有行
						var object = $('#tt3').datagrid('getRows');
						var arr3 = new Array(object.length);
						//创建二维数组
						for(var i=0;i<arr3.length;i++){
							arr3[i] = [];
						}
						
						if(object!=null){
							for(var i =0;i<object.length;i++){
								//循环把对象属性封装到一个数组中
								//这里的数据来源是：--------><th field="" 的值
								arr3[i].push({"cm_id":object[i].cm_id});
								arr3[i].push({"cm_drug_id":object[i].cm_drug_id});
								arr3[i].push({"cm_drug_name":object[i].cm_drug_name});
								arr3[i].push({"cm_drug_standard":object[i].cm_drug_standard});
								arr3[i].push({"cm_mg":object[i].cm_mg});
								arr3[i].push({"cm_unit":object[i].cm_unit});
								arr3[i].push({"cm_pathway":object[i].cm_pathway});
								arr3[i].push({"cm_frequency":object[i].cm_frequency});
								arr3[i].push({"cm_days":object[i].cm_days});
								arr3[i].push({"cm_totalCount":object[i].cm_totalCount});
								arr3[i].push({"cm_drug_price":object[i].cm_drug_price});
								//alert(JSON.stringify(object[i]))
							}
						}
						//alert(JSON.stringify(arr[0]));
						//alert(JSON.stringify(arr));
						//把list同过ajax发送到后台保存函数包存
						$.ajax({
							url:'${pageContext.request.contextPath}/AjaxCmedicine_save',
							data:{"arr3":JSON.stringify(arr3),"reg_id":reg_id,"deleteArr3":JSON.stringify(deleteArr3)},
							async:false,
							cache:false,
							success:function(){
								alert("保存成功");
								//刷新操作
								$('#tt3').datagrid('reload');
								//清空deleteArr的内容
								deleteArr3.splice(0,deleteArr3.length);
								//alert('reload了吗')
								//window.location.href="${pageContext.request.contextPath}/RegistrationAction_regmessage?reg_id="+reg_id; 
							},
							error:function(result){
								//请求失败后的操作
								throw 'tt3.error3';
							}
						})
					}
				},'-',{
					text:'reject',
					iconCls:'icon-undo',
					handler:function(){
						if(deleteArr3!=null){
							//返回操作那么要把之前delete操作放进去的数组都清空，因为这些数组不需要删除了
							deleteArr3.splice(0,deleteArr3.length);
						}
						$('#tt3').datagrid('rejectChanges');
					}
				},'-',{
					text:'GetChanges',
					iconCls:'icon-search',
					handler:function(){
						var rows = $('#tt3').datagrid('getChanges');
						//alert('changed rows: ' + rows.length + ' lines');
					}
				}]
			});
			$('#tt2').datagrid('hideColumn','pre_id');
			$('#tt3').datagrid('hideColumn','cm_id');
			$('#tt2').datagrid('hideColumn','pre_drug_id');
			$('#tt3').datagrid('hideColumn','cm_drug_id');
			
		});
		
	</script>
	<script>
		function productFormatter(value){
			for(var i=0; i<products.length; i++){
				if (products[i].editor_disdiagnose == value) return products[i].editor_disdiagnose;
			}
			return value;
		}
		
		//用于存放要删除的数据
		var deleteArr = [];
		//用于存放回显数据
		var products =[];
		//这是诊断记录查询返回结果
		$(function(){
			//处方单和诊断都需要用到
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
				//返回数据结构[{},{}....]
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
								//alert(JSON.stringify(object[i]))
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
								//alert('reload了吗')
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
						//alert('changed rows: ' + rows.length + ' lines');
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
					<span><a href="javascript:void(0)" onclick="findRegid()">查找</a></span>
				</li>
				<div style="display:inline;position:absolute;margin-left:270px;height:50px;width:1px;background-color:black"></div>
				<!--个人信息-->
				<li id="personalMessage" style="width: 20%;">
					<!--姓名-->
					<span>姓名:&nbsp;&nbsp;</span><span id="name" name="reg_patientName"><s:property value="#registration.reg_patientName" /></span>
					<br />
					<!--性别-->
					<span>性别:&nbsp;&nbsp;</span><span id="sex" name="reg_sex"><s:property value="#registration.reg_sex==true?'男':'女'" /></span>
					<br />
					<!--年龄-->
					<span>年龄:&nbsp;&nbsp;</span><span id="age" name="reg_age"><s:property value="#registration.reg_age" /></span>
					
				</li>
				<div style="display:inline;position:absolute;margin-left:560px;height:50px;width:1px;background-color:black"></div>
				<!--所属病人类型-->
				<li id="sickerType" style="width: 20%;">
					<span>疾病类型:&nbsp;&nbsp;</span><span id="sick" name=""><s:property value="#registration.reg_diseaseType" /></span>
					<br />
					<span>药物限额:&nbsp;&nbsp;</span><span id="drag" name=""></span>
					<br />
					<span>剩余限额:&nbsp;&nbsp;</span><span id="extraMoney" name=""></span>
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
	
	<!-- 第四部分，开发这个 -->
	<div region="south" title="药物详细信息" split="true" style="height:100px;padding:10px;background:#efefef;">
		<div class="easyui-layout" fit="true" style="background:#ccc;">
			<div region="center"><div style="font-size: 18px;" id="centerDrugMessage"></div></div>
			<!-- <div region="east" split="true" style="width:200px;">sub center</div> -->
		</div>
	</div>
	
	<!-- 先开发这个 -->
	<div region="east" iconCls="icon-reload" title="候诊列表" split="true" style="width:180px;">
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
	
	
	
	
	
	<div region="west" split="true" title="资料夹-病历" style="width:280px;padding1:1px;overflow:hidden;">
		<div class="easyui-accordion" fit="true" border="false">
			<div title="病历" selected="true" style="padding:10px;overflow:auto;">
				<ul id="ezt1" class="easyui-tree" url="tree_data.json">
				</ul>
				<!-- 这是右键菜单 -->
				<div id="mm" class="easyui-menu" style="width:120px;">
					<div onclick="append()" data-options="iconCls:'icon-add'">Append</div>
					<div onclick="removeit()" data-options="iconCls:'icon-remove'">Remove</div>
					<div class="menu-sep"></div>
					<div onclick="expand()">Expand</div>
					<div onclick="collapse()">Collapse</div>
				</div>
				<!-- 这是双击弹出窗口 -->
				<div id="w2" class="easyui-window" closed="true" title="病例诊断记录" style="width:900px;height:700px;padding:5px;">
					<div class="easyui-layout" fit="true">
						<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
							<iframe src="${pageContext.request.contextPath}/bingli.jsp#<s:property value="#registration.reg_id" />" id="Iframe_bingli" marginheight="0" marginwidth="0" height="625px" width="850px" ></iframe>
						</div>
					</div>
				</div>
				<div id="w3" class="easyui-window" closed="true" title="myWindow3" style="width:900px;height:700px;padding:5px;">
					<div class="easyui-layout" fit="true">
						<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
							<iframe src="${pageContext.request.contextPath}/bingli2.jsp#<s:property value="#registration.reg_id" />" id="Iframe_bingli2" marginheight="0" marginwidth="0" height="625px" width="850px" ></iframe>
						</div>
					</div>
				</div>
			</div>
			<div title="模板" style="padding:10px;">
				<ul id="ezt2" class="easyui-tree" url="tree_data2.json">
				</ul>
				<!-- 这是双击弹出窗口 -->
				<div id="w" class="easyui-window" closed="true" title="医院模板" iconCls="icon-save"  style="width:900px;height:700px;padding:5px;">
					<div class="easyui-layout" fit="true">
						<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
							<iframe src="frameset.jsp" marginheight="0" marginwidth="0" height="625px" width="850px" ></iframe>
						</div>
					</div>
				</div>
			</div>
			<div title="报表菜单" style="padding:10px">
				<ul id="ezt3" class="easyui-tree">
					<li>病人信息表</li>
					<li>查询日报表</li>
					<li>药物信息查询</li>
				</ul>
				<!-- 这是双击弹出窗口 -->
				<div id="Regiframeset2" class="easyui-window" closed="true" title="病人信息表" iconCls="icon-save"  style="width:1050px;height:700px;padding:5px;">
					<div class="easyui-layout" fit="true">
						<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
							<iframe src="frameset2.jsp" marginheight="0" marginwidth="0" height="625px" width="1000px" ></iframe>
						</div>
					</div>
				</div>
				<div id="Regiframeset3" class="easyui-window" closed="true" title="日报表信息" iconCls="icon-save"  style="width:1050px;height:700px;padding:5px;">
					<div class="easyui-layout" fit="true">
						<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
							<iframe src="frameset3.jsp" marginheight="0" marginwidth="0" height="625px" width="1000px" ></iframe>
						</div>
					</div>
				</div>
				<div id="Regiframeset4" class="easyui-window" closed="true" title="药物信息查询" iconCls="icon-save"  style="width:1050px;height:700px;padding:5px;">
					<div class="easyui-layout" fit="true">
						<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
							<iframe src="frameset4.jsp" marginheight="0" marginwidth="0" height="625px" width="1000px" ></iframe>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
			
	</script>
	
	
	<!-- 第三开发这个 -->
	<div region="center" title="" style="overflow:hidden;">
		<div class="easyui-tabs" fit="true" border="false">
		
		
			<input type="hidden" name="reg_id" value="<s:property value="#registration.reg_id" />" />
			<!--  =============================================================================-->
			<div title="诊断信息" style="padding:5px 5px;overflow:hidden;">
				<table id="tt" style="width:1000px;height:auto"
						title="门诊诊断" iconCls="icon-edit" singleSelect="true"
						idField="itemid" url="datagrid_data2.json">
					<thead>
						<tr>
						<!-- field是字段 -->
							<input type="hidden" name="dis_id" value="products.editor_disid"  />
							<th field="dis_id" width="60"  align="center" editor="{type:'text',options:{valueField:'editor_disid',textField:'editor_disid',data:products}}"></th>
							<th field="ck" width="60" align="center" checkbox="true"></th>
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
			
			
			<div title="处方" closable="false" style="padding:20px;" class="easyui-layout">
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
				<div >  
  
  
    				<button type="button" id="btnExport" onclick="method5('tableExcel')">导出Excel</button>  
				</div> 
			</div>
		</div>
	</div>
	 
<div id="myDiv">  
    <table id="tableExcel" width="100%" border="1" cellspacing="0" cellpadding="0">  
        <tr>  
            <td colspan="5" align="center">html 表格导出道Excel</td>  
        </tr>  
        <tr>  
            <td>列标题1</td>  
            <td>列标题2</td>  
            <td>类标题3</td>  
            <td>列标题4</td>  
            <td>列标题5</td>  
        </tr>  
        <tr>  
            <td>aaa</td>  
            <td>bbb</td>  
            <td>ccc</td>  
            <td>ddd</td>  
            <td>eee</td>  
        </tr>  
        <tr>  
            <td>AAA</td>  
            <td>BBB</td>  
            <td>CCC</td>  
            <td>DDD</td>  
            <td>EEE</td>  
        </tr>  
        <tr>  
            <td>FFF</td>  
            <td>GGG</td>  
            <td>HHH</td>  
            <td>III</td>  
            <td>JJJ</td>  
        </tr>  
    </table>  
</div>  
</body>
<script type="text/javascript">
$(document).ready(function () {  
    $("#btnExport").click(function () { 
    	//导出excel表调用api方法
        $("#tableExcel").table2excel({  
            exclude  : ".noExl", //过滤位置的 css 类名  
            filename : "日报表" + new Date().getTime() + ".xls", //文件名称  
            name: "Excel Document Name.xlsx",  
            exclude_img: true,  
            exclude_links: true,  
            exclude_inputs: true  

        });  
    });  
});  
//导出Excel表方法
var idTmr;  
function  getExplorer() {  
    var explorer = window.navigator.userAgent ;  
    //ie  
    if (explorer.indexOf("MSIE") >= 0) {  
        return 'ie';  
    }  
    //firefox  
    else if (explorer.indexOf("Firefox") >= 0) {  
        return 'Firefox';  
    }  
    //Chrome  
    else if(explorer.indexOf("Chrome") >= 0){  
        return 'Chrome';  
    }  
    //Opera  
    else if(explorer.indexOf("Opera") >= 0){  
        return 'Opera';  
    }  
    //Safari  
    else if(explorer.indexOf("Safari") >= 0){  
        return 'Safari';  
    }  
}  
function method5(tableid) {  
    if(getExplorer()=='ie')  
    {  
        var curTbl = document.getElementById(tableid);  
        var oXL = new ActiveXObject("Excel.Application");  
        var oWB = oXL.Workbooks.Add();  
        var xlsheet = oWB.Worksheets(1);  
        var sel = document.body.createTextRange();  
        sel.moveToElementText(curTbl);  
        sel.select();  
        sel.execCommand("Copy");  
        xlsheet.Paste();  
        oXL.Visible = true;  

        try {  
            var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");  
        } catch (e) {  
            print("Nested catch caught " + e);  
        } finally {  
            oWB.SaveAs(fname);  
            oWB.Close(savechanges = false);  
            oXL.Quit();  
            oXL = null;  
            idTmr = window.setInterval("Cleanup();", 1);  
        }  

    }  
    else  
    {  
        tableToExcel(tableid)  
    }  
}  
function Cleanup() {  
    window.clearInterval(idTmr);  
    CollectGarbage();  
}  
var tableToExcel = (function() {  
    var uri = 'data:application/vnd.ms-excel;base64,',  
            template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>',  
            base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },  
            format = function(s, c) {  
                return s.replace(/{(\w+)}/g,  
                        function(m, p) { return c[p]; }) }  
    return function(table, name) {  
        if (!table.nodeType) table = document.getElementById(table)  
        var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}  
        window.location.href = uri + base64(format(template, ctx))  
    }  
})()
</script>
</html>