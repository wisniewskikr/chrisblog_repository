<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<div id="navcontainer">			
	<ul id="navlist">
	
	
		
		<c:choose>
			
			<c:when test="${command.categoryList == null || empty command.categoryList}">
				<li><a href="#" id="current">No Category Available</a></li>		
			</c:when>
			
			<c:otherwise>
				<c:forEach items="${command.categoryList}" var="category">
					<li>
							
						<c:choose>
							<c:when test="${(command.displaySelectedCategory || command.displaySelectedArticle) && category.uniqueName == command.selectedCategoryUniqueName}">								
								<a href="categories/1/${category.uniqueName}" 
									id="current">${category.title}</a>
							</c:when>
							<c:otherwise>
								<a href="categories/1/${category.uniqueName}" 
									>${category.title}</a>
							</c:otherwise>
						</c:choose>				
								
					</li>
				</c:forEach>	
			</c:otherwise>
			
		</c:choose>
		
		
				
	</ul>						
</div>