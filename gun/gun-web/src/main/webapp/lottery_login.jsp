<%@page import="com.gun.common.utils.LotteryMessageCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.gun.common.utils.LotteryConstants"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<%
	String locale=request.getLocale().toString();
	Object obj =  request.getSession().getAttribute(LotteryMessageCode.ILLEGAL_OPERATION_FAILED);
	Object obj2 =  request.getSession().getAttribute("sessionAlreadyExists");
	String errorMesage =""; 
	String errorMesage2 ="";
	if(obj!=null){
		errorMesage = obj.toString();
	}
	if(obj2!=null){
		errorMesage2 = obj2.toString();
	}
%>

<head>
<c:set var="errorMesage" value="<%=errorMesage %>"/>
<c:set var="errorMesage2" value="<%=errorMesage2 %>"/>
<title>
<c:choose>
	<c:when test="${empty errorMesage2 }">
		User Login
	</c:when>
	<c:otherwise>
		Lottery
	</c:otherwise>
</c:choose>
</title>
<%@ include file="../jsp/common/meta.jsp" %>
<%@ include file="../jsp/common/css.jsp" %>
<link rel="stylesheet" type="text/css" href="css/login.css" />
<script type="text/javascript" src="${contextPath}/assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/lottery_utilities.js"></script>
<script type="text/javascript" src="${contextPath}/js/base-loading.js"></script>
<script type="text/javascript" src="${contextPath}/assets/bootstrap/lobibox/Lobibox.min.js"></script>
<script type="text/javascript">
	history.pushState(null, null, document.URL);
        window.addEventListener('popstate', function () {
            history.pushState(null, null, document.URL);
        });
	jQuery(document).ready(function() {
		if(!isEmpty('${errorMesage}')){
			Lobibox.alert('info', {msg: '${errorMesage}'});
		}
	});
</script>
<%
	request.getSession().setAttribute(LotteryMessageCode.ILLEGAL_OPERATION_FAILED, "");
%>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
<c:choose>
	<c:when test="${empty errorMesage2 }">
		<!-- BEGIN WRAPPER -->
<div class="wrapper"> 
  <%@ include file="../jsp/common/message.jsp" %>
<div class="login">
<form id="loginform" name="loginform" action="<%=LotteryConstants.AP_NAME%>login.do" METHOD="POST" >
	<input type="hidden" name="locale" value="<%=locale%>"/>
	<div class="logo"></div>
    <div class="login_form">
    	<div class="user">
        	<input class="text_value" id="userAccount" name="userAccount" type="text"  placeholder="UserAccount">
            <input class="text_value" id="passWord" name="passWord" type="password"  placeholder="Password">
        </div>
        <button class="button"  onclick="submitPassword()" type="button">登录</button>
    </div>
    
    <div id="tip"></div>
    <div class="foot">
		COPYRIGHT© 2017 MOEYAN .CO. ALL RIGHTS RESERVED.
    </div>
    </form>
</div>
</div>

<script>

		jQuery(document).ready(function() {
			 $("#loginform input").keypress(function(event){ 
			        if (event.keyCode == 13){
			        	submitPassword();
			        }
			    });
		});
		
		
		function submitPassword(){
			var aliasName = $.trim($("#userAccount").val());
			var password  = $.trim($("#passWord").val());
			if(aliasName=='' || aliasName == 'UserName') {
				Lobibox.alert('info', {
                    msg: "please enter account!"
            });
				$("#userAccount").blur();
				$("#passWord").blur();
				return false;
			}
			if(password == ''){
				Lobibox.alert('info', {
                    msg: "please enter password!"
            	});
				$("#userAccount").blur();
				$("#passWord").blur();
				return false;
			}
			showLoading();
			document.loginform.submit();
		}
    </script>
	</c:when>
	<c:otherwise>
		<span style="margin-left: 20px;margin-top:15px;color: white;font-size: 24px;">
			<img src="images/warning.png" width="60" height="60">
			You can already sign in Lottery on another browser tab. If so, close the tab and return to the other tabs. If this does not work, you can try:
		</span>
		<br>
		<table style="border: 0;color: white;margin-left: 100px;margin-top: 30px;">
			<tr>
				<td>-Close the browser window and log in again.</td>
			</tr>
			<tr>
				<td>-Remove the cookie from the browser and log in again.</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
</body>
<!-- END BODY -->
</html>