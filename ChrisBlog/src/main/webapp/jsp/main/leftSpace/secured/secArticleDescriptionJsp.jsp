<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<c:if test="${command.displaySecCreateArticleDescr}">
	<c:set var="disabledField" value="false"/>
	<c:set var="disabledCreate" value="class='disabledLink'"/>
	<c:set var="disabledView" value="class='disabledLink'"/>
	<c:set var="disabledEdit" value="class='disabledLink'"/>
	<c:set var="disabledDelete" value="class='disabledLink'"/>
	<c:set var="formMethod" value="POST"/>
	<c:set var="formAction" value="secured/handle-create-article-description"/>
	<c:set var="readonlyUniqueName" value="false"/>
</c:if>



<div id="secArticle">


	<div class="msgArea"></div>

	
	<div id="secPageHeader">
		<ul>
			<li class="secPageTitle">Article</li>
		</ul>
	</div>

	
	<div id="secPageActions">
		<ul>
			<li class="secPageAction">
				<a href="secured/create-article" ${disabledCreate}>Create</a>
			</li>
			<li class="secPageAction">
				<a href="secured/view-article/${article.uniqueName}" ${disabledView}>View</a>
			</li>
			<li class="secPageAction">
				<a href="secured/edit-article/${article.uniqueName}" ${disabledEdit}>Edit</a>
			</li>
			<li class="secPageAction">
				<a href="secured/delete-article/${article.uniqueName}" ${disabledDelete}>Delete</a>
			</li>
		</ul>		
	</div>
	
	
	
	<form:form method="${formMethod}" action="${formAction}" modelAttribute="article">
		<form:hidden path="uniqueName" />	
	
		
		<div class="secPageTextarea">	
		
			<div class="secPageField">
				<label>Description</label>	
				<form:errors path="description" cssClass="error"/>			
				<form:textarea path="description" disabled="${disabledField}"/>
			</div>
		
		</div>			
			
		
		<p id="secPageNavigation">
			<c:if test="${command.displaySecCreateArticleDescr}">			
				<input type="submit" class="button" value="Next �"/>
			</c:if>			
			<a id="cancel" href="secured/article-list" class="button">Cancel</a>
		</p>
		
	</form:form>
	
		

</div>