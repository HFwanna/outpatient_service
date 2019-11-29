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
	<script type="text/javascript">
	$(function(){
		$(".easyui-tree").tree({
			onDblClick: function (e) {
	            //alert("我是"+e.text+"你点了我");
	            if(e.text == "0309模板"){
		            window.parent.frames["rightframe"].window.loadData();
	            }else if(e.text == "我的模板2"){
	            	window.parent.frames["rightframe"].window.loadData2();
	            }
	            /* if(e.text=="0309模板"){
	            	// self.parent.frames["rightframe"].src = 'fright2.jsp';
	            	//调用父框架获取右框架改变src
	            	var iframeTag = self.parent.document.getElementById('rightframe').src = "fright2.jsp";
	            }else if(e.text=="我的模板2"){
	            	self.parent.document.getElementById('rightframe').src = 'fright.jsp';
	            } */
	        }
		});
	})
	</script>
<body>
	<div>
		<select style="width:190px">
			<option>全院模板</option>
			<option selected="selected">个人模板</option>
		</select>
		<br>
		<input type="text"  style="width:144px"><input type="button" name="search" value="搜索">
	</div>
		<div style="border:1px solid grey;width:188px;height: 450px"> 
			<ul id="tt5" class="easyui-tree" url="tree_data3.json">
			</ul>
		</div>
</body>
</html>