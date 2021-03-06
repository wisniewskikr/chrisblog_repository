<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:if test="${command.displaySecViewArticle}">
	<c:set var="disabledField" value="true"/>
	<c:set var="disabledCreate" value=""/>
	<c:set var="disabledView" value="class='disabledLink'"/>
	<c:set var="disabledEdit" value=""/>
	<c:set var="disabledDelete" value=""/>
	<c:set var="formMethod" value=""/>
	<c:set var="formAction" value=""/>
	<c:set var="readonlyUniqueName" value="true"/>
</c:if>
<c:if test="${command.displaySecEditArticle}">
	<c:set var="disabledField" value="false"/>
	<c:set var="disabledCreate" value=""/>
	<c:set var="disabledView" value=""/>
	<c:set var="disabledEdit" value="class='disabledLink'"/>
	<c:set var="disabledDelete" value=""/>
	<c:set var="formMethod" value="POST"/>
	<c:set var="formAction" value="secured/handle-edit-article"/>
	<c:set var="readonlyUniqueName" value="true"/>
</c:if>
<c:if test="${command.displaySecCreateArticle}">
	<c:set var="disabledField" value="false"/>
	<c:set var="disabledCreate" value="class='disabledLink'"/>
	<c:set var="disabledView" value="class='disabledLink'"/>
	<c:set var="disabledEdit" value="class='disabledLink'"/>
	<c:set var="disabledDelete" value="class='disabledLink'"/>
	<c:set var="formMethod" value="POST"/>
	<c:set var="formAction" value="secured/handle-create-article"/>
	<c:choose>
		<c:when test="${backButtonPressed}">
			<c:set var="readonlyUniqueName" value="true"/>
		</c:when>
		<c:otherwise>
			<c:set var="readonlyUniqueName" value="false"/>
		</c:otherwise>
	</c:choose>
	
</c:if>
<c:if test="${command.displaySecDeleteArticle}">
	<c:set var="disabledField" value="true"/>
	<c:set var="disabledCreate" value=""/>
	<c:set var="disabledView" value=""/>
	<c:set var="disabledEdit" value=""/>
	<c:set var="disabledDelete" value="class='disabledLink'"/>
	<c:set var="formMethod" value=""/>
	<c:set var="formAction" value=""/>
	<c:set var="readonlyUniqueName" value="true"/>
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
	
		<div id="secPageFields">
		
			<c:choose>
				<c:when test="${command.displaySecCreateArticle}">
					<form:hidden path="id"/>
				</c:when>
				<c:otherwise>
					<div class="secPageField">
						<label>Id*</label>
						<form:input path="id" readonly="true"/>
					</div>
				</c:otherwise>
			</c:choose>
			
			<div class="secPageField">
				<label>Title*</label>	
				<form:errors path="title" cssClass="error"/>			
				<form:input path="title" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Unique name*</label>
				<form:errors path="uniqueName" cssClass="error"/>
				<form:input path="uniqueName" readonly="${readonlyUniqueName}"/>
			</div>
			
			
			<div class="secPageField">
				<label>Author*</label>
				<form:errors path="author" cssClass="error"/>
				<form:input path="author" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Creation date*</label>
				<form:errors path="creationDate" cssClass="error"/>
				<form:input path="creationDate" cssClass="datepicker" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Demo name</label>
				<form:errors path="demoName" cssClass="error"/>
				<form:input path="demoName" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Example file name</label>
				<form:errors path="exampleFileName" cssClass="error"/>
				<form:input path="exampleFileName" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Source file name</label>
				<form:errors path="sourceFileName" cssClass="error"/>
				<form:input path="sourceFileName" disabled="${disabledField}"/>
			</div>
			
			<c:set var="articleStatusEnumValues" value="<%=pl.kwi.chrisblog.enums.ArticleStatusEnum.values()%>"/>
			<div class="secPageField">
				<label>Article status*</label>
				<form:errors path="status" cssClass="error"/>
				<form:select path="status" disabled="${disabledField}">
					<c:forEach items="${articleStatusEnumValues}" var="enumValue">
						<form:option value="${enumValue}">${enumValue.displayedName}</form:option>
					</c:forEach>
				</form:select>
			</div>
						
			<div id="tags" class="secPageField">
				<label>Tags*</label>
				<form:errors path="articleTagList" cssClass="error errorArticleTagList" element="div"/></br>
				<form:checkboxes path="articleTagList" items="${articleTagList}" itemValue="id" itemLabel="name" disabled="${disabledField}" />
			</div>
			
		</div>
		
		
		<p id="secPageNavigation">
		</br>
		
			<c:if test="${command.displaySecCreateArticle}">					
				<c:choose>
					<c:when test="${backButtonPressed}">
						<span class="back_next_buttons">
							<a id="next" href="javascript:send('secured/handle-create-article-back-button', 'article');" class="button">Next �</a>
						</span>
						<a id="cancel" href="secured/handle-create-article-cancel/${article.uniqueName}" class="button">Cancel</a>
					</c:when>
					<c:otherwise>					
						<span class="back_next_buttons">
							<a id="next" href="javascript:send('secured/handle-create-article', 'article');" class="button">Next �</a>
						</span>
						<a id="cancel" href="secured/article-list" class="button">Cancel</a>
					</c:otherwise>
				</c:choose>
			</c:if>
			<c:if test="${command.displaySecViewArticle}">
				<span class="back_next_buttons">			
					<a id="next" href="javascript:send('secured/handle-view-article', 'article');" class="button">Next �</a>
				</span>	
				<a id="cancel" href="secured/article-list" class="button">Cancel</a>
			</c:if>
			<c:if test="${command.displaySecEditArticle}">			
				<span class="back_next_buttons">
					<a id="next" href="javascript:send('secured/handle-edit-article', 'article');" class="button">Next �</a>
				</span>	
				<a id="cancel" href="secured/article-list" class="button">Cancel</a>
			</c:if>
			<c:if test="${command.displaySecDeleteArticle}">			
				<a id="delete" href="secured/confirmation/delete-article/${article.uniqueName}" class="button">Delete</a>
				<a id="cancel" href="secured/article-list" class="button">Cancel</a>
			</c:if>				
		</p>
		
		
		
	</form:form>
	
		

</div>