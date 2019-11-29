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
	function loadData(){
		var name = $("#drug_name").val();
		var drugType = $("#drugType").val();
		var drug_type;
		if($("#drug_type").val() == "---请选择---"){
			drug_type = null;
		}else{
			drug_type = $("#drug_type").val();
		}
		
		var beginPrice = $("#beginPrice").val();
		var endPrice = $("#endPrice").val();
		window.parent.frames["rightframe"].window.loadData(name,drugType,drug_type,beginPrice,endPrice);
	}
	</script>
<body>
	<div>
		<form method="post" action="">
			<table align="center">
				<tr><td rowspan="2" align="right">药物名：</td></tr><tr><td align="left"><input type="text" id="drug_name" /></td></tr>
				<tr><td rowspan="2" align="right">药物类型：</td></tr><tr><td align="left">
					<select id="drugType">
						<option selected="selected">西药</option>
						<option>中药</option>
					</select>
				</td></tr>
				<tr><td rowspan="2" align="right">医保类型：</td></tr><tr><td align="left">
					<select id="drug_type">
						<option selected="selected">--请选择---</option>
						<option value="true">是</option>
						<option value="false">否</option>
					</select>
				</td></tr>
				<tr><td rowspan="2" align="right">最低价：</td></tr><tr><td align="left"><input type="text" id="beginPrice" /></td></tr>
				<tr><td rowspan="2" align="right">最高价：</td></tr><tr><td align="left"><input type="text" id="endPrice" /></td></tr>
			</table>
			<div><input style="margin-left:10px" type="submit" onclick="loadData()" value="搜索" id="send" /> <input type="reset" value="重置" id="reset" align="middle" /></div>
		</form>
	</div>
</body>
</html>