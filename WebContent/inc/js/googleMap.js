var map;
var panel;
var initialize;
var calculate;
var direction;
var origin;
var destination;


/**
 * Fontion qui instancie une nouvelle map et qui l'affiche 
 * dans le conteneur dont l'id est passe en parametre
 * @param idContener	id du conteneur 
 */
function newMap(idContener,restaurantAddress,clientAddress)
{
	/*
	  var map;
	  var geocoder;
	  var map;
	  //var address ="Montréal, Canada";
	  
	  function initialize() {
	    geocoder = new google.maps.Geocoder();
	    var latlng = new google.maps.LatLng(-34.397, 150.644);
	    var myOptions = {
	      zoom: 14,
	      center: latlng,
	    mapTypeControl: true,
	    mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
	    navigationControl: true,
	      mapTypeId: google.maps.MapTypeId.ROADMAP
	    };
	    map = new google.maps.Map(document.getElementById(idContener), myOptions);
	    if (geocoder) {
	      geocoder.geocode( { 'address': address}, function(results, status) {
	        if (status == google.maps.GeocoderStatus.OK) {
	          if (status != google.maps.GeocoderStatus.ZERO_RESULTS) {
	          map.setCenter(results[0].geometry.location);

	            var infowindow = new google.maps.InfoWindow(
	                { content: '<b>'+address+'</b>',
	                  size: new google.maps.Size(150,50)
	                });

	            var marker = new google.maps.Marker({
	                position: results[0].geometry.location,
	                map: map, 
	                title:address
	            }); 
	            google.maps.event.addListener(marker, 'click', function() {
	                infowindow.open(map,marker);
	            });

	          } else {
	            alert("No results found");
	          }
	        } else {
	          alert("Geocode was not successful for the following reason: " + status);
	        }
	      });
	    }
	  }
	  
	  initialize();
	 // google.maps.event.addDomListener(window, 'load', initialize);
	 */
	/* 
	var myOptions = {
		      zoom: 14,
		    mapTypeControl: true,
		    mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
		    navigationControl: true,
		      mapTypeId: google.maps.MapTypeId.ROADMAP
		    };
	
	//direction = new google.maps.Map(document.getElementById(idContener), myOptions);
	direction = new google.maps.DirectionsRenderer({
	    map   : map, 
	    panel : document.getElementById(idContener) 
	});
	origin= restaurantAddress;
	destination= clientAddress;
	//alert(origin+"  "+destination);
	
	    if(origin && destination){
	        var request = {
	            origin      : origin,
	            destination : destination,
	            travelMode  : google.maps.DirectionsTravelMode.DRIVING // Type de transport
	        }
	        var directionsService = new google.maps.DirectionsService(); // Service de calcul d'itinéraire
	        directionsService.route(request, function(response, status){ // Envoie de la requête pour calculer le parcours
	            if(status == google.maps.DirectionsStatus.OK){
	              direction.setDirections(response); // Trace l'itinéraire sur la carte et les différentes étapes du parcours
	            }
	        });
	    } //http://code.google.com/intl/fr-FR/apis/maps/documentation/javascript/reference.html#DirectionsRequest
	
	   
	initialize(idContener);
	calculate = itinerary(restaurantAddress,clientAddress);*/
	    initialize(idContener);
	    calcRoute(restaurantAddress,clientAddress);
	
}

var directionsDisplay;
var directionsService = new google.maps.DirectionsService();

function initialize(idContener) {
  directionsDisplay = new google.maps.DirectionsRenderer();
  var mapOptions = {
    zoom: 7,
    center: new google.maps.LatLng(41.850033, -87.6500523)
  };
  var map = new google.maps.Map(document.getElementById(idContener),
      mapOptions);
  directionsDisplay.setMap(map);
  //directionsDisplay.setPanel(document.getElementById('directions-panel'));

}

function calcRoute(restaurantAddress,clientAddress) {
  
  var request = {
    origin: restaurantAddress,
    destination: clientAddress,
    travelMode: google.maps.TravelMode.DRIVING
  };
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
    }
  });
}