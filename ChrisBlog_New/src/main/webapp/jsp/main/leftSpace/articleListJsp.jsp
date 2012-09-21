<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
		
				
		
<div id="postList">
	<ul>
	
	
	
		<c:choose>			
			<c:when test="${command.articleList == null || 
							empty command.articleList}">
				<li>
			
					<h2><a href="#">No Article Available</a></h2>
												
					
				</li>
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${command.articleList}" var="article">
				
					<!-- c:if test="${article.numberCategoryPage == command.selectedCategoryPageCurrent}" -->				
						<li>
					
							<h2><a href="article/page/1/${article.uniqueName}">${article.title}</a></h2>
							
							<ul class="meta">
								<li><span class="metaDate" title="Creation date">${article.creationDateAsString}</span></li>
								<li><a href="${article.demoPath}" class="metaDemo" target="_blank" title="Demo">View Demo</a></li>
								<li><span class="metaAuthor" title="Article`s author">${article.author}</span></li>
								<li><span class="metaPagesCount" title="Pages count">${article.pagesCount}</span></li>
							</ul>
							
							<p>
								${article.description}
							</p>
							
							<p>
								<span class="frontAndBackEnd"><b>Front-End</b></span>: ${article.frontEndAsString}
								<br/>
								<span class="frontAndBackEnd"><b>Back-End</b></span>: ${article.backEndAsString}
							</p>
							
							<p>
								<a class="button" href="article/page/1/${article.uniqueName}">Read more</a>
							</p>
							
						</li>					
					<!-- /c:if -->
				
				</c:forEach>
			</c:otherwise>
		</c:choose>
		

		
	</ul>
</div>
	