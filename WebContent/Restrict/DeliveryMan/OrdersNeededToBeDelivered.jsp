<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:include page="/header.jsp"></jsp:include>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
<script>
var mapDirectionsDisplay = {};
var mapDirectionsService = {};
var mapClientAddress = {};
var mapRestaurantAddress = {};
var listMaps= {};

var idcontenerTable= new Array();

function calcRouteAllMaps()
{
	  for(var i=0; i< idcontenerTable.length;i++)
		  {
			  var directionsDisplay2 = mapDirectionsDisplay[idcontenerTable[i]];
			  var directionsService2 = mapDirectionsService[idcontenerTable[i]];
			  var clientAddress = mapClientAddress[idcontenerTable[i]];
			  var restaurantAddress = mapRestaurantAddress[idcontenerTable[i]];
			  var map2 = listMaps[idcontenerTable[i]];
			  calcRoute(restaurantAddress,clientAddress,map2,directionsDisplay2,directionsService2);
			  
		  }
}

function addInMaps(idContener,restaurantAddress,clientAddress)
{
	  idcontenerTable.push(idContener);
	  listMaps[idContener] = map;
	  mapDirectionsDisplay[idContener] = directionsDisplay;
	  mapDirectionsService[idContener] = directionsService;
	  mapClientAddress[idContener]  = clientAddress;
	  mapRestaurantAddress[idContener]  = restaurantAddress;
}

function newMap(idContener,restaurantAddress,clientAddress)
{
	    initialize(idContener);
	    addInMaps(idContener,restaurantAddress,clientAddress);
	
}

var directionsDisplay;
var directionsService;
var map;

function initialize(idContener) {
	//alert("initialize");
	directionsService = new google.maps.DirectionsService();
  directionsDisplay = new google.maps.DirectionsRenderer();
  var mapOptions = {
    zoom: 7,
    center: new google.maps.LatLng(41.850033, -87.6500523)
  };
  map = new google.maps.Map(document.getElementById(idContener),
      mapOptions);
  directionsDisplay.setMap(map);
  //directionsDisplay.setPanel(document.getElementById('directions-panel'));

}

function calcRoute(restaurantAddress,clientAddress, map2,directionsDisplay2, directionsService2) {
  
	var waypoints = [];
	waypoints.push({
        location: "211 Rue Notre-Dame Ouest Montréal, Québec H2Y 1T3",
        stopover: true
    });
	//var mapNode map2.getDiv();
	
  var request = {
    origin: restaurantAddress,
    destination: clientAddress,
    waypoints: waypoints,
    travelMode: google.maps.TravelMode.DRIVING
  };
  directionsService2.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
    //	alert("calcRoute");
      directionsDisplay2.setDirections(response);
    }
  });
  
  
}
</script>	 
<div class="row">
	<div class="col-md-12"> 		 
		 <h2 class="text-center" >Liste des commandes a prendre en charge</h2>  		 		 
	</div>
	<c:if test="${!empty returnMessage.succes}">
		<script>
 		toastr.options = {
 			  "closeButton": true,
 			  "debug": false,
 			  "positionClass": "toast-top-right",
 			  "onclick": null,
 			  "showDuration": "300",
 			  "hideDuration": "1000",
 			  "timeOut": "5000",
 			  "extendedTimeOut": "1000",
 			  "showEasing": "swing",
 			  "hideEasing": "linear",
 			  "showMethod": "fadeIn",
 			  "hideMethod": "fadeOut"
 			}
 		  
 			 toastr.success('${returnMessage.succes}', 'Succes');
 		 </script>
		</c:if>
		<c:if test="${!empty returnMessage.warning}">
	  		 <script>
		  		toastr.options = {
		  			  "closeButton": true,
		  			  "debug": false,
		  			  "positionClass": "toast-top-right",
		  			  "onclick": null,
		  			  "showDuration": "300",
		  			  "hideDuration": "1000",
		  			  "timeOut": "5000",
		  			  "extendedTimeOut": "1000",
		  			  "showEasing": "swing",
		  			  "hideEasing": "linear",
		  			  "showMethod": "fadeIn",
		  			  "hideMethod": "fadeOut"
		  			}
		  		  
		  			 toastr.warning('${returnMessage.warning}', 'Attention !!');
	  		 </script>
		</c:if>
  		 <c:if test="${!empty returnMessage.fail}">
			<script>
			
	 		toastr.options = {
		  			  "closeButton": true,
		  			  "debug": false,
		  			  "positionClass": "toast-top-right",
		  			  "onclick": null,
		  			  "showDuration": "300",
		  			  "hideDuration": "1000",
		  			  "timeOut": "5000",
		  			  "extendedTimeOut": "1000",
		  			  "showEasing": "swing",
		  			  "hideEasing": "linear",
		  			  "showMethod": "fadeIn",
		  			  "hideMethod": "fadeOut"
		  			}
	  			 toastr.error('${returnMessage.fail}', 'Erreur')
	  		</script>
		</c:if>
</div>

<c:forEach items="${orderReady}" var="orderForDelivery">
<div class="row">
	<div class="panel panel-primary col-md-10 col-md-offset-1 orderForDelivery">
		<div class="col-md-4">
			<h4>Date et heure livraison: <c:out value="${orderForDelivery.order.hourAndDate}"/></h4>
			<h5>Nom et prénom du client: <c:out value="${orderForDelivery.client.firstName}"/> <c:out value="${orderForDelivery.client.name}"/></h5>
			<h5>Restaurant: <c:out value="${orderForDelivery.restaurant.name}"/></h5>
			<h5>Adresse du restaurant: <c:out value="${orderForDelivery.restaurant.address}"/></h5>
			<h5>Adresse de livraison: <c:out value="${orderForDelivery.address.address}"/></h5>
			<form method=get action="<c:url value="/AcceptOrder"/>">
				<input type="hidden" name="idOrder" value="<c:out value="${orderForDelivery.order.idOrder}"/>">
				<button type="submit" class="btn btn-default btn-lg">
				  <span class="glyphicon glyphicon-road"></span> Prende en charge
				</button>
			</form>
		</div>
		<div class="col-md-8">
			<div class="map-canvas" id="map-canvas-${orderForDelivery.order.idOrder}"></div>
		</div>
	</div>	
</div>
<script>
//newMap("map-canvas-${orderForDelivery.order.idOrder}","${orderForDelivery.restaurant.address}","${orderForDelivery.address.address}");
//initialize("map-canvas-${orderForDelivery.order.idOrder}","${orderForDelivery.restaurant.address}","${orderForDelivery.address.address}");
newMap("map-canvas-${orderForDelivery.order.idOrder}","${orderForDelivery.restaurant.address}","${orderForDelivery.address.address}");

//calcRoute("${orderForDelivery.restaurant.address}","${orderForDelivery.address.address}");
</script>
</c:forEach>
<script>
calcRouteAllMaps();
</script>
<jsp:include page="/footer.jsp"></jsp:include>