<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag"%>    
<!DOCTYPE html>
<html>
<head>
	<title>Seller Requests</title>
	<%@ include file="parts/meta.jsp"%>
	<%@ include file="parts/css_links.jsp"%>
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
				        		<li class="breadcrumb-item"><a href="/admin/dashboard">Home</a></li>
              					<li class="breadcrumb-item active">Seller Requests</li>
				        	</ol>
			       		</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="card card-info card-outline table-responsive">
              		<div class="card-body">
              			<myTag:choose>
              				<myTag:when test="${sellerListSize > 0}">
		                		<table class="table table-bordered table-striped">
		                  			<thead>                  
		                    			<tr>
											<th>Id</th>                    			
											<th>Name</th>
											<th>Email</th>
											<th>Contact</th>
											<th>Status</th>
											<th>Shop Name</th>
											<th>Details</th>
		                    			</tr>
		                  			</thead>
		                  			<tbody>
										<myTag:forEach var="i" items="${sellerList}">
											<tr>
												<td>${i.sellerId}</td>
												<td>${i.sellerName}</td>
												<td>${i.sellerEmail}</td>
												<td>${i.sellerContact}</td>
												<td><span class="badge bg-warning">REQUESTED</span></td>
												<td>${i.shopName}</td>
												<td><center><a href="/sellerrequestdetails/${i.sellerId}" class="btn btn-info" style="padding:3px 15px 3px 15px;">More</a></center></td>
						                	</tr>
						                </myTag:forEach>
		                  			</tbody>
		                		</table>
	                		</myTag:when>
	                		<myTag:otherwise>
	                			<div class="row">
	                			<div class="col-lg-6 col-sm-6 col-md-6 col-xs-6">
	                				<h5>No Data Available</h5>
	                			</div>
	                			<div class="col-lg-6 col-sm-6 col-md-6 col-xs-6">
	                				<a href="/sellers" class="btn btn-info float-right">See Approved Sellers</a>
	                			</div>
	                			</div>
	                		</myTag:otherwise>
                		</myTag:choose>
              		</div>
              		<div class="card-footer">
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
</body>
</html>