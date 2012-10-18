<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<sec:authorize ifNotGranted="ROLE_USER">
	<div id="headerLogArea" class="fadeIn">
		<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
			<div id="logSection">
			
				<div class="headerSection">	
					<label class="labelLogArea" for="login" >Email</label>
					<div><input id="login" name="j_username" type="text"/></div>
				</div>
				
				<div class="headerSection">
					<table>
						<tr>
							<td><label class="labelLogArea" for="password">Password</label></td>
							<td>(<a href="#" id="createAccount">forgot password</a>)</td>
						</tr>
					</table>						
					<input id="password" name='j_password' type="password"/>
				</div>
				
				<div id="actionSection">				
					<input id="signIn" class="button" type="submit" value="Sign In" />
					or
					<a href="#" id="createAccount">Create Account</a>
				</div>
			
			</div>
		</form>
	</div>
</sec:authorize>


<sec:authorize ifAllGranted="ROLE_USER">
	<div id="headerLogArea" class="fadeIn">
		<div id="logSection">
		
			<div class="headerSection">			
				<label for="userName" >You are logged as:</label>
				<div id="userName" class="logText"><sec:authentication property="principal.username" /></div>
			</div>
			
			<div class="headerSection">
				<label>Your have an access to:</label>						
				<div class="logText"><a id="secResources" href="secured/article-list">secured resources</a></div>
			</div>
			
			<div id="actionSection">				
				<a id="signOut" href="<c:url value="/j_spring_security_logout"/>" class="button">Sign out</a>
				or
				<a href="#" id="createAccount">Edit Account</a>
			</div>
		
		</div>
	</div>
</sec:authorize>