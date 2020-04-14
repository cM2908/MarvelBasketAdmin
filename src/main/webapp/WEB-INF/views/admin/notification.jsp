<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Dashboard</title>
	<%@ include file="parts/meta.jsp"%>
	<%@ include file="parts/css_links.jsp"%>
	<style type="text/css">
		#toast-container.toast-top-center > div,
		#toast-container.toast-bottom-center > div {
			opacity:1;
			width: 500px;
			margin-left: auto;
			margin-right: auto;
			border-radius:6px;
		}
	</style>
	<myTag:set var="notificationCounter" value="${notificationCount}"/>
	<% int notificationCount=0;%>
	<myTag:choose>
		<myTag:when test="${notificationCounter == 1}">                    	
			<myTag:set var="height" value="100px"/>
		</myTag:when>
		<myTag:when test="${notificationCounter == 2}">                    	
			<myTag:set var="height" value="200px"/>
		</myTag:when>
		<myTag:when test="${notificationCounter == 3}">                    	
			<myTag:set var="height" value="300px"/>
		</myTag:when>
		<myTag:when test="${notificationCounter > 3}">                    	
			<myTag:set var="height" value="350px"/>
		</myTag:when>
	</myTag:choose>
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
			            	<h1 class="m-0 text-dark">Notifications</h1>
			          	</div>
			          	<div class="col-sm-6">
				        	<ol class="breadcrumb float-sm-right">
				        		<li class="breadcrumb-item"><a href="/admin/dashboard">Home</a></li>
              					<li class="breadcrumb-item active">Notifications</li>
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
			                		<h3 class="card-title">Notifications</h3>
		                			<div class="card-tools">
		                				<myTag:choose>
											<myTag:when test="${notificationCounter > 0}">
										  		<a href="#" class="btn btn-danger" style="padding-top:1px;padding-bottom:1px;padding-left:15px;padding-right:15px">Clear</a>
										  	</myTag:when>
										  	<myTag:otherwise>
												<h5 style="margin-left:350px;">No notifications to show</h5>						  	
										  	</myTag:otherwise>
										</myTag:choose>
		                			</div>
			              		</div>
			              		<myTag:if test="${notificationCounter > 0}">
				              		<div class="card-body table-responsive p-0" style="height:${height};">
				                		<table class="table table-head-fixed">
				                			<thead>
				                				<tr>
				                					<th>Sr No</th>
				                					<th>From</th>
				                					<th>Description</th>
				                					<th>Date</th>
				                					<th>Time</th>
				                				</tr>
				                			</thead>
				                			<tbody>
												<myTag:forEach var="i" items="${notifications}">
								                	<tr>
								                    	<td><%= ++notificationCount %>.</td>
								                    	<td>${i.from.getSellerName()}</td>
								                    	<td>${i.description}</td>
								                    	<td>${i.time.getDate()}-${i.time.getMonth()+1}-${i.time.getYear()-100+2000}</td>
								                    	<myTag:choose>
								                    		<myTag:when test="${i.time.getHours() == 0}">                    	
									                    		<td>${i.time.getHours()+12}:${i.time.getMinutes()} AM</td>
									                    	</myTag:when>
									                    	<myTag:when test="${i.time.getHours() == 12}">                    	
									                    		<td>${i.time.getHours()}:${i.time.getMinutes()} PM</td>
									                    	</myTag:when>
															<myTag:when test="${i.time.getHours() > 0 && i.time.getHours() < 12}">                    	
								                    			<td>${i.time.getHours()+12}:${i.time.getMinutes()} AM</td>
								                    		</myTag:when>
									                    	<myTag:when test="${i.time.getHours() > 12}">                    	
									                    		<td>${i.time.getHours()-12}:${i.time.getMinutes()} PM</td>
									                    	</myTag:when>
								                    	</myTag:choose>
								                	</tr>
							             		</myTag:forEach>
				                  			</tbody>
				                		</table>
									</div>
								</myTag:if>
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
	    $("#dashboard").addClass("active");
	  });
	</script>
	<script type="text/javascript">
		    $(document).ready(function() {
			    var a = "${welcomeUser}";
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
		    });
	</script>
</body>
</html>