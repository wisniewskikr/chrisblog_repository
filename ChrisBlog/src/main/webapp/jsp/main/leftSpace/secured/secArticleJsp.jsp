<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<c:if test="${command.displaySecViewArticle}">
	<c:set var="disabledField" value="true"/>
	<c:set var="disabledCreate" value=""/>
	<c:set var="disabledView" value="class='disabledLink'"/>
	<c:set var="disabledEdit" value=""/>
	<c:set var="disabledDelete" value=""/>
</c:if>
<c:if test="${command.displaySecEditArticle}">
	<c:set var="disabledField" value="false"/>
	<c:set var="disabledCreate" value=""/>
	<c:set var="disabledView" value=""/>
	<c:set var="disabledEdit" value="class='disabledLink'"/>
	<c:set var="disabledDelete" value=""/>
</c:if>
<c:if test="${command.displaySecCreateArticle}">
	<c:set var="disabledField" value="false"/>
	<c:set var="disabledCreate" value="class='disabledLink'"/>
	<c:set var="disabledView" value="class='disabledLink'"/>
	<c:set var="disabledEdit" value="class='disabledLink'"/>
	<c:set var="disabledDelete" value="class='disabledLink'"/>
</c:if>


<div id="secArticle">

	
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
				<a href="secured/view-article/${command.article.uniqueName}" ${disabledView}>View</a>
			</li>
			<li class="secPageAction">
				<a href="secured/edit-article/${command.article.uniqueName}" ${disabledEdit}>Edit</a>
			</li>
			<li class="secPageAction">
				<a href="#" ${disabledDelete}>Delete</a>
			</li>
		</ul>		
	</div>
	
	
	
	<form:form method="POST" action="secured/handle-create-article" modelAttribute="command.article">
	
		<div id="secPageFields">
		
			<c:if test="${not command.displaySecCreateArticle}">
				<div class="secPageField">
					<label>Id*</label>
					<form:input path="id" disabled="true"/>
				</div>
			</c:if>
			
			<div class="secPageField">
				<label>Title*</label>
				<form:input path="title" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Unique name*</label>
				<form:input path="uniqueName" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Author*</label>
				<form:input path="author" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Pages count*</label>				
				<form:select path="pagesCount" disabled="${disabledField}">
					<c:forEach begin="1" end="10" step="1" var="x">
						<form:option value="${x}">${x}</form:option>
					</c:forEach>
				</form:select>
				
			</div>
			
			<div class="secPageField">
				<label>Creation date*</label>
				<form:input path="creationDateAsString" cssClass="datepicker" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Demo name</label>
				<form:input path="demoName" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Example file name</label>
				<form:input path="exampleFileName" disabled="${disabledField}"/>
			</div>
			
			<div class="secPageField">
				<label>Source file name</label>
				<form:input path="sourceFileName" disabled="${disabledField}"/>
			</div>
			
			<c:set var="articleStatusEnumValues" value="<%=pl.kwi.chrisblog.enums.ArticleStatusEnum.values()%>"/>
			<div class="secPageField">
				<label>Article status</label>
				<form:select path="status" disabled="${disabledField}">
					<c:forEach items="${articleStatusEnumValues}" var="enumValue">
						<form:option value="${enumValue}">${enumValue.displayedName}</form:option>
					</c:forEach>
				</form:select>
			</div>
			
		</div>
		
		<p>
			<c:if test="${not command.displaySecViewArticle}">
				<input type="submit" class="button" value="Apply"/>
			</c:if>
			<a id="cancel" href="secured/article-list" class="button">Cancel</a>
		</p>
		
	</form:form>
	
		

</div>