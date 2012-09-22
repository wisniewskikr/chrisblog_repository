<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<h2>Step 5. Create Jsp file 'welcomeJsp.jsp'</h2>

<p>
	File <b>welcomeJsp.jsp</b> from folder <b>webapp/jsp</b> is responsible for displaying
	user`s name in web browser. This name is taken from request`s attribute and displaying after text 
	'Hello World' <i>(line 30)</i>.				  
</p>

<p>
	Button 'Back' submits whole form to web application automatically adding form`s action 
	at the end of url - in this case adding sentence <b>welcome.do</b> <i>(line 19) </i>.
</p>
		
<div class="code">
	<pre class="brush: xml;">							
		&lt;!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

		&lt;%@ page language="java" contentType="text/html; charset=utf-8"
			pageEncoding="utf-8"%>
		
		&lt;html>
		
		
		&lt;head>
			<title>Hello World Servlets</title>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<script type="text/javascript" src="scripts/script.js"></script>
			<link type="text/css" rel="stylesheet" href="styles/style.css">
		&lt;/head>
		
		
		&lt;body>
		<form action="welcome.do">
		
			<table align="center" frame="border" class="mainTable">
				<tr>
					<td>
						<h2>Hello World Servlets</h2>
						<h3>Page: <b>Welcome</b></h3>
					</td>
				</tr>		
				<tr>
					<td>
						Hello World: <b>&#36;{requestScope.userName} </b>
					</td>
				</tr>
				<tr>
					<td><input type="submit" id="submit" name="submit" value="Back"/></td>
				</tr>
			</table>
		
		</form>
		&lt;/body>
		
		
		&lt;/html>							
	</pre>
	<div>File 'welcomeJsp.jsp'</div>
</div>





<h2>Step 6. Create Servlet 'WelcomeServlet.java'</h2>

<p>
	Servlet class <b>WelcomeServlet</b> from package <b>pl.kwi.helloworldservlets.servlets</b> 
	handles user`s request from page <b>welcomeJsp.jsp</b>. 				  
</p>

<p>
	Servlet <b>WelcomeServlet</b> just forwards request back to first page 
	<b>helloJsp.jsp</b> <i>(lines 21 and 21)</i>.  
</p>

<div class="code">
	<script type="syntaxhighlighter" class="brush: java"><![CDATA[
		package pl.kwi.helloworldservlets.servlets;
	
		import java.io.IOException;
		
		import javax.servlet.RequestDispatcher;
		import javax.servlet.ServletException;
		import javax.servlet.http.HttpServlet;
		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;
		
		public class WelcomeServlet extends HttpServlet{
			
			
			private static final long serialVersionUID = 1L;
			
			
			@Override
			public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
								
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/helloJsp.jsp");
				requestDispatcher.forward(request, response);		
				
			}
		
		}
	]]></script>
	<div>File 'WelcomeServlet.java'</div>
</div>





<h2>Step 7. Create file 'index.jsp'</h2>

<p>
	File <b>index.jsp</b> is default, initial file of our example web application 
	<i>(see web descriptor <b>web.xml</b> line 26)</i>. It means that if user doesn`t
	explicit indicate for another file, this file is launched as first.
</p>

<p>
	File <b>index.jsp</b> just forwards request to page <b>helloJsp.jsp</b> <i>(line 5)</i>.  
</p>

<div class="code">
	<pre class="brush: xml;">							
		&lt;%@ page language="java" contentType="text/html; charset=utf-8"
		    pageEncoding="utf-8"%>
		
		&lt;jsp:forward page="/jsp/helloJsp.jsp">&lt;/jsp:forward>							
	</pre>
	<div>File 'index.jsp'</div>
</div>





<h2>Step 8. Create file 'style.css'</h2>

<p>
	The presentation of web pages is defined by  
	<a href="explanation/css" class='dialogLink'>CSS</a>. 
</p>

<p>
	File <b>'style.css'</b> from folder <b>'webapp/styles'</b> contains all styles definitions used in our 
	example application.
</p>

<div class="code">
	<pre class="brush: css;">							
		.mainTable{
			width: 450pt;
			height: 180pt;	 
			text-align: center; 
			margin-top: 100pt;	
		}							
	</pre>
	<div>File 'style.css'</div>
</div>