<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<%@page import="com.gun.common.utils.LotteryConstants"%>
<c:set var="loginUrl" value="<%=LotteryConstants.LOTTERY_LOGIN%>" scope="page"></c:set>
<c:redirect url="${loginUrl}.jsp"/>