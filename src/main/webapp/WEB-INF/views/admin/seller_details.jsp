<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag"%>    
<!DOCTYPE html>
<html>
<head>
	<title>Seller Profile</title>
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
			            <h3 class="m-0">Seller Profile</h3>
			          	</div>
			          	<div class="col-sm-6"> 
				        	<ol class="breadcrumb float-sm-right">
				        		<li class="breadcrumb-item"><a href="/dashboard">Home</a></li>
              					<li class="breadcrumb-item active">Seller Profile</li>
				        	</ol>
			       		</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="container-fluid">
					<div class="row">
          				<div class="col-12">
            				<div class="card card-info">
              					<div class="card-header">
                					<h3 class="card-title">
                  						<i class="fas fa-chart-pie"></i> 
                  						Product Stats
                					</h3>
                					<div class="card-tools">
                  						<button type="button" class="btn btn-tool" data-card-widget="collapse"><i class="fas fa-minus"></i></button>
                					</div>
              					</div>
              					<div class="card-body">
                					<div class="row">
                  						<div class="col-6 col-md-3">
                    						<div style="display:inline;width:120px;height:120px;">
                    							<canvas style="margin-left:-25%" width="120" height="120"></canvas>
                    							<input type="text" id="eknob" class="knob" value="${electronicCount}" data-linecap=round data-width="120" data-height="120" data-fgcolor="#f56954" style="width: 64px; height: 40px; position: absolute; vertical-align: middle; margin-top: 40px; margin-left: -92px; border: 0px; background: none; font: bold 24px Arial; text-align: center; color: rgb(245, 105, 84); padding: 0px; -webkit-appearance: none;" readonly>
                    						</div>
                    						<div class="knob-label text-info text-center"><b>Electronics</b></div>
                  						</div>
                  						<div class="col-6 col-md-3 text-center">
                    						<div style="display:inline;width:120px;height:120px;">
                    							<canvas style="margin-left:-50%" width="120" height="120"></canvas>
                    							<input type="text" id="bknob" class="knob" value="${bookCount}" data-linecap=round data-width="120" data-height="120" data-fgcolor="#f56954" style="width: 64px; height: 40px; position: absolute; vertical-align: middle; margin-top: 40px; margin-left: -92px; border: 0px; background: none; font: bold 24px Arial; text-align: center; color: rgb(245, 105, 84); padding: 0px; -webkit-appearance: none;" readonly>
                    						</div>
                    						<div class="knob-label text-info"><b>Books</b></div>
                  						</div>
                  						<div class="col-6 col-md-3 text-center">
                    						<div style="display:inline;width:120px;height:120px;">
                    							<canvas style="margin-left:-50%" width="120" height="120"></canvas>
                    							<input type="text" id="fknob" class="knob" value="${fashionCount}" data-linecap=round data-width="120" data-height="120" data-fgcolor="#f56954" style="width: 64px; height: 40px; position: absolute; vertical-align: middle; margin-top: 40px; margin-left: -92px; border: 0px; background: none; font: bold 24px Arial; text-align: center; color: rgb(245, 105, 84); padding: 0px; -webkit-appearance: none;" readonly>
                    						</div>
                    						<div class="knob-label text-info"><b>Fashion</b></div>
                  						</div>
                  						<div class="col-6 col-md-3 text-center">
                    						<div style="display:inline;width:120px;height:120px;">
                    							<canvas style="margin-left:-50%" width="120" height="120"></canvas>
                    							<input type="text" id="gknob" class="knob" value="${generalCount}" data-linecap=round data-width="120" data-height="120" data-fgcolor="#f56954" style="width: 64px; height: 40px; position: absolute; vertical-align: middle; margin-top: 40px; margin-left: -92px; border: 0px; background: none; font: bold 24px Arial; text-align: center; color: rgb(245, 105, 84); padding: 0px; -webkit-appearance: none;" readonly>
                    						</div>
                    						<div class="knob-label text-info"><b>General</b></div>
                  						</div>
					                </div>
              					</div>
            				</div>
          				</div>
        			</div>
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
		                  				<li class="list-group-item">
                    						<b>Products</b> <a class="float-right">${productCount}</a>
                  						</li>
		                			</ul>
									<myTag:if test="${seller.getSellerStatus() == APPROVED}">
										<a href="/stopselleraccount/${seller.getSellerId()}" class="btn btn-danger btn-block"><b>Stop Account</b></a>
									</myTag:if>
									<myTag:if test="${seller.getSellerStatus() == STOPPED}">
										<a href="/startselleraccount/${seller.getSellerId()}" class="btn btn-success btn-block"><b>Start Account</b></a>
									</myTag:if>
									<a href="/sellers" class="btn btn-default btn-block"><b>Back</b></a>
		              			</div>
		            		</div>
		          		</div>
			          	<div class="col-md-6">
			            	<div id="header_color" class="card card-info">
			            		<div class="card-header">
			                		<h3 class="card-title">
			                			<i class="fas fa-info fa-fw" id="title_icon"></i>
			                			<span id="title_text">About</span>
			                		</h3>
			              		</div>
			              		<div class="card-body">
			                		<div class="tab-content">
			                			<div class="active tab-pane" id="about">
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
							                  		<label class="col-sm-3 control-label" style="margin-top:6px;">Category</label>
							                  		<div class="col-sm-8">
							                  			<myTag:forEach var="i" items="${categories}">
							                  				<span class="badge badge-info">${i.categoryName}</span>
							                  			</myTag:forEach>
							                  		</div>
												</div>
												<myTag:choose>
													<myTag:when test="${productCount gt 0}">
														<a href="/sellerproducts/${seller.sellerId}" class="btn btn-success btn-block"><b>See Products</b></a>
													</myTag:when>
													<myTag:when test="${productCount eq 0}">
														<a href="#" class="btn btn-default btn-block"><b>No products added</b></a>
													</myTag:when>
												</myTag:choose>
		              						</form>
			                    		</div>
			                  			<div class="tab-pane" id="timeline">
			                    			<div class="timeline timeline-inverse">
			                      				<div class="time-label">
			                        				<span class="bg-danger">
			                          					10 Feb. 2014
			                        				</span>
			                      				</div>
			                      				<div>
			                        				<i class="fas fa-envelope bg-primary"></i>
			                        				<div class="timeline-item">
			                          					<span class="time"><i class="far fa-clock"></i> 12:05</span>
			                          					<h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>
			                          					<div class="timeline-body">
							                            	Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
							                            	weebly ning heekya handango imeem plugg dopplr jibjab, movity
							                            	jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
							                            	quora plaxo ideeli hulu weebly balihoo...
							                          	</div>
			                          					<div class="timeline-footer">
			                            					<a href="#" class="btn btn-primary btn-sm">Read more</a>
			                            					<a href="#" class="btn btn-danger btn-sm">Delete</a>
			                          					</div>
			                        				</div>
			                      				</div>
			                      			</div>
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
		                			<a href="#" id="about_btn" class="btn btn-info btn-block"><b>About</b></a>
		                			<a href="#" id="timeline_btn" class="btn btn-warning btn-block"><b>Timeline</b></a>
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
	    $("#example1").DataTable();
	    $('#example2').DataTable({
	      "paging": true,
	      "lengthChange": false,
	      "searching": false,
	      "ordering": true,
	      "info": true,
	      "autoWidth": false,
	    });
	    $("#users").addClass("active");
	    $("#usermenu").addClass("menu-open");
	    $("#sellers").addClass("active");
	  });
	 </script>
	 <script>
	  $("#about_btn").click(function() {
		  $("#timeline").removeClass("active");
		  $("#about").addClass("active");  
		  $("#title_text").text("About");
		  $("#title_icon").removeClass("fa-clock");
		  $("#title_icon").addClass("fa-info");
		  /* $("#header_color").removeClass("card-warning");
		  $("#header_color").addClass("card-info"); */
	  });
	  $("#timeline_btn").click(function() {
		  $("#about").removeClass("active");
		  $("#timeline").addClass("active");
		  $("#title_text").text("Timeline");
		  $("#title_icon").removeClass("fa-info");
		  $("#title_icon").addClass("fa-clock");
		  /* $("#header_color").removeClass("card-info");
		  $("#header_color").addClass("card-warning"); */
		  
	  });
	</script>
	<script type="text/javascript">
		    $(document).ready(function() {
			    var a = "${accountStopSuccess}";
			    var b = "${accountStartSuccess}";
			    var c = "${accountStopError}";
			    var d = "${accountStartError}";
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
		    $("#eknob").attr("data-min",0);
		    $("#eknob").attr("data-max",10);
		    $("#bknob").attr("data-min",0);
		    $("#bknob").attr("data-max",10);
		    $("#fknob").attr("data-min",0);
		    $("#fknob").attr("data-max",10);
		    $("#gknob").attr("data-min",0);
		    $("#gknob").attr("data-max",10);
	</script>
</body>
</html>