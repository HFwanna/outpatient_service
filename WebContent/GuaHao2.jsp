<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <input type="hidden" id="param" value="<s:property value="success" />">
    	<h1>挂号系统</h1>
    	<form action="${pageContext.request.contextPath}/GuaHaoAction_save" method="post">
	    	病人姓名：<input name="reg_patientName" type="text" />
	    	诊疗卡号：<input name="reg_card" type="text" />
	    	病人性别：<input name="reg_sex" type="text" />
	    	病人年龄：<input name="reg_age" type="text" />
	    	联系电话：<input name="reg_phone" type="text" />
	    	科室：<input name="reg_diseaseType" type="text" />
	    	<input type="submit" value="提交" />
    	</form>
 	</body>
</html>
<script>
	window.onload=function(){
       // var reg = new RegExp("?success=true"); //构造一个含有目标参数的正则表达式对象
       // var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        
       // if (r != null){
    	   //获取url?后面内容
    	   var url = location.search;
    	   if(url.indexOf("?")!=-1){
    		  // if(${param.success}!=null&&${param.success}==true){
    			//截取？后面内容
    			var str = url.substr(1);
    			if(str == "success"){
    				alert("保存成功！");
    			}else if(str == "fail"){
    				alert("保存失败！")
    			}
    		}
	   			
	    		
	    		
	    /* 		try{}
    	   catch (e) {  
    	        alert("保存失败！");  
    	   }  */
			
       // }
	}
</script>