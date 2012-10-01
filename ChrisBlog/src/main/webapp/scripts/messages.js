/**
 * Method inits closing messages.
 */
$(document).ready(function(){ 
	enableClosingMessages();
});

/**
 * Method enables closing messages.
 */
function enableClosingMessages(){
 $(".msgOK span.closeMsg").click(function(){
    $(this).parent().hide();
 });
 $(".msgInfo span.closeMsg").click(function(){
	 $(this).parent().hide();
 });
 $(".msgWarn span.closeMsg").click(function(){
	 $(this).parent().hide();
 });
 $(".msgError span.closeMsg").click(function(){
	 $(this).parent().hide();
 });
}