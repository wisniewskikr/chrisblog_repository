<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="leftSpace">
	<div id="left">		
		

		<c:if test="${command.displayArticleList}">
			<%@ include file="leftSpace/articleListJsp.jsp" %>
			<%@ include file="leftSpace/paginationArticleListJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displayArticle}">
			<%@ include file="leftSpace/articleJsp.jsp" %>
			<%@ include file="leftSpace/paginationArticleJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displaySelectedCategory}">
			<%@ include file="leftSpace/contentCategoryJsp.jsp" %>
			<%@ include file="leftSpace/paginationCategoryJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displaySelectedArticle}">
			<%@ include file="leftSpace/contentArticleJsp.jsp" %>
			<%@ include file="leftSpace/paginationArticleJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displaySelectedExplanation}">
			<%@ include file="leftSpace/contentExplanationJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displayAboutMe}">
			<%@ include file="leftSpace/contentAboutMeJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displayException}">
			<%@ include file="leftSpace/contentExceptionJsp.jsp" %>
		</c:if>
		
	</div>
</div>