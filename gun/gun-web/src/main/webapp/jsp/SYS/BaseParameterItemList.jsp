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
// 		$("option").click(function(){  
//             $("#queryEffectiveDate").removeAttr("size");  
//             $("#queryEffectiveDate").blur();  
//             $(this).attr("selected","");  
//         });  
  
//         $("#queryEffectiveDate").focus(function(){  
//             $("#queryEffectiveDate").attr("size","5");  
//         }); 
//         $("#queryEffectiveDate").blur(function(){  
//             $("#queryEffectiveDate").removeAttr("size");  
//             $("#queryEffectiveDate").attr("selected",""); 
//         });
	});
	
	/**
	  *分页方法
	  */
	 function changePage(page) {
	 	showLoading();
	 	$("#pageNo").val(page);
	 	$("#command").attr("action","<%=LotteryConstants.QUERY_BASE_PARAMETER_ITEM%>.do").submit();
	 }
	
	 /**
	  *查询 
	  */
	  function queryBaseParameterItem() {
	  var queryEffectiveDate = $("#queryEffectiveDate").val();
	  var queryBptdCode = $("#queryBptdCode").val();
// 	  if(isEmpty(queryEffectiveDate)){
// 	  		Lobibox.alert('info', {
//                     msg: "Please select the Effective Date!"
//             });
// 			return false;
// 		}
	   $("#hiddenQueryEffectiveDate").val(queryEffectiveDate);
	   $("#hiddenQueryBptdCode").val(queryBptdCode);
	   showLoading();
	   $("#command").attr("action","<%=LotteryConstants.QUERY_BASE_PARAMETER_ITEM%>.do").submit();
	  }
	  
	  function initEdit(bptdCode,effectiveDate,editFlag,approvedFlag){
	  	$("#bptdCode").val(bptdCode);
	  	$("#effectiveDate").val(effectiveDate);
	  	$("#editFlag").val(editFlag);
	  	$("#approvedFlag").val(approvedFlag);
	  	showLoading();
	  	$("#command").attr("action","<%=LotteryConstants.INIT_EDIT_BASE_PARAMETER_ITEM%>.do").submit();
	  }
	  
	  function deleteBaseParameter(bptdCode,effectiveDate){
	  	$("#bptdCode").val(bptdCode);
	  	$("#effectiveDate").val(effectiveDate);
	  	Lobibox.confirm({
                    msg: "Confirm delete?",
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                        	showLoading();
	  						$("#command").attr("action","<%=LotteryConstants.DELETE_BASEPARAMETERITEMDEF%>.do").submit();
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
		    <h1 class="page-title">　<i class="icon-dashboard"></i>Parameter Setting<small></small> </h1>
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
                 <input type="hidden"  name="selectPageFuncId" value="${selectPageFuncId }">
                 <input type="hidden" id="pageNo" name="pageNo" value="${baseParameterItemForm.pageNo }">
                 <input type="hidden"  id="hiddenQueryEffectiveDate"  name="queryEffectiveDate" value="${baseParameterItemForm.queryEffectiveDate }">
                 <input type="hidden"  id="hiddenQueryBptdCode"  name="queryBptdCode" value="${baseParameterItemForm.queryBptdCode }">
                 <input type="hidden" id="bptdCode" name="bptdCode" value="">
                 <input type="hidden" id="effectiveDate" name="effectiveDate" value="">
                 <input type="hidden" id="editFlag" name="editFlag" value="">
                 <input type="hidden" id="approvedFlag" name="approvedFlag" value="">
                 <input type="hidden"  id="queryFlag"  name="queryFlag" value="${baseParameterItemForm.queryFlag }">
                <div class="control-group">
					<label class="control-label">Parameter Type</label>
					<div class="controls">
						<select class="pre-scrollable" tabindex="1"  data-placeholder="Please select..." id="queryBptdCode" style="width: 220px;">
	                          	<option value="">Please select...</option>
	                          	<c:forEach items="${baseParameterTypeList}" var="typeList">
	                          		<option value="${typeList.value}" ${typeList.value==baseParameterItemForm.queryBptdCode ? 'selected' : ''}>${typeList.name}</option>
	                          	</c:forEach>
		               </select>
					</div>
				</div>
                <div class="control-group">
					<label class="control-label">Effective Date</label>
					<div class="controls">
						<select class="pre-scrollable" tabindex="1"  data-placeholder="Please select..." id="queryEffectiveDate" style="width: 220px;">
	                          	<option value="">Please select...</option>
	                          	<c:forEach items="${effectiveDateList}" var="effectiveDate">
	                          		<option value="${effectiveDate.value}" ${effectiveDate.value==baseParameterItemForm.queryEffectiveDate ? 'selected' : ''}>${effectiveDate.name}</option>
	                          	</c:forEach>
		               </select>
					</div>
				</div>
				 <div class="form-actions">
				 	 <c:set value="${selectPageFuncId}_Query" var="queryRole"/>
				 	 <c:if test="${fn:containsIgnoreCase(baseParameterItemRole,queryRole)}">
                   	 <button type="button" onclick="queryBaseParameterItem()" class="btn btn-default btn-primary ">Query</button>
                   	 </c:if>
                   	 <c:set value="${selectPageFuncId}_Add" var="addRole"/>
				 	 <c:if test="${fn:containsIgnoreCase(baseParameterItemRole,addRole)}">
              		 <button type="button" onclick="initEdit('','','Add','')" class="btn btn-success  btn-default ">Add</button>
              		 </c:if>
                 </div>	
               </form>
              </div>
            </div>
           </div>
	     </div><!--row-fluid portlet-->
	     <c:if test="${fn:length(baseParameterItemForm.results)>0}">
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
								<th style="text-align: center;">Operation</th>
								<th style="text-align: center;">Parameter Type Name</th>
								<th style="text-align: center;">Effectively Date</th>
								<th style="text-align: center;">Ineffectively Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${baseParameterItemForm.results }" var="baseParameterItem" varStatus="row">
							<tr>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${(model.pageInfo.page*model.pageInfo.size) + row.index + 1 }</font> </label></td>
								<td style="text-align: center;">
									<c:set value="${selectPageFuncId}_View" var="viewRole"/>
                        			<c:if test="${fn:containsIgnoreCase(baseParameterItemRole,viewRole)}">
									<button class="btn popovers " data-trigger="hover"  data-placement="left"  data-content="View" 
									 onclick='initEdit("${baseParameterItem.bptdCode}","${baseParameterItem.effectiveDate}","V","${baseParameterItem.approvedFlag}")'>
									 <i class="icon-eye-open icon-white "></i></button>
									</c:if>
									<c:if test="${baseParameterItem.approvedFlag ne 'Y' }">
									<c:set value="${selectPageFuncId}_Approval" var="approvalRole"/>
                        			<c:if test="${fn:containsIgnoreCase(baseParameterItemRole,approvalRole)}">
                        			<c:set value="<%=userSession.getUserId()%>" var="loginUser"/>
                        			<c:if test="${loginUser ne baseParameterItem.updatedById}">
                        				<button class="btn popovers btn-info" data-trigger="hover" data-placement="left" data-content="Approval"
										 onclick='initEdit("${baseParameterItem.bptdCode}","${baseParameterItem.effectiveDate}","A","${baseParameterItem.approvedFlag}")' >
										 <i class="icon-check icon-white "></i>
										</button>
                        			</c:if>
									</c:if>
									<c:set value="${selectPageFuncId}_Edit" var="editRole"/>
                        			<c:if test="${fn:containsIgnoreCase(baseParameterItemRole,editRole)}">
									<button class="btn popovers btn-primary" data-trigger="hover" data-placement="left" data-content="Edit"
									 onclick='initEdit("${baseParameterItem.bptdCode}","${baseParameterItem.effectiveDate}","E","${baseParameterItem.approvedFlag}")' >
									 <i class="icon-edit icon-white "></i>
									</button>
									</c:if>
									<c:set value="${selectPageFuncId}_Delete" var="deleteRole"/>
                        			<c:if test="${fn:containsIgnoreCase(baseParameterItemRole,deleteRole)}">
									<button class="btn popovers btn-danger" data-trigger="hover" data-placement="left" data-content="Delete" 
									 onclick="deleteBaseParameter('${baseParameterItem.bptdCode}','${baseParameterItem.effectiveDate}')" >
									<i class="icon-remove icon-white "></i>
									</button>
									</c:if>
									</c:if>
								</td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${baseParameterItem.ptName} </font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${baseParameterItem.effectiveDate} </font> </label></td>
								<td style="text-align: center;" class="blue"><lable class="control-label"><font style="font-size:14px">${baseParameterItem.expirationDate} </font> </label></td>
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
