<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<c:if test="${command.pagesCount > 1}">


	<div class="pagination">
	
	
		<c:set var="span" value="2"/>
	
		<c:choose>
			<c:when test="${(command.pageCurrent + span) > command.pagesCount}">
				<c:set var="extra" value="${(command.pageCurrent + span) - command.pagesCount}"/>
				<c:set var="min" value="${command.pageCurrent - span - extra}"/>
			</c:when>
			<c:otherwise>
				<c:set var="min" value="${command.pageCurrent - span}"/>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${(command.pageCurrent - span) < 1}">
				<c:set var="extra" value="${-1 * ((command.pageCurrent - span) - 1)}"/>
				<c:set var="max" value="${command.pageCurrent + span + extra}"/>
			</c:when>
			<c:otherwise>
				<c:set var="max" value="${command.pageCurrent + span}"/>
			</c:otherwise>
		</c:choose>
		
		
		
		
		
		<c:choose>
			<c:when test="${command.pageCurrent == 1}">
				<span class="disable">« First</span>
			</c:when>
			<c:otherwise>
				<a href="tag/page/1/${command.articleTag.uniqueName}" class="first">« First</a>
			</c:otherwise>
		</c:choose>
		
		
		<c:choose>
			<c:when test="${command.pageCurrent == 1}">
				<span class="disable">« Prev</span>
			</c:when>
			<c:otherwise>
				<a href="tag/page/${command.pageCurrent - 1}/${command.articleTag.uniqueName}" class="previouspostslink">« Prev</a>
			</c:otherwise>
		</c:choose>			
		
		
		<c:forEach begin="1" end="${command.pagesCount}" step="1" var="page">			
			<c:choose>
				<c:when test="${command.pageCurrent == page}">
					<span class="current">${page}</span>
				</c:when>
				<c:when test="${command.pageCurrent != page && page >= min && page <= max}">
					<a href="tag/page/${page}/${command.articleTag.uniqueName}">${page}</a>
				</c:when>
			</c:choose>			
		</c:forEach>
		
		
		<c:choose>
			<c:when test="${command.pageCurrent == command.pagesCount}">
				<span class="disable">Next »</span>
			</c:when>
			<c:otherwise>
				<a href="tag/page/${command.pageCurrent + 1}/${command.articleTag.uniqueName}" class="nextpostslink">Next »</a>
			</c:otherwise>
		</c:choose>
		
		
		<c:choose>
			<c:when test="${command.pageCurrent == command.pagesCount}">
				<span class="disable">Last »</span>
			</c:when>
			<c:otherwise>
				<a href="tag/page/${command.pagesCount}/${command.articleTag.uniqueName}" class="last">Last »</a>
			</c:otherwise>
		</c:choose>
		
		
		<span class="navigation">
			<a href="javascript:history.go(-1)" class="button">back</a>
			<a href="" class="button">home</a>
		</span>	
	
	
	</div>
	
		
</c:if>