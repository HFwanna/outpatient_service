<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/templatemo_main.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.js"></script>
	<script type="text/javascript">
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
		    			}else if(str == "none"){
		    				alert("查询内容不存在！");
		    			}
		    		}
			 }
		   	function callSB(){
		   		
		   	}
   			function searchB(){
   				//查询操作，设置查询开关打开
   				$("#search").val(1);
   			}
   			function save(){
   				//保存操作，设置查询开关关闭
   				$("#search").val(0);
   				//往表单内添加隐藏域<input type="hidden" name="reg_id" value="#registration.reg_id">
   				var inputID = $("#inputID").val();
   				$("#addOrModify").html("<input type='hidden' name='reg_id' value='"+ inputID +"'>")
   			}
	   		 function reSet() {
	             var tagName = document.getElementById('form').getElementByTagName("input");
	             //alert(trEle);
	             for (var i = 0; i< tagName.length; i++) {
	            	 tagName[i].innerHTML="";
	             }
	         }
	</script>
</head>
<body>
	<div class="navbar navbar-inverse" role="navigation">
      <div class="navbar-header">
        <div class="logo"><h1>挂号系统</h1></div>
      </div>   
    </div>
    <div class="template-page-wrapper">
    <div class="navbar-collapse collapse templatemo-sidebar">
        <ul class="templatemo-sidebar-menu">
        	<!--
            	作者：offline
            	时间：2018-05-17
            	描述：搜索
            -->
          <li>
            <form class="navbar-form">
              <input type="text" class="form-control" id="templatemo_search_box" placeholder="Search...">
              <span class="btn btn-default">Go</span>
            </form>
          </li>
          <!--
          	作者：offline
          	时间：2018-05-17
          	描述：
          -->
          <li class="active"><a href="#"><i class="fa fa-home"></i>现场挂号</a></li>
          <li><a href="#"><i class="fa fa-cubes"></i>预约</a></li>
          <li><a href="#"><i class="fa fa-cubes"></i>预约登记</a></li>
        </ul>
      </div><!--/.navbar-collapse -->
      
    <!--<div class="container">
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
            <img src="img/logo2.png">
        </div>
        <div class="col-lg-5 col-md-4 col-sm-6 hidden-xs">
            <img src="img/header.png">
        </div>
        <div class="col-lg-3 col-md-4 col-sm-12 col-xs-12" style="padding-top: 15px">
            <a href="#">登录</a>
            <a href="#">注册</a>
            <a href="#">购物车</a>
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><h1 align="center" style="background-color: ;">挂号系统</h1></div>
    </div>-->
 <div class="templatemo-content-wrapper">
        <div class="templatemo-content">
            <div class="container-fluid">
                <nav class="navbar navbar-inverse" role="navigation">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="#">排班搜索</a>
                        </div>

                        <!-- Collect the nav links, forms, and other content for toggling -->
                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <form class="navbar-form navbar-right">
                            	<div class="form-group">
                            		<span style="color: white;">医生：</span><input type="text" class="form-control" placeholder="医生">
                            	</div>
                            	<div class="form-group">
                            		<span style="color: white;">日期：</span><input type="text" class="form-control" placeholder="日期">
                            	</div>
                                <div class="form-group">
                                    <span style="color: white;">科室：</span><input type="text" class="form-control" placeholder="科室">
                                </div>
                                &nbsp;&nbsp;&nbsp;&nbsp;<button type="submit" class="btn btn-default">搜索</button>
                            </form>
                            <!--<ul class="nav navbar-nav navbar-right">-->
                                <!--<li><a href="#">Link</a></li>-->
                                <!--<li class="dropdown">-->
                                    <!--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>-->
                                    <!--<ul class="dropdown-menu">-->
                                        <!--<li><a href="#">Action</a></li>-->
                                        <!--<li><a href="#">Another action</a></li>-->
                                        <!--<li><a href="#">Something else here</a></li>-->
                                        <!--<li role="separator" class="divider"></li>-->
                                        <!--<li><a href="#">Separated link</a></li>-->
                                    <!--</ul>-->
                                <!--</li>-->
                            <!--</ul>-->
                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>
            </div>
            <!-- 隐藏域，用于更新或则添加操作 -->
            	<input type="hidden" id="inputID" name="reg_id" value="<s:property value="#registration.reg_id" />">
        		<form  id="form" class="form-horizontal" style="margin: 30px 0 0 0;"  onsubmit="return true" action="${pageContext.request.contextPath}/GuaHaoAction_save" method="post">
        		<!-- js往里面添加隐藏域 -->
        			<div id="addOrModify"></div>
        			<div class="form-group">
        				<label for="inputEmail3" class="col-sm-2 control-label">病人姓名</label>
        				<div class="col-sm-3">
        					<input type="text" class="form-control" value="<s:property value="#registration.reg_patientName" />" name="reg_patientName" id="reg_patientName" placeholder="病人姓名">
        				</div>
        				<label for="reg_card" class="col-sm-2 control-label">诊疗卡号</label>
        				<div class="col-sm-3">
        					<input type="text" class="form-control" value="<s:property value="#registration.reg_card" />" name="reg_card" id="reg_card" placeholder="诊疗卡号">
        				</div>
        			</div>
        			<div class="form-group">
        				<label for="reg_sex" class="col-sm-2 control-label">性别</label>
        				<div class="col-sm-3">
        						<select class="form-control" name="reg_sex" id="reg_sex">
        							<option>--- 请选择 ---</option>
        							<option value="1" <s:property value="#registration.reg_sex==true?'selected':''"/>>男</option>
        							<option value="0" <s:property value="#registration.reg_sex==false?'selected':''"/>>女</option>
        						</select>
        				</div>
        				<label for="reg_age" class="col-sm-2 control-label">年龄</label>
        				<div class="col-sm-3">
        					<input type="text" class="form-control" value="<s:property value="#registration.reg_age" />"  name="reg_age" id="reg_age" placeholder="年龄">
        				</div>
        			</div>
        			<div class="form-group">
        				<label for="reg_phone" class="col-sm-2 control-label">联系电话</label>
        				<div class="col-sm-3">
        					<input type="text" class="form-control" value="<s:property value="#registration.reg_phone" />" name="reg_phone" id="reg_phone" placeholder="联系电话">
        				</div>
        				<label for="reg_diseaseType" class="col-sm-2 control-label">科室</label>
        				<div class="col-sm-3">
        					<select class="form-control"   name="reg_diseaseType" id="reg_diseaseType">
        							<option selected="selected">--- 请选择 ---</option>
        							<option <s:property value="#registration.reg_diseaseType=='神经科'?'selected':''"/>>神经科</option>
        							<option <s:property value="#registration.reg_diseaseType=='皮肤科'?'selected':''"/>>皮肤科</option>
        							<option <s:property value="#registration.reg_diseaseType=='泌尿科'?'selected':''"/>>泌尿科</option>
        							<option <s:property value="#registration.reg_diseaseType=='肠胃科'?'selected':''"/>>肠胃科</option>
        							<option <s:property value="#registration.reg_diseaseType=='妇科'?'selected':''"/>>妇科</option>
        					</select>
        					<!--
                            	作者：offline
                            	时间：2018-05-17
                            	描述：flag
                            -->
        					<input type="hidden" id="search" name="flag" value="" />
        				</div>
        			</div>
        			<div class="form-group">
        				<div class="col-sm-offset-7 col-sm-10">
        					<button type="submit" class="btn btn-default" onclick="searchB()">查询</button>
        					<button type="submit" class="btn btn-default"" onclick="save()">保存/修改</button>
        					<button type="reset" class="btn btn-default" onclick="reSet()">重置</button>
        				</div>
        			</div>
        		</form>
        	<!--
            	***************************************************************************
            -->	
        		<div role="separator" class="divider">---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</div>
        		<table class="table table-hover">
        			<thead style="font-size: large;"><b><font size="4"><tr><td>科室</td><td>医生</td><td>剩余号源</td></tr></font></b></thead>
  					<tbody id="tbody">
  						<tr><td>科室一</td><td>王医生</td><td>22</td></tr>
  						<tr><td>科室二</td><td>李教授</td><td>18</td></tr>
  					</tbody>
				</table>
        </div>
      </div>
      <!-- Modal -->
      
      <footer class="templatemo-footer">
        <div class="templatemo-copyright">
          <p>Copyright &copy; GuaHaoV1.0</p>
        </div>
      </footer>
    </div>
    

</body>
</html>
