function updateMenu(id)
{
	var name= document.getElementById("name_"+id).innerHTML;
	var description= document.getElementById("address_"+id).innerHTML;

	
	$('#ModifyMenu').modal('show') ;
	
	document.getElementById("updateNameMenu").value=name;
	document.getElementById("updateDescriptionMenu").value=description;
	document.getElementById("updateIdMenu").value=id;
	
	
}



