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
<c:if test="${command.displaySecViewArticleDescr}">
	<c:set var="disabledField" value="true"/>
	<c:set var="disabledCreate" value=""/>
	<c:set var="disabledView" value="class='disabledLink'"/>
	<c:set var="disabledEdit" value=""/>
	<c:set var="disabledDelete" value=""/>
	<c:set var="formMethod" value=""/>
	<c:set var="formAction" value=""/>
	<c:set var="readonlyUniqueName" value="true"/>
</c:if>
<c:if test="${command.displaySecEditArticleDescr}">
	<c:set var="disabledField" value="false"/>
	<c:set var="disabledCreate" value=""/>
	<c:set var="disabledView" value=""/>
	<c:set var="disabledEdit" value="class='disabledLink'"/>
	<c:set var="disabledDelete" value=""/>
	<c:set var="formMethod" value=""/>
	<c:set var="formAction" value=""/>
	<c:set var="readonlyUniqueName" value="true"/>
</c:if>



<div id="secArticle">


	<div class="msgArea"></div>

	
	<div id="secPageHeader">
		<ul>
			<li class="secPageTitle">Article Description</li>
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
				<span class="back_next_buttons">			
					<a href="javascript:send('secured/handle-create-article-description-back-button', 'article');" class="button">� Back</a>
					<a href="javascript:send('secured/handle-create-article-description', 'article');" class="button">Next �</a>
				</span>	
				<a id="cancel" href="secured/handle-create-article-cancel/${article.uniqueName}" class="button">Cancel</a>
			</c:if>			
			<c:if test="${command.displaySecViewArticleDescr}">	
				<span class="back_next_buttons">		
					<a href="javascript:send('secured/handle-view-article-description-back-button', 'article');" class="button">� Back</a>
					<a href="javascript:send('secured/handle-view-article-description', 'article');" class="button">Next �</a>
				</span>	
				<a id="cancel" href="secured/article-list" class="button">Cancel</a>
			</c:if>			
			<c:if test="${command.displaySecEditArticleDescr}">			
				<span class="back_next_buttons">
					<a href="javascript:send('secured/handle-edit-article-description-back-button', 'article');" class="button">� Back</a>
					<a href="javascript:send('secured/handle-edit-article-description', 'article');" class="button">Next �</a>
				</span>	
				<a id="cancel" href="secured/article-list" class="button">Cancel</a>
			</c:if>			
		</p>
		
	</form:form>
	
		

</div>