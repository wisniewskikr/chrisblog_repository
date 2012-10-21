<h2>Build application</h2>

<p>
	Next step is built the example application. We need to pack all our Java classes, libraries,
	static web pages etc. in one 
	<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a> 
	which we can later move and launch (deploy) on web server. This is the simplest way to distribute
	web application sources.
</p>	

<p>	
	As it was mensioned earlier 
	<a href="explanation/apache_maven" class='dialogLink' target='_blank' 
		>Apache Maven</a>
	 tool will be used for building our example application. 
	In this purpose run console, go to the main project folder and use command <b>mvn clean install</b> 
	for building project. 
</p>

<div class="img">
	<img src="images/articles/helloWorldServlets_pic4.png"/>
	<div>Maven command for building project</div>
</div>

<p>
	If everything goes right 
	<a href="explanation/apache_maven" class='dialogLink' target='_blank' 
		>Apache Maven</a> 
	should create in main folder of our example application
	a new folder called <b>'target'</b> with 
	<a href="explanation/war_file" class='dialogLink' target='_blank' 
		>War file</a>  
	<b>'HelloWorldServlets.war'</b>.	 
</p>

<div class="img">
	<img src="images/articles/helloWorldServlets_pic5.png"/>
	<div>File 'HelloWorldServlets.war' in folder 'target'</div>
</div>