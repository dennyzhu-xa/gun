<!-- Java class Start -->
<%@page import="org.springframework.util.StringUtils"%>
<%@ page import="java.util.*,java.lang.*"%>
<%@ page import="org.springframework.util.CollectionUtils"%>

<%@ page import="com.gun.common.utils.LotteryConstants" %>
<%@ page import="com.gun.common.utils.LotteryMessageCode" %>
<%
	String selectPageFuncId="";
	selectPageFuncId = request.getParameter("selectFuncId");
	if(!StringUtils.hasText(selectPageFuncId)){
		selectPageFuncId = request.getParameter("selectPageFuncId");
	}
 %>
 <c:set var="selectPageFuncId" value="<%=selectPageFuncId %>"/>
<!-- Java class End -->