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
		
		<c:if test="${command.displayArticleListWithTag}">
			<%@ include file="leftSpace/articleListJsp.jsp" %>
			<%@ include file="leftSpace/paginationArticleListWithTagJsp.jsp" %>
		</c:if>
				
		<c:if test="${command.displayExplanation}">
			<%@ include file="leftSpace/explanationJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displayAboutMe}">
			<%@ include file="leftSpace/aboutMeJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displayException}">
			<%@ include file="leftSpace/exceptionJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displaySecArticleList}">
			<%@ include file="leftSpace/secured/secArticleListJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displaySecViewArticle}">
			<%@ include file="leftSpace/secured/secArticleJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displaySecEditArticle}">
			<%@ include file="leftSpace/secured/secArticleJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displaySecCreateArticle}">
			<%@ include file="leftSpace/secured/secArticleJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displaySecDeleteArticle}">
			<%@ include file="leftSpace/secured/secArticleJsp.jsp" %>
		</c:if>
		
		<c:if test="${command.displaySecConfirmation}">
			<%@ include file="leftSpace/secured/secConfirmationJsp.jsp" %>
		</c:if>
		
	</div>
</div>