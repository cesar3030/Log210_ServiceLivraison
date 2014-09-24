function verifierDate() {
	var date_pas_sure = document.getElementById('brithday').value;
	var date_temp = date_pas_sure.split('-');
	date_temp[1] -= 1; // On rectifie le mois !!!
	var ma_date = new Date();
	ma_date.setFullYear(date_temp[0]);
	ma_date.setMonth(date_temp[1]);
	ma_date.setDate(date_temp[2]);
	if (ma_date.getFullYear() == date_temp[0]
			&& ma_date.getMonth() == date_temp[1]
			&& ma_date.getDate() == date_temp[2]) {

	} else {
		document.getElementById('brithday').value = "";
		alert('Veuillez rentrer une date correct');
	}

}

function verifierNumeroTel() {
	var numtel = document.getElementById('phone').value;
	
	
	
	if(numtel.length==10) {

	} else {
		document.getElementById('phone').value = "";
		alert('Veuillez rentrer une date correct');
	}

}

function concordanceMdp(){
	var mdp = document.getElementById('password1').value;
	var mdp2 = document.getElementById('password2').value;

	if(mdp!=mdp2) {

	alert('Revérifier votre saisie');
	document.getElementById('password1').value = "";
	document.getElementById('password2').value = "";
	return false;
	} else {

	alert('les mots de passe concordent');
	return true;
	}
	}