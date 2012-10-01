<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	

<%@ include file="header/messagesJsp.jsp" %>


<div id="header">

	<div class="center">

		<a href="" id="headerImage">Duke Duke Duke Duke Duke Duke Duke Duke Duke Duke</a>
		
		<a href="" id="headerTitle" >Chris`s Blog</a>
		
		<div id="headerSearchArea">
			<form action="http://google.com/search" id="searchForm">
				<input type="hidden" name="sitesearch" value="${command.pathHost}${command.pathContext}" />  
				<input type="search" id="headerSearchBox" name="q" results="5" placeholder="Search..." autocomplete="on" /> 
				<input id="headerSearchSubmit" type="submit" value="Search" class="button" />
			</form>
		</div>
		
		<%@ include file="header/loggingJsp.jsp" %>
		
	</div>	
</div>
