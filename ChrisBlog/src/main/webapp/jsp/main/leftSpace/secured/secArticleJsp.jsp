<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<c:if test="${command.displaySecViewArticle}">
	<c:set var="disabled" value="disabled='disabled'"/>
	<c:set var="disabledCreate" value=""/>
	<c:set var="disabledView" value="class='disabledLink'"/>
	<c:set var="disabledEdit" value=""/>
	<c:set var="disabledDelete" value=""/>
</c:if>
<c:if test="${command.displaySecEditArticle}">
	<c:set var="disabled" value=""/>
	<c:set var="disabledCreate" value=""/>
	<c:set var="disabledView" value=""/>
	<c:set var="disabledEdit" value="class='disabledLink'"/>
	<c:set var="disabledDelete" value=""/>
</c:if>
<c:if test="${command.displaySecCreateArticle}">
	<c:set var="disabled" value=""/>
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
	
	
	<div id="secPageFields">
	
		<c:if test="${not command.displaySecCreateArticle}">
			<div class="secPageField">
				<label>Id*</label>
				<input type="text" value="${command.article.id}" disabled="disabled"/>
			</div>
		</c:if>
		
		<div class="secPageField">
			<label>Title*</label>
			<input type="text" value="${command.article.title}" ${disabled}/>
		</div>
		
		<div class="secPageField">
			<label>Unique name*</label>
			<input type="text" value="${command.article.uniqueName}" ${disabled}/>
		</div>
		
		<div class="secPageField">
			<label>Author*</label>
			<input type="text" value="${command.article.author}" ${disabled}/>
		</div>
		
		<div class="secPageField">
			<label>Pages count*</label>
			<select ${disabled}>
				<c:forEach begin="1" end="10" step="1" var="x">
					<c:choose>
						<c:when test="${command.article.pagesCount == x}">
							<option value="${x}" selected="selected">${x}</option>						
						</c:when>
						<c:otherwise>
							<option value="${x}">${x}</option>	
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			
		</div>
		
		<div class="secPageField">
			<label>Creation date*</label>
			<input type="text" class="datepicker" value="${command.article.creationDateAsString}" ${disabled}/>
		</div>
		
		<div class="secPageField">
			<label>Demo name</label>
			<input type="text" value="${command.article.demoName}" ${disabled}/>
		</div>
		
		<div class="secPageField">
			<label>Example file name</label>
			<input type="text" value="${command.article.exampleFileName}" ${disabled}/>
		</div>
		
		<div class="secPageField">
			<label>Source file name</label>
			<input type="text" value="${command.article.sourceFileName}" ${disabled}/>
		</div>
		
		<c:set var="articleStatusEnumValues" value="<%=pl.kwi.chrisblog.enums.ArticleStatusEnum.values()%>"/>
		<div class="secPageField">
			<label>Article status</label>
			<select ${disabled}>
				<c:forEach items="${articleStatusEnumValues}" var="enumValue">
					<c:choose>
						<c:when test="${command.article.status == enumValue}">
							<option value="${enumValue}" selected="selected">${enumValue.displayedName}</option>						
						</c:when>
						<c:otherwise>
							<option value="${enumValue}">${enumValue.displayedName}</option>	
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>
		
	</div>
	
	<p>
		<c:if test="${not command.displaySecViewArticle}">
			<a id="apply" href="#" class="button">Apply</a>
		</c:if>
		<a id="cancel" href="secured/article-list" class="button">Cancel</a>
	</p>
		

</div>