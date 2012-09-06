<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<c:if test="${command.selectedArticlePagesCount > 1}">


	<div class="pagination">
	
	
		<c:set var="span" value="2"/>
	
		<c:choose>
			<c:when test="${(command.selectedArticlePageCurrent + span) > command.selectedArticlePagesCount}">
				<c:set var="extra" value="${(command.selectedArticlePageCurrent + span) - command.selectedArticlePagesCount}"/>
				<c:set var="min" value="${command.selectedArticlePageCurrent - span - extra}"/>
			</c:when>
			<c:otherwise>
				<c:set var="min" value="${command.selectedArticlePageCurrent - span}"/>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${(command.selectedArticlePageCurrent - span) < 1}">
				<c:set var="extra" value="${-1 * ((command.selectedArticlePageCurrent - span) - 1)}"/>
				<c:set var="max" value="${command.selectedArticlePageCurrent + span + extra}"/>
			</c:when>
			<c:otherwise>
				<c:set var="max" value="${command.selectedArticlePageCurrent + span}"/>
			</c:otherwise>
		</c:choose>
		
		
		
		
		
		<c:choose>
			<c:when test="${command.selectedArticlePageCurrent == 1}">
				<span class="disable">« First</span>
			</c:when>
			<c:otherwise>
				<a href="articles/${command.selectedCategoryPageCurrent}/${command.selectedCategoryUniqueName}/1/${command.selectedArticleUniqueName}" class="first">« First</a>
			</c:otherwise>
		</c:choose>
		
		
		<c:choose>
			<c:when test="${command.selectedArticlePageCurrent == 1}">
				<span class="disable">« Prev</span>
			</c:when>
			<c:otherwise>
				<a href="articles/${command.selectedCategoryPageCurrent}/${command.selectedCategoryUniqueName}/${command.selectedArticlePageCurrent - 1}/${command.selectedArticleUniqueName}" class="previouspostslink">« Prev</a>
			</c:otherwise>
		</c:choose>			
		
		
		<c:forEach begin="1" end="${command.selectedArticlePagesCount}" step="1" var="page">			
			<c:choose>
				<c:when test="${command.selectedArticlePageCurrent == page}">
					<span class="current">${page}</span>
				</c:when>
				<c:when test="${command.selectedArticlePageCurrent != page && page >= min && page <= max}">
					<a href="articles/${command.selectedCategoryPageCurrent}/${command.selectedCategoryUniqueName}/${page}/${command.selectedArticleUniqueName}">${page}</a>
				</c:when>
			</c:choose>			
		</c:forEach>
		
		
		<c:choose>
			<c:when test="${command.selectedArticlePageCurrent == command.selectedArticlePagesCount}">
				<span class="disable">Next »</span>
			</c:when>
			<c:otherwise>
				<a href="articles/${command.selectedCategoryPageCurrent}/${command.selectedCategoryUniqueName}/${command.selectedArticlePageCurrent + 1}/${command.selectedArticleUniqueName}" class="nextpostslink">Next »</a>
			</c:otherwise>
		</c:choose>
		
		
		<c:choose>
			<c:when test="${command.selectedArticlePageCurrent == command.selectedArticlePagesCount}">
				<span class="disable">Last »</span>
			</c:when>
			<c:otherwise>
				<a href="articles/${command.selectedCategoryPageCurrent}/${command.selectedCategoryUniqueName}/${command.selectedArticlePagesCount}/${command.selectedArticleUniqueName}" class="last">Last »</a>
			</c:otherwise>
		</c:choose>	
	
	
	</div>
	
		
</c:if>