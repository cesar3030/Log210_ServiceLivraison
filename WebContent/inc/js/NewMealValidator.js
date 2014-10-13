function checkprice() {
	var price = document.getElementById('price').value;

	var priceNum;
	if (price != "") {
		priceNum = price.replace(/[^0-9]/g, '');
		if (priceNum.length == 0) {
			document.getElementById('price').value = "";
			alert("Veuillez rentrez des chiffres pour le prix");

		}

	}

}