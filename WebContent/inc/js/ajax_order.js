/**
* Methode qui envoie une requete AJAX au serveur afin de recevoir les restauraurants qui n'ont pas de restaurateurs.
*/
function Requete_AJAX_GetVisibleResaturants(url_request)
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
				
				//j"ffiche le xml sur la console
				console.log(reponse);
				
				/*
				 * Je supprime le contenu du select avant de les actualiser pour
				 * pour ne pas avoir les nombre en double au cas ou je recharge la
				 * liste en ouvrant une autre fois le modal.
				 */
				clearContantOfSelect("restaurantList");
				
				//J'ajoute les restaurants a la liste deroulante
				addRestaurantsInSelect(xmldom);			
			}    
		}

		var url=url_request;//"/GetListRestaurantsWithoutRestaurateur";
		console.log(url);
		xhr.open("GET",url,true);
		xhr.send(null);
}

/**
* Methode qui envoie une requete AJAX au serveur afin de recevoir les menus du restaurant qui a ete selectionne dans la liste deroulante.
*/
function Requete_AJAX_GetMenusResaturants(url_request)
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
				
				//j"ffiche le xml sur la console
				console.log(reponse);
				
				//J'efface les menus du précédent restaurant sélectionné qui sont contenus dans les balises<menu>
				clearHtml("menu");
				
				addMenus(xmldom,"menuList");
				
			}    
		}

		var restaurantId=$( "#restaurantList" ).val();
		var url=url_request+"?restaurantId="+restaurantId;
		console.log(url);
		xhr.open("GET",url,true);
		xhr.send(null);
}

/**
* Methode qui envoie une requete AJAX au serveur afin de recevoir les menus du restaurant qui a ete selectionne dans la liste deroulante.
*/
function Requete_AJAX_GetMealsResaturant(idMenu)
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
				
				//j"ffiche le xml sur la console
				console.log(reponse);
				
				//J'efface les menus du précédent restaurant sélectionné
				clearHtml("meal");
				
				addMeals(xmldom,"clear2");
				
			}    
		}

		
		var url="/Log210_ServiceLivraison/GetMealsResaturant?menuId="+idMenu;
		console.log(url);
		xhr.open("GET",url,true);
		xhr.send(null);
}

/**
 * Méthode qui efface toutes les balises dont le nom est passé en parametre
 * @param balise 	Le nom des balises à supprimer.
 */
function clearHtml(balise)
{
	$(balise).empty();
}

/**
 * Méthode qui ajoute les menus dans le div dont l'id est passé en parametre.
 * @param idDiv 	id du div ou l'on veut inserer les menus.
 * @param xmlFromServer 	xml contenant les données 
 */
function addMenus(xmlFromServer,idDiv)
{
	var xmlListMenus= xmlFromServer.getElementsByTagName("MENU");
	var id,name,description,html;
	
	for (var i=0;i<xmlListMenus.length;i++)
	 { 
		
		id=xmlListMenus[i].getElementsByTagName("ID")[0].childNodes[0].nodeValue;
		
		name=xmlListMenus[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
		
		description=xmlListMenus[i].getElementsByTagName("DESCRIPTION")[0].childNodes[0].nodeValue;
		
		//Je fait mon html correspondant a un menu
		html="<menu><a onClick=\"displayMealsOfAMenu(\'"+id+"\');\"><div class=\"alert alert-info\" role=\"alert\"><h5>"+name+"</h5>"+description+"</div></a></menu>";
		
		//html="<menu><a onClick=\"alert(\'"+id+"\');\"><div class=\"alert alert-info\" role=\"alert\"><h5>"+name+"</h5>"+description+"</div></a></menu>";
		//J'ajoute le menu dans le div
		addInDiv(idDiv,html)
	
	}
	
}

/**
 * Méthode qui ajoute les repas dans le div dont l'id est passé en parametre.
 * @param idDiv 	id du div ou l'on veut inserer les menus.
 * @param xmlFromServer 	xml contenant les données 
 */
function addMeals(xmlFromServer,idDiv)
{
	var xmlListMenus= xmlFromServer.getElementsByTagName("MEAL");
	var id,name,description,price,html;
	
	for (var i=0;i<xmlListMenus.length;i++)
	 { 
		
		id=xmlListMenus[i].getElementsByTagName("ID")[0].childNodes[0].nodeValue;
		
		name=xmlListMenus[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
		
		description=xmlListMenus[i].getElementsByTagName("DESCRIPTION")[0].childNodes[0].nodeValue;
		
		price=xmlListMenus[i].getElementsByTagName("PRICE")[0].childNodes[0].nodeValue;

		//Je fait mon html correspondant a un repas
		html="<li data-id="+id+"> <a href=\"#\"><img src=\"/Log210_ServiceLivraison/inc/pictures/chef.jpg\" alt=\"\"><h5>"+name+"&nbsp;&nbsp;&nbsp;</h5><h4>"+price+"$</h4><p>"+description+"</p></a></li>";
		//html="<li data-id=\"3\"><a href=\"#\"><img src=\"http://lorempixel.com/150/100/technics/3/\" alt=\"\"><h3>IBM 15\" super-fast computer</h3><p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p></a></li>";
		//J'ajoute le menu dans le div
		addInDiv(idDiv,html);
	
	}
	
}

/**
 * Méthode qui ajoute le contenu passé en parametre dans le div voulu
 * @param idDiv				id du div a modifier
 * @param newContant		contenu a inserer dans le div
 */
function addInDiv(idDiv,newContant)
{
	$( $.parseHTML(newContant ) ).appendTo( "."+idDiv);
}

/**
 * Fonction qui efface tous les choix d'une liste deroulante
 * @param idSelect
 */
function clearContantOfSelect(idSelect)
{
	//$("#"+idSelect+"  .option:lt(-1)").remove();
	n=document.getElementById(idSelect).length; // nombre d'options de la liste
	for (i=0;i<n;i++)
	{
		document.getElementById(idSelect)[0] = null;// suppression ligne par ligne
	}
}

/**
 * Fonction qui ajoute a la liste deroulante les restaurants recus du serveur par xml
 */
function addRestaurantsInSelect( xmlFromServer)
{
	var xmlListRestaurants= xmlFromServer.getElementsByTagName("RESTAURANT");
	var id,name;
	
	/*
	 * J'ajoute le choix, sans restaurant pour qu'un restaurateur 
	 * ne sois assigne a aucun resataurant 
	 * au moment de la creation de son compte
	 */
	addNewOptionInSelect("restaurantList",0,"Liste des Restaurants");
	
	for (var i=0;i<xmlListRestaurants.length;i++)
	 { 
		
		id=xmlListRestaurants[i].getElementsByTagName("ID")[0].childNodes[0].nodeValue;
		
		name=xmlListRestaurants[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue;
		
		//J'ajoute le restaurant dans la liste deroulante
		addNewOptionInSelect("restaurantList",id,name);
	
	}
}

/**
 * Fonction qui ajoute une balise OPTION dans le SELECT qui affiche
 * la liste des restaurants dans le formulaire d'ajout d'un restaurateur.
 * 
 * @param idSelect		id du select a updater
 * @param id				l'identifiant du restaurant dans la table tbrestaurant
 * @param name			nom du restaurant
 */
function addNewOptionInSelect(idSelect,id,name)
{
	var x = document.getElementById(idSelect);
    var opt = document.createElement("option");
    opt.value = id;
    opt.innerHTML = name;
    x.add(opt);
}


/**
 * Fonction qui cree un xml contenant tous les plats commandes 
 * ainsi que leur quantite afin que le serveur puisse les enregistrer 
 * dans la BD et effectuer les operations liees au traitement de la commande
 */
function cart_to_xml()
{
	//Je recupere le contenu du pannier
	var html=$('#choosenMeals').html();
	//Je cree le debut de mon xml
	var xml="<DATA>";
	var id,quantity;
	
	//pour chaque item dans le panier je recupere l<il du plat et la quantite voulu
	$( ".aMeal" ).each(function() {
		
		 id=$( this ).attr("data-id");	
		 quantity=$( ".count",this ).val();	
		 
		 //J'ajoute le plat et sa quantite au xml
		 xml=addToXml(xml,id,quantity);
		}); 
	//je ferme la balise cart du xml
	xml=xml+"</DATA>";
	$.get( "/Log210_ServiceLivraison/ShowOrderSummary", { cart: xml } )
	  .done(function( data ) {
	    alert( "Data Loaded: " + data );
	  });
	console.log(xml);
}

/**
 * Fonction qui ajoute le plat et la quantite voulu 
 * au xml qui va etre envoye au serveur pour enregistrer la commande
 * @param xml				XML qui va etre envoyer au serveur contenant la commande
 * @param idMeal			l'identifiant du plat commande
 * @param quantity		Le nombre de fois que le plat a ete commande
 */
function addToXml(xml,idMeal,quantity)
{
	xml=xml	+"<MEAL>"
					+"<ID>"+idMeal+"</ID>"
					+"<QUANTITY>"+quantity+"</QUANTITY>"
					+"</MEAL>";
	return xml;
}














/*
	Fonction qui cree et renvoie un objet XMLHttpRequest utilisÈ pour dialoguer en AJAX avec le serveur
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
