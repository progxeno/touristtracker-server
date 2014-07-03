$(document).ready(function()    {
	$('.rating').bind('click', function(){
		console.log(this.id);

	});	

	$('.first') {
    	width: 300px;
	    float:left; /* add this */
    	border: 1px solid red;
	}
	$('.second') {
	    border: 1px solid green;
	    overflow: hidden; /* if you don't want #second to wrap below #first */
	}
});