/*
	Fonction qui crÈÈ et renvoie un objet XMLHttpRequest utilisÈ pour dialoguer en AJAX avec le serveur
*/
function getXhr()
{
    var xhr = null; 
	if(window.XMLHttpRequest) // Firefox et autres
		   xhr = new XMLHttpRequest(); 
	else if(window.ActiveXObject){ // Internet Explorer 
	   try {
                xhr = new ActiveXObject("Msxml2.XMLHTTP");
            } 
            catch (e) 
            {
	            xhr = new ActiveXObject("Microsoft.XMLHTTP");
	        }
		}
		else { // XMLHttpRequest non supportÈ par le navigateur 
		   alert("Votre navigateur ne supporte pas les objets XMLHTTPRequest..."); 
		   xhr = false; 
		} 
            return xhr;
}


/**
* Methode qui envoie une requete AJAX au serveur afin de recevoir une nouvelle chaine XML pour actualiser les donnÈes.
*/
function Requete_AJAX_Email()
{
	var email = document.getElementById("email_subscribe").value;
	console.log(email);
	if(email!="")
	{
		var xhr = getXhr();
		// On dÈfini ce qu'on va faire quand on aura la rÈponse
		xhr.onreadystatechange = function()
		{
			// On ne fait quelque chose que si on a tout reÁu et que le serveur est ok
			if(xhr.readyState == 4 && xhr.status == 200)
			{
				reponse = xhr.responseText;
				xmldom = (new DOMParser()).parseFromString(reponse, 'text/xml');
				
				console.log(reponse);
				
				updateStatutInputEmail(xmldom);			
			}    
		}

		var url="CheckEmailNotUsed?mail="+email;
		console.log(url);
	xhr.open("GET",url,true);
	xhr.send(null);
		/*xhr.open("POST",url);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	    xhr.send("email=" + email);*/
	}
			
}

/**
* Methode qui envoie une requete AJAX au serveur afin de recevoir les restaurateurs actifs.
*/
function Requete_AJAX_GetActiveRestaurateurList()
{
	
	
		var xhr = getXhr();
		// On dÈfini ce qu'on va faire quand on aura la rÈponse
		xhr.onreadystatechange = function()
		{
			// On ne fait quelque chose que si on a tout reÁu et que le serveur est ok
			if(xhr.readyState == 4 && xhr.status == 200)
			{
				reponse = xhr.responseText;
				xmldom = (new DOMParser()).parseFromString(reponse, 'text/xml');
				
				console.log(reponse);
				
				/*
				 * Je supprime le contenu des select avant de les actualiser pour
				 * pour ne pas avoir les nombre en double au cas ou je recharge la
				 * liste en ouvrant un autre modal.
				 */
				clearContantOfSelect("updateRestaurateurId")
				clearContantOfSelect("restaurateurId")
				
				//J'ajoute les restaurateurs a la liste deroulante
				addRestaurateurInSelect(xmldom);			
			}    
		}

		var url="GetActiveResataurateur";
		console.log(url);
	xhr.open("GET",url,true);
	xhr.send(null);
		/*xhr.open("POST",url);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	    xhr.send("email=" + email);*/
	
			
}

/*
 * Fonction qui change la couleur du input email dans les formulaires d<inscription et modification
 * En fonction de si l'email est deja utilise ou non
 */
function updateStatutInputEmail( ajaxResponse )
{
	
	var retourXml= ajaxResponse.getElementsByTagName("message")[0];
	var state = retourXml.childNodes[0].nodeValue;
	
	if(state =="alreadyUsed")
	{
		
		if ( $('#email_part').hasClass('has-error') )
		{
			
		}
		else
		{
			document.getElementById('error-message').innerHTML = '<h5 id="email-error">Courriel deja utilise !</h5>';
			$('#email_part').addClass('has-error') 
		}
	}
	else
	{
		if ( $('#email_part').hasClass('has-success') )
		{
			
		}
		else
		{
			document.getElementById('email-error').remove();
			$('#email_part').removeClass('has-error') 
			$('#email_part').addClass('has-success') 
		}
	}
}

/**
 * Methode qui va ajouter dans le formulaire d'ajout/modification d'un restaurant
 * la liste des restaurateurs.
 * @param ajaxResponse
 */
function addRestaurateurInSelect( ajaxResponse )	
{
	var xmlListRestaurateur= ajaxResponse.getElementsByTagName("RESTAURATEUR");
	var id,name,firstName;
	
	for (var i=0;i<xmlListRestaurateur.length;i++)
	 { 
		
		id=xmlListRestaurateur[i].getElementsByTagName("ID")[0].childNodes[0].nodeValue;
		
		name=xmlListRestaurateur[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
		firstName=xmlListRestaurateur[i].getElementsByTagName("FIRSTNAME")[0].childNodes[0].nodeValue;
		
		
		//J'update les deux formulaires
		addNewOptionInSelect("updateRestaurateurId",id,name,firstName);	
		addNewOptionInSelect("restaurateurId",id,name,firstName);	
	}
	
}

/**
 * Fonction qui ajoute une balise OPTION dans le SELECT qui represente
 * la liste des restaurateurs
 * 
 * @param idSelect		id du select a updater
 * @param id				l'identifiant du restaurateur dans la table tbuseraccount
 * @param name			nom du restaurateur
 * @param firstName	prenom du restaurateur
 */
function addNewOptionInSelect(idSelect,id,name,firstName)
{
	var x = document.getElementById(idSelect);
    var opt = document.createElement("option");
    opt.value = id;
    opt.innerHTML = firstName+" "+name;
    x.add(opt);
}

function clearContantOfSelect(idSelect)
{
	//$("#"+idSelect+"  .option:lt(-1)").remove();
	n=document.getElementById(idSelect).length; // nombre d'options de la liste
	for (i=0;i<n;i++)
	{
		document.getElementById(idSelect)[0] = null;// suppression ligne par ligne
	}
}

