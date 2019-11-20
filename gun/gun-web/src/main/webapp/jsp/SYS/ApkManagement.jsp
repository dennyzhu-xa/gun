\<%@page import="com.gun.common.entity.pojo.ApkManagementDTO"%>
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
	#dateinfo {
		z-index: 100000;
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
	 	$("#command").attr("action","<%=LotteryConstants.QUERY_APK%>.do").submit();
	 }
	 /**
	  *查询 
	  */
	 function queryApk(isCleanPageNo) {
	   showLoading();
	   if(isCleanPageNo=='Y'){
	   		$("#pageNo").val(null);
	   }
	   $("#hiddenQueryApkName").val($("#queryApkName").val());
	   $("#command").attr("action","<%=LotteryConstants.QUERY_APK%>.do").submit();
	   $("#messageSpan").html("success");
	 }
	 /**
	  *检视/编辑/新增
	  */
	 function showEdit(obj,flag){
	  	 if(flag=='V'){
	  		 $(".modal-title").html("View Lottery Apk");
	  		 $("#confirmId").hide();
	  		 $("#apkDiv").hide();
	  		 $("#showApk").show();
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>").val(obj.appName);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>").attr("disabled",true);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>").val(obj.packageName);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>").attr("disabled",true);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").val(obj.versionCode);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").attr("disabled",true);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>").val(obj.versionName);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>").attr("disabled",true);
	  		 $("#apkName").val(obj.download);
	  		 $("#apkName").attr("disabled",true);
	  		 
	  	 }else if(flag=='E'){
	  		 $(".modal-title").html("Edit Apk");
	  		 $("#confirmId").show();
	  		 $("#apkDiv").show();
	  		 $("#showApk").hide();
	  		 var addFlag = $("#addFlag").val('N');
	  		 $("#hidden<%=ApkManagementDTO.ATTRIBUTE.ID.getValue()%>").val(obj.id);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>").val(obj.appName);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>").attr("disabled",false);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>").val(obj.packageName);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>").attr("disabled",false);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").val(obj.versionCode);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").attr("disabled",false);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>").val(obj.versionName);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>").attr("disabled",false);
	  	 }else {
	  		 $(".modal-title").html("Add Apk");
	  		 $("#confirmId").show();
	  		 $("#apkDiv").show();
	  		 $("#showApk").hide();
	  		 var addFlag = $("#addFlag").val('Y');
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>").val(null);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>").attr("disabled",false);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>").val(null);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>").attr("disabled",false);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").val(null);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").attr("disabled",false);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>").val(null);
	  		 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>").attr("disabled",false);
	  	 }
	  }
	 function onclickConfirm(){
		 var appName = $("#<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>").val();
		 var packageName = $("#<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>").val();
		 var versionCode = $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").val();
		 var versionName = $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>").val();
		 var apk = $("#apk").val();
		 if(isEmpty(appName)){
				 Lobibox.alert('info', {
	                 msg: "The App Name is required!"
	         	 });
				 $("#<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>").focus();
					return false;
		 }
		 if(isEmpty(packageName)){
			 Lobibox.alert('info', {
                 msg: "The Package Name is required!"
         	 });
			 $("#<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>").focus();
				return false;
	 	 }
		 var regBeginNumber = /\D/g;
		 if(isEmpty(versionCode)){
			 Lobibox.alert('info', {
                 msg: "The Version Code is required!"
         	 });
			 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").focus();
				return false;
	 	 } else if (regBeginNumber.test(versionCode)) {
	 		Lobibox.alert('info', {
                msg: "The Version Code must be natural number!"
        	 });
	 		$("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").focus();
			return false;
	 	 } else {
	 		var checkResult = checkApk();
	 		if (!checkResult) {
	 			Lobibox.alert('info', {
	                 msg: "The Version Code should not be repeated!"
	         	 });
				 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").focus();
					return false;
	 		}
	 	 }
		 if(isEmpty(versionName)){
			 Lobibox.alert('info', {
                 msg: "The Version Name is required!"
         	 });
			 $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>").focus();
				return false;
	 	 } 
		 if(isEmpty(apk)){
			 Lobibox.alert('info', {
                 msg: "The Apk is required!"
         	 });
			 $("#apk").focus();
			 return false;
	 	 } else if (!isApk(apk)) {
	 		Lobibox.alert('info', {
                msg: "Apk must end with '.apk'"
        	 });
			 $("#apk").focus();
			 return false;
	 	 }
		 showLoading();
		 $("#editApkForm").attr("action","${contextPath}/saveApk.do").submit();
	 }
	 
	 /**
	  *删除
	  */
	 function deleteApk(id){
	  	Lobibox.confirm({
                    msg: "Confirm delete?",
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                            showLoading();
					  	 	$.ajax({
					             type: "POST",
					             dataType: "json", //接受數據格式 
					             cache:false,
					             url: "<c:out value="${contextPath}"/>/deleteApk.do",
					             data: {"ids":id},          
					             async: false,   
					             success: function(data) {
					             	if(data.success){
					             		completeLoading();
					             		$("#messageCode").val(data.messageCode);
					             		queryApk('N');
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
	function checkApk(){
		var versionCode = $("#<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>").val();
		var result;
		var aj = $.ajax( {  
				url: "<c:out value="${contextPath}"/>/checkApk.do",
				data:{  
					versionCode : versionCode,  
				},  
				type:'post',  
				cache:false,  
				dataType:'json',
				async: false,
				success:function(data) {//true,可用。false,不可用。 
					result = data.success;
				},  
				error : function() {  
					result = "error";
				} 
			});
			return result;
		}
		//验证apk格式
		function isApk(str){
		    var rg = /^.*.apk$/;
		    return rg.test(str);
		} 
		//download
		function downloadApk(download){
			var merchantForm = document.forms['downloadApkForm'];
			document.getElementById("filePath").value=download;
			merchantForm.action = "fileDownload.do";
			merchantForm.submit();
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
		    <h1 class="page-title">　<i class="icon-dashboard"></i>Apk Management<small></small> </h1>
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
                 <input type="hidden" id="pageNo" name="pageNo" value="${apkManagementForm.pageNo }">
                 <input type="hidden" id="messageCode" name="messageCode" value="">
                 <input type="hidden" id="period" name="period" value="">
                 <div class="control-group">
					<label class="control-label">Apk Name</label>
					<div class="controls">
						<input type="hidden" class="span3" id="hiddenQueryApkName"  name="queryApkName"  value="${apkManagementForm.queryApkName}"/>
						<input type="text" class="span3" onkeydown="return ClearSubmit(event)" id="queryApkName" value="${apkManagementForm.queryApkName}"/>
					</div>
				 </div>	
				 <div class="form-actions">
				 	<c:set value="${selectPageFuncId}_Query" var="queryRole"/>
				 		<c:if test="${fn:containsIgnoreCase(apkManagementRole,queryRole)}">
                        <button type="button" onclick="queryApk('Y')" class="btn btn-default btn-primary ">Query</button>
                        </c:if>
                        <c:set value="${selectPageFuncId}_Add" var="addRole"/>
                        <c:if test="${fn:containsIgnoreCase(apkManagementRole,addRole)}">
                        <button type="button" onclick="showEdit()" class="btn btn-default btn-success " data-toggle="modal" data-placement="left" data-target="#editApk">Add</button>
                 	</c:if>
                 </div>	
               </form>
              </div>
            </div>
           </div>
	     </div><!--row-fluid portlet-->
	     <c:if test="${fn:length(apkManagementForm.apkManagementDTOList)>0}">
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
								<th style="text-align: center;" width="100px">Operation</th>
								<th style="text-align: center;" width="150px">Apk Name</th>
								<th style="text-align: center;" width="150px">Package Name</th>
								<th style="text-align: center;" width="150px">Version Code</th>
								<th style="text-align: center;" width="150px">Version Name</th>
								<th style="text-align: center;" width="200px">Create Time</th>
								<th style="text-align: center;" width="150px">File Size</th>
								<!-- <th style="text-align: center;" width="150px">Context</th> -->
								<th style="text-align: center;" width="150px">Download</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${apkManagementForm.apkManagementDTOList }" var="apkManagementDTO" varStatus="row">
							<tr>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${(model.pageInfo.page*model.pageInfo.size) + row.index + 1 }</font> </label></td>
								<td style="text-align: center;" class="blue">
									<c:set value="${selectPageFuncId}_View" var="viewRole"/>
                        			<c:if test="${fn:containsIgnoreCase(apkManagementRole,viewRole)}">
									<button class="btn popovers " data-trigger="hover"  data-toggle="modal" data-placement="left" data-target="#editApk" data-content="View" 
									 onclick='showEdit(${cyber:toJsonString(apkManagementDTO)},"V")'>
									 <i class="icon-eye-open icon-white "></i>
									</button>
									</c:if>
									<c:set value="${selectPageFuncId}_Edit" var="editRole"/>
                        			<%-- <c:if test="${fn:containsIgnoreCase(apkManagementRole,editRole) }">
									<button class="btn popovers btn-primary" data-trigger="hover" data-toggle="modal" data-placement="left" data-target="#editApk" data-content="Edit"
									 onclick='showEdit(${cyber:toJsonString(apkManagementDTO)},"E")'>
									 <i class="icon-edit icon-white "></i>
									</button>
									</c:if> --%>
									<c:set value="${selectPageFuncId}_Delete" var="deleteRole"/>
                        			<c:if test="${fn:containsIgnoreCase(apkManagementRole,deleteRole) }">
									<button class="btn popovers btn-danger" data-trigger="hover" data-placement="left" data-content="Delete" 
									 onclick="deleteApk('${apkManagementDTO.id}')" >
									<i class="icon-remove icon-white "></i>
									</button>
									</c:if>
								</td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${apkManagementDTO.appName}</font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${apkManagementDTO.packageName}</font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${apkManagementDTO.versionCode}</font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${apkManagementDTO.versionName} </font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">
								<fmt:formatDate value="${apkManagementDTO.releaseTime}" pattern="yyyy/MM/dd HH:mm:ss"/></font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${apkManagementDTO.filesize} </font> </label></td>
								<%-- <td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${apkManagementDTO.context} </font> </label></td> --%>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px"><a onclick="downloadApk('${apkManagementDTO.download }')">Download</a></font> </label></td>
							</tr>
							</c:forEach>
						</tbody>
						<form class="form-horizontal" id="downloadApkForm" name="downloadApkForm" method="post" action="">
                        	<input type="hidden" name="filePath" id="filePath" value =""/>
                        </form>
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
			 <div  class="modal fade" id="editApk" role="dialog"
                   style="display: none;position: absolute;top:20%;left: 45%" tabindex="-1" data-backdrop="static"
                   >
                   <div class="modal-dialog">
                   	<form class="form-horizontal" enctype="multipart/form-data" id="editApkForm" method="post">
                   		<input type="hidden" name="<%=ApkManagementDTO.ATTRIBUTE.ID.getValue()%>" id ="hidden<%=ApkManagementDTO.ATTRIBUTE.ID.getValue()%>"/>
                   		<input type="hidden" class="span3" id="queryApkName"  name="queryApkName"  value="${apkManagementForm.queryApkName}"/>
                   		<input type="hidden"  id ="addFlag"/>
                   		<input type="hidden" id="pageNo" name="pageNo" value="${apkManagementForm.pageNo }">
                       <div class="modal-content" >
                           <div class="modal-header">
                               <button type="button" class="close" data-dismiss="modal"
                                   aria-hidden="true">&times;</button>
                               <h3 class="modal-title"></h3>
                           </div>
                           <!-- 隐藏域 -->
                           	<div class="modal-body">
		                       <div class="control-group position-right">
									<label class="control-label">Apk Name<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>"  name="<%=ApkManagementDTO.ATTRIBUTE.APP_NAME.getValue()%>" onkeyup="value=stripscript(this.value)" maxlength="20"/>
									</div>
							   </div>
							   <div class="control-group position-right">
									<label class="control-label">Package Name<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>"  name="<%=ApkManagementDTO.ATTRIBUTE.PACKAGE_NAME.getValue()%>" onkeyup="value=stripscript(this.value)" maxlength="20"/>
									</div>
							   </div>
							   <div class="control-group position-right">
									<label class="control-label">Version Code<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>"  name="<%=ApkManagementDTO.ATTRIBUTE.VERSION_CODE.getValue()%>" onkeyup="value=stripscript(this.value)" maxlength="20"/>
									</div>
							   </div>
							   <div class="control-group position-right">
									<label class="control-label">Version Name<span class="red">*</label>
									<div class="controls">
										<input type="text" class="span12" id="<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>"  name="<%=ApkManagementDTO.ATTRIBUTE.VERSION_NAME.getValue()%>" onkeyup="value=stripscript(this.value)" maxlength="20"/>
									</div>
							   </div>
							   <div class="control-group position-right" id="apkDiv">
									<label class="control-label">Apk<span class="red">*</label>
									<div class="controls">
                                          <div class="fileupload fileupload-new " data-provides="fileupload">
                                              <div class="input-append">
                                                  <div class="uneditable-input span3"><i class="icon-file fileupload-exists"></i><span class="fileupload-preview"></span></div>
                                                  <span class="btn btn-file"><span class="fileupload-new">Browse..</span> <span class="fileupload-exists">Browse..</span>
                                                      <input type="file" class="default" name="apk" id="apk" />
                                                  </span><a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove..</a>
                                              </div>
                                          </div>
                                    </div>
							   </div>
							   <div class="control-group position-right" id="showApk">
									<label class="control-label">ApkFileName</label>
									<div class="controls">
										<input type="text" class="span12" id="apkName"/>
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
