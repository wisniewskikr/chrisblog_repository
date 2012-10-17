/**
 * Function changes specified object style 'overflow' to 'auto' and hidden borders.
 *  
 * @param obj with changed style
 */
function overflowAuto(obj){
	$(obj).attr("style", "overflow:auto !important; border-right-style:hidden; border-bottom-style: hidden;");
}

/**
 * Function sends form with specified action.
 * 
 * @param action object String with action
 */
function send(action){	
	$('#command').attr('action', action);
	$('#command').submit();	
}

/**
 * Function selects row on table according to hidden field 'selectedRows'
 * and checkboxes with specified ids.
 */
function selectRowsTable(){
	var value = $('#selectedRows').val();
	var array = value.split(",");
	for(var x in array) {                        
        var id = array[x];
        $('#' + id).attr('checked', true);
	}
}

/**
 * Function inits event of checkbox selection on table.
 */
function initEventSelectionTable(){
	
	$('.secPageContentCheckbox').click(function(){
		
		if($(this).is(':checked')){
			var value = $('#selectedRows').val();
			var uniqueName = $(this).attr('id') + ",";
			value += uniqueName;
			$('#selectedRows').val(value);
		}else{
			var value = $('#selectedRows').val();
			var uniqueName = $(this).attr('id') + ",";
			value = value.replace(uniqueName, "");
			$('#selectedRows').val(value);
		}
			
		
    });
	
}