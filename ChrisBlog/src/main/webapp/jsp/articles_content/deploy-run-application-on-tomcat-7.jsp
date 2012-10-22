<h2>Our goal</h2>

<p>
	The main goal of this tutorial is explaining how to deploy and run
	<a href='explanation/web_application' target='_blank' class='dialogLink'>web application</a> (.war)
	on 
	<a href="explanation/application_server" class='dialogLink' target='_blank'>application server</a> 
	Apache Tomcat in version 7 (in both Windows and Linux environment).
</p>



<h2>Environment and preconditions</h2>

<p>
	We will use following technologies:
	<ul>
		<li>
			<b> 
				<a href="explanation/java_virtual_machine" class='dialogLink' target='_blank'>Java Virtual Machine</a>
			</b>: 
			Java in version 5 or higher. This example was tested for Java in version <i>1.6.0_23-ea</i>;
		</li>
		<li>
			<b> 
				<a href="explanation/application_server" class='dialogLink' target='_blank'>Application Server</a>
			</b>: 
			this example was tested on application server Apache Tomcat in version <i>7.0.21</i>;
		</li>
		<li>
			<b>Web Browser</b>: 
			this example application was tested on web browser Google Chrome in version <i>8.0.552</i>;
		</li>
		<li>
			<b>Web application (.war)</b>: we assume that we have properly build 
			<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a> 
			with web application resources. In this tutorial we will use example application packed to 
			the war file called <i>hello.war</i>. You can download this file here:  
			<a href="${command.article.examplePath}">download</a>.
		</li>
	</ul>
</p>



<h2>List of steps</h2>

<p>
	The list of the steps for deploying and running
	<a href='explanation/web_application' target='_blank' class='dialogLink'>web application</a> (.war)
	on Apache Tomcat 7
	<a href="explanation/application_server" class='dialogLink' target='_blank'>application server</a> 
	consists of:
	<ul>
		<li>Step 1. Deploy web application (.war)</li>
		<li>Step 2. Start Tomcat 7</li>
	</ul>
</p>



<h2>Step 1. Deploy web application (.war)</h2>

<p>
	Deploying web application means transforming 
	<a href="explanation/web_application" class='dialogLink' target='_blank' 
		>web application</a> 
	from a packaged form to an operational working state. 
</p>

<p>				
	The way of deploying 
	<a href="explanation/war_file" class='dialogLink' target='_blank' 
		>War file</a>
	 in different for every application server - you should check documentation. 
</p>

<p>
	To deploy application on Tomcat 7 application server developer has
	to just copy 
	<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a> 
	(in our case <i>hello.war</i>) into folder <b>'&lt;tomcat_home&gt;/webapps'</b>. 
	The transformation from a packaged form to an operational working state is done automatically
	by running application server.
</p>




<h2>Step 2. Start Tomcat 7</h2>

<p>
	After deploying example application we have to start
	<a href="explanation/application_server" class='dialogLink' target='_blank'>application server</a>. 
</p>

<p>
	To run <b>Apache Tomcat 7</b> just go to location <b>'&lt;tomcat_home&gt;/bin'</b> and run file 
	<b>'startup.bat'</b> <i>(for Windows)</i> or <b>'startup.sh'</b> <i>(for Linux)</i>.
</p>



<h2>Check our goal</h2>

<p>	
	To test deployed example application 
	open web browser and type in address bar appropriate url to application <b>hello</b>.
</p>

<p>	
	For web server launched as localhost this url should look like this: 
	 <b>http://localhost:8080/hello</b>. If everything is ok we should see page with application.				
</p>

<div class="img">
	<img src="images/articles/helloWorldServlets_pic6.png"/>
	<div>Url of web application HelloWorldServlets</div>
</div>

<p>
	You can test <b>working example application</b> here: 
	<a href="${command.article.demoPath}" class="button" target="_blank">demo</a>
</p>





<h2>Download</h2>

<p>
	You can download built example 
	<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a>.
</p>

<p>
	<span  class="download"><b>War file:</b></span> <a href="${command.article.examplePath}">Download</a>
</p>