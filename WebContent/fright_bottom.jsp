<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function ToNewFile(){
		//fright_none.jsp下的getData方法
		var arr = window.parent.frames["rightframe"].window.getData();
		window.top.window.loadCenterData(arr);
	}
</script>
<body>
	<input onclick="ToNewFile()" style="margin-left: 500px;margin-right:20px;display: inline;" type="button" value="确认"><input type="button" value="取消">
</body>
</html>