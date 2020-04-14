<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title>Profile</title>
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
			            	<h1 class="m-0 text-dark">Admin Profile</h1>
			          	</div>
			          	<div class="col-sm-6">
				        	<ol class="breadcrumb float-sm-right">
				        		<li class="breadcrumb-item"><a href="/admin/dashboard">Home</a></li>
				        		<li class="breadcrumb-item active">Profile</li>
				        	</ol>
			       		</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="container-fluid">
					<div class="card card-primary card-outline">
						<div class="card-header p-2">
							<ul class="nav nav-pills">
								<li class="nav-item"><a class="nav-link active" href="#settings" data-toggle="tab">Settings</a></li>
								<li class="nav-item"><a class="nav-link" href="#gallery" data-toggle="tab">Gallery</a></li>
							</ul>
						</div>
              			<div class="card-body">
                			<div class="tab-content">
                  				<div class="active tab-pane" id="settings">
                    				<sf:form action="/admin/update" class="form-horizontal" method="post" modelAttribute="adminCommand" enctype="multipart/form-data">
                    					<div class="row">
              		  						<div class="col-md-6">
		                      					<div class="form-group">
		                        					<label for="inputName" class="col-sm-2 control-label">Name</label>
		                        					<div class="input-group mb-3">
		                        						<div class="input-group-append">
												        	<div class="input-group-text" style="border-radius:6px;background-color:#FFFFFF">
												          		<span class="fas fa-user"style="color:#0275d8;"></span>
												        	</div>
			      										</div>
	                          							<sf:input path="adminName" type="text" class="form-control" id="inputName" placeholder="Name" required="required" style="border-radius:6px;margin-left:2px"/>
			      									</div>
		                      					</div>	
		                      					<div class="form-group">
		                        					<label for="inputEmail" class="col-sm-2 control-label">Email</label>
	                        						<div class="input-group mb-3">
		                        						<div class="input-group-append">
												        	<div class="input-group-text" style="border-radius:6px;background-color:#FFFFFF">
												          		<span class="fas fa-envelope"style="color:#0275d8;"></span>
												        	</div>
			      										</div>
	                          							<sf:input path="adminEmail" type="email" class="form-control" id="inputEmail" placeholder="Email" required="required" disabled="true" style="border-radius:6px;margin-left:2px"/>
	                          							<sf:hidden path="adminEmail"/>
	                        						</div>
		                      					</div>
                      						</div>
	                    					<div class="col-md-6">
		                      					<div class="form-group">
		                        					<label for="inputContact" class="col-sm-2 control-label">Contact</label>
		                        					<div class="input-group mb-3">
		                        						<div class="input-group-append">
												        	<div class="input-group-text" style="border-radius:6px;background-color:#FFFFFF">
												          		<span class="fas fa-phone"style="color:#0275d8;"></span>
												        	</div>
			      										</div>
		                          						<sf:input path="adminContact" type="phone" class="form-control" id="inputContact" placeholder="Contact" required="required" style="border-radius:6px;margin-left:2px"/>
		                        					</div>
		                      					</div>
		                      					<div class="form-group">
		                        					<label for="inputPassword" class="col-sm-2 control-label">Password</label>
		                        					<div class="input-group mb-3">
		                        						<div class="input-group-append">
												        	<div class="input-group-text" style="border-radius:6px;background-color:#FFFFFF">
												          		<span class="fas fa-lock" style="color:#0275d8;"></span>
												        	</div>
			      										</div>
		                          						<sf:input path="adminPassword" type="password" class="form-control" id="inputPassword" placeholder="Password" required="required" style="border-radius:6px;margin-left:2px"/>
		                          						<div class="input-group-append">
												        	<div class="input-group-text" style="border-radius:6px;margin-left:3px;background-color:#FFFFFF">
												          		<span class="fas fa-eye-slash"style="color:#0275d8;" id="eye"></span>
												        	</div>
			      										</div>
		                        					</div>
		                        				</div>
		                      				</div>
                      					</div>
										<div class="form-group">
                        					<div class="col-sm-offset-2 col-sm-10" style="padding-top:3%">
                          						<center>
                          							<button type="submit" class="btn btn-primary" style="border-radius:6px">Update</button>
                          							<a href="/admin/dashboard" class="btn btn-default" style="border-radius:6px;margin-left:5px">Cancle</a>
                          						</center>
                        					</div>
                      					</div>              		  							
                    				</sf:form>
                  				</div>
                  				<div class="tab-pane" id="gallery">
									<div class="row">
										<div class="col-sm-2">
										    <a href="../images/avatar.png?text=1" data-toggle="lightbox" data-title="Admin Man" data-gallery="gallery">
										      <img src="../images/avatar.png?text=1" class="img-fluid mb-2" alt="white sample">
										    </a>
										</div>
										<div class="col-sm-2">
										  <a href="../images/avatar4.png?text=2" data-toggle="lightbox" data-title="Admin Man" data-gallery="gallery">
										    <img src="../images/avatar4.png?text=2" class="img-fluid mb-2" alt="black sample">
										  </a>
										</div>
										<div class="col-sm-2">
										  <a href="../images/avatar2.png?text=3" data-toggle="lightbox" data-title="Admin Lady" data-gallery="gallery">
										    <img src="../images/avatar2.png?text=3" class="img-fluid mb-2" alt="red sample">
										  </a>
										</div>
										<div class="col-sm-2">
										  <a href="../images/avatar5.png?text=5" data-toggle="lightbox" data-title="Admin Guy" data-gallery="gallery">
										    <img src="../images/avatar5.png?text=5" class="img-fluid mb-2" alt="black sample">
										  </a>
										</div>
										<div class="col-sm-2">
										  <a href="../images/avatar3.png?text=4" data-toggle="lightbox" data-title="Admin Woman" data-gallery="gallery">
										    <img src="../images/avatar3.png?text=4" class="img-fluid mb-2" alt="red sample">
										  </a>
										</div>
										<div class="col-sm-2">
										  <a href="../images/avatar6.jpg?text=4" data-toggle="lightbox" data-title="Admin Guy" data-gallery="gallery">
										    <img src="../images/avatar6.jpg?text=4" class="img-fluid mb-2" alt="red sample">
										  </a>
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
	<script>
	  $(function () {
	    $("#profile").addClass("active");
	  });
      $('#customFile').on('change',function(){
          //get the file name
          var fileName = $(this).val().split('\\').pop();
          //replace the "Choose a file" label
          $(this).next('.custom-file-label').html(fileName);
      })
	</script>
	<script type="text/javascript">
		    $(document).ready(function() {
			    var a = "${updateSuccess}";
			    var b = "${emailError}";
			    var c = "${dataError}";
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
			    	toastr["error"](b)
			    }
		    });
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
		    $("#eye").on('click', function(event) {
		        event.preventDefault();
		        if($('#inputPassword').attr("type") == "text"){
		            $('#inputPassword').attr('type', 'password');
		            $('#eye').addClass( "fa-eye-slash" );
		            $('#eye').removeClass( "fa-eye" );
		        }else if($('#inputPassword').attr("type") == "password"){
		            $('#inputPassword').attr('type', 'text');
		            $('#eye').removeClass( "fa-eye-slash" );
		            $('#eye').addClass( "fa-eye" );
		        }
		    });
		});
	</script>
	<script>
	  $(function () {
	    $(document).on('click', '[data-toggle="lightbox"]', function(event) {
		  alert("Called 1"+this);
	      event.preventDefault();
	      $(this).ekkoLightbox({
	        alwaysShowClose: true
	      });
	      $(".modal-footer").removeClass('hide');
	      $(".modal-footer").removeAttr('style');
	      $(".modal-footer").prepend("<a href='#' class='btn btn-primary'>Select</a>");
	    });
	  })
</script>
</body>
</html>
