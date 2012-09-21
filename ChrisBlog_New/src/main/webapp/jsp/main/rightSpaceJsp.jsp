<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<div id="rightSpace" class="fadeIn">
	<div id="right">
	

		<div class="sectionDivider">
			<span> Find Me On...</span>
		</div>
		<div id="rightSocial" class="rightSection">
			<ul>
				<li>
					<div class="addthis_toolbox">
						<a class="addthis_button_facebook_follow" addthis:userid="360130017344138">
							<img src="images/common/facebook.png"/>
						</a>
					</div>
				</li>
				<li>
					<div class="addthis_toolbox">
						<a class="addthis_button_google_follow" addthis:userid="u/0/112270161220903515676/posts">
							<img src="images/common/google.png"/>
						</a>
					</div>
				</li>
				<li>
					<div class="addthis_toolbox">		
						<a class="addthis_button_twitter_follow" addthis:userid="wisniewskikr">
							<img src="images/common/twitter.png"/>
						</a>
					</div>
				</li>
				<li>
					<div class="addthis_toolbox">
						<a class="addthis_button_linkedin_follow" addthis:userid="krzyszofwisniewski39">
							<img src="images/common/linkedin.png"/>
						</a>
					</div>
				</li>
			</ul>
		</div>
		
		
		<div class="sectionDivider">
			<span>Sponsors...</span>
		</div>		
		<div id="rightAds" class="rightSection">
			<ul>
				<li>
					<script type="text/javascript" id="AdTaily_Widget" src="http://static.adtaily.pl/widget.js#5V8PFd5i7FG4WUp"></script>
					<noscript>
						<a href="http://www.adtaily.pl">Reklama w internecie</a>
					</noscript>
				</li>
			</ul>
		</div>
		
		<c:if test="${command.displayArticle}">
			<div class="sectionDivider sectionDividerNoLink">
				<span>Tags</span>
			</div>		
			<div id="rightTags">
				<ul>
					<c:forEach items="${command.article.articleTagList}" var="articleTag">
						<li><span>${articleTag.name}</span></li>
					</c:forEach>					
				</ul>
			</div>
		</c:if>
		

	</div>
</div>