<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

		



<h2>Our goal</h2>

<p>
	The main goal of this tutorial is explaining - step by step - how to create a
	simple <a href="explanation/web_application" class='dialogLink' target='_blank'>web application</a> 
	using <a href="explanation/java_servlets_technology" class='dialogLink' target='_blank'>Java Servlets Technology</a>.
	The example web application consists of two pages: page 'Hello' and page 'Welcome'.
</p>
		
<p>
	<b>Page 'Hello'</b> is an input to the above application. Here the user can type his name and then send it
	off to the application by using the button "Ok". This page looks like that:
</p>
		
<div class="img">
	<img src="images/articles/helloWorldServlets_pic1.png"/>
	<div>Page 'Hello'</div>
</div>			

<p>
	<b>Page 'Welcome'</b> is an output from the application. The user`s name from the page 'Hello' 
	is sent back by the application and displayed on the web browser after the sentence: 
	'Hello World'. Then the user can go back again to the page 'Hello' by using the button 'Back'. 
	This page looks like that:
</p>

<div class="img">
	<img src="images/articles/helloWorldServlets_pic2.png"/>
	<div>Page 'Welcome'</div>
</div>

<p>
	You can test this example application here: 
	<a href="${command.article.demoPath}" target="_blank" class="button">demo</a>
</p>





<h2>Environment and preconditions</h2>

<p>
	This example application was created and tested in the following environment:
	<ul>
		<li>
			<b>Operating System</b>: 
			 Windows XP operating system.
		</li>
		<li>
			<b> 
				<a href="explanation/java_virtual_machine" class='dialogLink' target='_blank'>Java Virtual Machine</a>
			</b>: 
			Java in version 5 or higher. This application was tested for Java in version <i>1.6.0_23-ea</i>;
		</li>
		<li>
			<b>
				<a href="explanation/ide" class='dialogLink' target='_blank' 
				>IDE</a>
			</b>: 
			Eclipse Indigo.
		</li>
		<li>
			<b> 
				<a href="explanation/apache_maven" class='dialogLink' target='_blank'>Apache Maven</a>
			tool</b>: 
			Apache Maven tool in version 2 or higher. This application was tested for Apache Maven in version <i>2.2.1</i>;
		</li>
		<li>
			<b> 
				<a href="explanation/application_server" class='dialogLink' target='_blank'>Application Server</a>
			</b>: 
			this example application was tested on application server Apache Tomcat in version <i>7.0.21</i>;
		</li>
		<li>
			<b>Web Browser</b>: 
			this example application was tested on web browser Google Chrome in version <i>8.0.552</i>;
		</li>
	</ul>
</p>





<h2>Project structure</h2>

<p>
	The name of this example project is <b>HelloWorldServlets</b> and its` common structure in Eclipse IDE should 
	look like this:
</p>

<div class="img">
	<img src="images/articles/helloWorldServlets_pic3.png"/>
	<div>Structure of project</div>
</div>





<h2>List of steps</h2>

<p>
	The list of the steps for creating the example web application <b>HelloWorldServlets</b> consists of:
	<ul>
		<li>Step 1. Create a file 'pom.xml'</li>
		<li>Step 2. Create a file 'web.xml'</li>
		<li>Step 3. Create a Jsp file 'helloJsp.jsp'</li>
		<li>Step 4. Create Servlet 'HelloServlet.java'</li>
		<li>Step 5. Create a Jsp file 'welcomeJsp.jsp'</li>
		<li>Step 6. Create Servlet 'WelcomeServlet.java'</li>
		<li>Step 7. Create a file 'index.jsp'</li>
		<li>Step 8. Create a file 'style.css'</li>
		<li>Step 9. Create a file 'script.js'</li>
	</ul>
</p>




<h2>Step 1. Create a file 'pom.xml'</h2>

<p>
	This example project is a Maven`s project. It means that the application is built by using	 
	<a href="explanation/apache_maven" class='dialogLink' target='_blank' 
		>Apache Maven</a>
	tool which deals with such issues like external dependencies, 
	other components necessary for the project or the build order.  								
</p>

<p>
	Maven projects are configured by using a Project Object Model, which is stored in a file called <b>pom.xml</b>.
	This file should be placed in the main folder of the project and it looks like this:
</p>

<div class="code">
	<pre class="brush: xml;">
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
								
								
			<groupId>pl.kwi</groupId>
			<artifactId>HelloWorldServlets</artifactId>
			<version>1.0</version>
			<packaging>war</packaging>
			<modelVersion>4.0.0</modelVersion>
			<name>&#36;{artifactId}</name>
		
		
			<build>
			
				<finalName>&#36;{artifactId}</finalName>
				
				<plugins>
					<plugin>
						<groupId>org.apache.maven.plugins</groupId>
						<artifactId>maven-compiler-plugin</artifactId>
						<configuration>
							<source>1.5</source>
							<target>1.5</target>
						</configuration>
					</plugin>
				</plugins>
				
			</build>
		
		
			<dependencies>
				
				<!-- Servlets dependencies -->
				<dependency>
					<groupId>javax.servlet</groupId>
					<artifactId>servlet-api</artifactId>
					<version>2.3</version>
					<scope>provided</scope>
				</dependency>
				
				<!-- JSP dependencies -->
				<dependency>
					<groupId>jstl</groupId>
					<artifactId>jstl</artifactId>
					<version>1.0.6</version>
				</dependency>
				<dependency>
					<groupId>taglibs</groupId>
					<artifactId>standard</artifactId>
					<version>1.0.6</version>
				</dependency>
				
			</dependencies>
		
		</project>
	</pre>
	<div>File 'pom.xml'</div>
</div>





<h2>Step 2. Create a file 'web.xml'</h2>

<p>
	In	
	<a href="explanation/java_servlets_technology" class='dialogLink' target='_blank' 
		>Java Servlets Technology</a> 
	the file <b>web.xml</b> is called 'web descriptor'. This file is always 
	placed in a folder <b>webapp/WEB-INF</b>. Its` main task is mapping/connecting
	the specified <b>url-pattern</b> from the user`s browser with the appropriate <b>servlet-name</b> 
	and this <b>servlet-name</b> with the appropriate <b>servlet-class</b>.  
</p>

<p>
	In our case, for instance, when the url from the user`s browser ends with the sentence <b>/hello.do</b> 
	<i>(line 13)</i> then browser`s request is redirected to the Servlet called <b>HelloServlet</b> 
	<i>(line 12)</i>. And the Servlet called <b>HelloServlet</b> is connected with the class 
	<b>pl.kwi.helloworldservlets.servlets.HelloServlet</b> <i>(lines 8 and 9)</i>. 
</p>

<p>
	The file <b>web.xml</b> for our example web application should look like this:
</p>

<div class="code">
	<pre class="brush: xml;">							
		<?xml version="1.0" encoding="UTF-8"?>
		<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
				 xmlns="http://java.sun.com/xml/ns/javaee" 
				 xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" 
				 xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" 
				 id="WebApp_ID" 
				 version="2.5">
		
			<display-name>HelloWorldServlets</display-name>
				
			<servlet>
				<servlet-name>HelloServlet</servlet-name>
				<servlet-class>pl.kwi.helloworldservlets.servlets.HelloServlet</servlet-class>
			</servlet>
			<servlet-mapping>
				<servlet-name>HelloServlet</servlet-name>
				<url-pattern>/hello.do</url-pattern>
			</servlet-mapping>
				
			<servlet>
				<servlet-name>WelcomeServlet</servlet-name>
				<servlet-class>pl.kwi.helloworldservlets.servlets.WelcomeServlet</servlet-class>
			</servlet>
			<servlet-mapping>
				<servlet-name>WelcomeServlet</servlet-name>
				<url-pattern>/welcome.do</url-pattern>
			</servlet-mapping>
			
			
			<welcome-file-list>		
				<welcome-file>/index.jsp</welcome-file>		
			</welcome-file-list>
			
		</web-app>							
	</pre>
	<div>File 'web.xml'</div>
</div>




<h2>Step 3. Create a Jsp file 'helloJsp.jsp'</h2>

<p>
	In our example application every single 
	<a href="explanation/html" class='dialogLink' target='_blank'>HTML</a> 
	page connected with specified Servlet is dynamically generated by using 
	<a href="explanation/jsp" class='dialogLink' target='_blank'>JSP</a> 
	technology. 
</p>

<p>
	The file <b>helloJsp.jsp</b> from the folder <b>webapp/jsp</b> is responsible for sending
	user`s name to the web application. The user can type his name in the field <b>userName</b> <i>(line 29)</i> 
	which is part of the bigger html element called <b>form</b> <i>(lines from 19 to 36)</i>. 
</p>	

<p>	
	The button 'Ok' <i>(line 32)</i> submits this whole form to the web application. 
	Moreover, the form`s action is automatically added at the end of the url - in this case  
	<b>hello.do</b> <i>(line 19)</i>.				  
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
		<form action="hello.do">
		
			<table align="center" frame="border" class="mainTable">
				<tr>
					<td>
						<h2>Hello World Servlets</h2>
						<h3>Page: <b>Hello</b></h3>
					</td>
				</tr>
				<tr>
					<td>Type your name:	<input type="text" id="userName" name="userName" size="10"/></td>
				</tr>
				<tr>
					<td><input type="submit" id="submit" name="submit" value="OK"/></td>
				</tr>		
			</table>
			
		</form>
		&lt;/body>
		
		
		&lt;/html>							
	</pre>
	<div>File 'helloJsp.jsp'</div>
</div>





<h2>Step 4. Create Servlet 'HelloServlet.java'</h2>

<p>
	The Servlet class <b>HelloServlet</b> from the package <b>pl.kwi.helloworldservlets.servlets</b> 
	is responsible for handling the user`s request with his name. The web descriptor <b>web.xml</b>
	redirects requests from <b>helloJsp.jsp</b> to this servlet basing on his own mapping.				  
</p>

<p>
	Servlet <b>HelloServlet</b> takes parameter <b>userName</b> from request <i>(line 21)</i> and
	puts it as request`s attribute with the same name <i>(line 22)</i>. Then servlet
	forwards this request to page <b>welcomeJsp.jsp</b> <i>(lines 24 and 25)</i>.  
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
		
		public class HelloServlet extends HttpServlet{
			
			
			private static final long serialVersionUID = 1L;
			
			
			@Override
			public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
						
				String userName = request.getParameter("userName");
				request.setAttribute("userName", userName);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/welcomeJsp.jsp");
				requestDispatcher.forward(request, response);		
				
			}	
		
		}
	]]></script>
	<div>File 'HelloServlet.java'</div>
</div>




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
	<a href="explanation/css" class='dialogLink' target='_blank'>CSS</a>. 
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




<h2>Step 9. Create file 'script.js'</h2>

<p>
	Some actions of web application can be done not on server-side (web server) 
	but just on client-side (web browser). In our example application for this
	purpose is used script language
	<a href="explanation/java_script" class='dialogLink' target='_blank'>Java Script</a>. 
</p>

<p>
	The file <b>'script.js'</b> from folder <b>'webapp/scripts'</b> contains all java script functions used in our 
	example application. Because we don`t need to use any of such functions 
	thus this file is empty.
</p>

<div class="code">
	<pre class="brush: js;">							
		// Empty file							
	</pre>
	<div>File 'script.js'</div>
</div>




<h2>Check our goal</h2>

<p>	
	To test deployed example application 
	open web browser and type in address bar appropriate url to application <b>HelloWorldServlets</b>.
</p>

<p>	
	For web server launched as localhost this url should look like this: 
	 <b>http://localhost:8080/HelloWorldServlets</b>.				
</p>

<div class="img">
	<img src="images/articles/helloWorldServlets_pic6.png"/>
	<div>Url of web application HelloWorldServlets</div>
</div>

<p>
	If everything is ok after pressing 'Enter' you should see page 'Hello' displayed in your web browser.
</p>

<p>
	You can test <b>working example application</b> here: 
	<a href="${command.article.demoPath}" class="button" target="_blank">demo</a>
</p>





<h2>Download</h2>

<p>
	You can download source of this example application or whole already built example 
	<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a>
	.
</p>

<p>
	<span class="download"><b>Source:</b></span> <a href="${command.article.sourcePath}">Download</a>
</p>

<p>
	<span  class="download"><b>War file:</b></span> <a href="${command.article.examplePath}">Download</a>
</p>	
	
	
	
	
		
