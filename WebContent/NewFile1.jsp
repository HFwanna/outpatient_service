<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="js/themes/demo.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script>
				var products = [];
				$(function(){
					/* $.ajax({
						url:'${pageContext.request.contextPath}/svServlet2',
						data:{"reg_id":2},
						async:false,
						cache:false,
						success:function(response){
							for(var i=0;i<response.length;i++){
								//
							    var obj=response[i].dis_diagnose;
							    var obj2=response[i].dis_id;
							    products.push({
							        productid:obj2,//'FI-SW-01',
							        name:obj//'Koi'
							    })
							}
							alert(products);
							return false;
						}
					}) */
					 $('#tt1').treegrid({   
						    url:'treegrid_data.json',   
						      treeField:'name',   
						      columns:[[   
						          {title:'Task Name',field:'name',width:180},   
						          {field:'persons',title:'Persons',width:60,align:'right'},   
						          {field:'begin',title:'Begin Date',width:80},   
						          {field:'end',title:'End Date',width:80}   
						      ]]   
						  }); 
				})
				
			</script>
</head>

<body>
<!-- <table id="tt" style="width:1000px;height:auto"
						title="门诊诊断" iconCls="icon-edit" singleSelect="true"
						idField="itemid" url="datagrid_data2.json">
					<thead>
						<tr>
						field是字段
							<input type="hidden" name="dis_id" value="products.editor_disid"  />
							<th field="ck" width="60" align="center" editor="{type:'checkbox',options:{on:'',off:''}}"></th>
							<th field="itemid" width="80">序号</th>
							required：校验，valueField：值域，用于提交后台，textField：前台显示域，data：一个数组对象
							<th field="dis_diagnose" width="100" formatter="productFormatter" editor="{type:'combobox',options:{valueField:'editor_disdiagnose',textField:'editor_disdiagnose',data:products,required:true}}" >诊断</th>
							<th field="dis_description" width="80" align="right" editor="{type:'text',options:{valueFiled:'editor_disdescription',textField:'editor_disdescription',precision:1}}">描述</th>
							<th field="dis_doctor" width="80" align="right" editor="{type:'text',options:{valueField:'editor_disdoctor',textField:'editor_disdoctor',data:products}}">诊断人</th>
							<th field="dis_time" width="250" editor="{type:'text',options:{valueField:'editor_distime',textField:'editor_distime',data:products}}">诊断时间</th>
						</tr>
					</thead>
				</table> -->
				 <table id="tt1"></table>
</body>
</html>