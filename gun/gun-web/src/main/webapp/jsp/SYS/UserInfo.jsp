<%@page import="com.gun.common.entity.pojo.SysUserInfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lottery</title>
<%-- <script type="text/javascript" src="${contextPath}/jedate/jedate.min.js"></script> --%>
<%@ include file="../common/meta.jsp" %>
<%@ include file="../common/css.jsp" %>
<%@ include file="../common/js.jsp" %>
<style type="text/css">
	.position-right{
		position: relative;
		right: 50px;
	}
</style>
<script type="text/javascript">
	$(function(){	
		$("#data_table_1").colResizable({
			liveDrag:true, 
			gripInnerHtml:"<div class='grip'></div>", 
			draggingClass:"dragging", 
			onResize:onSampleResized
		});
	});
	 /**
	  *分页方法
	  */
	 function changePage(page) {
	 	showLoading();
	 	$("#pageNo").val(page);
	 	$("#command").attr("action","<%=LotteryConstants.QUERY_USER_INFO%>.do").submit();
	 }
	 /**
	  *查询 
	  */
	  function queryUserInfo(isCleanPageNo) {
	   showLoading();
	   if(isCleanPageNo=='Y'){
	   		$("#pageNo").val(null);
	   }
	   $("#hiddenQueryUserAccount").val($("#queryUserAccount").val());
	   $("#hiddenQueryUserName").val($("#queryUserName").val());
	   $("#command").attr("action","<%=LotteryConstants.QUERY_USER_INFO%>.do").submit();
	   
	  }
	  /**
	  *检视/编辑/新增
	  */
	  function showEdit(obj,flag){
	  	if(flag=='V'){
	  		$(".modal-title").html("View user");
	  		$("#modalPasswordId").hide();
	  		$("#confirmId").hide();
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>").val(obj.userId);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>").attr("disabled",true);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>").val(obj.userName);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>").attr("disabled",true);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>").val(obj.telephone);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>").attr("disabled",true);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>").val(obj.email);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>").attr("disabled",true);
	  		$("#roleName").val(obj.roleId);
	  		$("#roleName").attr("disabled",true);
	  	}else if(flag=='E'){
	  		$(".modal-title").html("Edit user");
	  		$("#modalPasswordId").hide();
	  		$("#confirmId").show();
	  		var addFlag = $("#addFlag").val('N');
	  		$("#hidden<%=SysUserInfoDTO.ATTRIBUTE.ID.getValue()%>").val(obj.id);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>").val(obj.userId);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>").attr("disabled",false);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>").val(obj.userName);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>").attr("disabled",false);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>").val(obj.telephone);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>").attr("disabled",false);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>").val(obj.email);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>").attr("disabled",false);
	  		$("#hiddenRoleId").val(obj.roleId);
	  		$("#roleName").val(obj.roleId);
	  		$("#roleName").attr("disabled",false);
	  	}else{
	  		$(".modal-title").html("Add user");
	  		$("#modalPasswordId").show();
	  		$("#confirmId").show();
	  		var addFlag = $("#addFlag").val('Y');
	  		$("#hidden<%=SysUserInfoDTO.ATTRIBUTE.ID.getValue()%>").val(null);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>").val(null);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>").attr("disabled",false);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.PASSWORD.getValue()%>").val(null);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>").val(null);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>").attr("disabled",false);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>").val(null);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>").attr("disabled",false);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>").val(null);
	  		$("#<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>").attr("disabled",false);
	  		$("#hiddenRoleId").val(null);
	  		$("#roleName").val(null);
	  		$("#roleName").attr("disabled",false);
	  	}
	  }
	  
	  function setRole(value){
	  	$("#hiddenRoleId").val(value);
	  }
	  
	  /**
	  *用户信息保存
	  */
	  function onclickConfirm(){
	  var userAccount = $.trim($("#<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>").val());
	  var userName = $.trim($("#<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>").val());
	  var password = $.trim($("#<%=SysUserInfoDTO.ATTRIBUTE.PASSWORD.getValue()%>").val());
	  var telephone = $.trim($("#<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>").val());
	  var email = $.trim($("#<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>").val());
	  var roleValue = $("#roleName").val();
	  var addFlag = $("#addFlag").val();
	  if(isEmpty(userAccount)){
	  		Lobibox.alert('info', {
                    msg: "The account is required!"
            });
			$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>").focus();
			return false;
		}
	  var regPassword = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$)/);
	  if(addFlag=='Y'){
	  	if(isEmpty(password)){
	  		Lobibox.alert('info', {
                    msg: "The password is required!"
            });
			$("#<%=SysUserInfoDTO.ATTRIBUTE.PASSWORD.getValue()%>").focus();
			return false;
		} else if (password.length < 6) {
			Lobibox.alert('info', {
                msg: "The length of new password is 6 bits at least!"
        	});
			$("#<%=SysUserInfoDTO.ATTRIBUTE.PASSWORD.getValue()%>").focus();
			return false;
		} else if (!regPassword.test(password)) {
			Lobibox.alert('info', {
                msg: "The password must contain both numbers and letters!"
        	});
			$("#<%=SysUserInfoDTO.ATTRIBUTE.PASSWORD.getValue()%>").focus();
			return false;
		}
	  }
	   if(isEmpty(userName)){
	   		Lobibox.alert('info', {
                    msg: "The user name is required!"
            });
			$("#<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>").focus();
			return false;
		}
	  var regTelephone = /^([0-9]|[\-])+$/g ; 
	  if(!isEmpty(telephone)){
	  	if(telephone.length<7 || telephone.length>18){
	  		Lobibox.alert('info', {
                    msg: "The length of Telephone is limited from 7 to 18 bits!"
            });
		  	$("#<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>").focus();
			return false;
	  	}
	  	if(!regTelephone.test(telephone)){
	  		Lobibox.alert('info', {
                    msg: "Phone format error!"
            });
		  	$("#<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>").focus();
			return false;
	  	}
	  }
	  var regEmail = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; 
	  if(!isEmpty(email)){
	  	if(!regEmail.test(email)){
	  		Lobibox.alert('info', {
                    msg: "Email format error!"
            });
		  	$("#<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>").focus();
			return false;
	  	}
	  }
	  if(isEmpty(roleValue)){
	  		Lobibox.alert('info', {
                    msg: "The role name is required!"
            });
			$("#roleName").focus();
			return false;
		}
	  showLoading();
	  $.ajax({
	             type: "POST",
	             dataType: "json", //接受數據格式 
	             cache:false,
	             url: "<c:out value="${contextPath}"/>/saveSysUser.do",
	             data: $("#editUserInfoForm").serialize(),          
	             async: false,   
	             success: function(data) {
	             	if(data.success){
	             		completeLoading();
	             		$("#confirmId").attr("data-dismiss","modal");
	             		$("#messageCode").val(data.messageCode);
	             		queryUserInfo('N');
	             	}else{
	             		completeLoading();
	             		Lobibox.alert('warning', {
		                    msg: data.message
		                });
	             	}
	    		},
			    error: function(data) {
			    		  completeLoading();
			              Lobibox.alert('error', {
			                    msg: "Storage failure!"
			              });
			              return false;
			    }
	        });
	  }
	  
	  function deleteUser(id){
	  	Lobibox.confirm({
                    msg: "Confirm delete?",
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                            showLoading();
					  	 	$.ajax({
					             type: "POST",
					             dataType: "json", //接受數據格式 
					             cache:false,
					             url: "<c:out value="${contextPath}"/>/deleteSysUser.do",
					             data: {"ids":id},          
					             async: false,   
					             success: function(data) {
					             	if(data.success){
					             		completeLoading();
					             		$("#messageCode").val(data.messageCode);
					             		queryUserInfo('N');
					             	}else{
					             		completeLoading();
					             		Lobibox.alert('error', {
					                    msg: data.message
					                	});
					             	}
					    		},
							    error: function(data) {
							    		  completeLoading();
							    		  Lobibox.alert('error', {
						                   msg: "Delete failure!"
						                  });
							              return false;
							    }
					        });
                        }
                    }
                });
	  }
	</script>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="fixed-top">
<!-- BEGIN MOBILE NAVIGATION -->
<div class="wrapper">
<%@ include file="../common/mobileNavigationAndHeader.jsp"%>
<!-- END MOBILE NAVIGATION -->
<!-- BEGIN CONTAINER -->
<div id="container" >
<!-- BEGIN CONTENT -->
<div id="content"  class="row-fluid" role="main">
<!-- BEGIN PAGE -->
	<div id="body" style="height: 100%">
    <!-- BEGIN TICKER-->
    <!-- END TICKER-->
    <!-- BEGIN PAGE CONTAINER-->
    <div class="container-fluid" style="background-color: white;">
    <!-- BEGIN PAGE HEADER-->
    <div class="row-fluid">
	    <div class="span12">
		    <!-- BEGIN PAGE TITLE -->
		    <h1 class="page-title">　<i class="icon-dashboard"></i> User Management<small></small> </h1>
		    <!-- END PAGE TITLE -->
	    </div>
    </div>
    <!-- END PAGE HEADER-->

    <!-- BEGIN PAGE CONTENT-->
    <div id="page">
    	<%@ include file="../common/message.jsp" %>
	    <div class="row-fluid portlet">
			<div class="span12">
              <div class="widget">
                 <div class="widget-title">
                 	<h4><i class="icon-reorder"></i>Query Condition</h4>
                     <span class="tools"><a href="javascript:;" class="icon-chevron-up"></a></span>
                 </div>
                 <div class="widget-body">
                 <form class="form-horizontal" id="command" method="post">
                 <input type="hidden" id="pageNo" name="pageNo" value="${sysUserInfoForm.pageNo }">
                 <input type="hidden" id="messageCode" name="messageCode" value="">
                 <input type="hidden"  name="selectPageFuncId" value="${selectPageFuncId }">
                <div class="control-group">
					<label class="control-label">User Account</label>
					<div class="controls">
					<input type="hidden" id="hiddenQueryUserAccount"  name="queryUserAccount"  value="${sysUserInfoForm.queryUserAccount}"/>
					<input type="text" class="span3" id="queryUserAccount"  value="${sysUserInfoForm.queryUserAccount}" maxlength="23"/>
<!-- 					<p class="datep"><input class="datainp" id="dateinfo" type="text" placeholder="请选择"  readonly></p> -->
					</div>
				</div>	
				<div class="control-group">
					<label class="control-label">User Name</label>
					<div class="controls">
						<input type="hidden" id="hiddenQueryUserName"  name="queryUserName"  value="${sysUserInfoForm.queryUserName}"/>
						<input type="text" class="span3" id="queryUserName"  value="${sysUserInfoForm.queryUserName}" maxlength="23"/>
					</div>
				</div>
				 <div class="form-actions">
				 		<c:set value="${selectPageFuncId}_Query" var="queryRole"/>
				 		<c:if test="${fn:containsIgnoreCase(userManagementRole,queryRole)}">
                        <button type="button" onclick="queryUserInfo('Y')" class="btn btn-default btn-primary ">Query</button>
                        </c:if>
                        <c:set value="${selectPageFuncId}_Add" var="addRole"/>
                        <c:if test="${fn:containsIgnoreCase(userManagementRole,addRole)}">
                        <button type="button" onclick="showEdit()" class="btn btn-default btn-success " data-toggle="modal" data-placement="left" data-target="#editUserInfo">Add</button>
                 		</c:if>
                 </div>	
               </form>
              </div>
            </div>
           </div>
	     </div><!--row-fluid portlet-->
	     <c:if test="${fn:length(sysUserInfoForm.results)>0}">
	     <!-- 查詢結果部分 -->
	      <div class="row-fluid portlet">
            <div class="span12">
              <!-- BEGIN GRID PORTLET-->
              <div class="widget">
                <div class="widget-title">
                  <h4><i class="icon-reorder"></i>Query Result</h4>
				  <span class="tools"><a href="javascript:;"class="icon-chevron-up"></a></span>
                </div>
                <div class="widget-body">
                   <div class="cyberTable">
                       <table class="table-hover" id="data_table_1" width="100%" border="0" cellpadding="0" cellspacing="0">
                        <thead>
							<tr>
								<th style="text-align: center;" width="80px">No</th>
								<th style="text-align: center;" width="200px">Operation</th>
								<th style="text-align: center;" width="200px">User Account</th>
								<th style="text-align: center;" width="200px">User Name</th>
								<th style="text-align: center;" width="200px">Email</th>
								<th style="text-align: center;" width="200px">Telephone</th>
								<th style="text-align: center;" width="200px">Role Name</th>
								<th style="text-align: center;" width="200px">Last Login Time</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sysUserInfoForm.results }" var="userInfo" varStatus="row">
							<tr>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${(model.pageInfo.page*model.pageInfo.size) + row.index + 1 }</font> </label></td>
								<td style="text-align: center;" class="blue">
									<c:set value="${selectPageFuncId}_View" var="viewRole"/>
                        			<c:if test="${fn:containsIgnoreCase(userManagementRole,viewRole)}">
									<button class="btn popovers " data-trigger="hover"  data-toggle="modal" data-placement="left" data-target="#editUserInfo" data-content="View" 
									 onclick='showEdit(${cyber:toJsonString(userInfo)},"V")'>
									 <i class="icon-eye-open icon-white "></i></button>
									 </c:if>
									<c:set value="${selectPageFuncId}_Edit" var="editRole"/>
                        			<c:if test="${fn:containsIgnoreCase(userManagementRole,editRole)}">
									<button class="btn popovers btn-primary" data-trigger="hover" data-toggle="modal" data-placement="left" data-target="#editUserInfo" data-content="Edit"
									 onclick='showEdit(${cyber:toJsonString(userInfo)},"E")' >
									 <i class="icon-edit icon-white "></i>
									</button>
									</c:if>
									<c:set value="${selectPageFuncId}_Delete" var="deleteRole"/>
                        			<c:if test="${fn:containsIgnoreCase(userManagementRole,deleteRole)}">
									<button class="btn popovers btn-danger" data-trigger="hover" data-placement="left" data-content="Delete" 
									 onclick="deleteUser('${userInfo.id}')" >
									<i class="icon-remove icon-white "></i>
									</button>
									</c:if>
								</td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${userInfo.userId}</font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${userInfo.userName}</font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${userInfo.email}</font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${userInfo.telephone} </font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${userInfo.roleName}</font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${userInfo.lastLogintime} </font> </label></td>
							</tr>
							</c:forEach>
						</tbody>
                       </table>
                       <div class="pagination pagination-right" style="padding-bottom: 20px;">
							<ul>
								<%@ include file="/jsp/common/pageInfo.jsp"%> 
							</ul>
						</div>
                     </div>
                     </div>
                    </div>
                    </div>    
                   </div> 
             </c:if>
             <!-- 新增与编辑模态框 start-->
               <div  class="modal fade" id="editUserInfo" role="dialog"
                   style="display: none;position:fixed; top:30%;left: 50%" tabindex="-1" data-backdrop="static"
                   >
                   <div class="modal-dialog">
                   	<form class="form-horizontal" id="editUserInfoForm" method="post">
                   		<input type="hidden" name="<%=SysUserInfoDTO.ATTRIBUTE.ID.getValue()%>" id ="hidden<%=SysUserInfoDTO.ATTRIBUTE.ID.getValue()%>"/>
                   		<input type="hidden"  id ="addFlag"/>
                       <div class="modal-content" >
                           <div class="modal-header">
                               <button type="button" class="close" data-dismiss="modal"
                                   aria-hidden="true">&times;</button>
                               <h3 class="modal-title"></h3>
                           </div>
                           <!-- 隐藏域 -->
                           	<div class="modal-body">
		                       <div class="control-group position-right">
										<label class="control-label">User Account<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>"  name="<%=SysUserInfoDTO.ATTRIBUTE.USER_ID.getValue()%>" onkeyup="value=value.replace(/[\W]/g,'')"   maxlength="15"/>
									</div>
							   </div>
							   <div class="control-group position-right" id="modalPasswordId">
										<label class="control-label">Password<span class="red">*</label>
									<div class="controls">
										<input type="password" class="span12" id="<%=SysUserInfoDTO.ATTRIBUTE.PASSWORD.getValue()%>"  name="<%=SysUserInfoDTO.ATTRIBUTE.PASSWORD.getValue()%>"   maxlength="15"/>
									</div>
							   </div>
							   <div class="control-group position-right">
										<label class="control-label">User Name<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>"  name="<%=SysUserInfoDTO.ATTRIBUTE.USER_NAME.getValue()%>" onkeyup="value=stripscript(this.value)"  maxlength="20"/>
									</div>
							   </div>
							   <div class="control-group position-right">
										<label class="control-label">Telephone</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>"  name="<%=SysUserInfoDTO.ATTRIBUTE.TELEPHONE.getValue()%>"  maxlength="20"/>
									</div>
							   </div>
							   <div class="control-group position-right">
										<label class="control-label">Email</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>"  name="<%=SysUserInfoDTO.ATTRIBUTE.EMAIL.getValue()%>"  maxlength="80"/>
									</div>
							   </div>
							   <div class="control-group position-right">
										<label class="control-label">Role Name<span class="red">*</label>
									<div class="controls">
										<input type="hidden" name="<%=SysUserInfoDTO.ATTRIBUTE.ROLE_ID.getValue()%>" id="hiddenRoleId"/>
										<select class="span12" tabindex="1"  data-placeholder="Please select..." id="roleName"  onchange="setRole(this.value)">
			                               	<option value="">Please select...</option>
				                               	<c:forEach items="${roleList}" var="paramItem">
				                               			<option value="${paramItem.value}">${paramItem.name }</option>
				                               	</c:forEach>
						               </select>
									</div>
							   </div>
                           </div>
                           <div class="modal-footer">
                               <button type="button" id="confirmId" class="btn btn-success btn-default"
                                   onclick="onclickConfirm()">Save</button>
                               <button type="button"
                                   class="btn btn-warning  btn-default"
                                   data-dismiss="modal" id = "cancelModal">Cancel</button>
                           </div>
                       </div>
                       </form>
                   </div>
               </div>    
           <!-- 新增与编辑模态框 end-->
             
      </div>
        <!-- END PAGE CONTENT-->
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
	
	<%@ include file="../common/resPassWord.jsp" %>
	
	<!-- BEGIN JAVASCRIPTS -->
	
</body>
<!-- END BODY -->
</html>
