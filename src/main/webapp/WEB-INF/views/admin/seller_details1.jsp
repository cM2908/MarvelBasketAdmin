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
          <div class="col-md-3">
            <div class="card card-info">
            <div class="card-header">
                <h3 class="card-title"><i class="fas fa-user"></i>  Seller</h3>
              </div>
              <div class="card-body box-info">
                <div class="text-center">
                  <img class="profile-user-img img-fluid img-circle"
                       src="../profile/avatar5.png"
                       alt="User profile picture" style="border-color:#5bc0de;">
                </div>
                <h3 class="profile-username text-center"></h3>

                <ul class="list-group list-group-unbordered mb-3">
                  <li class="list-group-item">
                    <b>Categories</b> <a class="float-right">${numOfCategory}</a>
                  </li>
                  <li class="list-group-item">
                    <b>Products</b> <a class="float-right">1,322</a>
                  </li>
                </ul>

				<myTag:if test="${seller.getSellerStatus() == APPROVED}">
					<a href="/stopselleraccount/${seller.getSellerId()}" class="btn btn-danger btn-block"><b>Stop Account</b></a>
				</myTag:if>
				<myTag:if test="${seller.getSellerStatus() == STOPPED}">
					<a href="/startselleraccount/${seller.getSellerId()}" class="btn btn-success btn-block"><b>Start Account</b></a>
				</myTag:if>
                
                
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
            <div class="card card-info">
              <div class="card-header">              	
                <h3 class="card-title"><i class="fas fa-store"></i> Shop</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <strong><i class="fas fa-book mr-1"></i> Name</strong>
                <p class="text-muted">
                  ${seller.getShopName()}
                </p>
                <hr>

                <strong><i class="fas fa-map-marker-alt mr-1"></i> Location</strong>
                <p class="text-muted">${seller.getShopAddress()}</p>
                <hr>
                
                <a href="#" class="btn btn-success">Products</a>
                <a href="#" id="toogle_btn" class="btn btn-info"><i class="fas fa-clock fa-fw" id="btn_icon"></i> Timeline</a>
                </div>
            </div>
          </div>
          <div class="col-md-9">
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
			                    <label class="col-sm-2 control-label" style="margin-top:6px;">Name</label>
			                    <div class="col-sm-8">
			                      <input type="text" class="form-control"value="${seller.getSellerName()}" disabled style="border-color:#5bc0de;">
			                    </div>
			                  </div>
                               <div class="form-group row">
									<i class="fas fa-envelope mr-1" style="margin-top:10px;"></i>
                    				<label class="col-sm-2 control-label" style="margin-top:6px;">Email</label>
                    				<div class="col-sm-8">
                      				<input type="email" class="form-control"value="${seller.getSellerEmail()}" disabled style="border-color:#5bc0de;">
                    				</div>
                    			</div>
			                  <div class="form-group row">
			                  <i class="fas fa-phone mr-1" style="margin-top:10px;"></i>
			                    <label class="col-sm-2 control-label" style="margin-top:6px;">Phone</label>
			                    <div class="col-sm-8">
			                      <input type="text" class="form-control"value="${seller.getSellerContact()}" disabled style="border-color:#5bc0de;">
			                    </div>
			                  </div>
			                  <div class="form-group row">
			                  <i class="fas fa-shopping-basket mr-1" style="margin-top:10px;"></i>
			                    <label class="col-sm-2 control-label" style="margin-top:6px;">Categories</label>
			                    <div class="col-sm-8">
			                      <myTag:forEach var="i" items="${categories}">
			                      	<span class="badge badge-info">${i.categoryName}</span>
			                      </myTag:forEach>
			                    </div>
			                  </div>
			                  <!-- <div class="card-footer" >
			                  <div class="form-group row">
			                  	<div class="col-sm-2">
			                  		<a href="#" class="btn btn-info">Products</a>
			                  	</div>
			                  	<div class="col-sm-8"></div>
			                  	<div class="col-sm-2">
			                    	<a href="#" id="timeline_btn" class="btn btn-danger">Timeline</a>
			                  	</div>
			                  </div>
			                  </div> -->
              				</form>
                    </div>
                  <div class="tab-pane" id="timeline">
                    <!-- The timeline -->
                    <div class="timeline timeline-inverse">
                      <!-- timeline time label -->
                      <div class="time-label">
                        <span class="bg-danger">
                          10 Feb. 2014
                        </span>
                      </div>
                      <!-- /.timeline-label -->
                      <!-- timeline item -->
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
                      <!-- END timeline item -->
                      <!-- timeline item -->
                      <div>
                        <i class="fas fa-user bg-info"></i>

                        <div class="timeline-item">
                          <span class="time"><i class="far fa-clock"></i> 5 mins ago</span>

                          <h3 class="timeline-header border-0"><a href="#">Sarah Young</a> accepted your friend request
                          </h3>
                        </div>
                      </div>
                      <!-- END timeline item -->
                      <!-- timeline item -->
                      <div>
                        <i class="fas fa-comments bg-warning"></i>
                        <div class="timeline-item">
                          <span class="time"><i class="far fa-clock"></i> 27 mins ago</span>

                          <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                          <div class="timeline-body">
                            Take me to your leader!
                            Switzerland is small and neutral!
                            We are more like Germany, ambitious and misunderstood!
                          </div>
                          <div class="timeline-footer">
                            <a href="#" class="btn btn-warning btn-flat btn-sm">View comment</a>
                          </div>
                        </div>
                      </div>
                      <!-- END timeline item -->
                      <!-- timeline time label -->
                      <div class="time-label">
                        <span class="bg-success">
                          3 Jan. 2014
                        </span>
                      </div>
                      <!-- /.timeline-label -->
                      <!-- timeline item -->
                      <div>
                        <i class="fas fa-camera bg-purple"></i>

                        <div class="timeline-item">
                          <span class="time"><i class="far fa-clock"></i> 2 days ago</span>

                          <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                          <div class="timeline-body">
                            <img src="http://placehold.it/150x100" alt="...">
                            <img src="http://placehold.it/150x100" alt="...">
                            <img src="http://placehold.it/150x100" alt="...">
                            <img src="http://placehold.it/150x100" alt="...">
                          </div>
                        </div>
                      </div>
                      <div>
                        <i class="far fa-clock bg-gray"></i>
                      </div>
                    </div>
                  </div>
                </div>
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
	</script>
</body>
</html>