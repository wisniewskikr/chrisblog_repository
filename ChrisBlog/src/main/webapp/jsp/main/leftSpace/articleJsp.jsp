<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<div id="article">
	
	<h1>
		<span id="articleTitle">${command.article.title}</span> 
		<span id="articlePages" class="articlePages">(${command.pageCurrent} of ${command.pagesCount})</span>
	</h1>
	
	<div class="addthis_toolbox addthis_default_style">
		<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
		<a class="addthis_button_tweet"></a>
		<a class="addthis_button_google_plusone" g:plusone:size="medium"></a>
		<a class="addthis_counter addthis_pill_style"></a>
	</div>	

	<jsp:include page="${command.article.content}_${command.pageCurrent}.jsp"/>
	
</div>