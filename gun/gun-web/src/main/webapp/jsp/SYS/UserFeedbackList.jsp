<%@page import="com.gun.common.entity.pojo.QuestionsAndAnswersDTO"%>
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
	 	$("#command").attr("action","<%=LotteryConstants.QUERY_USER_FEEDBACK%>.do").submit();
	 }
	 /**
	  *查询 
	  */
	  function queryUserFeedback(isCleanPageNo) {
	   showLoading();
	   if(isCleanPageNo=='Y'){
	   		$("#pageNo").val(null);
	   }
	   $("#hiddenQuerySuggestion").val($("#querySuggestion").val());
	   $("#command").attr("action","<%=LotteryConstants.QUERY_USER_FEEDBACK%>.do").submit();
	   
	  }
	  function changeStatus(id){
		  	Lobibox.confirm({
	                    msg: "Confirm the suggestion has been resolved?",
	                    callback: function ($this, type, ev) {
	                        if (type === 'yes') {
	                            showLoading();
						  	 	$.ajax({
						             type: "POST",
						             dataType: "json", //接受數據格式 
						             cache:false,
						             url: "<c:out value="${contextPath}"/>/confirmResolved.do",
						             data: {"ids":id},          
						             async: false,   
						             success: function(data) {
						             	if(data.success){
						             		completeLoading();
						             		$("#messageCode").val(data.messageCode);
						             		queryUserFeedback('N');
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
							                   msg: "Deal failure!"
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
		    <h1 class="page-title">　<i class="icon-dashboard"></i> User Feedback<small></small> </h1>
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
                 <input type="hidden" id="pageNo" name="pageNo" value="${userFeedbackForm.pageNo }">
                 <input type="hidden" id="messageCode" name="messageCode" value="">
                 <input type="hidden"  name="selectPageFuncId" value="${selectPageFuncId }">
				 <div class="control-group">
					<label class="control-label">Suggestion</label>
					<div class="controls">
						<input type="hidden" class="span3" id="hiddenQuerySuggestion"  name="querySuggestion"  value="${userFeedbackForm.querySuggestion}"/>
						<input type="text" class="span3" onkeydown="return ClearSubmit(event)" id="querySuggestion" value="${userFeedbackForm.querySuggestion}"/>
					</div>
				 </div>
				 <div class="form-actions">
				 		<c:set value="${selectPageFuncId}_Query" var="queryRole"/>
				 		<c:if test="${fn:containsIgnoreCase(userFeedbackRole,queryRole)}">
                        <button type="button" onclick="queryUserFeedback('Y')" class="btn btn-default btn-primary ">Query</button>
                        </c:if>
                 </div>	
               </form>
              </div>
            </div>
           </div>
	     </div><!--row-fluid portlet-->
	     <c:if test="${fn:length(userFeedbackForm.userFeedbackDTOList)>0}">
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
								<th style="text-align: center;" width="200px">Telephone</th>
								<th style="text-align: center;" width="200px">Suggestion</th>
								<th style="text-align: center;" width="100px">Deal Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userFeedbackForm.userFeedbackDTOList }" var="userFeedback" varStatus="row">
							<tr>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${(model.pageInfo.page*model.pageInfo.size) + row.index + 1 }</font> </label></td>
								<td style="text-align: center;" class="blue">
									<c:set value="${selectPageFuncId}_Approval" var="approvalRole"/>
                        			<c:if test="${fn:containsIgnoreCase(userFeedbackRole,approvalRole) and userFeedback.dealStatus == '0'}">
                        			<button class="btn popovers btn-success" data-trigger="hover" data-toggle="modal" data-placement="left"  data-content="Deal" 
									 onclick="changeStatus('${userFeedback.id}')" >
									<i class="icon-check icon-white "></i>
									</button>
									</c:if>
								</td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${userFeedback.mobileNo}</font> </label></td>
								<td title="${userFeedback.suggestion}" style="text-align: center;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" class="blue"><lable class="control-label"><font style="font-size:14px">${userFeedback.suggestion}</font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${userFeedback.dealName}</font> </label></td>
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
