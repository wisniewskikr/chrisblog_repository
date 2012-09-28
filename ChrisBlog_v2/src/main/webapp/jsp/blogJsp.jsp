<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>


<html>


<head>

	<title>Chris`s Blog - ${command.windowTitle}</title>
	
	<base href="${command.pathHost}${command.pathContext}">
	
	<link rel="shortcut icon" href="icons/duke.ico">
	<link rel="icon" href="icons/duke.ico" type="image/ico">
	
	<link type="text/css" rel="stylesheet" href="styles/style.css">
	<link type="text/css" rel="stylesheet" href="styles/menu.css">
	<link type="text/css" rel="stylesheet" href="styles/pagination.css">
	<script type="text/javascript" src="scripts/jquery-1.6.1.min.js"></script>
	<script type="text/javascript" src="scripts/script.js"></script>
	
	<!-- Scripts for 'SyntaxHighlighter' - google tool for highlighting code.
	Part 1. -->
	<script type="text/javascript" src="scripts/shCore.js"></script>
	<script type="text/javascript" src="scripts/shBrushXml.js"></script>
	<script type="text/javascript" src="scripts/shBrushJava.js"></script>
	<script type="text/javascript" src="scripts/shBrushPlain.js"></script>
	<script type="text/javascript" src="scripts/shBrushJScript.js"></script>
	<script type="text/javascript" src="scripts/shBrushCss.js"></script>
	<link href="styles/shCore.css" rel="stylesheet" type="text/css" />
	<link href="styles/shThemeDefault.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		.syntaxhighlighter {
		  width: 700px !important;
		  max-height: 600px !important;
		}
	</style>
	
	<!-- Scripts for 'PrettyPhoto' - tool for view image. Part 1 -->
	<script type="text/javascript" src="scripts/jquery.prettyPhoto.js"></script>
	<link href="styles/prettyPhoto.css" rel="stylesheet" type="text/css" />
	
	<!-- Scripts for Addthis - social sharing tool -->
	<script type="text/javascript">
 		var addthis_config = {"data_track_clickback":true};
 	</script> 
	<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-4f5338d911231718"></script>
	
</head>


<body>
	
	<%@ include file="headerJsp.jsp" %>
	
	<%@ include file="mainJsp.jsp" %>
				
	<%@ include file="footerJsp.jsp" %>		

</body>


<!-- Scripts for 'SyntaxHighlighter' - google tool for highlighting code.
	Part 2. -->
<script type="text/javascript">
	SyntaxHighlighter.defaults['toolbar'] = false;
    SyntaxHighlighter.all();
</script>

<!-- Scripts for 'PrettyPhoto' - tool for view image. Part 2 -->
<script type="text/javascript" charset="utf-8">
	$(document).ready(function(){
		$("a[rel^='prettyPhoto']").prettyPhoto({
			social_tools: false
		});
	});
</script>


</html>