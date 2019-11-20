<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<%
	UserSessionContext userSessionContext = (UserSessionContext)SessionManager.getUserSessionContext(request);
	userSessionContext.setSelectParentFuncid(null);
	userSessionContext.setSelectFuncId(null);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lottery</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta content="" name="description" />
<meta content="" name="author" />
<%@ include file="../common/css.jsp" %>
<%@ include file="../common/js.jsp" %>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
<!-- BEGIN MOBILE NAVIGATION -->
<div class="wrapper">
<%@ include file="../common/mobileNavigationAndHeader.jsp"%>
<!-- END MOBILE NAVIGATION -->
<!-- BEGIN CONTAINER -->
<div id="container">
<!-- BEGIN CONTENT -->
<div id="content" class="row-fluid" role="main">
<!-- BEGIN PAGE -->
	<div id="body" style="height: 100%" >
    <!-- BEGIN TICKER-->
    <!-- END TICKER-->
    <!-- BEGIN PAGE CONTAINER-->
    <div class="container-fluid">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
    <div class="span12">
    <!-- BEGIN PAGE TITLE -->
    <h1 class="page-title">　<i class="icon-home"></i> Home<small></small> </h1>
    <!-- END PAGE TITLE -->
    <canvas style="display:none;" id="can" width="230" height="200"></canvas>
	<div id="WaterMark"></div>
    </div>
    </div>
    <!-- END PAGE HEADER-->

    <!-- BEGIN PAGE CONTENT-->
    <form id="command" method="POST" class="form-horizontal">
    <div id="page">
    <div class="row-fluid portlet">


        </div><!--row-fluid portlet-->

        </div>
        <!-- END PAGE CONTENT-->
        </form>
   	</div>
        <!-- END PAGE CONTAINER-->
	</div>
	<!-- END PAGE -->
	</div>
	<!-- END CONTENT -->
    <!-- BEGIN SIDEBAR -->
   	<%@include file="../common/functionTree.jsp"%>
    <!-- END SIDEBAR -->
    <div class="clearfix"></div>
  	</div>
	<!-- BEGIN FOOTER -->
	<%@include file="../common/footer.jsp"%>
	</div>
	<!-- END FOOTER -->
	<%@ include file="../common/message.jsp" %>
	<!-- BEGIN JAVASCRIPTS -->
	<%@ include file="../common/resPassWord.jsp" %>
	<script type="text/javascript">
	
		(function() {
			var myDate = new Date();
			 
			$('#WaterMark').css({
		        "position": "absolute",
		        "left": "0",
// 		        "height": "800px",
		        "height": $(document).height()*0.85,
		        "opacity": "1",
		        "width": "100%",
		        "z-index": "1"
		    });
			var canvas = document.getElementById('can');
			if(!canvas.getContext) {
				//你的浏览器不支持canvas!
				return;
			}
			ctx = canvas.getContext('2d');
			ctx.font="20px Colonna MT";
			ctx.rotate(-45*Math.PI/180);
			ctx.fillStyle = 'rgba(0, 0, 0, 0.3)';
			ctx.fillText("LOTTERY",-20,150)
			ctx.font="15px Consolas Bold";;
			ctx.fillStyle = 'rgba(0, 0, 0, 0.3)';
			ctx.fillText("<%=userSession.getUserId().toUpperCase()%>",-20,175);
			var year = myDate.getFullYear();
			var month = myDate.getMonth()+1;
			var day = myDate.getDate();
			if(month<10){
				if(day<10){
					ctx.fillText(year+"/"+"0"+month+"/"+"0"+day,-20,200);
				}else{
					ctx.fillText(year+"/"+"0"+month+"/"+day,-20,200);
				}
			}else{
				if(day<10){
					ctx.fillText(year+"/"+month+"/"+"0"+day,-20,200);
				}else{
					ctx.fillText(year+"/"+month+"/"+day,-20,200);
				}
			}
			document.getElementById('WaterMark').style.backgroundImage = 'url("' + ctx.canvas.toDataURL() + '")';
		})();
	</script>
</body>
<!-- END BODY -->
</html>
