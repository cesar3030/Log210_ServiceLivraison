/**
 * Fontion qui instancie une nouvelle map et qui l'affiche 
 * dans le conteneur dont l'id est passe en parametre
 * @param idContener	id du conteneur 
 */
function newMap(idContener,address)
{
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
}