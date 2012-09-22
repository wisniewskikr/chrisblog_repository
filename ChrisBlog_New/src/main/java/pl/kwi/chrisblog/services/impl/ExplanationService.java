package pl.kwi.chrisblog.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import pl.kwi.chrisblog.entities.ExplanationEntity;
import pl.kwi.chrisblog.exceptions.ExplanationException;
import pl.kwi.chrisblog.services.intf.IExplanationService;

/**
 * Class implementing interface IExplanationService.
 * 
 * @author Krzysztof Wisniewski
 */
@Service
public class ExplanationService implements IExplanationService {
	
	
	private List<ExplanationEntity> completeExplanationList;
		
	
	@PostConstruct
	public void init(){		
		completeExplanationList = initCompleteExplanationList();		
	}
	
	/* (non-Javadoc)
	 * @see pl.kwi.chrisblog.services.intf.IExplanationService#getExplanationByUniqueName(java.lang.String)
	 */
	public ExplanationEntity getExplanationByUniqueName(String uniqueName) throws Exception {
		
		if(uniqueName == null){
			throw new ExplanationException("Error explanation handling. Unique name of explanation is null.");
		}
				
		ExplanationEntity result = null;
		
		for (ExplanationEntity explanation : completeExplanationList) {
			
			if(uniqueName.equals(explanation.getUniqueName())){
				result = explanation;
				break;
			}
			
		}
		
		return result;
	}
		
	
	// ************************************************************************************************************ //
	// *********************************************** HELP METHODS *********************************************** //
	// ************************************************************************************************************ //
	
	
	/**
	 * Method gets all available explanations.
	 * 
	 * @return list of objects ExplanationEntity with all available explanations
	 */
	protected List<ExplanationEntity> initCompleteExplanationList(){
		
		List<ExplanationEntity> explanationList = new ArrayList<ExplanationEntity>();
		ExplanationEntity explanation;
		
		explanation = new ExplanationEntity();
		explanation.setId(1L);
		explanation.setUniqueName("java_language");
		explanation.setTitle("Java language");
		explanation.setContent("<b>Java language</b> is a class-based, object-oriented programming language " +
				"which is used for creating programming applications. Java code (*.java files) is compiled to bytecode " +
				"(*.class files) that can run on any " +
				"<a href='explanation/java_virtual_machine' " +
				"class='dialogLink'>Java Virtual Machine</a> (Java VM) regardless of computer architecture. " +
				"It`s so called 'write once, run anywhere' solution - code that runs on one platform does not need " +
				"to be edited to run on another.");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(2L);
		explanation.setUniqueName("web_application");
		explanation.setTitle("Web Application");
		explanation.setContent("<b>Web application</b> is an application which can be accessed by every user " +
				"over network (for instance the Internet) only by using a web browser. It means that the user doesn`t have to " +
				"install any software to use this application and the developer doesn`t have to take care about any " +
				"specific computer architecture. Moreover, every change of a web application is instantly visible for " +
				"all users (client-server environment).");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(3L);
		explanation.setUniqueName("java_servlets_technology");
		explanation.setTitle("Java Servlets Technology");
		explanation.setContent("<b>Java Servlet technology</b> is used for creating " +
				"<a href='explanation/web_application' " +
				"class='dialogLink'>web applications</a>. " +
				"This technology is based on 'servlet' classes from Java language. Every such a class can be accessed " +
				"from web browsers and after processing can send feedback to the browser " +
				"(request-response programming).");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(4L);
		explanation.setUniqueName("java_virtual_machine");
		explanation.setTitle("Java Virtual Machine");
		explanation.setContent("<b>Java Virtual Machine</b> (Java VM) is a software which translates a code of Java " +
				"(<a href='explanation/java_bytecode' " +
				"class='dialogLink'>Java bytecode</a>) " +
				"into a code which is acceptable for different operating systems (for instance Windows, Linux etc). " +
				"In other words, it is a middle tier between Java applications and any specified computer architecture. " +
				"Input to the Java VM is always the same - " +
				"<a href='explanation/java_bytecode' " +
				"class='dialogLink'>Java bytecode</a>" +
				" - but output created by VM is different and depends on an operating system. In this way every code that runs " +
				"on one platform does not need to be edited to run on another. This is so called " +
				"'write once, run anywhere' solution - Java applications code can always look the same because " +
				"Java VM just takes care of all the differences between computer architectures.");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(5L);
		explanation.setUniqueName("apache_maven");
		explanation.setTitle("Apache Maven");
		explanation.setContent("Apache Maven is a build automation tool. Its` main goal is to transform all resources " +
				"(for instance " +
				"<a href='explanation/java_bytecode' " +
				"class='dialogLink'>Java bytecode</a> " +
				"files, images, configuration files etc.) into one executable package. This package, for instance " +
				"<a href='explanation/jar_file' " +
				"class='dialogLink'>Jar file</a> or " +
				"<a href='explanation/war_file' " +
				"class='dialogLink'>War file</a>" +
				", can be later deployed (put and run) on " +
				"<a href='explanation/application_server' " +
				"class='dialogLink'>application servers</a>" +
				". Apache Maven takes care of such issues like: external dependencies, other components necessary for project " +
				"or build order. These issues for every project are configured using a Project Object Model and stored " +
				"in a file called pom.xml. In other words, Apache Maven is a very useful tool which helps " +
				"to build a project (for instance " +
				"<a href='explanation/war_file' " +
				"class='dialogLink'>War file</a>) from different resources.");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(6L);
		explanation.setUniqueName("application_server");
		explanation.setTitle("Application Server");
		explanation.setContent("<b>Application server</b> is a software with an enviroment where " +
				"<a href='explanation/web_application' " +
				"class='dialogLink'>web applications</a> " +
				"can be put and launch - shortly deployed. After deployment such applications become accesable for " +
				"everyone connected to network (for instance to Internet). User can contact and interact with such " +
				"programs using web browser. His requests from such browser are received by application server and passed " +
				"to application. After processing by program, application server sent back responses again to " +
				"user`s web browser where they are displayed. It`s so called request-response technology. " +
				"One application server with deployed " +
				"<a href='explanation/web_application' " +
				"class='dialogLink'>web application</a>" +
				" can be accessed in the same time by a lot of users. It`s co called clien-server technology. " +
				"Mostly used application servers are Apache Tomcat, Apache JBoss and Oracle Glassfish.");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(7L);
		explanation.setUniqueName("java_bytecode");
		explanation.setTitle("Java bytecode");
		explanation.setContent("<b>Java bytecode</b> is a set of instructions which are readable for " +
				"<a href='explanation/java_virtual_machine' " +
				"class='dialogLink'>Java Virtual Machine</a>" +
				". These instructions are created from Java code (*.java files) after compilation. Typically, this " +
				"compilation is done automatically by " +
				"<a href='explanation/ide' " +
				"class='dialogLink'>IDE</a>" +
				".");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(8L);
		explanation.setUniqueName("jar_file");
		explanation.setTitle("Jar file");
		explanation.setContent("<b>Jar file</b> (Java Archive) is an archive file format typically used to " +
				"aggregate many Java class files and associated metadata and resources (text, images and so on) " +
				"into one file to distribute application software or libraries on the Java platform.");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(9L);
		explanation.setUniqueName("war_file");
		explanation.setTitle("War file");
		explanation.setContent("<b>War file</b>(Web application ARchive) is a " +
				"<a href='explanation/jar_file' " +
				"class='dialogLink'>Jar file</a> " +
				"used to distribute a collection of JavaServer Pages, Java Servlets, Java classes, XML files, " +
				"tag libraries and static Web pages (HTML and related files) that together constitute a " +
				"<a href='explanation/web_application' " +
				"class='dialogLink'>web application</a>" +
				". War files can be later deployed (put and run) on " +
				"<a href='explanation/application_server' " +
				"class='dialogLink'>application server</a>" +
				".");
		explanationList.add(explanation);
		
		explanation = new ExplanationEntity();
		explanation.setId(10L);
		explanation.setUniqueName("ide");
		explanation.setTitle("IDE");
		explanation.setContent("<b>IDE</b> (Integrated Development Environment) is a single program where " +
				"development can be made. It deals with such issues like source code edition, code compilation, " +
				"build automation or debugging. The main purpose of IDE is to maximize a developer`s productivity. " +
				"The most frequently used IDE of Java " +
				"are Eclipse, NetBeans and InteligJ IDEA.");
		explanationList.add(explanation);
		
		StringBuffer javaSeSb = new StringBuffer();
		javaSeSb.append("<b>Java Platform, Standard Edition</b> (Java SE, J2SE) is a basic platform ");
		javaSeSb.append("for developing and running Java software. ");
		javaSeSb.append("J2SE consists of ");
		javaSeSb.append("<a href='explanation/java_virtual_machine' " +
						"class='dialogLink'>Java Virtual Machine</a> ");
		javaSeSb.append("and a set of libraries handling such issues like: file systems, graphical interfaces and so on. ");
		javaSeSb.append("This platform is enought for developing and running standalone Java applications (bun not web applications). ");
		javaSeSb.append("To create Java ");
		javaSeSb.append("<a href='explanation/web_application' " +
						"class='dialogLink'>web applications</a> ");
		javaSeSb.append("another platform has to be used - extension of J2SE called ");
		javaSeSb.append("<a href='explanation/j2ee' " +
						"class='dialogLink'>J2EE</a>.");
		explanation = new ExplanationEntity();
		explanation.setId(11L);
		explanation.setUniqueName("j2se");
		explanation.setTitle("Java Platform, Standard Edition");
		explanation.setContent(javaSeSb.toString());
		explanationList.add(explanation);
		
		StringBuffer javaEeSb = new StringBuffer();
		javaEeSb.append("<b>Java Platform, Enterprise Edition</b> (Java EE, J2EE) is a platform with Java environment ");
		javaEeSb.append("for developing and running Java software including ");
		javaEeSb.append("<a href='explanation/web_application' " +
						"class='dialogLink'>web applications</a>. ");
		javaEeSb.append("J2EE consists of ");
		javaEeSb.append("<a href='explanation/j2se' class='dialogLink'>J2SE</a> ");
		javaEeSb.append("and a set of additional libraries handling such issues like: network, web services, multi-tiers levels and so on. ");
		javaEeSb.append("This platform is enought for developing standalone and web applications. ");
		explanation = new ExplanationEntity();
		explanation.setId(12L);
		explanation.setUniqueName("j2ee");
		explanation.setTitle("Java Platform, Enterprise Edition");
		explanation.setContent(javaEeSb.toString());
		explanationList.add(explanation);
		
		StringBuffer javaJspSb = new StringBuffer();
		javaJspSb.append("<b>JavaServer Pages</b> (JSP) is a Java technology for developing dynamically generated ");
		javaJspSb.append("web pages basing on HTML or XML. ");
		explanation = new ExplanationEntity();
		explanation.setId(13L);
		explanation.setUniqueName("jsp");
		explanation.setTitle("JavaServer Pages");
		explanation.setContent(javaJspSb.toString());
		explanationList.add(explanation);
		
		StringBuffer htmlSb = new StringBuffer();
		htmlSb.append("<b>HTML</b> (HyperText Markup Language) is the language which ");
		htmlSb.append("describes content of web pages in HTML documents. ");
		htmlSb.append("The main purpose of every web browser is to read such documents and compose them into ");
		htmlSb.append("visible or audible web pages. ");
		htmlSb.append("The browser does not display the HTML tags, ");
		htmlSb.append("but uses the tags to interpret the content of the page.");
		htmlSb.append("The most often HTML is used together with ");
		htmlSb.append("<a href='explanation/css' " +
					  "target='_blank' class='dialogLink'>CSS</a> ");
		htmlSb.append("- language which describes not content but presentation of web pages (colors, fonts etc.).");
		explanation = new ExplanationEntity();
		explanation.setId(14L);
		explanation.setUniqueName("html");
		explanation.setTitle("HTML");
		explanation.setContent(htmlSb.toString());
		explanationList.add(explanation);
		
		StringBuffer cssSb = new StringBuffer();
		cssSb.append("<b>CSS</b> (Cascading Style Sheets) is the language which ");
		cssSb.append("describes presentation of web pages. ");
		cssSb.append("It is responsible for such elements like layout, colors, fonts etc. ");
		cssSb.append("This language has to be used together with ");
		cssSb.append("<a href='explanation/html' " +
					 "target='_blank' class='dialogLink'>HTML</a> ");
		cssSb.append("or a similar markup language which describes content of web pages.");
		explanation = new ExplanationEntity();
		explanation.setId(15L);
		explanation.setUniqueName("css");
		explanation.setTitle("CSS");
		explanation.setContent(cssSb.toString());
		explanationList.add(explanation);
		
		StringBuffer javaScriptSb = new StringBuffer();
		javaScriptSb.append("<b>Java Script</b> is object-oriented script language (not compiled but only interpreted) ");
		javaScriptSb.append("which primarily describes dynamic action on client-side of web applications. ");
		javaScriptSb.append("It means that this language is interpreted and run not by ");
		javaScriptSb.append("<a href='explanation/application_server' " +
							"target='_blank' class='dialogLink'>application servers</a> ");
		javaScriptSb.append("but by web browsers every single user.  ");
		javaScriptSb.append("Java Script is mainly responsible for event handling, validation or navigation. ");
		explanation = new ExplanationEntity();
		explanation.setId(16L);
		explanation.setUniqueName("java_script");
		explanation.setTitle("Java Script");
		explanation.setContent(javaScriptSb.toString());
		explanationList.add(explanation);
		
		StringBuffer webPageSb = new StringBuffer();
		webPageSb.append("<b>Web Page</b> is a document or information resource ");
		webPageSb.append("that is suitable for the World Wide Web ");
		webPageSb.append("and can be accessed through a web browser ");
		webPageSb.append("and displayed on a monitor or mobile device. ");
		webPageSb.append("This information usually consists of ");
		webPageSb.append("<a href='explanation/html' " +
						 "target='_blank' class='dialogLink'>Html</a>, ");
		webPageSb.append("which is responsible for the content, and ");
		webPageSb.append("<a href='explanation/css' " +
						 "target='_blank' class='dialogLink'>Css</a>, ");
		webPageSb.append("which is responsible for a presentation. ");
		webPageSb.append("Web pages frequently subsume also other resources such as scripts or images ");
		webPageSb.append("into their final presentation.");
		explanation = new ExplanationEntity();
		explanation.setId(17L);
		explanation.setUniqueName("web_page");
		explanation.setTitle("Web Page");
		explanation.setContent(webPageSb.toString());
		explanationList.add(explanation);
		
		return explanationList;
		
	}
	
	
	// ************************************************************************************************************ //
	// *********************************************** GETTERS AND SETTERS **************************************** //
	// ************************************************************************************************************ //


	public List<ExplanationEntity> getCompleteExplanationList() {
		return completeExplanationList;
	}
	public void setCompleteExplanationList(
			List<ExplanationEntity> completeExplanationList) {
		this.completeExplanationList = completeExplanationList;
	}
		
	
}
