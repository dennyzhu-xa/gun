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
	var str="";
	var emptyFlag=true; 
	var eqFlag=true; 
	function saveBtnView(){
		if($("#data_table_1").find("tr").length>1){
			$("#saveBtn").show();
		}else{
			$("#saveBtn").hide();
		}
		if(isEmpty($('#bptdCode').val())){
				$("#addSpan").hide();
		}else{
			$("#addSpan").show();
		}
		if($('#bptdCode').val()=='LOTTERY_CATEGORY'){
				$("#desSpan").html("( describe:please Idle Column1 Choose to upload a picture )");
				$("#desSpan").show();
			}else if($('#bptdCode').val()=='LOTTERY_WINNIN_NAME'){
				$("#desSpan").html("( describe:please Idle Column2 input winning amount,Idle Column3 input winning quantity )");
				$("#desSpan").show();
			}else if($('#bptdCode').val()=='LOTTERY_SUB_CATEGORY'){
				$("#desSpan").html("( describe:please Idle Column2 input lottery sale price )");
				$("#desSpan").show();
			}else{
				$("#desSpan").hide();
			}
	}
	$(function(){
		$("#data_table_1").colResizable({
			liveDrag:true, 
			gripInnerHtml:"<div class='grip'></div>", 
			draggingClass:"dragging", 
			onResize:onSampleResized
		});
		
		$('#bptdCode').change(function(){ 
			var bptdCode = $(this).children('option:selected').val();
			if(isEmpty(bptdCode)){
				$("#addSpan").hide();
			}else{
				$("#addSpan").show();
			}
			if(bptdCode=='LOTTERY_CATEGORY'){
				$("#desSpan").html("( describe:please Idle Column1 Choose to upload a picture )");
				$("#desSpan").show();
			}else if(bptdCode=='LOTTERY_WINNIN_NAME'){
				$("#desSpan").html("( describe:please Idle Column2 input winning amount,Idle Column3 input winning quantity )");
				$("#desSpan").show();
			}else{
				$("#desSpan").hide();
			}
			initTable(bptdCode,'','Add');
		});
		
		saveBtnView();
		
		if('${baseParameterItemForm.editFlag}'=='Add'){
			initTable('','','');
		}else if('${baseParameterItemForm.editFlag}'=='A' || '${baseParameterItemForm.editFlag}'=='V'){
			initTable('${baseParameterItemForm.bptdCode}','${baseParameterItemForm.effectiveDate}','${baseParameterItemForm.editFlag}');
			$("#hiddenBptdCode").val('${baseParameterItemForm.bptdCode}');
	   		$("#hiddenEffectiveDate").val('${baseParameterItemForm.effectiveDate}');
			$("#bptdCode").val('${baseParameterItemForm.bptdCode}');
	   		$("#effectiveDate").val('${baseParameterItemForm.effectiveDate}');
	   		$("#bptdCode").attr("disabled",true);
	   		$("#effectiveDate").attr("disabled",true);
		}else if('${baseParameterItemForm.editFlag}'=='E'){
			initTable('${baseParameterItemForm.bptdCode}','${baseParameterItemForm.effectiveDate}','${baseParameterItemForm.editFlag}');
			$("#hiddenBptdCode").val('${baseParameterItemForm.bptdCode}');
	   		$("#hiddenEffectiveDate").val('${baseParameterItemForm.effectiveDate}');
			$("#bptdCode").val('${baseParameterItemForm.bptdCode}');
	   		$("#effectiveDate").val('${baseParameterItemForm.effectiveDate}');
	   		$("#bptdCode").attr("disabled",true);
	   		$("#effectiveDate").attr("disabled",true);
		}
	});
	
	function initTable(bptdCode,effectiveDate,editFlag){
		var approvedFlag="";
		if(editFlag=='V' && '${baseParameterItemForm.approvedFlag}'=='Y'){
			approvedFlag = "Y";
		}
		if(!isEmpty(bptdCode)){
				str = "";
				showLoading();
				$.ajax({
	             type: "POST",
	             dataType: "json",
	             cache:false,
	             url: "<c:out value="${contextPath}"/>/getParameter.do",
	             data: {"bptdCode":bptdCode,"effectiveDate":effectiveDate,"editFlag":editFlag,"approvedFlag":approvedFlag},          
	             async: true,   
	             success: function(data) {
	             	$("#data_table_1 tbody").html('');
	             	var trIndex=0;
	            	$.each(data,function() {
	            		 trIndex++;
	             		 addRow(this.bpidId,this.bptdCode,this.effectiveDate,this.itemValue,this.itemName,this.itemDesc,this.itemOrder,this.imgFlag,this.numberField1,this.numberField2,trIndex);
	             	});
	             	saveBtnView();
	             	completeLoading();
	             	if(editFlag=='A' || editFlag=='V'){
	             		if(editFlag=='A'){
			   				$("#approvalBtn").show();
				   		}else{
				   			$("#cancelBtn").text("Return");
				   		}
				   		$("#addSpan").hide();
				   		$("#saveBtn").hide();
				   		$("table[id='data_table_1'] button").attr("disabled",true);
				   		$("table[id='data_table_1'] :text").attr("disabled",true);
				   		$("table[id='data_table_1'] div[id='fileDiv']").css('display','none'); 
				   		$("table[id='data_table_1'] textarea").attr("disabled",true);
	             	}else if(editFlag=='E'){
	             		$("input[name='itemValue']").each(function(){
				   			$(this).attr("readonly",true);
					  });
	             	}
	    		},
			    error: function(data) {
			              completeLoading();
			              Lobibox.alert('error', {
			                    msg: "Data loading failure!"
			              });
			              return false;
			    },
	        });
			}else{
				$("#data_table_1 tbody").html('');
				saveBtnView();
			}
	}
	
	 function save(editFlag){
	   var bptdCode = $("#bptdCode").val();
	   var effectiveDate = $("#effectiveDate").val();
	   if(isEmpty(effectiveDate)){
	   	Lobibox.alert('info', {
                    msg: "Please select the Effective Date!"
            });
			return false;
	   }
	   $("#hiddenBptdCode").val(bptdCode);
	   $("#hiddenEffectiveDate").val(effectiveDate);
	   var emptyValueFlag = false;
	   var emptyNameFlag = false;
	   var eqValueFlag = false;
	   var eqNameFlag = false;
	   var imgTypeFlag = false;
	   var imgSizeFlag = false;
	   if(editFlag=='Add' || editFlag=='E'){
	   	var o1={};
	   	var o2={};
		$("input[name='itemValue']").each(function(){
			if(isEmpty($.trim($(this).val()))){
				emptyValueFlag = true;
		        return false;
			}
		    if(!(o1[$(this).val()])){
		        o1[$(this).val()] = true;
		    }else{
		        eqValueFlag = true;
		        return false;
		    }
		});
		$("input[name='itemName']").each(function(){
			if(isEmpty($.trim($(this).val()))){
				emptyNameFlag = true;
		        return false;
			}
		    if(!(o2[$(this).val()])){
		        o2[$(this).val()] = true;
		    }else{
		        eqNameFlag = true;
		        return false;
		    }
		});
		$("input[type='file']").each(function(){
			if(!isEmpty($(this).val())){
				if(!CheckFileExt($(this).val(),/.jpg|.gif|.png|.jpeg|.jpe|.bmp|.tif/i)){
					imgTypeFlag = true;
					return false;
			 	}
			 if (!chkImgsize(this,0.5)) {
			 		imgSizeFlag = true;
					return false;
			 	}
			}
		});
	   }
	   if(emptyValueFlag){
	   		Lobibox.alert('info', {
                    msg: "Parameter Value existence of empty values.Please amend!"
            });
	   	return false;
	   }
	   if(eqValueFlag){
	   		Lobibox.alert('info', {
                    msg: "Parameter Value existence of the same value.Please amend!"
            });
	   	return false;
	   }
	   if(emptyNameFlag){
	   		Lobibox.alert('info', {
                    msg: "Parameter Name existence of empty values.Please amend!"
            });
	   	return false;
	   }
	   if(eqNameFlag){
	   		Lobibox.alert('info', {
                    msg: "Parameter Name existence of the same value.Please amend!"
            });
	   	return false;
	   }
	   if(imgTypeFlag){
	   		Lobibox.alert('info', {
	                 msg: "The file of Category Image you uploaded is not a picture type!"

	        });
	   	return false;
	   }
	   if(imgSizeFlag){
	   		Lobibox.alert('info', {
	                 msg: "The file of Category Image can't exceed 500K!"
	        });
	   	return false;
	   }
	   
	   $(":input[type=file]").each(function(){
			$(this).attr("name",'categoryImage')
		});
		
		$("input[name='winningAmount']").each(function(){
			if(!isEmpty($(this).val())){
				$(this).attr("value",commafyback(this));
			}
		});
	   if(editFlag=='Add'){
	   		showLoading();
	   		$.ajax({
	             type: "POST",
	             dataType: "json",
	             cache:false,
	             url: "<c:out value="${contextPath}"/>/checkBaseParameterItemDef.do",
	             data: {"bptdCode":bptdCode,"effectiveDate":effectiveDate},          
	             async: true,   
	             success: function(data) {
	             	if(data){
	             		completeLoading();
	             		Lobibox.alert('info', {
			                    msg: "This version of the parameter has already existed!"
			            });
	             	}else{
	             		$("#command").attr("action","<%=LotteryConstants.EDIT_BASE_PARAMETER_ITEM%>.do").submit();
	             	}
	    		},
			    error: function(data) {
			              completeLoading();
			              Lobibox.alert('error', {
			                    msg: "Storage failure!"
			              });
			              return false;
			    },
	        });
	   }else{
	   		if(editFlag=='A'){
	   			Lobibox.confirm({
                    msg: "Confirm approval?",
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                        	showLoading();
                        	$("#command").attr("action","<%=LotteryConstants.EDIT_BASE_PARAMETER_ITEM%>.do").submit();
                        }
                    }
                });
	   		}else{
	   			showLoading();
	   			$("#command").attr("action","<%=LotteryConstants.EDIT_BASE_PARAMETER_ITEM%>.do").submit();
	   		}
	   }
	  }
	  
	  function addRow(bpidId,bptdCode,effectiveDate,value,name,desc,order,imgFlag,number1,number2,trIndex){
	  	if(desc==null){
	  		desc='';
	  	}
	  	if(order==null){
	  		order='';
	  	}
	  	if(number1==null){
	  		number1='';
	  	}
	  	if(number2==null){
	  		number2='';
	  	}
	    var rowTemplate = '<tr>'+
	    				  '<td style="text-align: center;">'+
	    				  '<button type="button" class="btn popovers btn-danger" data-trigger="hover" data-placement="right" data-content="Delete" onclick="removeRow(this)" >'+
						  '<i class="icon-remove icon-white "></i></button>'+
	    				  '</td>'+
	    				  '<td style="text-align: center;">'+
	    				  '<input type="text" name="itemValue" style="width: 80%;height: 30px;" value="'+value+'" maxlength="15" onkeyup="checkNumber(this)"></input>'+
	    				  '</td>'+
	    				  '<td style="text-align: center;">'+
	    				  '<input type="text" name="itemName" style="width: 80%;height: 30px;" value="'+name+'" maxlength="100" onkeyup="value=stripscript(this.value)"/>'+
	    				  '</td>'+
	    				  '<td style="text-align: center;">'+
	    				  '<textarea name="itemDesc" rows="2" cols="100" style="width: 80%;" maxlength="500">'+desc+'</textarea>'+
	    				  '</td>'+
	    				  '<td style="text-align: center;">'+
	    				  '<input type="text" name="itemOrder" style="width: 80%;height: 30px;" value="'+order+'" maxlength="5" onkeyup="checkNumber(this)"/>'+
	    				  '</td>'+
	    				  '<td style="text-align: center;">'+
	    				  '<div class="fileupload fileupload-new " id="fileDiv" data-provides="fileupload">'+
	    				  '<div class="input-append">'+
	    				  '<div class="uneditable-input"><i class="icon-file fileupload-exists"></i><span class="fileupload-preview"></span></div>'+
	    				  '<span class="btn btn-file"><span class="fileupload-new">Browse..</span> <span class="fileupload-exists">Change..</span>'+
	    				  '<input type="file" class="default" name="categoryImage"/>'+
	    				  '</span><a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove..</a>'+
	    				  '</div></div>';
	   	if(imgFlag=='Y'){
	   	   rowTemplate += '<div id="showCategoryImg_'+trIndex+'">'+
	    				  '<img src="loadCategoryImg.do?bpidId='+bpidId+'&&bptdCode='+bptdCode+'&&effectiveDate='+effectiveDate+'&&value='+value+'" width="80px" height="100px;" onclick="showImg('+trIndex+')"/>'+
	    				  '</div>';
	   	}
	       rowTemplate += '</td>'+
	 					  '<td style="text-align: center;">';
	 		var winningAmount="";
	 			winningAmount = formatNumber(number1);
	 			
	    rowTemplate +=	  '<input type="text" name="winningAmount" style="width: 80%;height: 30px;" onkeyup="checkAmount(this)" value="'+winningAmount+'" onblur="commafy(this)" onfocus="commafyback(this)" />'+
	    				  '</td>'+
	    				  '<td style="text-align: center;">'+
	    				  '<input type="text" name="numberOfWinners" style="width: 80%;height: 30px;" onkeyup="checkNumber(this)" value="'+number2+'" maxlength="5"/>'+
	    				  '</td></tr>';
	    				  
	    var tableHtml = $("#data_table_1 tbody").append(rowTemplate);
// 	    tableHtml += rowTemplate;
// 	    $("#data_table_1 tbody").html(tableHtml);
	    saveBtnView();
	  }
	  
	  function commafy(obj){
	  	return obj.value=formatNumber(obj.value);
	  }
	  
	  function commafyback(obj){
	  		var value = "";
	  		if(!isEmpty(obj.value)){
	  		  var array = obj.value.split(',');
	  		  if(array.length>0){
	  		  	for(var i=0;i<array.length;i++){
		       	value+=array[i]+"";
		       }
	  		  }
	  		}
	  		return obj.value=value;
		}
	  
	  function checkAmount(obj){
	  	if(obj.value==obj.value2)return;if(obj.value.search(/^\d{0,12}(?:\.\d{0,2})?$/)==-1)obj.value=(obj.value2)?obj.value2:'';else obj.value2=obj.value;
	  }
	  
	  function checkNumber(obj){
	  	obj.value=obj.value.replace(/\D|^0/g,'');
	  }
	  function checkInput(obj){
	  	obj.value=obj.value.replace(/[\W]/g,'');
	  }
	  
	  //檢視圖片格式
	  function CheckFileExt(extstr,exg){
	        var extstr = extstr.substring(extstr.lastIndexOf(".")).toLowerCase();
	        if (!extstr.match(exg)) {
	            return false;
	        }
	        return true;
	  } 
	  
	   //檢核上傳檔案大小size單位為MB 
   function chkImgsize(obj,size){
       	   var fileSizes = 0;
           if(!isEmpty(obj.value)){
           if ($.browser.msie) { //IE
           if(10 > $.browser.version){
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            fileSize = fileSystem.GetFile(obj.value).size;
           }else{
            fileSize = obj.files[0].size; //forIE10Up
           }
          }else{ //NoIE
   			 fileSize = obj.files[0].size; 
          }
  			 fileSizes += fileSize;
           }
       if(0 == fileSizes){
        return false;
       }else if(size*1024*1024 < fileSizes){
        return false;
       }else{
        return true;
       }
   }
	  
	  function removeRow(btn){
	  	$(btn).parent().parent().remove();
	  	saveBtnView();
	  }
	  
	  function showImg(id){
	  	if(isEmpty(str)){
	  		str+=id;
	  		layer.photos({
	     		        photos: '#showCategoryImg_'+id
	     		    });
	  	}else{
	  		if(str.indexOf(id)==-1){
	  			str+=id;
	  			layer.photos({
	     		        photos: '#showCategoryImg_'+id
	     		    });
	  		}
	  	}
	  }
	  
	  function cancel() {
	  var btnText = $("#cancelBtn").text();
	  var queryFlag = $("#queryFlag").val();
	  if(btnText=='Cancel'){
	  		Lobibox.confirm({
                    msg: "Confirm cancel?",
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                        	showLoading();
                        	if(queryFlag=='Y'){
                        		$("#command").attr("action","<%=LotteryConstants.QUERY_BASE_PARAMETER_ITEM%>.do").submit();
                        	}else{
                        		$("#command").attr("action","<%=LotteryConstants.INIT_BASE_PARAMETER_ITEM%>.do").submit();
                        	}
                        }
                    }
                });
		}else{
			showLoading();
          	if(queryFlag=='Y'){
          		$("#command").attr("action","<%=LotteryConstants.QUERY_BASE_PARAMETER_ITEM%>.do").submit();
          	}else{
          		$("#command").attr("action","<%=LotteryConstants.INIT_BASE_PARAMETER_ITEM%>.do").submit();
          	}
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
		    <h1 class="page-title">　<i class="icon-dashboard"></i>Parameter Setting Maintenance<small></small> </h1>
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
                 	<h4><i class="icon-reorder"></i>Parameter Setting</h4>
                     <span class="tools"><a href="javascript:;" class="icon-chevron-up"></a></span>
                 </div>
                 <div class="widget-body">
                 <form class="form-horizontal">
                <div class="control-group">
					<label class="control-label">Parameter Type <span class="red">*</span></label>
					<div class="controls">
						<select class="pre-scrollable" tabindex="1"  data-placeholder="Please select..." id="bptdCode" style="width: 220px;">
	                          	<option value="">Please select...</option>
	                          	<c:forEach items="${baseParameterTypeList}" var="typeList">
	                          		<option value="${typeList.value}">${typeList.name}</option>
	                          	</c:forEach>
		               </select>
		               <span id="desSpan" style="font-size: 14px;color:#980505;display: none;"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Effective Date <span class="red">*</span></label>
					<div class="controls">
						<div class="input-prepend">
						<span class="add-on"><i class="icon-calendar"></i></span><input type="text" id="effectiveDate" value="" onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd',lang:'en',readOnly:true,minDate:'%y-%M-%d'})" readonly="readonly" style="width: 193px;height: 30px;cursor:pointer;"/>
						</div>
					</div>
				</div>
				</form>
              </div>
            </div>
           </div>
	     </div>
	     
	     <div class="row-fluid portlet">
            <div class="span12">
              <!-- BEGIN GRID PORTLET-->
              <div class="widget">
                <div class="widget-title">
                  <h4><i class="icon-reorder"></i>Parameter List</h4>
				  <span class="tools"><a href="javascript:;"class="icon-chevron-up"></a></span>
                </div>
                <div class="widget-body">
                <form class="form-horizontal" id="command" method="post" enctype="multipart/form-data">
                 <input type="hidden"  name="selectPageFuncId" value="${selectPageFuncId }">
                 <input type="hidden" id="pageNo" name="pageNo" value="${baseParameterItemForm.pageNo }">
                 <input type="hidden" id="hiddenBptdCode" name="bptdCode" value="">
                 <input type="hidden" id="hiddenEffectiveDate" name="effectiveDate" value="">
                 <input type="hidden"  id="hiddenQueryEffectiveDate"  name="queryEffectiveDate" value="${baseParameterItemForm.queryEffectiveDate }">
                 <input type="hidden"  id="hiddenQueryBptdCode"  name="queryBptdCode" value="${baseParameterItemForm.queryBptdCode }">
                 <input type="hidden" id="editFlag" name="editFlag" value="${baseParameterItemForm.editFlag}">
                 <input type="hidden"  id="queryFlag"  name="queryFlag" value="${baseParameterItemForm.queryFlag }">
                    <div class="cyberTable">
                       <table class="table-hover" id="data_table_1" width="100%" border="0" cellpadding="0" cellspacing="0">
                        <thead>
							<tr>
								<th width="150px;">Operation
                               	<font style="color:#555555;"><span id="addSpan" title="Add">
                               	<font style="color:white;font-size:14px;cursor:pointer;"><i class="icon-plus-sign icon-white" onclick="addRow('','','','','','','','','','','')"></i></font></span></font>
								</th>
								<th width="150px">Parameter Value</th>
								<th width="200px">Parameter Name</th>
								<th width="300px">Parameter Desc</th>
								<th width="150px">Display Order</th>
								<th width="400px">Idle Column1<span style="font-size: 14px;margin-left: 5px;color: #F7DED6;">( img size:500k )</span></th>
								<th width="200px">Idle Column2</th>
								<th width="200px">Idle Column3</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
                       </table>
                       <div class="pagination pagination-right" style="padding-bottom: 20px;">
	                   	 <div style="position: absolute;right: 30px;">
		                   	 <button type="button" onclick="save('${baseParameterItemForm.editFlag}')" id="saveBtn" class="btn btn-success btn-default ">Save</button>
		                   	 <button type="button" onclick="save('${baseParameterItemForm.editFlag}')" id="approvalBtn" class="btn btn-primary btn-default " style="display: none;">Confirm</button>
		              		 <button type="button" onclick="cancel()" id="cancelBtn" class="btn btn-warning  btn-default ">Cancel</button>
	              		 </div>
	                  </div> 
                     </div>
                     </form>
                     </div>
                    </div>
                    </div>    
                   </div> 
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
