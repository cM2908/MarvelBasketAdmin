<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag"%>    
<!DOCTYPE html>
<html>
<head>
	<title>Seller Request</title>
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
			            	<h3 class="m-0">Seller Requests</h3>
			          	</div>
			          	<div class="col-sm-6"> 
				        	<ol class="breadcrumb float-sm-right">
				        		<li class="breadcrumb-item"><a href="/dashboard">Home</a></li>
              					<li class="breadcrumb-item active">Seller Request</li>
				        	</ol>
			       		</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="container-fluid">
		        	<div class="row">
		          		<div class="col-md-3">
		            		<div class="card card-info">
		            			<div class="card-header">
		                			<h3 class="card-title"><i class="fas fa-user"></i>  Seller</h3>
		              			</div>
		              			<div class="card-body box-info">
		                			<div class="text-center">
		                  				<img class="profile-user-img img-fluid img-circle" src="../profile/avatar5.png" alt="User profile picture" style="border-color:#5bc0de;">
		                			</div>
		                			<h3 class="profile-username text-center"></h3>
		                			<ul class="list-group list-group-unbordered mb-3">
		                  				<li class="list-group-item">
		                    				<b>Categories</b> <a class="float-right">${numOfCategory}</a>
		                  				</li>
		                			</ul>
									<a href="/approveselleraccount/${seller.getSellerId()}" class="btn btn-success btn-block"><b>Approve</b></a>
									<a href="/rejectselleraccount/${seller.getSellerId()}" class="btn btn-danger btn-block"><b>Reject</b></a>
		              			</div>
		            		</div>
		          		</div>
		          		<div class="col-md-6">
		            		<div class="card card-info">
		            			<div class="card-header">
		                			<h3 class="card-title">
		                				<i class="fas fa-info fa-fw" id="c_icon"></i>
		                				<span id="c_title">About</span>
		                			</h3>
		              			</div>
								<div class="card-body">
		                			<div class="tab-content">
		                				<div class="active tab-pane" id="settings">
		                					<form class="form-horizontal">
					                  			<div class="form-group row">
					                    			<i class="fas fa-user mr-1" style="margin-top:10px;"></i>
					                    			<label class="col-sm-3 control-label" style="margin-top:6px;">Name</label>
								                    <div class="col-sm-8">
								                    	<input type="text" class="form-control"value="${seller.getSellerName()}" disabled style="border-color:#5bc0de;">
								                    </div>
					                  			</div>
				                               	<div class="form-group row">
													<i class="fas fa-envelope mr-1" style="margin-top:10px;"></i>
				                    				<label class="col-sm-3 control-label" style="margin-top:6px;">Email</label>
				                    				<div class="col-sm-8">
				                      					<input type="email" class="form-control"value="${seller.getSellerEmail()}" disabled style="border-color:#5bc0de;">
				                    				</div>
				                    			</div>
							                  	<div class="form-group row">
								                	<i class="fas fa-phone mr-1" style="margin-top:10px;"></i>
								                  	<label class="col-sm-3 control-label" style="margin-top:6px;">Phone</label>
								                  	<div class="col-sm-8">
								                  		<input type="text" class="form-control"value="${seller.getSellerContact()}" disabled style="border-color:#5bc0de;">
								                  	</div>
							                  	</div>
												<div class="form-group row">
													<i class="fas fa-shopping-basket mr-1" style="margin-top:10px;"></i>
							                  		<label class="col-sm-3 control-label" style="margin-top:6px;">Categories</label>
							                  		<div class="col-sm-8">
							                  			<myTag:forEach var="i" items="${categories}">
							                  				<span class="badge badge-info">${i.categoryName}</span>
							                  			</myTag:forEach>
							                  		</div>
												</div>
		              						</form>
		                    			</div>
		                			</div>
		              			</div>
		            		</div>
		          		</div>
		          		<div class="col-md-3">
							<div class="card card-info">
								<div class="card-header">              	
		                			<h3 class="card-title"><i class="fas fa-store"></i> Shop</h3>
		              			</div>
		              			<div class="card-body">
		                			<strong><i class="fas fa-book mr-1"></i> Name</strong>
		                			<p class="text-muted">${seller.getShopName()}</p>
		                			<hr>
		                			<strong><i class="fas fa-map-marker-alt mr-1"></i> Location</strong>
		                			<p class="text-muted">${seller.getShopAddress()}</p>
		                			<hr>
		                			<a href="/sellerrequests" class="btn btn-info btn-block"><b>Back</b></a>
		                		</div>
		            		</div>     	
		          		</div>
		        	</div>
		      	</div>
			</section>
		</div>
		<jsp:include page="parts/footer.jsp"></jsp:include>
	</div>
	<%@ include file="parts/js_src.jsp"%>
	<!-- page script -->
	<script>
	$(function () {
	    $("#sellerrequests").addClass("active");
	    $("#users").addClass("active");
	    $("#usermenu").addClass("menu-open");
	  });
	</script>
	 <script>
	  $("#toogle_btn").click(function() {
		  if($("#c_title").text() == "About"){
				// Change the Card Header title to "Timeline" & icon
			  	$("#c_title").text("Timeline");
			  	$("#c_icon").removeClass("fa-info");
				$("#c_icon").addClass("fa-clock");

				$("#btn_icon").removeClass("fa-clock");
				$("#btn_icon").addClass("fa-info");
				
			 	// Change the content of the Card
				$("#settings").removeClass("active");
			  	$("#timeline").addClass("active");

				//Change the button to "About"
				$("#toogle_btn").text("About");
		  }
		  else if($("#c_title").text() == "Timeline"){
				// Change the Card Header title to "About" & icon
			  	$("#c_title").text("About");
			  	$("#c_icon").removeClass("fa-clock");
			  	$("#c_icon").addClass("fa-info");

			  	$("#btn_icon").removeClass("fa-info");
				$("#btn_icon").addClass("fa-clock");
			  	
			  	// Change the content of the Card
			  	$("#settings").addClass("active");
			  	$("#timeline").removeClass("active");

			  	//Change the button to "Timeline"
			  	$("#toogle_btn").text("Timeline");  
		  }
		});
	</script>
	<script type="text/javascript">
		    $(document).ready(function() {
			    var c = "${accountRejectError}";
			    var d = "${accountApproveError}";
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