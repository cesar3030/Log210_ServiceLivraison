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
* MÈthode qui envoie une requete AJAX au serveur afin de recevoir une nouvelle chaine XML pour actualiser les donnÈes.
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