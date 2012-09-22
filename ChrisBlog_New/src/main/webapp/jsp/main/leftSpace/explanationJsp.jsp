<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>



<div id="explanations">

	<h1>${command.explanation.title}</h1>
	
	<p>${command.explanation.content}</p>
	
	<p><a href="javascript:history.go(-1)" class="button">Back</a></p>

</div>
