<%@page import="com.gun.common.utils.LotteryConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp" %>
<%@ page import="com.gun.server.controller.utils.SessionManager"%>
<%@ page import="com.gun.common.pojo.UserSessionContext"%>
<%@ page import="java.util.Calendar"%>
<script type="text/javascript" src="${contextPath}/assets/js/respond.js"></script>
<script type="text/javascript">
	function logout(){
		top.location.href = '${contextPath}/logout.do';
	}
</script>

<% 
	UserSessionContext userSession = (UserSessionContext)SessionManager.getUserSessionContext(request);
	if (userSession == null){
		userSession = new UserSessionContext();
	}
	Calendar cal = Calendar.getInstance();
%>
  <!-- BEGIN MOBILE NAVIGATION -->
  <form id="logoutForm" method="post">
  <div id="sidr"> 
    <!-- BEGIN PROFILE BOX -->
            <div class="profile-box">
              <div class="profile-identity"> <span class="profile-badge"><img src="${contextPath}/assets/img/profile-badge-blue.png" alt="Profile Badge"/></span> <span class="profile-name">Hi！<%=userSession.getAliasName()%></span> </div>
              <div class="profile-content" style="color: white;">
              		<span>User Account：<%=userSession.getUserId()%></span><br/>
                	<span style="position: relative;top: 10px">Logon Time：<fmt:formatDate value="<%=cal.getTime()%>" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                </div>
            </div>
            <div class=""><%--class="profile-function" --%>
            	<div class="btn-group" align="center" style="margin-top: 15px">
	              	<button type="button" class="btn  btn-danger" onclick="openResPassword();"><i class="icon-cog"></i> Reset password</button>
	              	<button type="button" class="btn  btn-warning" onclick="logout();"><i class=" icon-unlock icon-white"></i> Account logout</button>
              	</div>
            </div>
  </div>
  <!-- END MOBILE NAVIGATION --> 
  
  
  <!-- BEGIN HEADER -->
  <div id="header" role="banner" class="navbar navbar-inverse navbar-fixed-top"> 
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="navbar-inner">
      <div class="mobile-nav"><a id="m-menu" class="btn mobile-toggler" href="#sidr"><i class="icon-reorder"></i></a></div>
      <!-- BEGIN BRAND --> 
      <a class="brand" href="javascript:logoClick()"><img src="${contextPath}/assets/img/logo.png" alt="Logo"/></a> 
      <!-- END BRAND --> 
      <span class="app-name hidden-phone">Lottery System</span> 
      <!-- BEGIN TOP NAVIGATION MENU -->
      <ul id="top_nav" class="nav pull-right">
<!-- 		<li class="dropdown" id="operationManualDownload" style="cursor:pointer;position: relative;bottom: 5px" onclick="operationManualDownload();"> -->
<!--         	<a  align="center"> -->
<%--         		<img src="${contextPath}/assets/img/icons/color/upcoming-work.png"> <span class="hidden-phone">操作手冊下載&nbsp;&nbsp;</span> --%>
<!--         	</a> -->
<!--         </li> -->

        <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-user white-shadow hidden-tablet"></i> <span class="hidden-phone">Account Management</span> <b class="caret hidden-phone"></b> </a>
          <div class="dropdown-menu"> 
            <!-- BEGIN PROFILE BOX -->
            <div class="profile-box">
              <div class="profile-identity"> <span class="profile-badge"><img src="${contextPath}/assets/img/profile-badge-blue.png" alt="Profile Badge"/></span> <span class="profile-name">Hi！<%=userSession.getAliasName()%></span> </div>
              <div class="profile-content" style="color: blue;">
              		<span>User Account：<%=userSession.getUserId()%></span><br/>
                	<span style="position: relative;top: 10px">Logon Time：<fmt:formatDate value="<%=cal.getTime()%>" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                </div>
            </div>
            <div class=""><%--class="profile-function" --%>
            	<div class="btn-group" align="center" style="margin-top: 15px">
	              	<button type="button" class="btn  btn-danger" onclick="openResPassword();"><i class="icon-cog"></i> Reset password</button>
	              	<button type="button" class="btn  btn-warning" onclick="logout();"><i class=" icon-unlock icon-white"></i> Account logout</button>
              	</div>
            </div>
            <!-- END PROFILE BOX --> 
          </div>
        </li>
        <!-- END PROFILE DROPDOWN -->
      </ul>
      <!-- END TOP NAVIGATION MENU --> 
    </div>
    <!-- END TOP NAVIGATION BAR --> 
  </div>
  <!-- END HEADER --> 
  	<script type="text/javascript">
		function logoClick(){			
			$("#logoutForm").attr("action","${contextPath}/<%=LotteryConstants.FIRST_PAGE_INIT%>.do").submit();
		}

		// 操作手冊下載
		function operationManualDownload(){
// 			$.blockUI({ message: '<div>準備下載中，請稍後...</div>'});
// 			$("#logoutForm").attr("action","operationManualDownload.do").submit();
// 			ControlPrintEnable();
		}
		//重置密码
		function openResPassword(){
			$("#resPassword").css("display","block")
		}
	</script>
	
 </form> 
 
 
 
 