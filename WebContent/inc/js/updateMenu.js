function updateMenu(id)
{
	var name= document.getElementById("name_"+id).innerHTML;
	var description= document.getElementById("desc_"+id).innerHTML;
	
	document.getElementById("updNameM").value=name.trim();
	document.getElementById("updDescM").value=description.trim();
	document.getElementById("updIdM").value=id;
	
	$('#ModifyMenu').modal('show') ;
	
	
	
}



