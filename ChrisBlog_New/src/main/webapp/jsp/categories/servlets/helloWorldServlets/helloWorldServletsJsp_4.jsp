<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<h2>Step 9. Create file 'script.js'</h2>

<p>
	Some actions of web application can be done not on server-side (web server) 
	but just on client-side (web browser). In our example application for this
	purpose is used script language
	<a href="explanations/java_script" class='dialogLink'>Java Script</a>. 
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





<h2>Step 10. Build application</h2>

<p>
	Next step is built the example application. We need to pack all our Java classes, libraries,
	static web pages etc. in one 
	<a href="explanations/war_file" class='dialogLink'>War file</a> 
	which we can later move and launch (deploy) on web server. This is the simplest way to distribute
	web application sources.
</p>	

<p>	
	As it was mensioned earlier 
	<a href="explanations/apache_maven" class='dialogLink' 
		>Apache Maven</a>
	 tool will be used for building our example application. 
	In this purpose run console, go to the main project folder and use command <b>mvn clean install</b> 
	for building project. 
</p>

<div class="img">
	<img src="images/servlets/helloWorldServlets_pic4.png"/>
	<div>Maven command for building project</div>
</div>

<p>
	If everything goes right 
	<a href="explanations/apache_maven" class='dialogLink' 
		>Apache Maven</a> 
	should create in main folder of our example application
	a new folder called <b>'target'</b> with 
	<a href="explanations/war_file" class='dialogLink' 
		>War file</a>  
	<b>'HelloWorldServlets.war'</b>.	 
</p>

<div class="img">
	<img src="images/servlets/helloWorldServlets_pic5.png"/>
	<div>File 'HelloWorldServlets.war' in folder 'target'</div>
</div>





<h2>Step 11. Deploy application</h2>

<p>
	Deploying web application means transforming 
	<a href="explanations/web_application" class='dialogLink' 
		>web application</a> 
	from a packaged form to an operational working state. 
</p>

<p>				
	The way of deploying 
	<a href="explanations/war_file" class='dialogLink' 
		>War file</a>
	 in different for every application server - you should check documentation. 
</p>

<p>
	As it was mentioned earlier <i>(see point 'Enviroment')</i> this example application 
	was tested on <b>Apache Tomcat</b> 
	<a href="explanations/application_server" class='dialogLink' 
		>application server</a>. To deploy application on this server developer has
	to just copy created file <b>'HelloWorldServlets.war'</b> into folder <b>'&lt;tomcat_home&gt;/webapps'</b>. 
	The transformation from a packaged form to an operational working state is done automatically
	by running application server.
</p>





<h2>Step 12. Run application</h2>

<p>
	After deploying example application we can run our 
	<a href="explanations/application_server" class='dialogLink' 
		>application server</a>. The way, how to do it, depends on type
	of server - check documentation.
</p>

<p>
	To run <b>Apache Tomcat</b> just go to location <b>'&lt;tomcat_home&gt;/bin'</b> and run file 
	<b>'startup.bat'</b> <i>(for Windows)</i> or <b>'startup.sh'</b> <i>(for Linux)</i>.
</p>





<h2>Step 13. Test example application</h2>

<p>	
	To test deployed example application 
	open web browser and type in address bar appropriate url to application <b>HelloWorldServlets</b>.
</p>

<p>	
	For web server launched as localhost this url should look like this: 
	 <b>http://localhost:8080/HelloWorldServlets</b>.				
</p>

<div class="img">
	<img src="images/servlets/helloWorldServlets_pic6.png"/>
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
	<a href="explanations/war_file" class='dialogLink'>War file</a>
	.
</p>

<p>
	<span class="download"><b>Source:</b></span> <a href="${command.article.sourcePath}">Download</a>
</p>

<p>
	<span  class="download"><b>War file:</b></span> <a href="${command.article.examplePath}">Download</a>
</p>