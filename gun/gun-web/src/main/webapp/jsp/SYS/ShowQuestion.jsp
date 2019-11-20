<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<style type="text/css">
	body{
		background-color: #f3f3f3;
	}
</style>
</head>
<body>
	<form class="form-horizontal" id="command" method="post" action="showAnswer.do">
		<input type="hidden" id="queryQuestion" name="queryQuestion" value="">
		<div id="helpTitle" style="margin:0 auto; text-align:center;">
			<img src="../images/question.png" width="32px" height="32px">
			<span style="font-size: 20px;text-align: center;position: relative;top: -8px;<font color="#666666">You might want to ask</font></span>
		</div>
		<c:forEach items="${questionsAndAnswersForm.questionsAndAnswersList }" var="questionsAndAnswers" varStatus="row" >
			<p onclick="showAnswer('${questionsAndAnswers.question}')"><span>${row.index + 1}.&nbsp;</span>${questionsAndAnswers.question}</p>
			<div style="width:100%; height:1px;background-color:#D7D7D7;"></div>
		</c:forEach>
	
	</form>
	<script type="text/javascript">
		function showAnswer(queryQuestion) {
			document.getElementById("queryQuestion").value=queryQuestion;
			document.getElementById("command").submit();
		}
	</script>
</body>
</html>	