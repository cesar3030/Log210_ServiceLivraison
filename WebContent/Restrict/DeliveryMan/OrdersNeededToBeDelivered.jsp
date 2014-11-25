<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.ResourceBundle"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ page import="java.util.Locale"%>
<jsp:include page="/header.jsp"></jsp:include>
<%
	ClassLoader cl = ClassLoader.getSystemClassLoader();
	
	ResourceBundle.clearCache(cl);
	
	//System.out.println("LANGUE : " + session.getAttribute("langue"));

	Locale localeUS = new Locale("en", "US");

	Locale localeFR = new Locale("fr", "FR");

	ResourceBundle resourceBundle = ResourceBundle.getBundle("ca.etsmtl.log210.Traduction.Bundle", localeFR);

	if (session.getAttribute("langue").equals("en")) {
		System.out.println("EN ANGLAIS");
		resourceBundle = ResourceBundle.getBundle(
				"ca.etsmtl.log210.Traduction.Bundle", localeUS);
	}
	if (session.getAttribute("langue").equals("fr")) {
		System.out.println("EN FRANCAIS");
		resourceBundle = ResourceBundle.getBundle(
				"ca.etsmtl.log210.Traduction.Bundle", localeFR);
	}
	System.out.println("ici");
%>


<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
<!-- <script src="<c:url value="/inc/js/jquery.mixitup.js"/>"></script> -->
<script
	src="http://cdn.jsdelivr.net/jquery.mixitup/latest/jquery.mixitup.min.js"></script>
<script>
	var mapDirectionsDisplay = {};
	var mapDirectionsService = {};
	var mapClientAddress = {};
	var mapRestaurantAddress = {};
	var mapIdDivItineraryDirrections = {};

	var idcontenerTable = new Array();



	function calcRouteAllMaps(deleveryManPosition) {
		for (var i = 0; i < idcontenerTable.length; i++) {
			var directionsDisplay2 = mapDirectionsDisplay[idcontenerTable[i]];
			var directionsService2 = mapDirectionsService[idcontenerTable[i]];
			var clientAddress = mapClientAddress[idcontenerTable[i]];
			var restaurantAddress = mapRestaurantAddress[idcontenerTable[i]];
			var idDivItineraryDirrections2 = mapIdDivItineraryDirrections[idcontenerTable[i]];
			var orderNum = "order-" + idcontenerTable[i].split(/-/)[2];
			calcRoute(restaurantAddress, clientAddress, directionsDisplay2,
					directionsService2, idDivItineraryDirrections2, orderNum,
					deleveryManPosition);

		}
		if (deleveryManPosition != null) {
			$('#orderContainer').mixItUp('sort', 'distance:desc');
		}
	}

	function addInMaps(idContener, restaurantAddress, clientAddress,
			idDivItineraryDirrections) {
		idcontenerTable.push(idContener);
		mapIdDivItineraryDirrections[idContener] = idDivItineraryDirrections;
		mapDirectionsDisplay[idContener] = directionsDisplay;
		mapDirectionsService[idContener] = directionsService;
		mapClientAddress[idContener] = clientAddress;
		mapRestaurantAddress[idContener] = restaurantAddress;
	}

	function newMap(idContener, restaurantAddress, clientAddress,
			idDivItineraryDirrections) {
		initialize(idContener, idDivItineraryDirrections);
		addInMaps(idContener, restaurantAddress, clientAddress,
				idDivItineraryDirrections);

	}

	var directionsDisplay;
	var directionsService;
	var map;

	function initialize(idContener, idDivItineraryDirrections) {
		//alert("initialize");
		directionsService = new google.maps.DirectionsService();
		directionsDisplay = new google.maps.DirectionsRenderer();
		var mapOptions = {
			zoom : 7,
			center : new google.maps.LatLng(41.850033, -87.6500523)
		};
		map = new google.maps.Map(document.getElementById(idContener),
				mapOptions);
		directionsDisplay.setMap(map);
		// directionsDisplay.setPanel(document.getElementById(idDivItineraryDirrections));

	}

	function calcRoute(restaurantAddress, clientAddress, directionsDisplay2,
			directionsService2, idDivItineraryDirrections2, orderNum,
			deleveryManPosition) {
		var waypoints = [];
		if (deleveryManPosition != null && deleveryManPosition != "") {
			waypoints.push({
				location : restaurantAddress,
				stopover : true
			});

			var request = {
				origin : deleveryManPosition,
				destination : clientAddress,
				waypoints : waypoints,
				travelMode : google.maps.TravelMode.DRIVING
			};
		} else {
			var request = {
				origin : restaurantAddress,
				destination : clientAddress,
				travelMode : google.maps.TravelMode.DRIVING
			};
		}

		//var mapNode map2.getDiv();

		directionsService2.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {

				$("#" + orderNum).attr("data-distance",
						response.routes[0].legs[0].distance.value);
				directionsDisplay2.setDirections(response);
			}
		});

		directionsDisplay2.setPanel(document
				.getElementById(idDivItineraryDirrections2));

	}

	function updateDeleveryManPosition() {

		var deleveryManPosition = $("#deleveryManPosition").val();
		calcRouteAllMaps(deleveryManPosition);

	}

	
</script>

<c:if test="${sessionScope.langue=='en'}">
	<a href="<c:url value="/SwitchLanguage"/>">
		<img src="inc/pictures/French_Flag.jpg" width=30 height=20>
	</a>
</c:if> 
<c:if test="${sessionScope.langue=='fr'}">
	<a href="<c:url value="/SwitchLanguage"/>">
		<img src="inc/pictures/US_Flag.gif" width=30 height=20>
	</a>
</c:if> 

<div class="row">
	<div class="col-md-12">

		<%=resourceBundle.getString("TRADUCTION")%>

		<h2 class="text-center">
			<%=resourceBundle.getString("TITRE")%>


		</h2>
	</div>
	<br> <br>
	<div class="col-md-6 col-md-offset-3">
		<input type="text" class="form-control"
			placeholder="<%=resourceBundle.getString("INPUT")%>"
			id="deleveryManPosition" onblur="updateDeleveryManPosition();" />
	</div>
	<c:if test="${!empty returnMessage.succes}">
		<script>
			toastr.options = {
				"closeButton" : true,
				"debug" : false,
				"positionClass" : "toast-top-right",
				"onclick" : null,
				"showDuration" : "300",
				"hideDuration" : "1000",
				"timeOut" : "5000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "fadeIn",
				"hideMethod" : "fadeOut"
			}

			toastr.success('${returnMessage.succes}', 'Succes');
		</script>
	</c:if>
	<c:if test="${!empty returnMessage.warning}">
		<script>
			toastr.options = {
				"closeButton" : true,
				"debug" : false,
				"positionClass" : "toast-top-right",
				"onclick" : null,
				"showDuration" : "300",
				"hideDuration" : "1000",
				"timeOut" : "5000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "fadeIn",
				"hideMethod" : "fadeOut"
			}

			toastr.warning('${returnMessage.warning}', 'Attention !!');
		</script>
	</c:if>
	<c:if test="${!empty returnMessage.fail}">
		<script>
			toastr.options = {
				"closeButton" : true,
				"debug" : false,
				"positionClass" : "toast-top-right",
				"onclick" : null,
				"showDuration" : "300",
				"hideDuration" : "1000",
				"timeOut" : "5000",
				"extendedTimeOut" : "1000",
				"showEasing" : "swing",
				"hideEasing" : "linear",
				"showMethod" : "fadeIn",
				"hideMethod" : "fadeOut"
			}
			toastr.error('${returnMessage.fail}', 'Erreur')
		</script>
	</c:if>
</div>
<div class="row" id="orderContainer">
	<c:forEach items="${orderReady}" var="orderForDelivery">
		<div class="row mix" style="width: 100%"
			id="order-${orderForDelivery.order.idOrder}">
			<div
				class="panel panel-primary col-md-10 col-md-offset-1 orderForDelivery">
				<div class="col-md-4">
					<h4>
						<%=resourceBundle.getString("DATE_HEURE")%>

						<c:out value="${orderForDelivery.order.hourAndDate}" />
					</h4>
					<h5>
						<%=resourceBundle.getString("NOM_PRENOM")%>

						<c:out value="${orderForDelivery.client.firstName}" />
						<c:out value="${orderForDelivery.client.name}" />
					</h5>
					<h5>
						<%=resourceBundle.getString("RESTAURANT")%>

						<c:out value="${orderForDelivery.restaurant.name}" />
					</h5>
					<h5>
						<%=resourceBundle.getString("ADRESSE_RESTAURANT")%>

						<c:out value="${orderForDelivery.restaurant.address}" />
					</h5>
					<h5>
						<%=resourceBundle.getString("ADRESSE_LIVRAISON")%>

						<c:out value="${orderForDelivery.address.address}" />
					</h5>
					<form method=get action="<c:url value="/AcceptOrder"/>">
						<input type="hidden" name="idOrder"
							value="<c:out value="${orderForDelivery.order.idOrder}"/>">
						<button type="submit" class="col-md-5 btn btn-default btn-md">
							<span class="glyphicon glyphicon-road"></span>

							<%=resourceBundle.getString("CHARGE")%>
						</button>
						<a href="#infos-${orderForDelivery.order.idOrder}"
							data-toggle="modal">
							<button type="submit"
								class="col-md-5 col-md-offset-1 btn btn-default btn-md">
								<span class="glyphicon glyphicon-search"></span>

								<%=resourceBundle.getString("DETAILS")%>
							</button>
						</a>
					</form>
				</div>
				<div class="col-md-8">
					<div class="map-canvas"
						id="map-canvas-${orderForDelivery.order.idOrder}"></div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="infos-${orderForDelivery.order.idOrder}">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div id="directions-panel-${orderForDelivery.order.idOrder}"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		<script>
			newMap("map-canvas-${orderForDelivery.order.idOrder}","${orderForDelivery.restaurant.address}","${orderForDelivery.address.address}","directions-panel-${orderForDelivery.order.idOrder}");
		</script>
	</c:forEach>
</div>
<script>
	calcRouteAllMaps(null);

	$('#orderContainer').mixItUp();
</script>
<jsp:include page="/footer.jsp"></jsp:include>