<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="js/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="js/themes/demo.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.1.2.6.min.js"></script>
	<script>
		$(function(){
			$(".easyui-tree").tree({
				onDblClick: function (e) {
                    //alert("我是"+e.text+"你点了我");
                    if(e.text == "诊断信息"){
						$('#w').window('open',{
							width:1200,
							height:900,
							modal:false
							
						});
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
		})
		function append(){
			var t = $('#tt4');
			var node = t.tree('getSelected');
			t.tree('append', {
				parent: (node?node.target:null),
				data: [{
					text: 'new item1'
				},{
					text: 'new item2'
				}]
			});
		}
		function removeit(){
			var node = $('#tt4').tree('getSelected');
			$('#tt4').tree('remove', node.target);
		}
		function collapse(){
			var node = $('#tt4').tree('getSelected');
			$('#tt4').tree('collapse',node.target);
		}
		function expand(){
			var node = $('#tt4').tree('getSelected');
			$('#tt4').tree('expand',node.target);
		}
		function loadCenterData(arr){
			/* for(var i = 0 ;i<arr.length;i++){
				arr.push({
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
	  			})
			}  */
			//alert(JSON.stringify(arr))
			//var data = $.parseJSON(arr);
			$("#tt8").datagrid('loadData',arr); 
			$("#w").window('close');
			//加载完数据后清空数组
			//arr.splice(0,regAndDis.length);
		}
	</script>
</head>
<body >
	
	
		<h2>Border Layout on Panel</h2>
	<div class="demo-info" style="margin-bottom:10px">
		<div class="demo-tip icon-tip"></div>
		<div>The layout can be applied on panel.</div>
	</div>
	
	<div class="easyui-layout" style="width:700px;height:400px;">
		<div region="north" style="overflow:hidden;height:60px;padding:10px">
			<h2>Layout in Panel</h2>
		</div>
		<div region="south" split="true" style="height:50px;background:#fafafa;">
		</div>
		<div region="east" icon="icon-reload" title="East" split="true" style="width:180px;">
		</div>
		<div region="west" split="true" title="West" style="width:100px;">
			<ul id="tt4" class="easyui-tree" url="tree_data.json">
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
			<div id="w" class="easyui-window" closed="true" title="My Window" iconCls="icon-save"  style="width:900px;height:700px;padding:5px;">
				<div class="easyui-layout" fit="true">
					<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
						<iframe src="frameset.jsp" marginheight="0" marginwidth="0" height="625px" width="850px" ></iframe>
					</div>
				</div>
			</div>
			<div id="w2" class="easyui-window" closed="true" title="myWindow2" style="width:900px;height:700px;padding:5px;">
				<div class="easyui-layout" fit="true">
					<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
						<iframe src="${pageContext.request.contextPath}/bingli.jsp#2" id="Iframe_bingli" marginheight="0" marginwidth="0" height="625px" width="850px" ></iframe>
						<%-- <script type="text/javascript">
							var iframeTag = document.getElementById('#Iframe_bingli');
							iframeSrc = '${pageContext.request.contextPath}/bingli.jsp'; 
							iframeTag.src = iframeSrc;
						</script> --%>
					</div>
				</div>
			</div>
			<div id="w3" class="easyui-window" closed="true" title="myWindow3" style="width:900px;height:700px;padding:5px;">
				<div class="easyui-layout" fit="true">
					<div region="center" border="false" style="padding:10px;background:#fff;border:1px solid #ccc;">
						<iframe src="${pageContext.request.contextPath}/bingli2.jsp#2" id="Iframe_bingli2" marginheight="0" marginwidth="0" height="625px" width="850px" ></iframe>
					</div>
				</div>
			</div>
		</div>
		<div region="center" title="Main Title" style="background:#fafafa;overflow:hidden">
			<table class="easyui-datagrid"
					url="" border="false"
					fit="true" fitColumns="true" id="tt8">
				<thead>
					<tr>
						<th field="ck" width="50">Item ID</th>
						<th field="pre_id" width="50">Product ID</th>
						<th field="pre_drug_id" width="50" align="right">List Price</th>
						<th field="pre_drug_name" width="80" align="right" sortable="true">名称</th>
						<th field="pre_drug_standard" width="150" sortable="true">规格</th>
						<th field="pre_mg" width="50" align="center" sortable="true">每次量</th>
						<th field="pre_unit" width="50" align="center" sortable="true">单位</th>
						<th field="pre_pathway" width="50" align="center" sortable="true">用药途径</th>
						<th field="pre_frequency" width="50" align="center" sortable="true">用药时间</th>
						<th field="pre_days" width="50" align="center" sortable="true">用药天数</th>
						<th field="pre_totalCount" width="50" align="center" sortable="true">总量</th>
						<th field="pre_drug_price" width="50" align="center" sortable="true">金额</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>
<!--
$("#tb1").datagrid({
			title:'西药',
			iconCls:'icon-save',
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
				//下面这个方法把tb1选择内容显示在tb2
				getSelected(rowIndex,rowData);
			},
			onSelectAll:function(rows){
				//[{},{}]
				//下面这个方法把tb1选择内容显示在tb2
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
 -->
 
<!-- 
	<div class="easyui-accordion" fit="true" border="false" style="height:500px;">
			<div title="模板" selected="true" style="padding:10px;">
				<ul id="tt4" class="easyui-tree" data-options="
				data: [{
	'id':1,
	'text':'My Documents',
	'children':[{
		'id':11,
		'text':'Photos',
		'state':'closed',
		'children':[{
			'id':111,
			'text':'Friend'
		},{
			'id':112,
			'text':'Wife'
		},{
			'id':113,
			'text':'Company'
		}]
	},{
		'id':12,
		'text':'Program Files',
		'children':[{
			'id':121,
			'text':'Intel'
		},{
			'id':122,
			'text':'Java',
			'attributes':{
				'p1':'Custom Attribute1',
				'p2':'Custom Attribute2'
			}
		},{
			'id':123,
			'text':'Microsoft Office'
		},{
			'id':124,
			'text':'Games',
			'checked':true
		}]
	},{
		'id':13,
		'text':'index.html'
	},{
		'id':14,
		'text':'about.html'
	},{
		'id':15,
		'text':'welcome.html'
	}]
}],
				method: 'get',
				animate: true,
                //e：event	必需。规定要使用的事件。这个 event 参数来自事件绑定函数。
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
			"></ul>
			</div>
			<div title="Title3" style="padding:10px">
				content3
			</div>
		
	</div>





 -->