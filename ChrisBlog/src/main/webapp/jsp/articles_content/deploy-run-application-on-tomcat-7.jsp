



<h2>Step 11. Deploy application</h2>

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
	As it was mentioned earlier <i>(see point 'Enviroment')</i> this example application 
	was tested on <b>Apache Tomcat</b> 
	<a href="explanation/application_server" class='dialogLink' target='_blank' 
		>application server</a>. To deploy application on this server developer has
	to just copy created file <b>'HelloWorldServlets.war'</b> into folder <b>'&lt;tomcat_home&gt;/webapps'</b>. 
	The transformation from a packaged form to an operational working state is done automatically
	by running application server.
</p>




<h2>Step 12. Run application</h2>

<p>
	After deploying example application we can run our 
	<a href="explanation/application_server" class='dialogLink' target='_blank' 
		>application server</a>. The way, how to do it, depends on type
	of server - check documentation.
</p>

<p>
	To run <b>Apache Tomcat</b> just go to location <b>'&lt;tomcat_home&gt;/bin'</b> and run file 
	<b>'startup.bat'</b> <i>(for Windows)</i> or <b>'startup.sh'</b> <i>(for Linux)</i>.
</p>