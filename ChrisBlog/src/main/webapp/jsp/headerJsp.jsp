<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
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
		
		<div id="headerLogArea" class="fadeIn">
			<form name='f' action="<c:url value='j_spring_security_check' />"
			method='POST'>
			
				<div id="headerLoginSection">	
					<label for="login" >Login:</label>
					<input id="login" name="j_username" type="text"/>
				</div>
				
				<div id="passwordSection">
					<label for="password">Password:</label>
					<input id="password" name='j_password' type="password"/>
				</div>
				
				<div id="actionSection">
					<!-- a href="#" id="headerPasswordReset">Password reset</a -->
					<!-- a href="#" id="headerSignIn">Sign In</a -->
					<!-- a href="j_spring_security_logout" id="logout">Logout</a -->
					<input id="signIn" class="button" type="submit" value="Sign In" />
				</div>
			
			</form>
		</div>
		
	</div>	
</div>
