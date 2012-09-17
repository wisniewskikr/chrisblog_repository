<div id="header">
	<div class="center">

		<a href="init" id="headerImage">Duke Duke Duke Duke Duke Duke Duke Duke Duke Duke</a>
		
		<a href="init" id="headerTitle" >Chris`s Blog</a>
		
		<div id="headerSearchArea">
			<form action="http://google.com/search" id="searchForm">
				<input type="hidden" name="sitesearch" value="${command.pathHost}${command.pathContext}" />  
				<input type="search" id="headerSearchBox" name="q" results="5" placeholder="Search..." autocomplete="on" /> 
				<input id="headerSearchSubmit" type="submit" value="Search" class="button" />
			</form>
		</div>
		
		<div id="headerLogArea" class="fadeIn">
			
			<div id="headerLoginSection">	
				<label for="login" >Login:</label>
				<input id="login" type="text"/>
			</div>
			
			<div id="passwordSection">
				<label for="password">Password:</label>
				<input id="password" type="password"/>
			</div>
			
			<div id="actionSection">
				<a href="#" id="headerPasswordReset">Password reset</a>
				<a href="#" id="headerSignIn">Sign In</a>
			</div>
			
		</div>
		
	</div>	
</div>
