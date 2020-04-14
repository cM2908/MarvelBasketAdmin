<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag"%>    
<!DOCTYPE html>
<html>
<head>
	<title>Product Details</title>
	<%@ include file="parts/meta.jsp"%>
	<%@ include file="parts/css_links.jsp"%>
	<style type="text/css">
		#toast-container.toast-top-center > div,
		#toast-container.toast-bottom-center > div {
			opacity:1;
			width: 400px;
			margin-left: auto;
			margin-right: auto;
			border-radius:6px;
		}
	</style>
</head>
<body class="hold-transition sidebar-mini layout-fixed layout-navbar-fixed">
	<div class="wrapper">
		<jsp:include page="parts/header.jsp"></jsp:include>
		<jsp:include page="parts/sidebar.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
			        	<div class="col-sm-6">
			            <h3 class="m-0">Product Details</h3>
			          	</div>
			          	<div class="col-sm-6">
				        	<ol class="breadcrumb float-sm-right">
				        		<li class="breadcrumb-item"><a href="/admin/dashboard">Home</a></li>
              					<li class="breadcrumb-item active">Product Details</li>
				        	</ol>
			       		</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="card card-solid card-outline card-info">
        			<div class="card-body">
        				<myTag:choose>
              				<myTag:when test="${electronic ne null}">
              					<div class="row">
            						<div class="col-12 col-sm-6">
             							<div class="col-12">
               								<img id="mainImage" src="../productImages/${electronic.seller.sellerEmail}/electronic/${electronic.electronicId}/prod1.jpg" class="product-image" alt="Product Image" style="border-style:dashed;border-size:1px;border-color:#696969;border-radius:5px;">
             							</div>
             							<div class="col-12 product-image-thumbs">
							                <div id="imagediv1" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${electronic.seller.sellerEmail}/electronic/${electronic.electronicId}/prod1.jpg" id="image1" alt="Product Image"></div>
							                <div id="imagediv2" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${electronic.seller.sellerEmail}/electronic/${electronic.electronicId}/prod2.jpg" id="image2" alt="Product Image"></div>
							                <div id="imagediv3" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${electronic.seller.sellerEmail}/electronic/${electronic.electronicId}/prod3.jpg" id="image3" alt="Product Image"></div>
							                <div id="imagediv4" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${electronic.seller.sellerEmail}/electronic/${electronic.electronicId}/prod4.jpg" id="image4" alt="Product Image"></div>
							                <div id="imagediv5" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${electronic.seller.sellerEmail}/electronic/${electronic.electronicId}/prod5.jpg" id="image5" alt="Product Image"></div>
		             					</div>
		            				</div>
            						<div class="col-12 col-sm-6">
              							<h3 class="my-3 text-info"><b>${electronic.electronicName}</b>
	              							<myTag:if test="${electronic.electronicStatus == APPROVED}">
	              								<span class="badge bg-success">${electronic.electronicStatus}</span>
	              							</myTag:if>
	              							<myTag:if test="${electronic.electronicStatus == STOPPED}">
	              								<span class="badge bg-danger">${electronic.electronicStatus}</span>
	              							</myTag:if>
	              							<myTag:if test="${electronic.electronicStatus == REQUESTED}">
	              								<span class="badge bg-warning">${electronic.electronicStatus}</span>
	              							</myTag:if>
              							</h3>
              							<p><b>${electronic.electronicType} Brand : </b>${electronic.electronicBrand}</p>
              							<p><b>${electronic.electronicType} Description : </b>${electronic.electronicDescription}</p>
              							<p><b>${electronic.electronicType} Specification : </b>${electronic.electronicSpecification}</p>
              							<p><b>Available Stock : </b>${electronic.electronicStock}</p>
              							<hr>
		              					<div class="btn-group btn-group-toggle" data-toggle="buttons">
		                					<label class="btn btn-default text-center active">
			                  					<input type="radio" name="color_option" id="color_option1" autocomplete="off" checked="">
			                  					${electronic.electronicType} Color<br>
			                  					<i class="fas fa-circle fa-2x text-${electronic.electronicColor}"></i>
		                					</label>
							            	<label class="btn btn-default text-center">
							                	<input type="radio" name="color_option" id="color_option2" autocomplete="off">
							                	<span class="text-l">${electronic.electronicType} Size</span>
							                  	<br>${electronic.electronicSize}
							            	</label>
							            	<label class="btn btn-default text-center">
							                	<input type="radio" name="color_option" id="color_option3" autocomplete="off">
							                	<span class="text-l">${electronic.electronicType} Weight</span>
							                  	<br>${electronic.electronicWeight} gm
							            	</label>
		              					</div>
		              					<div class="bg-info py-2 px-3 mt-4" style="border-radius:5px;">
		                					<h2 class="mb-0">Rs.${electronic.electronicPrice} /-</h2>
		                					<h4 class="mt-0"><small>Delivery Charge : Rs.70 /-</small></h4>
		              					</div>
		              					<div class="mt-4">
		              						<myTag:if test="${electronic.electronicStatus == STOPPED}">
			                					<a href="/startelectronic/${electronic.electronicId}" class="btn btn-success btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-check-circle fa-lg mr-2"></i> Start
			                					</a>
			                				</myTag:if>
			                				<myTag:if test="${electronic.electronicStatus == APPROVED}">
			                					<a href="/stopelectronic/${electronic.electronicId}" class="btn btn-danger btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-times-circle fa-lg mr-2"></i> Stop
			                					</a>
			                				</myTag:if>
			                				<myTag:if test="${electronic.electronicStatus == REQUESTED}">
			                					<a href="/approveelectronic/${electronic.electronicId}" class="btn btn-success btn-lg btn-flat" style="border-radius:5px;">
		                  							<i class="fas fa-check-circle fa-lg mr-2"></i> Approve
			                					</a>
			                					<a href="/rejectelectronic/${electronic.electronicId}" class="btn btn-danger btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-times-circle fa-lg mr-2"></i> Reject
			                					</a>
			                				</myTag:if>
		              					</div>
            						</div>
          						</div>
              				</myTag:when>
              				<myTag:when test="${fashionBean ne null}">
              					<div class="row">
            						<div class="col-12 col-sm-6">
             							<div class="col-12">
               								<img id="mainImage" src="../productImages/${fashionBean.fashion.seller.sellerEmail}/fashion/${fashionBean.fashion.fashionId}/prod1.jpg" class="product-image" alt="Product Image" style="border-style:dashed;border-size:1px;border-color:#696969;border-radius:5px;">
             							</div>
             							<div class="col-12 product-image-thumbs">
							                <div id="imagediv1" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${fashionBean.fashion.seller.sellerEmail}/fashion/${fashionBean.fashion.fashionId}/prod1.jpg" id="image1" alt="Product Image"></div>
							                <div id="imagediv2" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${fashionBean.fashion.seller.sellerEmail}/fashion/${fashionBean.fashion.fashionId}/prod2.jpg" id="image2" alt="Product Image"></div>
							                <div id="imagediv3" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${fashionBean.fashion.seller.sellerEmail}/fashion/${fashionBean.fashion.fashionId}/prod3.jpg" id="image3" alt="Product Image"></div>
							                <div id="imagediv4" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${fashionBean.fashion.seller.sellerEmail}/fashion/${fashionBean.fashion.fashionId}/prod4.jpg" id="image4" alt="Product Image"></div>
							                <div id="imagediv5" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${fashionBean.fashion.seller.sellerEmail}/fashion/${fashionBean.fashion.fashionId}/prod5.jpg" id="image5" alt="Product Image"></div>
		             					</div>
		            				</div>
            						<div class="col-12 col-sm-6">
              							<h3 class="my-3 text-info"><b>${fashionBean.fashion.fashionName}</b>
              								<myTag:if test="${fashionBean.fashion.fashionStatus == APPROVED}">
	              								<span class="badge bg-success">${fashionBean.fashion.fashionStatus}</span>
	              							</myTag:if>
	              							<myTag:if test="${fashionBean.fashion.fashionStatus == STOPPED}">
	              								<span class="badge bg-danger">${fashionBean.fashion.fashionStatus}</span>
	              							</myTag:if>
	              							<myTag:if test="${fashionBean.fashion.fashionStatus == REQUESTED}">
	              								<span class="badge bg-warning">${fashionBean.fashion.fashionStatus}</span>
	              							</myTag:if>
              							</h3>
              							<p><b>Fashion Type : </b>${fashionBean.fashion.fashionType}</p>
              							<p><b>Fashion Product for : </b>${fashionBean.fashion.fashionBuyerType}</p>
              							<p><b>Description : </b>${fashionBean.fashion.fashionDescription}</p>
              							<hr>
              							<h4>Available Sizes</h4>
              							<myTag:forEach var="i" items="${fashionBean.subFashion}">
			              					<div class="btn-group btn-group-toggle" data-toggle="buttons">
								            	<label class="btn btn-default text-center">
								                	<input type="radio" name="color_option" id="color_option2" autocomplete="off">
								                	<span class="text-l">Size</span>
								                  	<br>${i.fashionSize}
								            	</label>
								            	<label class="btn btn-default text-center">
								                	<input type="radio" name="color_option" id="color_option2" autocomplete="off">
								                	<span class="text-l">Stock</span>
								                  	<br>${i.fashionStock}
								            	</label>
								            	<label class="btn btn-info text-center">
								                	<input type="radio" name="color_option" id="color_option2" autocomplete="off">
								                	<span class="text-l">Rs.</span>
								                  	<br>${i.fashionPrice}
								            	</label>
			              					</div>
              							</myTag:forEach>
		              					<div class="bg-info py-2 px-3 mt-4" style="border-radius:5px;">
		                					<h4 class="mt-0"><small>Delivery Charge : Rs.70 /-</small></h4>
		              					</div>
		              					<div class="mt-4">
	              							<myTag:if test="${fashionBean.fashion.fashionStatus == APPROVED}">
	              								<a href="/stopfashion/${fashionBean.fashion.fashionId}" class="btn btn-danger btn-lg btn-flat" style="border-radius:5px;">
		                  							<i class="fas fa-times-circle fa-lg mr-2"></i> Stop
		                						</a>
	              							</myTag:if>
		              						<myTag:if test="${fashionBean.fashion.fashionStatus == STOPPED}">
	              								<a href="/startfashion/${fashionBean.fashion.fashionId}" class="btn btn-success btn-lg btn-flat" style="border-radius:5px;">
		                  							<i class="fas fa-check-circle fa-lg mr-2"></i> Start
		                						</a>
	              							</myTag:if>
	              							<myTag:if test="${fashionBean.fashion.fashionStatus == REQUESTED}">
	              								<a href="/approvefashion/${fashionBean.fashion.fashionId}" class="btn btn-success btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-check-circle fa-lg mr-2"></i> Approve
			                					</a>
			                					<a href="/rejectfashion/${fashionBean.fashion.fashionId}" class="btn btn-danger btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-times-circle fa-lg mr-2"></i> Reject
			                					</a>
	              							</myTag:if>
		              					</div>
            						</div>
          						</div>
              				</myTag:when>
              				<myTag:when test="${book ne null}">
              					<div class="row">
            						<div class="col-12 col-sm-6">
             							<div class="col-12">
               								<img id="mainImage" src="../productImages/${book.seller.sellerEmail}/book/${book.bookId}/prod1.jpg" class="product-image" alt="Product Image" style="border-style:dashed;border-size:1px;border-color:#696969;border-radius:5px;">
             							</div>
             							<div class="col-12 product-image-thumbs">
							                <div id="imagediv1" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${book.seller.sellerEmail}/book/${book.bookId}/prod1.jpg" id="image1" alt="Product Image"></div>
							                <div id="imagediv2" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${book.seller.sellerEmail}/book/${book.bookId}/prod2.jpg" id="image2" alt="Product Image"></div>
							                <div id="imagediv3" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${book.seller.sellerEmail}/book/${book.bookId}/prod3.jpg" id="image3" alt="Product Image"></div>
							                <div id="imagediv4" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${book.seller.sellerEmail}/book/${book.bookId}/prod4.jpg" id="image4" alt="Product Image"></div>
							                <div id="imagediv5" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${book.seller.sellerEmail}/book/${book.bookId}/prod5.jpg" id="image5" alt="Product Image"></div>
		             					</div>
		            				</div>
            						<div class="col-12 col-sm-6">
              							<h3 class="my-3 text-info"><b>${book.bookName}</b>
	              							<myTag:if test="${book.bookStatus == APPROVED}">
	              								<span class="badge bg-success">${book.bookStatus}</span>
	              							</myTag:if>
	              							<myTag:if test="${book.bookStatus == STOPPED}">
	              								<span class="badge bg-danger">${book.bookStatus}</span>
	              							</myTag:if>
	              							<myTag:if test="${book.bookStatus == REQUESTED}">
	              								<span class="badge bg-warning">${book.bookStatus}</span>
	              							</myTag:if>
              							</h3>
              							<p><b>Author : </b>${book.authorName}</p>
              							<p><b>Book Description : </b>${book.bookDescription}</p>
              							<p><b>Available Stock : </b>${book.bookStock}</p>
              							<hr>
		              					<div class="btn-group btn-group-toggle" data-toggle="buttons">
							            	<label class="btn btn-default text-center">
							                	<input type="radio" name="color_option" id="color_option2" autocomplete="off">
							                	<span class="text-l">Book Type</span>
							                  	<br>${book.bookType}
							            	</label>
		              					</div>
		              					<div class="bg-info py-2 px-3 mt-4" style="border-radius:5px;">
		                					<h2 class="mb-0">Rs.${book.bookPrice} /-</h2>
		                					<h4 class="mt-0"><small>Delivery Charge : Rs.70 /-</small></h4>
		              					</div>
		              					<div class="mt-4">
		              						<myTag:if test="${book.bookStatus == STOPPED}">
			                					<a href="/startbook/${book.bookId}" class="btn btn-success btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-check-circle fa-lg mr-2"></i> Start
			                					</a>
			                				</myTag:if>
			                				<myTag:if test="${book.bookStatus == APPROVED}">
			                					<a href="/stopbook/${book.bookId}" class="btn btn-danger btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-times-circle fa-lg mr-2"></i> Stop
			                					</a>
			                				</myTag:if>
			                				<myTag:if test="${book.bookStatus == REQUESTED}">
			                					<a href="/approvebook/${book.bookId}" class="btn btn-success btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-check-circle fa-lg mr-2"></i> Approve
			                					</a>
			                					<a href="/rejectbook/${book.bookId}" class="btn btn-danger btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-times-circle fa-lg mr-2"></i> Reject
			                					</a>
			                				</myTag:if>
		              					</div>
            						</div>
          						</div>
              				</myTag:when>
              				<myTag:when test="${general ne null}">
              					<div class="row">
            						<div class="col-12 col-sm-6">
             							<div class="col-12">
               								<img id="mainImage" src="../productImages/${general.seller.sellerEmail}/general/${general.productId}/prod1.jpg" class="product-image" alt="Product Image" style="border-style:dashed;border-size:1px;border-color:#696969;border-radius:5px;">
             							</div>
             							<div class="col-12 product-image-thumbs">
							                <div id="imagediv1" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${general.seller.sellerEmail}/general/${general.productId}/prod1.jpg" id="image1" alt="Product Image"></div>
							                <div id="imagediv2" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${general.seller.sellerEmail}/general/${general.productId}/prod2.jpg" id="image2" alt="Product Image"></div>
							                <div id="imagediv3" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${general.seller.sellerEmail}/general/${general.productId}/prod3.jpg" id="image3" alt="Product Image"></div>
							                <div id="imagediv4" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${general.seller.sellerEmail}/general/${general.productId}/prod4.jpg" id="image4" alt="Product Image"></div>
							                <div id="imagediv5" style="border-style:dashed;border-size:2px;border-color:#696969;" class="product-image-thumb"><img src="../productImages/${general.seller.sellerEmail}/general/${general.productId}/prod5.jpg" id="image5" alt="Product Image"></div>
		             					</div>
		            				</div>
            						<div class="col-12 col-sm-6">
              							<h3 class="my-3 text-info"><b>${general.productName}</b>
	              							<myTag:if test="${general.productStatus == APPROVED}">
	              								<span class="badge bg-success">${general.productStatus}</span>
	              							</myTag:if>
	              							<myTag:if test="${general.productStatus == STOPPED}">
	              								<span class="badge bg-danger">${general.productStatus}</span>
	              							</myTag:if>
	              							<myTag:if test="${general.productStatus == REQUESTED}">
	              								<span class="badge bg-warning">${general.productStatus}</span>
	              							</myTag:if>
              							</h3>
              							<p><b>Product Type : </b>${general.productType}</p>
              							<p><b>Product Brand : </b>${general.productBrand}</p>
              							<p><b>Product Description : </b>${general.productDescription}</p>
              							<hr>
		              					<div class="btn-group btn-group-toggle" data-toggle="buttons">
							            	<label class="btn btn-default text-center">
							                	<input type="radio" name="color_option" id="color_option2" autocomplete="off">
							                	<span class="text-l">Available Stock</span>
							                  	<br>${general.productStock}
							            	</label>
		              					</div>
		              					<div class="bg-info py-2 px-3 mt-4" style="border-radius:5px;">
		                					<h2 class="mb-0">Rs.${general.productPrice} /-</h2>
		                					<h4 class="mt-0"><small>Delivery Charge : Rs.70 /-</small></h4>
		              					</div>
		              					<div class="mt-4">
		              						<myTag:if test="${general.productStatus == STOPPED}">
			                					<a href="/startgeneral/${general.productId}" class="btn btn-success btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-check-circle fa-lg mr-2"></i> Start
			                					</a>
			                				</myTag:if>
			                				<myTag:if test="${general.productStatus == APPROVED}">
			                					<a href="/stopgeneral/${general.productId}" class="btn btn-danger btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-times-circle fa-lg mr-2"></i> Stop
			                					</a>
			                				</myTag:if>
			                				<myTag:if test="${general.productStatus == REQUESTED}">
				                				<a href="/approvegeneral/${general.productId}" class="btn btn-success btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-check-circle fa-lg mr-2"></i> Approve
			                					</a>
			                					<a href="/rejectgeneral/${general.productId}" class="btn btn-danger btn-lg btn-flat" style="border-radius:5px;">
			                  						<i class="fas fa-times-circle fa-lg mr-2"></i> Reject
			                					</a>
			                				</myTag:if>
		              					</div>
            						</div>
          						</div>
              				</myTag:when>
              		</myTag:choose>
        			</div>
      			</div>			
			</section>
		</div>
		<jsp:include page="parts/footer.jsp"></jsp:include>
	</div>
	<%@ include file="parts/js_src.jsp"%>
	<!-- page script -->
	<myTag:choose>
		<myTag:when test="${electronic ne null}">
			<script>
			  $(function () {
				  $("#electronics").addClass("active");
				    $("#productmenu").addClass("menu-open");
				    $("#products").addClass("active");
			  });
			</script>
		</myTag:when>
		<myTag:when test="${fashionBean ne null}">
			<script>
			  $(function () {
				  $("#fashion").addClass("active");
				    $("#productmenu").addClass("menu-open");
				    $("#products").addClass("active");
			  });
			</script>
		</myTag:when>
		<myTag:when test="${general ne null}">
			<script>
			  $(function () {
				  $("#general").addClass("active");
				    $("#productmenu").addClass("menu-open");
				    $("#products").addClass("active");
			  });
			</script>
		</myTag:when>
		<myTag:when test="${book ne null}">
			<script>
			  $(function () {
				  $("#books").addClass("active");
				    $("#productmenu").addClass("menu-open");
				    $("#products").addClass("active");
			  });
			</script>
		</myTag:when>
	</myTag:choose>
	<script>
	  $("#imagediv1").click(function() {
		  $("#mainImage").attr("src",$("#image1").attr("src"));
	  });
	  $( "#imagediv2" ).click(function() {
		  $("#mainImage").attr("src",$("#image2").attr("src"));
	  });
	  $( "#imagediv3" ).click(function() {
		  $("#mainImage").attr("src",$("#image3").attr("src"));
	  });
	  $( "#imagediv4" ).click(function() {
		  $("#mainImage").attr("src",$("#image4").attr("src"));
	  });
	  $( "#imagediv5" ).click(function() {
		  $("#mainImage").attr("src",$("#image5").attr("src"));
	  });
	</script>
	<script type="text/javascript">
		    $(document).ready(function() {
		    	var a = "${productStopSuccess}";
			    var b = "${productStartSuccess}";
			    var c = "${productStopError}";
			    var d = "${productStartError}";
			    toastr.options = {
				    		  "closeButton": true,
				    		  "debug": true,
				    		  "newestOnTop": false,
				    		  "progressBar": true,
				    		  "positionClass": "toast-top-center",// toast-bottom-center , toast-top-right , toast-bottom-right , toast-bottom-left , toast-top-left , toast-top-full-width , toast-bottom-full-width , 
				    		  "preventDuplicates": true,
				    		  "onclick": null,
				    		  "showDuration": "300",
				    		  "hideDuration": "1000",
				    		  "timeOut": "5000",
				    		  "extendedTimeOut": "5000",
				    		  "showEasing": "linear",
				    		  "hideEasing": "linear",
				    		  "showMethod": "fadeIn",
				    		  "hideMethod": "fadeOut"
		   			}
			    if(a != '')
			    {
			    	toastr["success"](a)
			    }
			    if(b != '')
			    {
			    	toastr["success"](b)
			    }
			    if(c != '')
			    {
			    	toastr["error"](c)
			    }
			    if(d != '')
			    {
			    	toastr["error"](d)
			    }
		    });
	</script>
</body>
</html>