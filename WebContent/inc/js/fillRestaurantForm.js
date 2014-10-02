/*
 * Ce fichier contient le code js necessaire pour faire apparete et remplir le formulaire de modification d'un restaurant.
 */

function updateRestaurantDatas(id)
{
	var name= document.getElementById("name_"+id).innerHTML;
	var address= document.getElementById("address_"+id).innerHTML;
	var phoneNumber= document.getElementById("phoneNumber_"+id).innerHTML;
	var kindOfFood= document.getElementById("kindOfFood_"+id).innerHTML;
	
	alert(id+" "+name+" "+address+" "+phoneNumber+" "+kindOfFood);
	
	$('#UpdateRestaurant').modal('show') ;
	
	document.getElementById("updateName").value=name;
	document.getElementById("updatePhone").value=address;
	document.getElementById("updateAddress").value=phoneNumber;
	document.getElementById("updateRestaurateurId").value=1;
	document.getElementById("updateTypeFood").value=kindOfFood;
	
}

