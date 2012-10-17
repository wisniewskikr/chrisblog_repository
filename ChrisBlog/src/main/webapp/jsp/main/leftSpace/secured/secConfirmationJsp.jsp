<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<div id="confirmation">

	<h1 id="confirmationTitle">${title}</h1>
	
	<p id="confirmationContent">${content}</p>
	
	<p id="confirmationButtons">
		<c:if test="${confirmDeleteArticle}">
			<a id="delete" href="secured/handle-delete-article/${uniqueName}" class="button">Delete</a>
			<a id="cancel" href="secured/delete-article/${uniqueName}" class="button">Cancel</a>
		</c:if>
	</p>
	
</div>
