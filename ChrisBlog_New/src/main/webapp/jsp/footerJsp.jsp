<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="footer" class="fadeIn">
	<div class="center">
	
		<section>
			<a href="about_me">
				<img width="90px" height="100px" src="images/common/chris.jpg" style="float: left; margin-right: 15px;"/>
			</a>
			<h5>About me</h5>
			<p>
				My name is <b>Krzysztof Wisniewski</b> (or Chris for short) and I work as a Java Developer 
				for an IT company in Szczecin, Poland. In my free time I like reading books, watching movies
				and traveling. You can find more information about me 
				<a href="about_me">here</a>.
			</p>
		</section>
		
		<section>
			<h5>The blog</h5>
			<p>
				I started this blog to write about my daily experience from work 
				and other projects which I deal with in my spare time. Mostly, I write 
				about topics which I find very interesting - Spring 3, EJB 3, Hibernate etc.
			</p>
		</section>
		
		<section>
			<h5>Tags</h5>
			<p>
				<c:forEach items="${command.tagsCloudFooter.tags()}" var="tag">
					<a href="#" class="footerTagsCloud" style="font-size: ${tag.weight}px;">${tag.name}</a> 
				</c:forEach>
			</p>
		</section>
	
	</div>	
</div>