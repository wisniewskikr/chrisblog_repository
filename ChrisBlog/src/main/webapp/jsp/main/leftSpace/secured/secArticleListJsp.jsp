<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript">

	$(document).ready(function() {
		
		selectRowsTable();		
		initEventSelectionTable();		
		
	});
	
</script>

<div id="secArticleList">

	
	<form:form modelAttribute="command">
		<form:hidden path="selectedRows" />
		<form:errors path="*" />
	
		<div id="secPageHeader">
			<ul>
				<li class="secPageTitle">Articles</li>
			</ul>
		</div>
		
		
		<div id="secPageActions">
			<ul>
				<li class="secPageAction">
					<a href="secured/create-article">Create</a>
				</li>
				<li class="secPageAction">
					<a href="javascript:send('secured/handle-article-list/view-article');">View</a>
				</li>
				<li class="secPageAction">
					<a href="javascript:send('secured/handle-article-list/edit-article');">Edit</a>
				</li>
				<li class="secPageAction">
					<a href="javascript:send('secured/handle-article-list/delete-article');">Delete</a>
				</li>
			</ul>		
		</div>
		
		
		<div id="secPageContent">
		
			<table class="secPageContentTable">			
				<c:choose>
					
							
					<c:when test="${command.articleList == null || 
									empty command.articleList}">
									
						<tr><td id="secPageContentTableNoData">No data</td></tr>
						
					</c:when>
					
					
					<c:otherwise>
						<c:forEach items="${command.articleList}" var="article">
						
						<tr class="secPageContentRow">
							<td class="secPageContentColumnPoint">
								<span class="secPageContentPoint"></span>
							</td>
							<td class="secPageContentColumnCheckbox">
								<input id="${article.uniqueName}" class="secPageContentCheckbox" type="checkbox"/>
							</td>
							<td class="secPageContentColumnText">
								<div class="secPageContentTitle"><a href="secured/view-article/${article.uniqueName}">${article.title}</a></div>
								<div class="secPageContentInfo">by ${article.author} ${article.diffToCurrentDateAsString}</div>
							</td>
							<td class="secPageContentColumnStatus">
								<c:if test="${article.status == 'ACTIVE'}">
									<span class="secPageContentStatusActive" title="Status active"></span>
								</c:if>
								<c:if test="${article.status == 'NOT_ACTIVE'}">
									<span class="secPageContentStatusNotActive" title="Status not active"></span>
								</c:if>
							</td>
						</tr>
						
						</c:forEach>
					</c:otherwise>
					
					
				</c:choose>						
			</table>	
	
		</div>
		
	</form:form>
		

</div>