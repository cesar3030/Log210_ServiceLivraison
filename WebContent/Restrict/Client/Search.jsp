<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<jsp:include page="/header.jsp"></jsp:include>	
  		 
  		  <div class="row">
	  		  	<div class="col-md-12"> 		 
	  		 			 <h1 class="text-center" >Faites votre choix !</h1>  	
	  		 			 <br>
	  		 	</div>
  		  </div> 
  		  <div class="row">
	  		  	<div class="col-md-3"> 
	  		 	</div>
	  		 	<div class="connntainer">
	  		 		<div class="col-md-6"> 	
					    <section id="product">
					        <ul class="clear">
					            <li data-id="1">
					                <a href="#">
					                    <img src="http://lorempixel.com/150/100/technics/1/" alt="">
					                    <h3>iPad 32gb retina screen</h3>
					                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>
					                </a>
					            </li>
					            <li data-id="2">
					                <a href="#">
					                    <img src="http://lorempixel.com/150/100/technics/2/" alt="">
					            		<h3>Turntable mixer</h3>
					                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>
					                </a>
					            </li>
					            <li data-id="3">
					                <a href="#">
					                    <img src="http://lorempixel.com/150/100/technics/3/" alt="">
					            		<h3>IBM 15" super-fast computer</h3>
					                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>
					                </a>
					            </li>
					            <li data-id="4">
					                <a href="#">
					                    <img src="http://lorempixel.com/150/100/technics/4/" alt="">
					            		<h3>Some crazy circuit</h3>
					                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>
					                </a>
					            </li>
					            <li data-id="5">
					                <a href="#">
					                    <img src="http://lorempixel.com/150/100/technics/5/" alt="">
					                    <h3>White earpieces</h3>
					                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>
					                </a>
					            </li>
					            <li data-id="6">
					                <a href="#">
					                    <img src="http://lorempixel.com/150/100/technics/6/" alt="">
					            		<h3>Headphones with free keyboard</h3>
					                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>
					                </a>
					            </li>
					            <li data-id="7">
					                <a href="#">
					                    <img src="http://lorempixel.com/150/100/technics/7/" alt="">
					            		<h3>iPhone 4S</h3>
					                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>
					                </a>
					            </li>
					            <li data-id="8">
					                <a href="#">
					                    <img src="http://lorempixel.com/150/100/technics/8/" alt="">
					            		<h3>Another crazy circuit or..</h3>
					                    <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>
					                </a>
					            </li>
					        </ul>
					  </section>
					  </div>
					  <div class="col-md-2"> 
					    <aside id="sidebar">
					        <div class="basket">
					            <div class="basket_list">
					                <div class="head">
					                    <span class="name">Nom du plat</span>
					                    <span class="count">Quantité</span>
					                </div>
					                <ul>
					                    <!--li>
					                        <span class="name">Samsung S3 asd asdasdaf dfsdghgfg dgfg</span>
					                        <input class="count" value="1" type="text">
					                        <button class="delete">&#10005;</button>
					                    </li-->
					                </ul>
					            </div>
					        </div>
					    </aside>		 
	  		 		</div>	   		 		 
	  		 	</div>
		 </div>
 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="<c:url value="/inc/js/DragNDrop/jquery-ui-1.9.0.js"/>"></script>
<script src="<c:url value="/inc/js/DragNDrop/dragNDrop.js"/>"></script>

<jsp:include page="/footer.jsp"></jsp:include>