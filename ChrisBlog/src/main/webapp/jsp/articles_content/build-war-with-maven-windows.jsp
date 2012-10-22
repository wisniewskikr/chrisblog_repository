<h2>Our goal</h2>

<p>
	The main goal of this tutorial is explaining how to build
	<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a>
	using
	<a href="explanation/apache_maven" class='dialogLink' target='_blank' >Apache Maven</a>
	tool in Windows environment. We will pack all project`s Java classes, libraries, static web pages etc. in one 
	<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a> 
	which we can later move and launch (deploy) on web server. This is the simplest way to distribute
	web application sources. 
</p>



<h2>Environment and preconditions</h2>

<p>
	We will use following technologies:
	<ul>
		<li>
			<b>Operating System</b>: Windows XP operating system.
		</li>
		<li>
			<b> 
				<a href="explanation/java_virtual_machine" class='dialogLink' target='_blank'>Java Virtual Machine</a>
			</b>: 
			Java in version 5 or higher. This example was tested for Java in version <i>1.6.0_23-ea</i>;
		</li>
		<li>
			<b> 
				<a href="explanation/apache_maven" class='dialogLink' target='_blank'>Apache Maven</a>
			tool</b>: 
			Apache Maven tool in version 2 or higher. This example was tested for Apache Maven in version <i>2.2.1</i>;
		</li>
		<li>
			<b>Maven`s project</b>: we assume that we have properly configured Maven`s project in location <i>C:\MavenProject</i>.
		</li>
	</ul>
</p>



<h2>List of steps</h2>

<p>
	The list of the steps for building 
	<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a>
	using
	<a href="explanation/apache_maven" class='dialogLink' target='_blank' >Apache Maven</a>
	tool consists of:
	<ul>
		<li>Step 1. Open console in project`s main folder</li>
		<li>Step 2. Run Maven building command</li>
	</ul>
</p>



<h2>Step 1. Open console in project`s main folder</h2>

<p>Open the Start menu by clicking on the icon in the lower-left corner.</p>

<p>Click on the "Run" icon at the bottom of the Start menu.</p>

<p>Type "cmd" into the pop-up window and then press the "Enter" key. The command prompt window will now open.</p>

<div class="img">
	<img src="images/articles/build-war-with-maven-windows/console_run.png"/>
	<div>Open command line console in Windows environment</div>
</div>

<p>Go to the Maven`s project main folder (for instance C:\MavenProject)</p>

<div class="img">
	<img src="images/articles/build-war-with-maven-windows/maven_project.png"/>
	<div>Maven`s project in console</div>
</div>




<h2>Step 2. Run Maven building command</h2>

<p>Type command <i>mvn package</i> in console. This command should start building process 
of our project.</p>

<div class="img">
	<img src="images/articles/build-war-with-maven-windows/maven_package.png"/>
	<div>Run building command</div>
</div>

<p>At the end of the process we should see the information: 'BUILD SUCCESS'.</p>

<div class="img">
	<img src="images/articles/build-war-with-maven-windows/maven_success.png"/>
	<div>Success building</div>
</div>



<h2>Check our goal</h2>

<p>In the location <i>C:\MavenProject\target</i> we should see now 
<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a> 
with resources of our Maven`s project. The name of 
<a href="explanation/war_file" class='dialogLink' target='_blank'>War file</a>
we can set in <i>pom.xml</i> using element <i>finalName</i>.</p>

<div class="img">
	<img src="images/articles/build-war-with-maven-windows/maven_war.png"/>
	<div>War file in target folder</div>
</div>



