<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<!-- <frameset rows="80px,*" frameborder="no" border="1" framespacing="0">
	<frame src="ftop.jsp" name="top" scrolling="no"></frame>
	<frameset cols="300px,*" noresize="noresize" frameborder="0">
		<frame src="fleft.jsp" name="left" scrolling="no"></frame>
		<frame src="fright.jsp" name="right" scrolling="no"></frame>
	</frameset>
</frameset> -->
<script type="text/javascript">
	function loadCenterData(arr){
		window.opener.window.loadCenterData(arr);
	}
</script>
<frameset rows="7%,*" rameborder="no" border="1" framespacing="0">
    <!--头部-->
    <frame src="ftop.jsp" name="topframe" scrolling="no" noresize="noresize" frameborder="1" />
    <!--主体部分-->
    <frameset cols="27%,*">
    	<!--主体左边-->
        <frame src="fleft.jsp" name="leftframe" id="leftframe" scrolling="no" noresize="noresize" frameborder="1"/>
		<!--主体右边-->
		<frameset rows="90%,*">
	        <frame src="fright_none.jsp" name="rightframe" id="rightframe" scrolling="yes" noresize="noresize" frameborder="1"/>
	        <frame src="fright_bottom.jsp" name="bottomframe" id="bottomframe" scrolling="no" noresize="noresize" frameborder="1" />
        </frameset>
    </frameset> 
</frameset>
</html>