<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



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




<h2>Test example application</h2>

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