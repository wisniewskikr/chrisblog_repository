<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

		



<h2>Introduction</h2>

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





<h2>Environment</h2>

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
		<li>Step 10. Build the application</li>
		<li>Step 11. Deploy the application</li>
		<li>Step 12. Run the application</li>
		<li>Step 13. Test the example application</li>
	</ul>
</p>
	
	
	
	
	
		
