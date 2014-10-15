function updateMenu(id)
{
	var name= document.getElementById("name_"+id).innerHTML;
	var description= document.getElementById("desc_"+id).innerHTML;

	
	$('#ModifyMenu').modal('show') ;
	
	document.getElementById("updNameM").value=name;
	document.getElementById("updDescM").value=description;
	document.getElementById("updIdM").value=id;
	
	
}



