<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="secArticleList">

	
	<div id="secArticleListHeader">
		<ul>
			<li class="secArticleListTitle">Articles</li>
		</ul>
	</div>
	
	
	<div id="secArticleListActions">
		<ul>
			<li class="secArticleListAction">
				<a href="#">Create</a>
			</li>
			<li class="secArticleListAction">
				<a href="#">Edit</a>
			</li>
			<li class="secArticleListAction">
				<a href="#">Delete</a>
			</li>
		</ul>		
	</div>
	
	
	<div id="secArticleListContent">
	
		<table class="secArticleListContentTable">			
			<c:choose>
				
						
				<c:when test="${command.articleList == null || 
								empty command.articleList}">
								
					<tr><td id="secArticleListContentTableNoData">No data</td></tr>
					
				</c:when>
				
				
				<c:otherwise>
					<c:forEach items="${command.articleList}" var="article">
					
					<tr class="secArticleListContentRow">
						<td class="secArticleListContentColumnPoint">
							<span class="secArticleListContentPoint"></span>
						</td>
						<td class="secArticleListContentColumnCheckbox">
							<input class="secArticleListContentCheckbox" type="checkbox"/>
						</td>
						<td class="secArticleListContentColumnText">
							<div class="secArticleListContentTitle"><a href="#">${article.title}</a></div>
							<div class="secArticleListContentInfo">by ${article.author} ${article.diffToCurrentDateAsString}</div>
						</td>
						<td class="secArticleListContentColumnStatus">
							<c:if test="${article.status == 'ACTIVE'}">
								<span class="secArticleListContentStatusActive" title="Status active"></span>
							</c:if>
							<c:if test="${article.status == 'NOT_ACTIVE'}">
								<span class="secArticleListContentStatusNotActive" title="Status not active"></span>
							</c:if>
						</td>
					</tr>
					
					</c:forEach>
				</c:otherwise>
				
				
			</c:choose>						
		</table>	

	</div>
		

</div>