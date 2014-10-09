/*
 * Ce fichier contient le code js necessaire pour faire apparete et remplir le formulaire de modification d'un restaurant.
 */

function updateRestaurantDatas(id)
{
	var name= document.getElementById("name_"+id).innerHTML;
	var address= document.getElementById("address_"+id).innerHTML;
	var phoneNumber= document.getElementById("phoneNumber_"+id).innerHTML;
	var kindOfFood= document.getElementById("kindOfFood_"+id).innerHTML;
	var restaurateurName = document.getElementById("resaurateurName_"+id).innerHTML;

	
	$('#UpdateRestaurant').modal('show') ;
	
	document.getElementById("updateName").value=name;
	document.getElementById("updatePhone").value=phoneNumber;
	document.getElementById("updateAddress").value=address;
	document.getElementById("updateIdRestaurant").value=id;
	$("#updateTypeFood").val(kindOfFood);
	$("#updateupdateRestaurateurId").val(restaurateurName);
	
	
}

