<%@page import="com.gun.common.entity.pojo.RoleAuthorityDTO"%>
<%@page import="com.gun.common.entity.pojo.FunctionTypeDTO"%>
<%@page import="com.gun.common.entity.pojo.RoleDefDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lottery</title>
<%@ include file="../common/meta.jsp" %>
<%@ include file="../common/css.jsp" %>
<%@ include file="../common/js.jsp" %>
<style type="text/css">
	.position-right{
		position: relative;
		right: 50px;
	}
	.selectTr{
		background-color: #FFEEBC;
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
		$("#data_table_2").colResizable({
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
	 	$("#command").attr("action","<%=LotteryConstants.INIT_ROLE%>.do").submit();
	 }
	 /**
	  *查询 
	  */
	  function queryRoleInfo(isCleanPageNo) {
	   showLoading();
	   if(isCleanPageNo=='Y'){
	   		$("#pageNo").val(null);
	   }
	   $("#command").attr("action","<%=LotteryConstants.INIT_ROLE%>.do").submit();
	  }
	  /**
	  *编辑/新增
	  */
	  function showEdit(obj,flag){
	  	if(flag=='E'){
	  		$(".modal-title").html("Edit Role");
	  		$("#<%=RoleDefDTO.ATTRIBUTE.ROLE_ID.getValue()%>").val(obj.value);
	  		$("#<%=RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue()%>").val(obj.name);
	  	}else{
	  		$(".modal-title").html("Add Role");
	  		$("#<%=RoleDefDTO.ATTRIBUTE.ROLE_ID.getValue()%>").val(null);
	  		$("#<%=RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue()%>").val(null);
	  	}
	  }
	  
	  /**
	  *role 保存
	  */
	  function onclickConfirm(){
	  	var roleName  = $.trim($("#<%=RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue()%>").val());
	  	if(isEmpty(roleName)){
	  		Lobibox.alert('info', {
                    msg: "The Role Name is required!"
            });
            $("#<%=RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue()%>").focus();
            $("#<%=RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue()%>").blur();
			return false;
		}
		showLoading();
	  	$.ajax({
	             type: "POST",
	             dataType: "json", //接受數據格式 
	             cache:false,
	             url: "<c:out value="${contextPath}"/>/saveRoleDef.do",
	             data: $("#editRoleDefForm").serialize(),          
	             async: true,   
	             success: function(data) {
	             	if(data.success){
	             		completeLoading();
	             		$("#confirmId").attr("data-dismiss","modal");
	             		$("#messageCode").val(data.messageCode);
	             		queryRoleInfo('N');
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
	  
	function deleteRoleDef(id){
		Lobibox.confirm({
                    msg: "Confirm delete?",
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                            showLoading();
					  	 	$.ajax({
					             type: "POST",
					             dataType: "json", //接受數據格式 
					             cache:false,
					             url: "<c:out value="${contextPath}"/>/deleteRoleDef.do",
					             data: {"roleId":id},          
					             async: false,   
					             success: function(data) {
					             	if(data.success){
					             		completeLoading();
					             		$("#messageCode").val(data.messageCode);
					             		queryRoleInfo('N');
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
     //全选 
	function chekboxAllChecked(allCheckBoxId,className){
		var isChecked = $('#'+allCheckBoxId).is(':checked');
		$("."+className).each(function(){
		     if(isChecked){
		     	$(this).attr("checked",true)
		    	$(this).parent("span").addClass("checked");
		   }else{
		    	$(this).attr("checked",false);
		    	$(this).parent("span").removeClass("checked");
		   }
		 });
	}
	
	//取消全选
	function subCheckBox(parentCheckBoxId,className){
		var isAllChecked = true;
		$("."+className).each(function(){
		     if(!$(this).is(':checked')){
		    	isAllChecked = false;
		    	return false;
		   }
		 });
		 if(isAllChecked){
		 	$("#"+parentCheckBoxId).attr("checked",true)
		    $("#"+parentCheckBoxId).parent("span").addClass("checked");
		 }else{
		 	$("#"+parentCheckBoxId).attr("checked",false)
		     $("#"+parentCheckBoxId).parent("span").removeClass("checked");
		 }
	}
	
	
	var trIdFlag="";
	function selectTr(trId,roleId){
	  		if(!isEmpty(trIdFlag)){
	  			$('#'+trIdFlag+" td").removeClass("selectTr");	
	  		}
	  		trIdFlag = trId;
	  		$('#hidden<%=RoleAuthorityDTO.ATTRIBUTE.ROLE_ID.getValue() %>').val(roleId);
          	$('#'+trId+" td").addClass("selectTr");
          	showLoading();
	  		$.ajax({
	             type: "POST",
	             dataType: "json", //接受數據格式 
	             cache:false,
	             url: "<c:out value="${contextPath}"/>/queryUserRole.do",
	             data: {"roleId":roleId},          
	             async: false,   
	             success: function(data) {
	             	if(data.success){
	             		$(":checkbox").attr("checked", false);
		             	$(":checkbox").parent("span").removeClass("checked");
		             	var roleList = data.result;
		            	$.each(roleList,function() {
		            		$("#"+this.functionId).attr("checked", true);
		            		$("#"+this.functionId).parent("span").addClass("checked");
		             	});
		             	checkSelectAll();
		             	completeLoading();
	             	}else{
	             		completeLoading();
	             		Lobibox.alert('warning', {
		                    msg: data.message
		                });
	             	}
	    		},
			    error: function(data) {
			    		  Lobibox.alert('error', {
			                    msg: "Data acquisition failed!"
			              });
			              completeLoading();
			              return false;
			    }
	        });
          }
    
	function saveRole(){
		if(isEmpty(trIdFlag)){
	  		Lobibox.alert('info', {
                    msg: "In the list of roles, select the object you want to set!"
            });
            return false;
	  	}
	  	showLoading();
	  	$.ajax({
	             type: "POST",
	             dataType: "json", //接受數據格式 
	             cache:false,
	             url: "<c:out value="${contextPath}"/>/saveRoleAuthority.do",
	             data: $("#functionListForm").serialize(),          
	             async: false,   
	             success: function(data) {
	             	if(data.success){
	             		completeLoading();
	             		Lobibox.alert('success', {
                   			 msg: data.message
            			});
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
	
	function checkSelectAll(){
		if($(".functionName").size()>0){
			subCheckBox('function_name_all_checkbox','functionName');
		}
		if($(".queryAll").size()>0){
			subCheckBox('queryTh','queryAll');
		}
		if($(".addAll").size()>0){
			subCheckBox('addTh','addAll');
		}
		if($(".editAll").size()>0){
			subCheckBox('editTh','editAll');
		}
		if($(".deleteAll").size()>0){
			subCheckBox('deleteTh','deleteAll');
		}
		if($(".viewAll").size()>0){
			subCheckBox('viewTh','viewAll');
		}
		if($(".importAll").size()>0){
			subCheckBox('importTh','importAll');
		}
		if($(".exportAll").size()>0){
			subCheckBox('exportTh','exportAll');
		}
		if($(".approvalAll").size()>0){
			subCheckBox('approvalTh','approvalAll');
		}
		if($(".saveAll").size()>0){
			subCheckBox('saveTh','saveAll');
		}
		if($(".accountStatusAll").size()>0){
			subCheckBox('accountStatusTh','accountStatusAll');
		}
	}
	function ClearSubmit(e) {
        if (e.keyCode == 13) {
            return false;
        }
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
		    <h1 class="page-title">　<i class="icon-dashboard"></i> Role Management <small></small> </h1>
		    <!-- END PAGE TITLE -->
	    </div>
    </div>
    <!-- END PAGE HEADER-->

    <!-- BEGIN PAGE CONTENT-->
    <div id="page">
    	<%@ include file="../common/message.jsp" %>
    	<form class="form-horizontal" id="command" method="post">
           <input type="hidden" id="pageNo" name="pageNo" value="${systemForm.pageNo }">
           <input type="hidden" id="messageCode" name="messageCode" value="">
           <input type="hidden"  name="selectPageFuncId" value="${selectPageFuncId }">
        </form>
        
	     <!-- 查詢結果部分 -->
	      <div class="row-fluid portlet">
            <div class="span12">
              <!-- BEGIN GRID PORTLET-->
              <div class="widget">
                <div class="widget-title">
                  <h4><i class="icon-reorder"></i>Role List</h4>
                  <c:set value="${selectPageFuncId}_Add" var="addRole"/>
                  <c:if test="${fn:containsIgnoreCase(roleManagementRole,addRole)}">
                  <button class="btn popovers btn-success" data-trigger="hover" data-toggle="modal" data-placement="left" data-target="#editRoleDef" data-content="Add" 
					style="position: relative;top:8px;" onclick="showEdit()" >
					<i class=" icon-plus icon-white "></i>
				  </button>
				  </c:if>
				  <span class="tools"><a href="javascript:;"class="icon-chevron-up"></a></span>
                </div>
                <div class="widget-body">
                   <div class="cyberTable">
                       <table class="table-hover" id="data_table_1" width="100%" border="0" cellpadding="0" cellspacing="0">
                        <thead>
							<tr>
								<th style="text-align: center;" width="5%">No</th>
								<th style="text-align: center;">Operation</th>
								<th style="text-align: center;">Role Name</th>
							</tr>
						</thead>
						<tbody>
						<c:choose>
								<c:when test="${!(fn:length(systemForm.roleResults)>0)}">
									<td align="center" colspan="3" style="font-size: 25px;color: #C81522;font-weight: bold;"><spring:message code="${systemForm.notDataCode}" arguments=""/></td>
								</c:when>
								<c:otherwise>
								<c:forEach items="${systemForm.roleResults }" var="roleInfo" varStatus="row">
									<tr id="${roleInfo.value}_tr" onclick="selectTr(this.id,'${roleInfo.value}')">
										<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${(model.pageInfo.page*model.pageInfo.size) + row.index + 1 }</font> </label></td>
										<td style="text-align: center;" class="blue">
											<c:set value="${selectPageFuncId}_Edit" var="editRole"/>
		                  					<c:if test="${fn:containsIgnoreCase(roleManagementRole,editRole)}">
											<button type="button" class="btn popovers btn-primary" data-trigger="hover" data-toggle="modal" data-placement="left" data-target="#editRoleDef" data-content="Edit"
											 onclick='showEdit(${cyber:toJsonString(roleInfo)},"E")' >
											 <i class="icon-edit icon-white "></i>
											</button>
											</c:if>
											<c:set value="${selectPageFuncId}_Delete" var="deleteRole"/>
		                  					<c:if test="${fn:containsIgnoreCase(roleManagementRole,deleteRole)}">
											<button class="btn popovers btn-danger" data-trigger="hover" data-placement="left" data-content="Delete" 
											 onclick="deleteRoleDef('${roleInfo.value}')" >
											<i class="icon-remove icon-white "></i>
											</button>
											</c:if>
										</td>
										<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${roleInfo.name}</font> </label></td>
									</tr>
							</c:forEach>
								</c:otherwise>
							</c:choose>
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
                   
	          <!-- 功能清单 start -->       
	          <div class="row-fluid portlet">
				<div class="span12">
	              <div class="widget">
	                 <div class="widget-title">
	                 	<h4><i class="icon-reorder"></i>Function Setting</h4>
	                     <span class="tools"><a href="javascript:;" class="icon-chevron-up"></a></span>
	                 </div>
	                 <div class="widget-body">
	                 <form class="form-horizontal" id="functionListForm" method="post">
	                 	<input type="hidden" name="<%=RoleAuthorityDTO.ATTRIBUTE.ROLE_ID.getValue() %>" id="hidden<%=RoleAuthorityDTO.ATTRIBUTE.ROLE_ID.getValue() %>"/>
		                 <div class="cyberTable">
	                       <table class="table-hover" id="data_table_2" width="100%" border="0" cellpadding="0" cellspacing="0">
	                        <thead>
								<tr style="font-size: 14px">
									<th width="15%" style="text-align: left;">
									<input type="checkbox" id="function_name_all_checkbox" onclick="chekboxAllChecked(this.id,'functionName')"/><span style="position: relative;top: 2px;l">Function Name</span>
									</th>
									<th style="text-align: center;"  title="Query">
										<input type="checkbox" id="queryTh" onclick="chekboxAllChecked(this.id,'queryAll')"/><i style="position: relative;top: 2px;" class="icon-search icon-white "></i> 
									</th>
									<th style="text-align: center;" title="Add">
										<input type="checkbox" id="addTh" onclick="chekboxAllChecked(this.id,'addAll')"/><i style="position: relative;top: 2px;" class="icon-plus-sign icon-white "></i> 
									</th>
									<th style="text-align: center;" title="Edit">
										<input type="checkbox" id="editTh" onclick="chekboxAllChecked(this.id,'editAll')"/><i style="position: relative;top: 2px;" class="icon-edit icon-white "></i>
									</th>
									<th style="text-align: center;" title="Delete">
										<input type="checkbox" id="deleteTh" onclick="chekboxAllChecked(this.id,'deleteAll')"/><i style="position: relative;top: 2px;" class="icon-remove icon-white "></i>
									</th>
									<th style="text-align: center;" title="View">
										<input type="checkbox" id="viewTh" onclick="chekboxAllChecked(this.id,'viewAll')"/><i style="position: relative;top: 2px;" class="icon-eye-open icon-white "></i>
									</th>
									<th style="text-align: center;" title="Import">
										<input type="checkbox" id="importTh" onclick="chekboxAllChecked(this.id,'importAll')"/><i style="position: relative;top: 2px;" class="icon-download icon-white "></i> 
									</th>
									<th style="text-align: center;" title="Export">
										<input type="checkbox" id="exportTh" onclick="chekboxAllChecked(this.id,'exportAll')"/><i style="position: relative;top: 2px;" class="icon-upload icon-white "></i>  
									</th>
									<th style="text-align: center;" title="Approval">
										<input type="checkbox" id="approvalTh" onclick="chekboxAllChecked(this.id,'approvalAll')"/><i style="position: relative;top: 2px;" class="icon-check icon-white "></i>  
									</th>
									<th style="text-align: center;" title="Save">
										<input type="checkbox" id="saveTh" onclick="chekboxAllChecked(this.id,'saveAll')"/><i style="position: relative;top: 2px;" class="icon-ok-sign icon-white "></i>  
									</th>
									<th style="text-align: center;" title="Account setting">
										<input type="checkbox" id="accountStatusTh" onclick="chekboxAllChecked(this.id,'accountStatusAll')"/><i style="position: relative;top: 2px;" class=" icon-wrench icon-white "></i>  
									</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${systemForm.functionList }" var="function" varStatus="row">
								<tr>
									<td style="background-color: #FFEEBC;" colspan="11">
									<font style="font-size:16px">
									<input type="checkbox" class="functionName" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${function.functionId }" onclick="subCheckBox('function_name_all_checkbox','functionName')" value="${function.functionId }"/>
									<b>${function.functionName}</b>
									</font></td>
								</tr>
								<c:forEach items="${systemForm.subFunctionList[function.parentFunctionId] }" var="subFunction" varStatus="row">
								<tr>
									<td class="blue">
									<font style="font-size:14px">
									<input type="checkbox" class="functionName" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }" onclick="subCheckBox('function_name_all_checkbox','functionName')" value="${subFunction.functionId }"/>
									${subFunction.functionName}</font>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'query') }">
									<input type="checkbox" class="queryAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_Query" onclick="subCheckBox('queryTh','queryAll')" value="${subFunction.functionId }_Query"/>
									</c:if>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'add') }">
									<input type="checkbox" class="addAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_Add" onclick="subCheckBox('addTh','addAll')" value="${subFunction.functionId }_Add"/>
									</c:if>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'edit') }">
									<input type="checkbox" class="editAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_Edit" onclick="subCheckBox('editTh','editAll')" value="${subFunction.functionId }_Edit"/>
									</c:if>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'delete') }">
									<input type="checkbox" class="deleteAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_Delete" onclick="subCheckBox('deleteTh','deleteAll')" value="${subFunction.functionId }_Delete"/>
									</c:if>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'view') }">
									<input type="checkbox" class="viewAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_View" onclick="subCheckBox('viewTh','viewAll')" value="${subFunction.functionId }_View"/>
									</c:if>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'import') }">
									<input type="checkbox" class="importAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_Import" onclick="subCheckBox('importTh','importAll')" value="${subFunction.functionId }_Import"/>
									</c:if>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'export') }">
									<input type="checkbox" class="exportAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_Export"  onclick="subCheckBox('exportTh','exportAll')"value="${subFunction.functionId }_Export"/>
									</c:if>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'approval') }">
									<input type="checkbox" class="approvalAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_Approval" onclick="subCheckBox('approvalTh','approvalAll')" value="${subFunction.functionId }_Approval"/>
									</c:if>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'save') }">
									<input type="checkbox" class="saveAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_Save" onclick="subCheckBox('saveTh','saveAll')" value="${subFunction.functionId }_Save"/>
									</c:if>
									</td>
									<td class="blue" style="text-align: center;">
									<c:if test="${fn:containsIgnoreCase(subFunction.buttons,'accountStatus') }">
									<input type="checkbox" class="accountStatusAll" name="<%=RoleAuthorityDTO.ATTRIBUTE.FUNCTION_ID.getValue() %>" id="${subFunction.functionId }_AccountStatus" onclick="subCheckBox('accountStatusTh','accountStatusAll')" value="${subFunction.functionId }_AccountStatus"/>
									</c:if>
									</td>
								</tr>
								</c:forEach>
							</c:forEach>
							</tbody>
	                       </table>
	                     </div>
	                 <c:set value="${selectPageFuncId}_Save" var="saveRole"/>
                  	 <c:if test="${fn:containsIgnoreCase(roleManagementRole,saveRole)}">
					 <div style="width:100%;text-align:right; margin-top: 15px">
	                        <button type="button" onclick="saveRole()" class="btn btn-default btn-info ">Save</button>
	                 </div>	
	                 </c:if>
	               </form>
	              </div>
	            </div>
	           </div>
		     </div>
		     <!-- 功能清单 end -->
		     
             <!-- 新增与编辑模态框 start-->
               <div  class="modal fade" id="editRoleDef" role="dialog"
                   style="display: none;position:fixed; top:30%;left: 50%" tabindex="-1" data-backdrop="static">
                   <div class="modal-dialog">
                   	<form class="form-horizontal" id="editRoleDefForm" method="post">
                   		<input type="hidden" name="<%=RoleDefDTO.ATTRIBUTE.ROLE_ID.getValue()%>" id ="<%=RoleDefDTO.ATTRIBUTE.ROLE_ID.getValue()%>"/>
                       <div class="modal-content" >
                           <div class="modal-header">
                               <button type="button" class="close" data-dismiss="modal"
                                   aria-hidden="true">&times;</button>
                               <h3 class="modal-title"></h3>
                           </div>
                           <!-- 隐藏域 -->
                           	<div class="modal-body">
		                       <div class="control-group position-right">
										<label class="control-label">Role Name<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue()%>"  name="<%=RoleDefDTO.ATTRIBUTE.ROLE_NAME.getValue()%>" onkeydown="return ClearSubmit(event)" onkeyup="value=stripscript(this.value)"  maxlength="60"/>
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
