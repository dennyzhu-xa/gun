<%@page
	import="com.gun.common.entity.pojo.QuestionsAndAnswersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lottery</title>
<%-- <script type="text/javascript" src="${contextPath}/jedate/jedate.min.js"></script> --%>
<%@ include file="../common/meta.jsp"%>
<%@ include file="../common/css.jsp"%>
<%@ include file="../common/js.jsp"%>
<style type="text/css">
.position-right {
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
	 	$("#command").attr("action","<%=LotteryConstants.QUERY_QUESTIONS_AND_ANSWERS%>.do").submit();
	 }
	 /**
	  *查询 
	  */
	  function queryQuestionAndAnswer(isCleanPageNo) {
	     showLoading();
	     if(isCleanPageNo=='Y'){
	   		$("#pageNo").val(null);
	     }
	   	 $("#command").attr("action","<%=LotteryConstants.QUERY_QUESTIONS_AND_ANSWERS%>.do").submit();
	  }
	  /**
	  *检视/编辑/新增
	  */
	  function showEdit(obj,flag){
	  	if(flag=='V'){
	  		$(".modal-title").html("View Q&A／Contact us");
	  		$("#confirmId").hide();
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").val(obj.category);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").attr("disabled",true);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").val(obj.question);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").attr("disabled",true);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>").val(obj.answer);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>").attr("disabled",true);
	  	}else if(flag=='E'){
	  		$(".modal-title").html("Edit Q&A／Contact us");
	  		$("#confirmId").show();
	  		var addFlag = $("#addFlag").val('N');
	  		$("#hidden<%=QuestionsAndAnswersDTO.ATTRIBUTE.ID.getValue()%>").val(obj.id);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").val(obj.category);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").attr("disabled",false);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").val(obj.question);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").attr("disabled",false);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>").val(obj.answer);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>").attr("disabled",false);
	  	}else{
	  		$(".modal-title").html("Add Q&A／Contact us");
	  		$("#confirmId").show();
	  		var addFlag = $("#addFlag").val('Y');
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").val(null);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").attr("disabled",false);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").val(null);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").attr("disabled",false);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>").val(null);
	  		$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>").attr("disabled",false);
	  	}
	  	$("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").change(function(){
			  var category = $.trim($("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").val());
			  if(category == 3){
				  $("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").attr("disabled",true);
				  $("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").val(null);
			  } else {
				  $("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").attr("disabled",false);
				  $("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").val(obj.question);
			  }
		  });
	  }
	  
	  /**
	  *保存
	  */
	  function onclickConfirm(){
		  var category = $.trim($("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").val());
		  var question = $.trim($("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").val());
		  var answer = $.trim($("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>").val());
		  if(isEmpty(category)){
			  Lobibox.alert('info', {
				  msg: "The category is required!"
			  });
			  $("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>").focus();
			  return false;
		  }
		  if(category == 1){
			  if(isEmpty(question)){
				  Lobibox.alert('info', {
					  msg: "The Question/Column is required when Category is help!"
				  });
				  $("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>").focus();
				  return false;
			  }
		  }
		  if(isEmpty(answer)){
			  Lobibox.alert('info', {
				  msg: "The Answer/Content is required!"
			  });
			  $("#<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>").focus();
			  return false;
		  }
	  	  showLoading();
	  	  $.ajax({
	          type: "POST",
	          dataType: "json", //接受數據格式 
	          cache:false,
	          url: "<c:out value="${contextPath}"/>/saveQuestionsAndAnswers.do",
	          data: $("#editQuestionAndAnswerForm").serialize(),          
	          async: false,   
	          success: function(data) {
	             if(data.success){
	             	 completeLoading();
	             	 $("#confirmId").attr("data-dismiss","modal");
	             	 $("#messageCode").val(data.messageCode);
	             	queryQuestionAndAnswer('N');
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
					             url: "<c:out value="${contextPath}"/>/deleteQuestionsAndAnswers.do",
					             data: {"ids":id},          
					             async: false,   
					             success: function(data) {
					             	if(data.success){
					             		completeLoading();
					             		$("#messageCode").val(data.messageCode);
					             		queryQuestionAndAnswer('N');
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
	  function filterScript(s) {
		  var pattern = new RegExp("[<>]") 
		  var rs = ""; 
		  for (var i = 0; i < s.length; i++) {
			  rs = rs+s.substr(i, 1).replace(pattern, ''); 
		  } 
		  return rs; 
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
		<div id="container">
			<!-- BEGIN CONTENT -->
			<div id="content" class="row-fluid" role="main">
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
								<h1 class="page-title">
									<i class="icon-dashboard"></i> Q&A／Contact us<small></small>
								</h1>
								<!-- END PAGE TITLE -->
							</div>
						</div>
						<!-- END PAGE HEADER-->

						<!-- BEGIN PAGE CONTENT-->
						<div id="page">
							<%@ include file="../common/message.jsp"%>
							<div class="row-fluid portlet">
								<div class="span12">
									<div class="widget">
										<div class="widget-title">
											<h4>
												<i class="icon-reorder"></i>Query Condition
											</h4>
											<span class="tools"><a href="javascript:;"
												class="icon-chevron-up"></a></span>
										</div>
										<div class="widget-body">
											<form class="form-horizontal" id="command" method="post">
												<input type="hidden" id="pageNo" name="pageNo"
													value="${questionsAndAnswersForm.pageNo }"> <input
													type="hidden" id="messageCode" name="messageCode" value="">
												<input type="hidden" name="selectPageFuncId"
													value="${selectPageFuncId }">
												<div class="control-group">
													<label class="control-label">Category</label>
													<div class="controls">
														<select class="span3" tabindex="1"
															data-placeholder="Please select..." name="queryCategory"
															id="queryCategory">
															<option value="">Please select...</option>
															<option value="1"
																${questionsAndAnswersForm.queryCategory eq "1" ? 'selected':'' }>help</option>
															<%-- <option value="2"
																${questionsAndAnswersForm.queryCategory eq "2" ? 'selected':'' }>contact</option> --%>
															<option value="3"
																${questionsAndAnswersForm.queryCategory eq "3" ? 'selected':'' }>about</option>
														</select>
														<%-- <input type="text" class="span3" id="queryCategory"  name="queryCategory"  value="${questionsAndAnswersForm.queryCategory}" maxlength="23"/> --%>
													</div>
												</div>
												<div class="control-group">
													<label class="control-label">Question</label>
													<div class="controls">
														<input type="text" class="span3" id="queryQuestion"
															name="queryQuestion"
															onkeydown="return ClearSubmit(event)"
															value="${questionsAndAnswersForm.queryQuestion}"
															maxlength="23" />
													</div>
												</div>
												<div class="form-actions">
													<c:set value="${selectPageFuncId}_Query" var="queryRole" />
													<c:if
														test="${fn:containsIgnoreCase(questionAndAnswerRole,queryRole)}">
														<button type="button"
															onclick="queryQuestionAndAnswer('Y')"
															class="btn btn-default btn-primary ">Query</button>
													</c:if>
													<c:set value="${selectPageFuncId}_Add" var="addRole" />
													<c:if
														test="${fn:containsIgnoreCase(questionAndAnswerRole,addRole)}">
														<button type="button" onclick="showEdit()"
															class="btn btn-default btn-success " data-toggle="modal"
															data-placement="left"
															data-target="#editQuestionAndAnswer">Add</button>
													</c:if>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
							<!--row-fluid portlet-->
							<c:if
								test="${fn:length(questionsAndAnswersForm.questionsAndAnswersList)>0}">
								<!-- 查詢結果部分 -->
								<div class="row-fluid portlet">
									<div class="span12">
										<!-- BEGIN GRID PORTLET-->
										<div class="widget">
											<div class="widget-title">
												<h4>
													<i class="icon-reorder"></i>Query Result
												</h4>
												<span class="tools"><a href="javascript:;"
													class="icon-chevron-up"></a></span>
											</div>
											<div class="widget-body">
												<div class="cyberTable">
													<table class="table-hover" id="data_table_1" width="100%"
														border="0" cellpadding="0" cellspacing="0">
														<thead>
															<tr>
																<th style="text-align: center;" width="20px">No</th>
																<th style="text-align: center;" width="70px">Operation</th>
																<th style="text-align: center;" width="50px">Category</th>
																<th style="text-align: center;" width="200px">Question/Column</th>
																<th style="text-align: center;" width="200px">Answer/Content</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach
																items="${questionsAndAnswersForm.questionsAndAnswersList }"
																var="questionsAndAnswers" varStatus="row">
																<tr>
																	<td style="text-align: center;" class="blue"><lable
																			class="control-label">
																		<font style="font-size: 14px">${(model.pageInfo.page*model.pageInfo.size) + row.index + 1 }</font>
																		</label></td>
																	<td style="text-align: center;" class="blue"><c:set
																			value="${selectPageFuncId}_View" var="viewRole" /> <c:if
																			test="${fn:containsIgnoreCase(questionAndAnswerRole,viewRole)}">
																			<button class="btn popovers " data-trigger="hover"
																				data-toggle="modal" data-placement="left"
																				data-target="#editQuestionAndAnswer"
																				data-content="View"
																				onclick='showEdit(${cyber:toJsonString(questionsAndAnswers)},"V")'>
																				<i class="icon-eye-open icon-white "></i>
																			</button>
																		</c:if> <c:set value="${selectPageFuncId}_Edit"
																			var="editRole" /> <c:if
																			test="${fn:containsIgnoreCase(questionAndAnswerRole,editRole)}">
																			<button class="btn popovers btn-primary"
																				data-trigger="hover" data-toggle="modal"
																				data-placement="left"
																				data-target="#editQuestionAndAnswer"
																				data-content="Edit"
																				onclick='showEdit(${cyber:toJsonString(questionsAndAnswers)},"E")'>
																				<i class="icon-edit icon-white "></i>
																			</button>
																		</c:if> <c:set value="${selectPageFuncId}_Delete"
																			var="deleteRole" /> <c:if
																			test="${fn:containsIgnoreCase(questionAndAnswerRole,deleteRole)}">
																			<button class="btn popovers btn-danger"
																				data-trigger="hover" data-placement="left"
																				data-content="Delete"
																				onclick="deleteUser('${questionsAndAnswers.id}')">
																				<i class="icon-remove icon-white "></i>
																			</button>
																		</c:if></td>
																	<td style="text-align: center;" class="blue"><lable
																			class="control-label">
																		<font style="font-size: 14px">${questionsAndAnswers.categoryName}</font>
																		</label></td>
																	<td title="${questionsAndAnswers.question}"
																		style="text-align: center; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"
																		class="blue"><lable class="control-label">
																		<font style="font-size: 14px">${questionsAndAnswers.question}</font>
																		</label></td>
																	<td title="${questionsAndAnswers.answer}"
																		style="text-align: center; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"
																		class="blue"><lable class="control-label">
																		<font style="font-size: 14px">${questionsAndAnswers.answer}</font>
																		</label></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
													<div class="pagination pagination-right"
														style="padding-bottom: 20px;">
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
							<div class="modal fade" id="editQuestionAndAnswer" role="dialog"
								style="display: none; position: fixed; top: 30%; left: 50%"
								tabindex="-1" data-backdrop="static">
								<div class="modal-dialog">
									<form class="form-horizontal" id="editQuestionAndAnswerForm"
										method="post">
										<input type="hidden"
											name="<%=QuestionsAndAnswersDTO.ATTRIBUTE.ID.getValue()%>"
											id="hidden<%=QuestionsAndAnswersDTO.ATTRIBUTE.ID.getValue()%>" />
										<input type="hidden" id="addFlag" />
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h3 class="modal-title"></h3>
											</div>
											<!-- 隐藏域 -->
											<div class="modal-body">
												<div class="control-group position-right">
													<label class="control-label" style="width: 180px;">Category<span
														class="red">* &nbsp;&nbsp;</label>
													<div class="controls">
														<select class="span12" tabindex="1"
															data-placeholder="Please select..."
															name="<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>"
															id="<%=QuestionsAndAnswersDTO.ATTRIBUTE.CATEGORY.getValue()%>">
															<option value="">Please select...</option>
															<option value="1">help</option>
															<!-- <option value="2">contact</option> -->
															<option value="3">about</option>
														</select>
													</div>
												</div>
												<div class="control-group position-right">
													<label class="control-label" style="width: 180px;">Question/Column&nbsp;&nbsp;</label>
													<div class="controls">
														<input type="text" class="span12"
															id="<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>"
															name="<%=QuestionsAndAnswersDTO.ATTRIBUTE.QUESTION.getValue()%>"
															maxlength="100" onkeyup="value=filterScript(this.value)"
															onkeydown="return ClearSubmit(event)" />
													</div>
												</div>
												<div class="control-group position-right">
													<label class="control-label" style="width: 180px;">Answer/Content<span
														class="red">* &nbsp;&nbsp;</label>
													<div class="controls">
														<%-- <input type="text" class="span12" id="<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>"  name="<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>"   maxlength="200" onkeyup="value=stripscript(this.value)"/> --%>
														<textarea rows="" cols="" class="span12"
															id="<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>"
															name="<%=QuestionsAndAnswersDTO.ATTRIBUTE.ANSWER.getValue()%>"
															maxlength="2000" onkeyup="value=filterScript(this.value)"></textarea>
													</div>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" id="confirmId"
													class="btn btn-success btn-default"
													onclick="onclickConfirm()">Save</button>
												<button type="button" class="btn btn-warning  btn-default"
													data-dismiss="modal" id="cancelModal">Cancel</button>
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

	<%@ include file="../common/resPassWord.jsp"%>

	<!-- BEGIN JAVASCRIPTS -->

</body>
<!-- END BODY -->
</html>
