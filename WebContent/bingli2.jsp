<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
</head>
<script>
/* window.setTimeout(function(){ 
	var hash = location.hash; 
	$.ajax({
		url:'${pageContext.request.contextPath}/AjaxCase_bingli_list',
		data:{"reg_id":hash},
		dataType:"json",
		async:false,
		cache:false
	})
	
},2000); */ 

$(function(){ 
	var firstArr = []; 
	var secondArr = []; 
	var thirdArr = []; 
	
	
	//alert('主页面设置我（IframeB）的src，通过Hash（#）给我传递它收到的高度：' + location.hash); 
	var s = location.hash.split('#')[1];
	//alert(s);
	var reg_id = parseFloat(s);
	$.ajax({
		url:'${pageContext.request.contextPath}/AjaxDiagnose_list',
		data:{"reg_id":reg_id},
		dataType:"json",
		async:false,
		cache:false
	})
	.done(function(response){
		console.log("successBingli"+JSON.stringify(response));
		console.log(response);
		for(var i = 0; i<response.first.length ; i++){
			firstArr.push({
				reg_patientName:response.first[i].reg_patientName,
				reg_date:response.first[i].reg_date
			})
		}
		$('#firstPatient').html(firstArr[0].reg_patientName);
		$('#firstDate').html(firstArr[0].reg_date);
		
		var diag = "";
		for(var i = 0; i<response.second.length ; i++){
			secondArr.push({
				dis_diagnose:response.second[i].dis_diagnose,
				dis_doctor:response.second[i].dis_doctor
			})
			diag = diag +"<br/>"+ (i+1) + "." + secondArr[i].dis_diagnose + "--诊断人:" + response.second[i].dis_doctor;
		}
		$('#secondDiagnose').html(diag);
		
		var drug = "";
		var totalPrice = 0;
		for(var i = 0; i<response.third.length ; i++){
			thirdArr.push({
				pre_totalCount:response.third[i].pre_totalCount,
				pre_drug_name:response.third[i].pre_drug_name,
				pre_drug_price:response.third[i].pre_drug_price
			})
			drug = drug + "<br/>"+ (i+1) + "." + thirdArr[i].pre_drug_name + " " + thirdArr[i].pre_totalCount;
			//匹配 “1盒”、“1片”等，取出其中的数字
			var str = thirdArr[i].pre_totalCount;
			var n = str.replace(/\D+/i,"");
			if(thirdArr[i].pre_drug_price!=""&&thirdArr[i].pre_totalCount!=""){
				totalPrice = totalPrice + parseInt(thirdArr[i].pre_drug_price)*parseInt(n);
			}
		}
		$('#thirdtotalPrice').html(totalPrice);
		$('#thirddrugName').html(drug);
	})
	.fail(function(){
		console.log("bingli.jspError");
	})
});  
	
</script>
<body>
	病人<span id="firstPatient" style=""></span> 于 <span id="firstDate"></span> 前来我院
	就诊，其诊断为:<span id="secondDiagnose" style=""></span>
	<br/><br/>经医生诊断后，决定开定处方为:<span id="thirddrugName" style=""></span>,
	<br/><br/>总计<span id="thirdtotalPrice" style=""></span>元,
	<br/>请病人按照医生开药的使用方法进行疾病的治疗。
</body>
</html>