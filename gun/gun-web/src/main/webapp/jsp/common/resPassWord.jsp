<%@page import="com.gun.common.utils.LotteryConstants"%>
<form id="resPasswordForm"  method="POST">
	<div id="resPassword">
	<div class="center">
	<div style="text-align: right;margin-right: 2px"><h4><i class="icon-remove" style="cursor:pointer;position: relative;bottom: 10px" onclick="cancelResPassWord('Y')"></i></h4></div>
		<div class="row-fluid">
        <div class="span12">
          <div class="row-fluid">
            <div class="span12 control-group">
              <div class="span4">
                <label class="control-label textR">Old password</label>
              </div>
              <div class="span8">
                <div class="controls">
                  <div class="input-prepend textL"> <span class="add-on"><i class="icon-lock"></i></span>
                    <input id="oldPassword" name="oldPassword" type="password"  class="span9" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        <div class="row-fluid">
        <div class="span12">
          <div class="row-fluid">
            <div class="span12 control-group">
              <div class="span4">
                <label class="control-label textR">New password</label>
              </div>
              <div class="span8">
                <div class="controls">
                  <div class="input-prepend textL"> <span class="add-on"><i class="icon-lock"></i></span>
                    <input id="newPassword" name="newPassword" type="password"  class="span9"  maxlength="10"/>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row-fluid">
            <div class="span12 control-group">
              <div class="span4">
                <label class="control-label textR">Confirm Password</label>
              </div>
              <div class="span8">
                <div class="controls">
                  <div class="input-prepend textL"> <span class="add-on"><i class="icon-lock"></i></span>
                    <input type="password" id="confirmPassword" name="confirmPassword"  class="span9" autocomplete="false" maxlength="10"/>
                  </div>
                </div>
              </div>
            </div>
          </div> 
          <div class="row-fluid">
            <div class="span12">
            	<div class="span10">
                	<button type="button" style="margin-left:100px"  class="btn btn-default btn-primary" onclick="resPassword()"><i class="icon-ok"></i> Confirm </button> &nbsp;&nbsp;&nbsp;            
                	<button type="button"  class="btn btn-default btn-primary" onclick="cancelResPassWord('N')"><i class="icon-refresh"></i> Reset&nbsp;&nbsp;&nbsp; </button>             
            	</div>
                <div class="span4">
                </div>
            </div>
          </div>
        </div> 
      </div>  
	</div>
</div>
</form>
<script type="text/javascript">
	$(function(){
			if($('body') != null){
				var height = document.body.scrollHeight;
				$("#resPassword").css("height", height);
			}
// 			$(".center").mousedown(function(e)
// 			{ 
// 				$(this).css("cursor","move"); 
// 				var offset = $(this).offset();
// 				var x = e.pageX - offset.left;
// 				var y = e.pageY - offset.top;
// 			$(document).bind("mousemove",function(ev)
// 			{ 
// 				$(".center").stop();
		
// 				var _x = ev.pageX - x+220;
// 				var _y = ev.pageY - y+240;
		
// 				$(".center").animate({left:_x+"px",top:_y+"px"},8); 
// 				}); 
// 			}); 
		
// 			$(document).mouseup(function() 
// 			{ 
// 				$(".center").css("cursor","default"); 
// 				$(this).unbind("mousemove"); 
// 			}) 
		});
		
	function cancelResPassWord(closeFlag){
		$('#resPasswordForm')[0].reset();
		if(closeFlag=='Y'){
			$("#resPassword").css("display","none")
		}
	}
	function resPassword(){
	
		if(isEmpty($.trim($("#oldPassword").val()))){
			Lobibox.alert('warning', {
                    msg: "The old password cannot be empty!"
            });
			$("#oldPassword").focus();
			return false;
		}
		var regPassword = new RegExp(/^(?![^a-zA-Z]+$)(?!\D+$)/);
		var newPassword = $.trim($("#newPassword").val());
		if(isEmpty($.trim($("#newPassword").val()))){
			Lobibox.alert('warning', {
                    msg: "The new password cannot be empty!"
            });
			$("#newPassword").focus();
			return false;
		} else if (newPassword.length < 6) {
			Lobibox.alert('info', {
                msg: "The length of new password is 6 bits at least!"
        	});
			$("#newPassword").focus();
			return false;
		} else if (!regPassword.test(newPassword)) {
			Lobibox.alert('info', {
                msg: "The password must contain both numbers and letters!"
        	});
			$("#newPassword").focus();
			return false;
		}
		if($.trim($("#newPassword").val())==$.trim($("#oldPassword").val())){
			Lobibox.alert('warning', {
                    msg: "The new password cannot be the same as the old one!"
            });
			$("#confirmPassword").focus();
			return false;
		}
		if(isEmpty($.trim($("#confirmPassword").val()))){
			Lobibox.alert('warning', {
                    msg: "Please confirm the password!"
            });
			$("#confirmPassword").focus();
			return false;
		}
		if($("#confirmPassword").val()!= $("#newPassword").val()){
			Lobibox.alert('warning', {
                    msg: "password inconsistency!"
            });
			$("#confirmPassword").focus();
			return false;
		}
		var password = $.trim($("#oldPassword").val());
		showLoading();
		$.ajax({
	             type: "POST",
	             dataType: "json", //接受數據格式 
	             cache:false,
	             url: "<c:out value="${contextPath}"/>/checkPassword.do",
	             data: {"oldPassword":password},          
	             async: false,   
	             success: function(data) {
	             	if(data.success){
	             		completeLoading();
	             		Lobibox.confirm({
			                   msg: "After the update, the system will automatically logout ?",
			                   callback: function ($this, type, ev) {
			                       if (type === 'yes') {
			                       		showLoading();
							  	 	   $("#resPasswordForm").attr("action","${contextPath}/<%=LotteryConstants.RESET_PASSWORD%>.do").submit();
			                       }
			                   }
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
			                    msg: "Old password authentication failed!"
			              });
			              return false;
			    }
	        });
	}
</script>
<style>
	.center {
	  	position: fixed;
	  	left: 50%;
	  	top: 50%;
	  	width:400px;
  	  	height:230px;
  	  	background-color: #DDDDDD;
	  	margin-left:-200px;
	  	margin-top:-250px;
	} 
	#resPassword{
		position: absolute;
		top: 0;
		left: 0;
		z-index: 30;
		width: 100%;
		height:100%;
		display: none;
		background: #f5f5f5;
		filter:alpha(opacity=90);
		opacity:0.9;
	}
</style>
