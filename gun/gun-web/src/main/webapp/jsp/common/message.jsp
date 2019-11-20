<div id="op_title_name">
<div id="msg" style="display: none;z-index:50; width: 403px; height: 110px; position:fixed; top: 50px; left: 40%; background-color: #004D97; border-radius:10px 10px 10px 10px;">
    <div id="msgcent" style="font-size: 16px;padding-top:10px;text-align:center; padding-left:3px; width: 380px; height: 80px; position: absolute; top: 10px; left: 10px; background-color: #ffffff; word-break: break-all;word-wrap: break-word;border-radius:10px 10px 10px 10px;"></div>
</div>
<c:if test="${model.message!=null && model.message.code!=null}">
<script type="text/javascript">
		  $("#msg").show(500).delay(800).hide(500); 
          $("#msgcent").html('<spring:message code="${model.message.code}" arguments="${model.message.arguments}"/>');
</script>
</c:if>
</div>