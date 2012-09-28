/*
 * Method sends form with specified action.
 */
function send(action){
	
	document.form.action = action;
	document.form.submit();	
}

/**
 * Function changes specified object style 'overflow' to 'auto' and hidden borders.
 *  
 * @param obj with changed style
 */
function overflowAuto(obj){
	$(obj).attr("style", "overflow:auto !important; border-right-style:hidden; border-bottom-style: hidden;");
}