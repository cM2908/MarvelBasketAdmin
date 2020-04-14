<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag"%>
<!DOCTYPE html>
<html>
<head>
	<title>Buyers</title>
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
			            <h3 class="m-0">Buyers</h3>
			          	</div>
			          	<div class="col-sm-6">
				        	<ol class="breadcrumb float-sm-right">
				        		<li class="breadcrumb-item"><a href="/admin/dashboard">Home</a></li>
              					<li class="breadcrumb-item active">Buyers</li>
				        	</ol>
			       		</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="card table-responsive card-outline card-info">
		            <div class="card-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Email</th>
									<th>Contact</th>
									<th>Address</th>
				                </tr>
							</thead>
			                <tbody>
								<myTag:forEach var="i" items="${userList}">			                
									<tr>
										<td>${i.firstName}</td>
										<td>${i.lastName}</td>
										<td>${i.email}</td>
										<td>${i.contact}</td>
										<td>${i.address}</td>
				                	</tr>
			                	</myTag:forEach>
							</tbody>
						</table>
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
	    $("#buyers").addClass("active");
	  });
	</script>
</body>
</html>