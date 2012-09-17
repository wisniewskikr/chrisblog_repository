<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<c:if test="${command.selectedCategoryPagesCount > 1}">


	<div class="pagination">
	
	
		<c:set var="span" value="2"/>
	
		<c:choose>
			<c:when test="${(command.selectedCategoryPageCurrent + span) > command.selectedCategoryPagesCount}">
				<c:set var="extra" value="${(command.selectedCategoryPageCurrent + span) - command.selectedCategoryPagesCount}"/>
				<c:set var="min" value="${command.selectedCategoryPageCurrent - span - extra}"/>
			</c:when>
			<c:otherwise>
				<c:set var="min" value="${command.selectedCategoryPageCurrent - span}"/>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${(command.selectedCategoryPageCurrent - span) < 1}">
				<c:set var="extra" value="${-1 * ((command.selectedCategoryPageCurrent - span) - 1)}"/>
				<c:set var="max" value="${command.selectedCategoryPageCurrent + span + extra}"/>
			</c:when>
			<c:otherwise>
				<c:set var="max" value="${command.selectedCategoryPageCurrent + span}"/>
			</c:otherwise>
		</c:choose>
		
		
		
		
		
		<c:choose>
			<c:when test="${command.selectedCategoryPageCurrent == 1}">
				<span class="disable">« First</span>
			</c:when>
			<c:otherwise>
				<a href="categories/1/${command.selectedCategoryUniqueName}" class="first">« First</a>
			</c:otherwise>
		</c:choose>
		
		
		<c:choose>
			<c:when test="${command.selectedCategoryPageCurrent == 1}">
				<span class="disable">« Prev</span>
			</c:when>
			<c:otherwise>
				<a href="categories/${command.selectedCategoryPageCurrent - 1}/${command.selectedCategoryUniqueName}" class="previouspostslink">« Prev</a>
			</c:otherwise>
		</c:choose>			
		
		
		<c:forEach begin="1" end="${command.selectedCategoryPagesCount}" step="1" var="page">			
			<c:choose>
				<c:when test="${command.selectedCategoryPageCurrent == page}">
					<span class="current">${page}</span>
				</c:when>
				<c:when test="${command.selectedCategoryPageCurrent != page && page >= min && page <= max}">
					<a href="categories/${page}">${page}</a>
				</c:when>
			</c:choose>			
		</c:forEach>
		
		
		<c:choose>
			<c:when test="${command.selectedCategoryPageCurrent == command.selectedCategoryPagesCount}">
				<span class="disable">Next »</span>
			</c:when>
			<c:otherwise>
				<a href="categories/${command.selectedCategoryPageCurrent + 1}/${command.selectedCategoryUniqueName}" class="nextpostslink">Next »</a>
			</c:otherwise>
		</c:choose>
		
		
		<c:choose>
			<c:when test="${command.selectedCategoryPageCurrent == command.selectedCategoryPagesCount}">
				<span class="disable">Last »</span>
			</c:when>
			<c:otherwise>
				<a href="categories/${command.selectedCategoryPagesCount}/${command.selectedCategoryUniqueName}" class="last">Last »</a>
			</c:otherwise>
		</c:choose>	
	
	
	</div>
	
		
</c:if>