<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="secArticle">

	
	<div id="secPageHeader">
		<ul>
			<li class="secPageTitle">Article</li>
		</ul>
	</div>
	
	
	<div id="secPageActions">
		<ul>
			<li class="secPageAction">
				<a href="#">Edit</a>
			</li>
			<li class="secPageAction">
				<a href="#">Delete</a>
			</li>
		</ul>		
	</div>
	
	
	<div id="secPageFields">
	
		<div class="secPageField">
			<label>Id</label>
			<input type="text" value="${command.article.id}"/>
		</div>
		
		<div class="secPageField">
			<label>Title*</label>
			<input type="text" value="${command.article.title}"/>
		</div>
		
		<div class="secPageField">
			<label>Unique name*</label>
			<input type="text" value="${command.article.uniqueName}"/>
		</div>
		
		<div class="secPageField">
			<label>Author*</label>
			<input type="text" value="${command.article.author}"/>
		</div>
		
		<div class="secPageField">
			<label>Pages count*</label>
			<select>
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
			<input type="text" value="${command.article.creationDateAsString}"/>
		</div>
		
		<div class="secPageField">
			<label>Demo name</label>
			<input type="text" value="${command.article.demoName}"/>
		</div>
		
		<div class="secPageField">
			<label>Example file name</label>
			<input type="text" value="${command.article.exampleFileName}"/>
		</div>
		
		<div class="secPageField">
			<label>Source file name</label>
			<input type="text" value="${command.article.sourceFileName}"/>
		</div>
		
		<c:set var="articleStatusEnumValues" value="<%=pl.kwi.chrisblog.enums.ArticleStatusEnum.values()%>"/>
		<div class="secPageField">
			<label>Article status</label>
			<select>
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
		<a id="back" href="javascript:history.go(-1)" class="button">back</a>
	</p>
		

</div>