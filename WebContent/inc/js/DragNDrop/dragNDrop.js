/**
 * @author Adem İlter
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
			basket.find("ul").append('<li data-id="' + move.attr("data-id") + '">'
					+ '<span class="name">' + move.find("h3").html() + '</span>'
					+ '<input class="count" value="1" type="text">'
					+ '<button class="delete">&#10005;</button>');
		}


        // The function that is triggered once delete button is pressed
        $(".basket ul li button.delete").live("click", function () {
			$(this).closest("li").remove();
		});

    });

}
