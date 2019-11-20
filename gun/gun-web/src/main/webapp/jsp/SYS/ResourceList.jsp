<%@page import="com.gun.common.entity.pojo.FunctionTypeDTO"%>
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
	 	$("#command").attr("action","<%=LotteryConstants.INIT_RESOURCE%>.do").submit();
	 }
	 /**
	  *查询 
	  */
	  function queryResource(isCleanPageNo) {
	   showLoading();
	   if(isCleanPageNo=='Y'){
	   		$("#pageNo").val(null);
	   }
	   $("#command").attr("action","<%=LotteryConstants.INIT_RESOURCE%>.do").submit();
	  }
	  /**
	  *检视/编辑/新增
	  */
	  function showEdit(obj,flag){
	  	showLoading();
	  	$.ajax({
	             type: "POST",
	             dataType: "json", //接受數據格式 
	             cache:false,
	             url: "<c:out value="${contextPath}"/>/queryParentFunction.do",
	             data: {},          
	             async: false,   
	             success: function(data) {
	             	$("#<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>").empty();
	             	$("#<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>").append("<option value=''>Please select...</option>");
	            	$.each(data,function() {
	             		 $("#<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>").append("<option value='"+this.value+"'>"+this.name+"</option>");
	             	});
	             	completeLoading();
	    		},
			    error: function(data) {
			    		  Lobibox.alert('error', {
			                    msg: "Parent Function load failed!"
			              });
			              completeLoading();
			              return false;
			    }
	        });
	  	if(flag=='V'){
	  		$("#confirmId").hide();
	  		$(".modal-title").html("View Function");
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").val(obj.functionId);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").attr("disabled",true);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>").val(obj.functionName);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>").attr("disabled",true);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>").val(obj.functionOrder);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>").attr("disabled",true);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_URL.getValue()%>").val(obj.functionUrl);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_URL.getValue()%>").attr("disabled",true);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>").val(obj.parentFunctionId);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>").attr("disabled",true);
<%-- 	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_DESCRIPTION.getValue()%>").val(description); --%>
<%-- 	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_DESCRIPTION.getValue()%>").attr("disabled",true); --%>
	  		$("[name = buttons]:checkbox").attr("disabled", true);
	  		$("[name = buttons]:checkbox").attr("checked", false);
	  		$("[name = buttons]:checkbox").parent("span").removeClass("checked");
	  		var btns = obj.buttons;
	  		if (!isEmpty(btns)) {
				if (btns.indexOf('Query')>-1) {
					$("#query<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#query<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Add')>-1) {
					$("#add<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#add<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Edit')>-1) {
					$("#edit<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#edit<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Delete')>-1) {
					$("#delete<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#delete<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('View')>-1) {
					$("#view<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#view<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Import')>-1) {
					$("#import<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#import<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Export')>-1) {
					$("#export<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#export<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Approval')>-1) {
					$("#approval<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#approval<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Save')>-1) {
					$("#save<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#save<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('AccountStatus')>-1) {
					$("#accountStatus<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#accountStatus<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
			}
	  	}else if(flag=='E'){
	  		$(".modal-title").html("Edit Function");
	  		$("#confirmId").show();
	  		var addFlag = $("#addFlag").val('N');
			$("#hidden<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ID.getValue()%>").val(obj.functionId);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").val(obj.functionId);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").attr("disabled",false);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").attr("readonly",true);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>").val(obj.functionName);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>").attr("disabled",false);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>").val(obj.functionOrder);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>").attr("disabled",false);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_URL.getValue()%>").val(obj.functionUrl);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_URL.getValue()%>").attr("disabled",false);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>").val(obj.parentFunctionId);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>").attr("disabled",false);
<%-- 	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_DESCRIPTION.getValue()%>").val(description); --%>
<%-- 	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_DESCRIPTION.getValue()%>").attr("disabled",false); --%>
	  		$("#hiddenParentFunctionId").val(obj.parentFunctionId);
	  		$("[name = buttons]:checkbox").attr("disabled", false);
	  		$("[name = buttons]:checkbox").attr("checked", false);
	  		$("[name = buttons]:checkbox").parent("span").removeClass("checked");
	  		var btns = obj.buttons;
	  		if (!isEmpty(btns)) {
				if (btns.indexOf('Query')>-1) {
					$("#query<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#query<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Add')>-1) {
					$("#add<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#add<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Edit')>-1) {
					$("#edit<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#edit<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Delete')>-1) {
					$("#delete<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#delete<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('View')>-1) {
					$("#view<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#view<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Import')>-1) {
					$("#import<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#import<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Export')>-1) {
					$("#export<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#export<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Approval')>-1) {
					$("#approval<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#approval<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('Save')>-1) {
					$("#save<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#save<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
				if (btns.indexOf('AccountStatus')>-1) {
					$("#accountStatus<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").attr("checked", true);
					$("#accountStatus<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>").parent("span").addClass("checked");
				}
			}
// 			$("#hiddenButtons").val(buttons);
	  	}else{
	  		$(".modal-title").html("Add Function");
	  		$("#confirmId").show();
	  		var addFlag = $("#addFlag").val('Y');
			$("#hidden<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ID.getValue()%>").val(null);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").val(null);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").attr("disabled",false);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").attr("readonly",false);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>").val(null);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>").attr("disabled",false);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>").val(null);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>").attr("disabled",false);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_URL.getValue()%>").val(null);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_URL.getValue()%>").attr("disabled",false);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>").val(null);
	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>").attr("disabled",false);
<%-- 	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_DESCRIPTION.getValue()%>").val(description); --%>
<%-- 	  		$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_DESCRIPTION.getValue()%>").attr("disabled",false); --%>
	  		$("#hiddenParentFunctionId").val(null);
	  		$("[name = buttons]:checkbox").attr("disabled", false);
	  		$("[name = buttons]:checkbox").attr("checked", false);
	  		$("[name = buttons]:checkbox").parent("span").removeClass("checked");
	  	}
	  }
	  
	  function setParentFunction(value){
	  	$("#hiddenParentFunctionId").val(value);
	  }
	  
	  /**
	  *function 保存
	  */
	  function onclickConfirm(){
	  	var functionName  = $.trim($("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>").val());
	  	var functionCode  = $.trim($("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").val());
	  	var functionOrder = $.trim($("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>").val());
	  	
	  	if(isEmpty(functionName)){
	  		Lobibox.alert('info', {
                    msg: "The Function Name is required!"
            });
			$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>").focus();
			return false;
		}
		if(isEmpty(functionCode)){
	  		Lobibox.alert('info', {
                    msg: "The Function Code is required!"
            });
			$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>").focus();
			return false;
		}
	  	if(isEmpty(functionOrder)){
	  		Lobibox.alert('info', {
                    msg: "The Order is required!"
            });
			$("#<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>").focus();
			return false;
		}
		showLoading();
	  	$.ajax({
	             type: "POST",
	             dataType: "json", //接受數據格式 
	             cache:false,
	             url: "<c:out value="${contextPath}"/>/saveFunction.do",
	             data: $("#editFunctionTypeForm").serialize(),          
	             async: false,   
	             success: function(data) {
	             	if(data.success){
	             		completeLoading();
	             		$("#confirmId").attr("data-dismiss","modal");
	             		$("#messageCode").val(data.messageCode);
	             		queryResource('N');
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
	  
	function deleteResource(id){
		Lobibox.confirm({
                    msg: "Confirm delete?",
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                            showLoading();
					  	 	$.ajax({
					             type: "POST",
					             dataType: "json", //接受數據格式 
					             cache:false,
					             url: "<c:out value="${contextPath}"/>/deleteFunction.do",
					             data: {"ids":id},          
					             async: false,   
					             success: function(data) {
					             	if(data.success){
					             		completeLoading();
					             		$("#messageCode").val(data.messageCode);
					             		queryResource('N');
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
		    <h1 class="page-title">　<i class="icon-dashboard"></i> Resource Management <small></small> </h1>
		    <!-- END PAGE TITLE -->
	    </div>
    </div>
    <!-- END PAGE HEADER-->

    <!-- BEGIN PAGE CONTENT-->
    <div id="page">
    	<%@ include file="../common/message.jsp" %>
    	<form class="form-horizontal" id="command" method="post">
           <input type="hidden" id="pageNo" name="pageNo" value="${functionTypeForm.pageNo}">
           <input type="hidden" id="messageCode" name="messageCode" value="">
           <input type="hidden"  name="selectPageFuncId" value="${selectPageFuncId }">
        </form>
	     <!-- 查詢結果部分 -->
	      <div class="row-fluid portlet">
            <div class="span12">
              <!-- BEGIN GRID PORTLET-->
              <div class="widget">
                <div class="widget-title">
                  <h4><i class="icon-reorder"></i>Function List</h4>
                  <c:set value="${selectPageFuncId}_Add" var="addRole"/>
                  <c:if test="${fn:containsIgnoreCase(resourceManagementRole,addRole)}">
                  <button class="btn popovers btn-success" data-trigger="hover" data-toggle="modal" data-placement="left" data-target="#editFunctionType" data-content="Add" 
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
								<th style="text-align: center;" width="80px">No</label></th>
								<th style="text-align: center;" width="200px">Operation</th>
								<th style="text-align: center;" width="250px">Function Name</th>
								<th style="text-align: center;" width="200px">Function Code</th>
								<th style="text-align: center;" width="100px">Order</th>
								<th style="text-align: center;" width="200px">Url</th>
								<th style="text-align: center;" width="200px">Buttons</th>
								<th style="text-align: center;" width="200px">Parent Function Code</th>
<!-- 								<th style="text-align: center;"><label>Description</label></th> -->
							</tr>
						</thead>
						<tbody>
						<c:choose>
								<c:when test="${!(fn:length(functionTypeForm.results)>0)}">
									<td align="center" colspan="8" style="font-size: 25px;color: #C81522;font-weight: bold;"><spring:message code="${functionTypeForm.notDataCode}" arguments=""/></td>
								</c:when>
								<c:otherwise>
								<c:forEach items="${functionTypeForm.results }" var="functionInfo" varStatus="row">
									<tr>
										<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${(model.pageInfo.page*model.pageInfo.size) + row.index + 1 }</font> </label></td>
										<td style="text-align: center;" class="blue">
											<c:set value="${selectPageFuncId}_View" var="viewRole"/>
		                  					<c:if test="${fn:containsIgnoreCase(resourceManagementRole,viewRole)}">
											<button class="btn popovers " data-trigger="hover"  data-toggle="modal" data-placement="left" data-target="#editFunctionType" data-content="View" 
											 onclick='showEdit(${cyber:toJsonString(functionInfo)},"V")'>
											 <i class="icon-eye-open icon-white "></i>
											</button>
											</c:if>
											<c:set value="${selectPageFuncId}_Edit" var="editRole"/>
		                  					<c:if test="${fn:containsIgnoreCase(resourceManagementRole,editRole)}">
											<button class="btn popovers btn-primary" data-trigger="hover" data-toggle="modal" data-placement="left" data-target="#editFunctionType" data-content="Edit"
											 onclick='showEdit(${cyber:toJsonString(functionInfo)},"E")' >
											 <i class="icon-edit icon-white "></i>
											</button>
											</c:if>
											<c:set value="${selectPageFuncId}_Delete" var="deleteRole"/>
		                  					<c:if test="${fn:containsIgnoreCase(resourceManagementRole,deleteRole)}">
											<button class="btn popovers btn-danger" data-trigger="hover" data-placement="left" data-content="Delete" 
											 onclick="deleteResource('${functionInfo.functionId}')" >
											<i class="icon-remove icon-white "></i>
											</button>
											</c:if>
										</td>
										<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${functionInfo.functionName}</font> </label></td>
										<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${functionInfo.functionCode}</font> </label></td>
										<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${functionInfo.functionOrder}</font> </label></td>
										<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${functionInfo.functionUrl}</font> </label></td>
										<td title="${functionInfo.buttons}" style="text-align: center;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" class="blue"><lable class="control-label"><font style="font-size:14px">${functionInfo.buttons}</font> </label></td>
										<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${functionInfo.parentFunctionId} </font> </label></td>
		<%-- 								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${functionInfo.functionDescription} </font> </label></td> --%>
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
                   
             <!-- 新增与编辑模态框 start-->
               <div  class="modal fade" id="editFunctionType" role="dialog"
                   style="display: none;position:fixed; top:30%;left: 50%" tabindex="-1" data-backdrop="static"
                   >
                   <div class="modal-dialog">
                   	<form class="form-horizontal" id="editFunctionTypeForm" method="post">
                   		<input type="hidden" name="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ID.getValue()%>" id ="hidden<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ID.getValue()%>"/>
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
										<label class="control-label">Function Name<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>"  name="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_NAME.getValue()%>" onkeyup="value=stripscript(this.value)"  maxlength="60"/>
									</div>
							   </div>
							   <div class="control-group position-right">
										<label class="control-label">Function Code<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>"  name="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_CODE.getValue()%>" onkeyup="value=value.replace(/[\W]/g,'')"   maxlength="15"/>
									</div>
							   </div>
							   <div class="control-group position-right">
										<label class="control-label">Order<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>"  name="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_ORDER.getValue()%>"  onkeyup="this.value=this.value.replace(/\D/g,'')"  maxlength="6"/>
									</div>
							   </div>
							   <div class="control-group position-right">
										<label class="control-label">Url</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_URL.getValue()%>"  name="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_URL.getValue()%>" onkeyup="value=stripscript(this.value)" maxlength="100"/>
									</div>
							   </div>
							   <div class="control-group position-right">
	                                            <div class="span12">
	                                            	<label class="control-label">Buttons</label>
		                                    		<div class="controls">
			                     						<input type="checkbox" id="query<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="Query"/>Query 
			                     						<input type="checkbox" id="add<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="Add"/>Add 
			                     						<input type="checkbox" id="edit<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="Edit"/>Edit 
			                     						<input type="checkbox" id="delete<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="Delete"/>Delete 
			                     						<input type="checkbox" id="view<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="View"/>View 
			                     						<input type="checkbox" id="import<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="Import"/>Import 
			                     						<input type="checkbox" id="export<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="Export"/>Export 
			                     						<input type="checkbox" id="approval<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="Approval"/>Approval 
			                     						<input type="checkbox" id="save<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="Save"/>Save 
			                     						<input type="checkbox" id="accountStatus<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" 
			                     									name="<%=FunctionTypeDTO.ATTRIBUTE.BUTTONS.getValue()%>" value="AccountStatus"/>Account setting 
		                                        	</div>
		                                    	</div>
                                            </div>
							   <div class="control-group position-right">
										<label class="control-label">Parent Function</label>
									<div class="controls">
										<input type="hidden" name="<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>" id="hiddenParentFunctionId"/>
										<select class="span12" tabindex="1"  data-placeholder="Please select..." id="<%=FunctionTypeDTO.ATTRIBUTE.PARENT_FUNCTION_ID.getValue()%>"  onchange="setParentFunction(this.value)">
						               </select>
									</div>
							   </div>
<!-- 							    <div class="control-group position-right"> -->
<!-- 										<label class="control-label">Description :</label> -->
<!-- 									<div class="controls"> -->
<%-- 										<input type="text" class="span12" id="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_DESCRIPTION.getValue()%>"  name="<%=FunctionTypeDTO.ATTRIBUTE.FUNCTION_DESCRIPTION.getValue()%>"   maxlength="100"/> --%>
<!-- 									</div> -->
<!-- 							   </div> -->
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
