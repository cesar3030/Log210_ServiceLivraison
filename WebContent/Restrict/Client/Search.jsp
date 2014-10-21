<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
  		 
  		  <div class="row">
	  		  	<div class="col-md-12 col-xs-12"> 		 
	  		 			 <h1 class="text-center" >Faites votre choix !</h1>  	
	  		 			 <br>
	  		 	</div>
  		  </div> 
  		  <div class="row">
	  		  	<div class="col-md-3 col-xs-12"> 
		  		  	<select class="form-control"   id="restaurantList" name="restaurantList">
	 			
					</select>
					<br>
					<div class="menuList col-xs-12">
					
					</div>
	  		 	</div>
	  		 	<div class="connntainer">
	  		 		<div class="col-md-6  col-xs-12 "> 	
					    <section id="product">
					        <ul class="clear">
					        <meal class="clear2">
					          
					         </meal>
					        </ul>
					  </section>
					  </div>
					  <div class="col-md-3 hidden-xs "> 
					    <aside id="sidebar">
					        <div class="basket">
					            <div class="basket_list col-md-12">
					                <div class="head">
					                    <span class="name col-md-6">Nom du plat</span>
					                    <span class="price col-md-3" id="price">Prix</span>
					                    <span class="count col-md-3">Quantité</span>
					                 </div>
					                <ul id="choosenMeals">
					              
					                </ul>
					                <button type="button" onclick="cart_to_xml();" class="btn btn-default btn-xs col-md-5 col-md-offset-7">
										  <span class="glyphicon glyphicon-hand-right"></span>&nbspConfirmer
									</button>
					            </div>
					        </div>
					    </aside>		 
	  		 		</div>	 
	  		 		<div class="row visible-xs">
		  		 		<div class="col-xs-12"> 
						    <aside id="sidebar">
					        <div class="basket">
					            <div class="basket_list">
					                <div class="head">
					                    <span class="name col-xs-6">Nom du plat</span>
					                    <span class="price col-xs-3" id="price">Prix</span>
					                    <span class="count col-xs-3">Quantité</span>
					                </div>
					                <ul>
					                 
					                </ul>
					                <button type="button" onclick="cart_to_xml();" class="btn btn-default btn-xs col-xs-12">
										  <span class="glyphicon glyphicon-hand-right"></span>&nbspConfirmer
									</button>
					            </div>
					        </div>
					    </aside>		 
		  		 		</div>	 
	  		 		</div>  		 		 
	  		 	</div>
		 </div>
 <script src="<c:url value="/inc/js/DragNDrop/dragNDrop.js"/>"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="<c:url value="/inc/js/DragNDrop/jquery-ui-1.9.0.js"/>"></script>
<script src="<c:url value="/inc/js/ajax_order.js"/>"></script>
<script src="<c:url value="/inc/js/DragNDrop/jquery.ui.touch-punch.min.js"/>"></script>

<script>

	//Après le chargement de la page, je vais faire une requete ajax pour peupler la liste déroulante des restaurants
	$( window ).load(function() 
	{
		Requete_AJAX_GetVisibleResaturants("<c:url value="/GetListVisibleRestaurants"/>");
		updateDragNDropElements();
		//Requete_AJAX_GetVisibleResaturants("/GetListVisibleRestaurants");
	});
	
	//Quand un restaurant est sélectionné on va chercher en ajax ses menus
	$( "#restaurantList" )
	  .change(function () {
	    $( "select option:selected" ).each(function() {
	    Requete_AJAX_GetMenusResaturants("<c:url value="/GetAllMenuResto"/>");
	//	Requete_AJAX_GetMenusResaturants("Log210_ServiceLivraison/GetAllMenuResto");
	    });
	  }) .change();
	
	function displayMealsOfAMenu(menuId)
	{
		Requete_AJAX_GetMealsResaturant(menuId);
		setTimeout(function() {
			updateDragNDropElements();
		}, 1200);
		
	}
	
	/**
	 * @author Adem İlter et modifie par César Jeanroy
	 * {@link} http://www.webresourcesdepot.com/drag-n-drop-shopping-cart-with-jquery-ui-tutorial/ visité le 16/10/2014
	 */
	function updateDragNDropElements()
	{
		$(function () {

			// jQuery UI Draggable
			$("#product li").draggable({
			
				// brings the item back to its place when dragging is over
				revert:true,
			
				// once the dragging starts, we decrease the opactiy of other items
				// Appending a class as we do that with CSS
				drag:function () {
					$(this).addClass("active");
					$(this).closest("#product").addClass("active");
				},
			
				// removing the CSS classes once dragging is over.
				stop:function () {
					$(this).removeClass("active").closest("#product").removeClass("active");
				}
			});

	        // jQuery Ui Droppable
			$(".basket").droppable({
			
				// The class that will be appended to the to-be-dropped-element (basket)
				activeClass:"active",
			
				// The class that will be appended once we are hovering the to-be-dropped-element (basket)
				hoverClass:"hover",
			
				// The acceptance of the item once it touches the to-be-dropped-element basket
				// For different values http://api.jqueryui.com/droppable/#option-tolerance
				tolerance:"touch",
				drop:function (event, ui) {
			
					var basket = $(this),
							move = ui.draggable,
							itemId = basket.find("ul li[data-id='" + move.attr("data-id") + "']");
			
					// To increase the value by +1 if the same item is already in the basket
					if (itemId.html() != null) {
						itemId.find("input").val(parseInt(itemId.find("input").val()) + 1);
					}
					else {
						// Add the dragged item to the basket
						addBasket(basket, move);
			
						// Updating the quantity by +1" rather than adding it to the basket
						move.find("input").val(parseInt(move.find("input").val()) + 1);
					}
				}
			});

	        // This function runs onc ean item is added to the basket
	        function addBasket(basket, move) {
				basket.find("ul").append('<li  id="liMeal" class="aMeal" data-id="' + move.attr("data-id") + '">'
						+ '<span class="name col-md-6 col-xs-6">' + move.find("h5").html() + '</span>'
						+ '<span class="price col-md-3 col-xs-3" >' + move.find("h4").html() + '</span>'
						+ '<input class="count" value="1" type="text">'
						+ '<button class="delete">&#10005;</button>');
			}


	        // The function that is triggered once delete button is pressed
	        $(".basket ul li button.delete").live("click", function () {
				$(this).closest("li").remove();
			});

	    });

	}

	
</script>
<jsp:include page="/footer.jsp"></jsp:include>