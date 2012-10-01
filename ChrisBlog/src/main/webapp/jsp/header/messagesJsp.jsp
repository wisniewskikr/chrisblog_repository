<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div class="messages">

	<c:forEach items="${command.errorMsgs}" var="msg">
		<div class="msgError">
			<span class="textMsg">${msg}</span> 
			<span class="closeMsg">close</span>
		</div>
	</c:forEach>

	<c:forEach items="${command.okMsgs}" var="msg">
		<div class="msgOK" title="Click to close">
			<span class="textMsg">${msg}</span> 
			<span class="closeMsg">close</span>
		</div>
	</c:forEach>

	<c:forEach items="${command.warnMsgs}" var="msg">
		<div class="msgWarn" title="Click to close">
			<span class="textMsg">${msg}</span> 
			<span class="closeMsg">close</span>
		</div>
	</c:forEach>

	<c:forEach items="${command.infoMsgs}" var="msg">
		<div class="msgInfo" title="Click to close">
			<span class="textMsg">${msg}</span> 
			<span class="closeMsg">close</span>
		</div>
	</c:forEach>


</div>