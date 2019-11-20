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
	p{
		text-align: center; 
		font-size: 18sp;
		font-weight: bold;
		paddingTop: 10dp;
	}
	pre{
		paddingLeft: 10dp;
		paddingRight: 10dp;
		paddingTop: 10dp;
		font-size: 10dp;
	}
</style>
</head>
<body>
	<c:forEach items="${questionsAndAnswersForm.questionsAndAnswersList }" var="questionsAndAnswers" varStatus="row" >
		<p>${questionsAndAnswers.question}</p>
		<pre>${questionsAndAnswers.answer}</pre>
	</c:forEach>
</body>
</html>	