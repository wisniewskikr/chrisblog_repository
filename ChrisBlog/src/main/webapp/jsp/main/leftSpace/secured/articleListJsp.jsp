<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div id="secArticleList">

	
	<div id="secArticleListHeader">
		<ul>
			<li class="secArticleListTitle">Articles</li>
		</ul>
	</div>
	
	
	<div id="secArticleListActions">
		<ul>
			<li class="secArticleListAction">
				<a href="#">Create</a>
			</li>
			<li class="secArticleListAction">
				<a href="#">Edit</a>
			</li>
			<li class="secArticleListAction">
				<a href="#">Delete</a>
			</li>
		</ul>		
	</div>
	
	
	<div id="secArticleListContent">
	
		<table class="secArticleListContentTable">
		
			<tr class="secArticleListContentRow">
				<td class="secArticleListContentColumnPoint">
					<span class="secArticleListContentPoint"></span>
				</td>
				<td class="secArticleListContentColumnCheckbox">
					<input class="secArticleListContentCheckbox" type="checkbox"/>
				</td>
				<td class="secArticleListContentColumnText">
					<div class="secArticleListContentTitle"><a href="#">Hello World 1</a></div>
					<div class="secArticleListContentInfo">by Chris 3 days ago</div>
				</td>
			</tr>
			
		</table>	

	</div>
		

</div>