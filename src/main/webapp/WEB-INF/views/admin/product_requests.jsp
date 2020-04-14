<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag"%>    
<!DOCTYPE html>
<html>
<head>
	<title>Product Requests</title>
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
			            <h3 class="m-0">Product Requests</h3>
			          	</div>
			          	<div class="col-sm-6">
				        	<ol class="breadcrumb float-sm-right">
				        		<li class="breadcrumb-item"><a href="/admin/dashboard">Home</a></li>
              					<li class="breadcrumb-item active">Product Requests</li>
				        	</ol>
			       		</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="card card-info card-outline table-responsive">
              		<div class="card-body">
              			<myTag:choose>
              				<myTag:when test="${electronicListSize > 0 || fashionBeanListSize > 0 || bookListSize > 0 || generalListSize > 0}">
		                		<table class="table table-bordered table-striped">
		                  			<thead>                  
		                    			<tr>
											<th>Product Name</th>
											<th>Product Category</th>
											<th>Product Type</th>
											<th>Status</th>
											<th>Seller Name</th>
											<th>Shop Name</th>
											<th>Details</th>
		                    			</tr>
		                  			</thead>
		                  			<tbody>
										<myTag:forEach var="i" items="${electronicList}">
											<tr>
												<td>${i.electronicName}</td>
												<td>Electronics</td>
												<td>${i.electronicType}</td>
												<td><span class="badge bg-warning">REQUESTED</span></td>
												<td>${i.seller.sellerName}</td>
												<td>${i.seller.shopName}</td>
												<td><center><a href="/electronicrequestdetails/${i.electronicId}" class="btn btn-info" style="padding:3px 15px 3px 15px;">More</a></center></td>
						                	</tr>
						                </myTag:forEach>
						                <myTag:forEach var="i" items="${fashionBeanList}">
											<tr>
												<td>${i.fashion.fashionName}</td>
												<td>Fashion</td>
												<td>${i.fashion.fashionType}</td>
												<td><span class="badge bg-warning">REQUESTED</span></td>
												<td>${i.fashion.seller.sellerName}</td>
												<td>${i.fashion.seller.shopName}</td>
												<td><center><a href="/fashionrequestdetails/${i.fashion.fashionId}" class="btn btn-info" style="padding:3px 15px 3px 15px;">More</a></center></td>
						                	</tr>
						                </myTag:forEach>
						                <myTag:forEach var="i" items="${bookList}">
											<tr>
												<td>${i.bookName}</td>
												<td>Book</td>
												<td>${i.bookType}</td>
												<td><span class="badge bg-warning">REQUESTED</span></td>
												<td>${i.seller.sellerName}</td>
												<td>${i.seller.shopName}</td>
												<td><center><a href="/bookrequestdetails/${i.bookId}" class="btn btn-info" style="padding:3px 15px 3px 15px;">More</a></center></td>
						                	</tr>
						                </myTag:forEach>
						                <myTag:forEach var="i" items="${generalList}">
											<tr>
												<td>${i.productName}</td>
												<td>General</td>
												<td>${i.productType}</td>
												<td><span class="badge bg-warning">REQUESTED</span></td>
												<td>${i.seller.sellerName}</td>
												<td>${i.seller.shopName}</td>
												<td><center><a href="/generalrequestdetails/${i.productId}" class="btn btn-info" style="padding:3px 15px 3px 15px;">More</a></center></td>
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
		                				<a href="/electronics" class="btn btn-info float-right">See All Products</a>
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
	    $("#productrequests").addClass("active");
	    $("#products").addClass("active");
	    $("#productmenu").addClass("menu-open");
	  });
	</script>
</body>
</html>