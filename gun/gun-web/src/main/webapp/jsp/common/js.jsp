<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/importJavaClass.jsp"%> 
<%-- <script type="text/javascript" src="${contextPath}/js/jquery-1.8.3.js"></script> --%>
<%-- <script type="text/javascript" src="${contextPath}/js/jquery.1.11.min.js"></script> --%>
<script type="text/javascript" src="${contextPath}/assets/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/plugins/jquery-slimscroll/jquery-ui-1.9.2.custom.min.js"></script> 
<script type="text/javascript" src="${contextPath}/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script> 
<script type="text/javascript" src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script> 
<script type="text/javascript" src="${contextPath}/assets/plugins/jquery.cookie.js"></script> 
<script type="text/javascript" src="${contextPath}/assets/plugins/jquery.blockui.js"></script>
<script type="text/javascript" src="${contextPath}/assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/plugins/bootstrap-fileupload.js"></script>

<script type="text/javascript" src="${contextPath}/assets/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/plugins/jquery.sidr.min.js"></script> 
<script type="text/javascript" src="${contextPath}/assets/plugins/uniform/jquery.uniform.min.js"></script> 
<script type="text/javascript" src="${contextPath}/assets/plugins/jquery.backstretch.min.js"></script>
<script type="text/javascript" src="${contextPath}/assets/plugins/gritter/js/jquery.gritter.js"></script>
<script type="text/javascript" src="${contextPath}/assets/js/app.js"></script>
<script type="text/javascript" src="${contextPath}/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${contextPath}/assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="${contextPath}/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="${contextPath}/assets/plugins/bootstrap-daterangepicker/date.js"></script>
<script type="text/javascript" src="${contextPath}/js/lottery_utilities.js"></script>
<script type="text/javascript" src="${contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${contextPath}/js/jquery.paginate.js"></script>
<script type="text/javascript" src="${contextPath}/js/base-loading.js"></script>
<script type="text/javascript" src="${contextPath}/assets/bootstrap/lobibox/Lobibox.min.js"></script>
<script type="text/javascript" src="${contextPath}/tableColResizable/js/colResizable-1.5.min.js"></script>
<script type="text/javascript" src="${contextPath}/viewImages/layer/layer.js"></script>
<script type="text/javascript" src="${contextPath}/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${contextPath}/jBox/js/jquery.jBox.src.js"></script>
<script type="text/javascript">
	history.pushState(null, null, document.URL);
        window.addEventListener('popstate', function () {
            history.pushState(null, null, document.URL);
        });
	var onSampleResized;
	var onkeyNumb = 0;
	var alertFlag = false;
	var lastTime = new Date().getTime();
    var currentTime = new Date().getTime();
    var timeOut = 5 * 60 * 1000; //设置超时时间：5分
    var logoffTime = 0;
    var countDown = 60;
    var showConfirmFlag = false;
// 	jeDate({
// 		dateCell:"#dateinfo",
// 		format:"YYYY/MM/DD hh:mm:ss",
// 		isinitVal:true,
// 		isTime:true, //isClear:false,
// 		minDate:"2017-07-19 00:00:00",
// 		okfun:function(val){}
// 	})
	jQuery(document).ready(function() {
		// initiate layout and plugins
		App.init(); // initlayout and core plugins
// 		Index.initIntro();
		
		$('#m-menu').sidr();
		$('.carousel').carousel({
			interval : 4000
		});
		onSampleResized = function(e){
			var columns = $(e.currentTarget).find("th");
		};
		
		
		 /* jBox 全局设置 */
        var _jBoxConfig = {};
        _jBoxConfig.defaults = {
            id: null, /* 在页面中的唯一ID，如果为null则自动为随机ID,一个ID只会显示一个jBox */
            top: '30%', /* 窗口离顶部的距离,可以是百分比或像素(如 '100px') */
            border: 5, /* 窗口的外边框像素大小,必须是0以上的整数 */
            opacity: 0.2, /* 窗口隔离层的透明度,如果设置为0,则不显示隔离层 */
            timeout: 0, /* 窗口显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
            showType: 'fade', /* 窗口显示的类型,可选值有:show、fade、slide */
            showSpeed: 'fast', /* 窗口显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
            showIcon: true, /* 是否显示窗口标题的图标，true显示，false不显示，或自定义的CSS样式类名（以为图标为背景） */
            showClose: false, /* 是否显示窗口右上角的关闭按钮 */
            draggable: true, /* 是否可以拖动窗口 */
            dragLimit: true, /* 在可以拖动窗口的情况下，是否限制在可视范围 */
            dragClone: false, /* 在可以拖动窗口的情况下，鼠标按下时窗口是否克隆窗口 */
            persistent: true, /* 在显示隔离层的情况下，点击隔离层时，是否坚持窗口不关闭 */
            showScrolling: true, /* 是否显示浏览的滚动条 */
            ajaxData: {},  /* 在窗口内容使用post:前缀标识的情况下，ajax post的数据，例如：{ id: 1 } 或 "id=1" */
            iframeScrolling: 'auto', /* 在窗口内容使用iframe:前缀标识的情况下，iframe的scrolling属性值，可选值有：'auto'、'yes'、'no' */

            title: 'jBox', /* 窗口的标题 */
            width: 350, /* 窗口的宽度，值为'auto'或表示像素的整数 */
            height: 'auto', /* 窗口的高度，值为'auto'或表示像素的整数 */
            bottomText: '', /* 窗口的按钮左边的内容，当没有按钮时此设置无效 */
            buttons: { 'Ok': 'ok' }, /* 窗口的按钮 */
            buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
            loaded: function (h) { }, /* 窗口加载完成后执行的函数，需要注意的是，如果是ajax或iframe也是要等加载完http请求才算窗口加载完成，参数h表示窗口内容的jQuery对象 */
            submit: function (v, h, f) { return true; }, /* 点击窗口按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
            closed: function () { } /* 窗口关闭后执行的函数 */
        };
        _jBoxConfig.stateDefaults = {
            content: '', /* 状态的内容，不支持前缀标识 */
            buttons: { 'Ok': 'ok' }, /* 状态的按钮 */
            buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
            submit: function (v, h, f) { return true; } /* 点击状态按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
        };
        _jBoxConfig.messagerDefaults = {
            content: '', /* 信息的内容，不支持前缀标识 */
            title: 'jBox', /* 信息的标题 */
            icon: 'none', /* 信息图标，值为'none'时为不显示图标，可选值有'none'、'info'、'question'、'success'、'warning'、'error' */
            width: 350, /* 信息的高度，值为'auto'或表示像素的整数 */
            height: 'auto', /* 信息的高度，值为'auto'或表示像素的整数 */
            timeout: 3000, /* 信息显示多少毫秒后自动关闭,如果设置为0,则不自动关闭 */
            showType: 'slide', /* 信息显示的类型,可选值有:show、fade、slide */
            showSpeed: 600, /* 信息显示的速度,可选值有:'slow'、'fast'、表示毫秒的整数 */
            border: 0, /* 信息的外边框像素大小,必须是0以上的整数 */
            buttons: {}, /* 信息的按钮 */
            buttonsFocus: 0, /* 表示第几个按钮为默认按钮，索引从0开始 */
            loaded: function (h) { }, /* 窗口加载完成后执行的函数，参数h表示窗口内容的jQuery对象 */
            submit: function (v, h, f) { return true; }, /* 点击信息按钮后的回调函数，返回true时表示关闭窗口，参数有三个，v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗口内容里的form表单键值 */
            closed: function () { } /* 信息关闭后执行的函数 */
        };
        _jBoxConfig.languageDefaults = {
            Close: 'Close', /* 窗口右上角关闭按钮提示 */
            Ok: 'Ok', /* $.jBox.prompt() 系列方法的“确定”按钮文字 */
            Yes: 'Yes', /* $.jBox.warning() 方法的“是”按钮文字 */
            No: 'No', /* $.jBox.warning() 方法的“否”按钮文字 */
            Cancel: 'Cancel' /* $.jBox.confirm() 和 $.jBox.warning() 方法的“取消”按钮文字 */
        };

        $.jBox.setDefaults(_jBoxConfig);
		/* 鼠标移动事件 */
        $(document).mouseover(function(){
        	if(!showConfirmFlag){
        		lastTime = new Date().getTime(); //更新操作时间
             	logoffTime = 0;
             	countDown = 60;
        	}
        });
        window.setInterval(function(){checkTimeoutFunction();}, 1000); 
	});
	
	$(function () {
		//全選 
		$("#checkedAll").click(function () {
     		$(":checkbox").attr("checked", this.checked);
     		if (!this.checked){
  	   			$(this).parent("span").css('background-position', '0px -260px');
  	 			$(":checkbox").parent("span").css('background-position', '0px -260px');
  	   			flag = false;
     		}else{
  	   			$(this).parent("span").css('background-position', '-76px -260px');
  	 			$(":checkbox").parent("span").css('background-position', '-76px -260px');
     		}
     
  		});
		//檢測是否為全選狀態 
 		$("[name=items]:checkbox").click(function () {
       		var flag = true;
       		$("[name=items]:checkbox").each(function () {
	           	if (!this.checked){
	        		$(this).parent("span").css('background-position', '0px -260px');
	        		flag = false;
	           	}else{
	        		$(this).parent("span").css('background-position', '-76px -260px');
	           	}
      		});
       		$("#checkedAll").attr("checked", flag);
	       	if(flag==true){
	    		$("#checkedAll").parent("span").css('background-position', '-76px -260px');
	       	}else{
	    	   	$("#checkedAll").parent("span").css('background-position', '0px -260px');
	       	}
   		});
	});
	
	
	
	window.onload=function(){
        document.getElementsByTagName("body")[0].onkeydown =function(){  
            if(event.keyCode==8){  
                var elem = event.srcElement;  
                var name = elem.nodeName;  
                if(name!='INPUT' && name!='TEXTAREA'){  
                    event.returnValue = false ;  
                    return ;  
                }  
                var elemType = elem.type.toUpperCase();  
                if(name=='INPUT' && (elemType!='TEXT' && elemType!='TEXTAREA' && elemType!='PASSWORD' && elemType!='FILE')){  
                    event.returnValue = false ;  
                    return ;  
                }  
                if(name=='INPUT' && (elem.readOnly==true || elem.disabled ==true)){  
                    event.returnValue = false ;  
                    return ;  
                }  
            }  
        }  
    } 
	
	
	//處理滾動條 頁面位置
	function handleScrollTop(objId){
		if(objId!=null){
			$("html,body").animate({ scrollTop: (-150) + $("#"+objId).offset().top }, 500);
		}
	}
	
    function eformReload(pSource,pParam,link21,link22,Arg2) {
    	var obj=window.opener;	
    	if (obj=="null"){
    	}else{
    		if(Arg2=="Approve") {
	    		if (pSource=="efp2_21"){
	    			obj.location.href=link21 +'?'+pParam;
	    			obj.location.reload();	
	    		}
	    		else if (pSource=="efp2_22"){
	    			obj.location.href=link22 +'?'+ pParam;
	    			obj.location.reload();
	    		}
    		}
    	}
    }
  //檢核文本域【最多可輸入500個中英文字】
	function limitTextArea(field,checkObj){ 
	  	if(checkObj=="0"){
	  		var maxlimit=500; 
		  	}else if(checkObj=="1"){
		  		var maxlimit=2000; 
		  	}
   	   if (field.value.length > maxlimit) {
	   		if(checkObj=="0"){
	   		 alert("最多可輸入500個中英文字!");
		  	}else if(checkObj=="1"){
		  		 alert("最多可輸入2000個中英文字!");
		  	}
    	  field.value = field.value.substring(0, maxlimit); 
    	  field.focus();
    	 }
    }
  //Task #5633 所有textarea改成高度可動態變高 add by Felxili
	var adjustedOriginalHeight;
	function autogrow(textarea,checkObj){//for readonly="readonly" : onfocus
		adjustedOriginalHeight=textarea.clientHeight;
		autogrowOnkeyup(textarea,checkObj);
	}
	function autogrowOnkeyup(textarea,checkObj){//for onkeyup
		var adjustedHeight=textarea.clientHeight;
			adjustedHeight=Math.max(textarea.scrollHeight,adjustedHeight);
			if (adjustedHeight>textarea.clientHeight){
				textarea.style.height=adjustedHeight+'px';
			}
			limitTextArea(textarea,checkObj);
	}
	function autogrowOnblur(textarea){//for onblur
		var adjustedHeight=textarea.clientHeight;
		textarea.style.height=adjustedOriginalHeight+'px';
	}
	
	function ControlPrintEnable() {    
        if (typeof ScriptEngine == 'function') { //IE
            document.onreadystatechange = function () {
                if (document.readyState == "interactive" || document.readyState == "complete") {
                	$.unblockUI();
                }
            }
        }
    }
  function getnowtime() {  
          var nowtime = new Date();  
          var year = nowtime.getFullYear();  
          var month = padleft0(nowtime.getMonth() + 1);  
          var day = padleft0(nowtime.getDate());  
          return year + "-" + month + "-" + day;  
  }  
  //补齐两位数  
   function padleft0(obj) {  
       return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
   }
   
   function stripscript(s){ 
	var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\]<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]") 
	var rs = ""; 
	for (var i = 0; i < s.length; i++) { 
		rs = rs+s.substr(i, 1).replace(pattern, ''); 
	} 
	return rs; 
   } 
   
   //檢核上傳檔案大小size單位為MB 
   function chksize(inputId,size){
       var fileSizes = 0;
       $("input[name="+inputId+"]").each(function(){
           if($(this).val()){
          if ($.browser.msie) { //IE
           if(10 > $.browser.version){
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            fileSize = fileSystem.GetFile(this.value).size;
           }else{
            fileSize = this.files[0].size; //forIE10Up
           }
          }else{ //NoIE
    fileSize = this.files[0].size; 
          }
   fileSizes += fileSize;
           }
       });
       if(0 == fileSizes){
        return false;
       }else if(size*1024*1024 < fileSizes){
        return false;
       }else{
        return true;
       }
   }
   
   function checkTimeoutFunction(){
   	currentTime = new Date().getTime(); //更新当前时间
    if(currentTime - lastTime > timeOut){ //判断是否超时
    	showConfirmFlag = true;
    	logoffTime = logoffTime+1;
    	countDown = countDown-1;
    	var submit = function (v, h, f) {
          if (v == true){
          	  lastTime = new Date().getTime(); //更新操作时间
              logoffTime = 0;
              countDown = 60;
              showConfirmFlag = false;
          }
          else{
         	 top.location.href = '${contextPath}/logout.do';
          }
          return true;
      	};
      	if(logoffTime<=60){
      		jBox.confirm(countDown+" seconds later, the system will automatically log off, continue?", "System information", submit, { id:'checkTimeoutId', showScrolling: false, buttons: { 'Yes': true, 'No': false } });
	    	$(".contentText").html(countDown+" seconds later, the system will automatically log off, continue?");
	    	if(logoffTime==60){
    			top.location.href = '${contextPath}/logout.do';
    	  	}
      	}
      }
   }
   function ClearSubmit(e) {
       if (e.keyCode == 13) {
           return false;
       }
   }

   //计算两个时间相隔的天数
   function dateDiff(startDate, endDate){
		    var aDate, oDate1, oDate2, iDays ;
		    aDate = startDate.split('-');
		    oDate1 = new Date(aDate[1]+'-'+aDate[2]+'-'+aDate[0]) ;
		    aDate = endDate.split('-');
		    oDate2 = new Date(aDate[1]+'-'+ aDate[2] +'-'+aDate[0]);
		    iDays = parseInt(Math.abs(oDate1 -oDate2)/1000/60/60/24); //把相差的毫秒数转换为天数
		    return iDays ;
   }
   
   	function focusNextInput(thisInput) {
		if(thisInput.type!='button'){
			onkeyNumb = 0;
		}
		if(onkeyNumb==0 && !alertFlag){
            var inputs = $(".convertEnter")
	        for(var i = 0;i<inputs.length;i++){
	          // 如果是最后一个，则焦点回到第一个
	        if(thisInput == inputs[i]){
	        	 try {
                     inputs[i+1].focus();
	           		 break;
                 } catch (e) {//到了最后一个的下一个可能找不到元素会出现异常通过try 捕捉不至于程序出现异常
                     return false;//必须要写以免错误信息被提交
                 }
	          }
	        }
		}
	}
	
	function closeAlert(){
	  	$(".btn-close").click(function(){
			alertFlag  = false;
		});
	  }
</script>