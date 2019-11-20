﻿/*******************************************
 * 
 * 创建人：Felixli
 * 创建时间：2017年05月3日
 * 创建说明：Base=>页面加载（loading）效果
 * 
*********************************************/

//呈现loading效果
function showLoading() {
	//在页面未加载完毕之前显示的loading Html自定义内容
	var _PageHeight = $(window).height();
	var _PageWidth  = $(window).width();
	if (_PageWidth === 0) {
	     _PageWidth = window.innerWidth;
	  }
	if (_PageHeight === 0) {
	     _PageHeight = window.innerHeight;
	  }
	var  _LoadingHtml= "<div id='loading' style='width:"+_PageWidth+"px;height:"+_PageHeight+"px'><div id='loading-center'><div id='loading-center-absolute'>"+
				"<div class='object' id='object_one'></div>"+
				"<div class='object' id='object_two'></div>"+
				"<div class='object' id='object_three'></div>"+
				"<div class='object' id='object_four'></div>"+
				"<div class='object' id='object_five'></div>"+
				"<div class='object' id='object_six'></div>"+
				"<div style='color:#0383F7;position:relative;right:230px;top:85px'>"+
				"<hr class='hrLineRight'/> Loading <hr class='hrLineLeft'/>"+
				"</div></div></div></div>";
	$("body").append(_LoadingHtml);
}

//加载状态为complete时移除loading效果
function completeLoading() {
	var loadingMask = document.getElementById('loading');
	loadingMask.parentNode.removeChild(loadingMask);
}