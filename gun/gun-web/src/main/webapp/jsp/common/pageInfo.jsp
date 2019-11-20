<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<c:set var="pageInfo" value="${model.pageInfo }"></c:set>
<c:set var="firstPageCss" scope="session" value="${pageInfo.isFirstPage ? 'disabled':''}" />
<c:set var="lastPageCss" scope="session" value="${pageInfo.isLastPage ?'disabled':''}" />
<ul style="box-shadow: 0 -1px 2px rgba(0, 0, 0, 0.05);position: absolute;right: 30px;">
<c:choose>
	<c:when test="${pageInfo.isFirstPage}">
		<li class="${firstPageCss}"><a>« First Page</a></li>
		<li class="${firstPageCss}"><a>‹ Previous Page</a></li>
	</c:when>
	<c:otherwise>
		<li class="${firstPageCss}"><a style="cursor:pointer;" onClick="changePage(0)">« First Page</a></li>
		<li class="${firstPageCss}"><a style="cursor:pointer;" onClick="changePage(${pageInfo.page-1})">‹ Previous Page</a></li>
	</c:otherwise>
</c:choose>
<%-- Bug #5290 SIT_0009372: 查詢結果分頁，無法用頁碼跳頁 --%>
<c:forEach var="i" step="1" begin="1" end="${pageInfo.pageNumbers }">
	<c:choose>
		<%-- 當前頁-4  當前頁頁碼大於等於5 並且當前頁為Last Page  顯示前四頁 --%>
        <c:when test="${pageInfo.page >= 4 && pageInfo.pageNumbers - pageInfo.page == 1 && pageInfo.page-3 eq i }">
        	<li><a class="ng-binding" style="cursor:pointer;" onClick="changePage(${pageInfo.page-4})">${i }</a></li>
        </c:when>
        <%-- 當前頁-3 當前頁頁碼大於等於4 --%>
        <c:when test="${pageInfo.page >= 3 && pageInfo.page-2 eq i && 
        	(pageInfo.pageNumbers - pageInfo.page == 1 || pageInfo.pageNumbers - pageInfo.page == 2)}">
        	<li><a class="ng-binding" style="cursor:pointer;" onClick="changePage(${pageInfo.page-3})">${i }</a></li>
        </c:when>
		<%-- 當前頁-2 當前頁頁碼大於等於3 --%>
        <c:when test="${pageInfo.page >= 2 && pageInfo.page-1 eq i }">
        	<li><a class="ng-binding" style="cursor:pointer;" onClick="changePage(${pageInfo.page-2})">${i }</a></li>
        </c:when>
        <%-- 當前頁-1 當前頁頁碼大於等於2 --%>
        <c:when test="${pageInfo.page >= 1 && pageInfo.page eq i }">
        	<li><a class="ng-binding" style="cursor:pointer;" onClick="changePage(${pageInfo.page-1})">${i }</a></li>
        </c:when>
        <%-- 當前頁 --%>
        <c:when test="${pageInfo.page+1 eq i }">
        	<li class="disabled" ><a class="ng-binding" >${i }</a></li>
        </c:when>
        <%-- 當前頁+1 --%>
        <c:when test="${pageInfo.page+2 eq i }">
        	<li><a class="ng-binding" style="cursor:pointer;" onClick="changePage(${pageInfo.page+1})">${i }</a></li>
        </c:when>
        <%-- 當前頁+2 --%>
        <c:when test="${pageInfo.page+3 eq i }">
        	<li><a class="ng-binding" style="cursor:pointer;" onClick="changePage(${pageInfo.page+2})">${i }</a></li>
        </c:when>
        <%-- 當前頁+3 當前頁頁碼小於等於2 --%>
        <c:when test="${pageInfo.page <= 1 && pageInfo.page+4 eq i }">
        	<li><a class="ng-binding" style="cursor:pointer;" onClick="changePage(${pageInfo.page+3})">${i }</a></li>
        </c:when>
        <%-- 當前頁+4 當前頁頁碼小於等於1(初始化為1) --%>
        <c:when test="${pageInfo.page == 0 && pageInfo.page+5 eq i }">
        	<li><a class="ng-binding" style="cursor:pointer;" onClick="changePage(${pageInfo.page+4})">${i }</a></li>
        </c:when>
    </c:choose>
</c:forEach>
<%-- <li><a class="ng-binding">${pageInfo.page+1}/${pageInfo.pageNumbers}</a></li> --%>
<c:choose>
	<c:when test="${pageInfo.isLastPage}">
		<li class="${lastPageCss}"><a>Next Page ›</a></li>
		<li class="${lastPageCss}"><a class="${lastPageCss}">Last Page  »</a></li>
	</c:when>
	<c:when test="${pageInfo.pageNumbers==0}">
		<li class="${firstPageCss}"><a>Next Page ›</a></li>
		<li class="${firstPageCss}"><a>Last Page  »</a></li>
	</c:when>
	<c:otherwise>
		<li class="${lastPageCss}"><a style="cursor:pointer;" onClick="changePage(${pageInfo.page+1})">Next Page ›</a></li>
		<li class="${lastPageCss}"><a style="cursor:pointer;" onClick="changePage(${pageInfo.pageNumbers-1})" class="${lastPageCss}">Last Page  »</a></li>
	</c:otherwise>
</c:choose>
</ul>

