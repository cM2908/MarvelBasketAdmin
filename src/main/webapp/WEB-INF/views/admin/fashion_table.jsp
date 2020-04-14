<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="myTag"%>    
<!DOCTYPE html>
<html>
<head>
	<title>Fashion</title>
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
			            <h3 class="m-0">Fashion</h3>
			          	</div>
			          	<div class="col-sm-6">
				        	<ol class="breadcrumb float-sm-right">
				        		<li class="breadcrumb-item"><a href="/admin/dashboard">Home</a></li>
              					<li class="breadcrumb-item active">Fashion</li>
				        	</ol>
			       		</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="card card-info card-outline table-responsive">
		            <div class="card-body">
                		<table id="example1" class="table table-bordered table-striped">
                  			<thead>                  
                    			<tr>
									<th>Fashion Id</th>                    			
									<th>Fashion Name</th>
									<th>Fashion Type</th>
									<th>Fashion Status</th>
									<th>Seller Name</th>
									<th>Shop Name</th>
									<th>Details</th>
                    			</tr>
                  			</thead>
                  			<tbody>
								<myTag:forEach var="i" items="${fashionBeanList}">
									<tr>
										<td>${i.fashion.fashionId}</td>
										<td>${i.fashion.fashionName}</td>
										<td>${i.fashion.fashionType}</td>
										<myTag:if test="${i.fashion.fashionStatus == APPROVED}">
											<td><span class="badge bg-success">APPROVED</span></td>
										</myTag:if>
										<myTag:if test="${i.fashion.fashionStatus == STOPPED}">
											<td><span class="badge bg-danger">STOPPED</span></td>
										</myTag:if>
										<td>${i.fashion.seller.sellerName}</td>
										<td>${i.fashion.seller.shopName}</td>
										<td><center><a href="/fashiondetails/${i.fashion.fashionId}" class="btn btn-info" style="padding:3px 15px 3px 15px;">More</a></center></td>
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
	    $("#fashion").addClass("active");
	    $("#productmenu").addClass("menu-open");
	    $("#products").addClass("active");
	  });
	</script>
	<script type="text/javascript">
		    $(document).ready(function() {
			    var c = "${productRejectSuccess}";
			    var d = "${productApproveSuccess}";
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
			    	toastr["success"](c)
			    }
			    if(d != '')
			    {
			    	toastr["success"](d)
			    }
		    });
	</script>
</body>
</html>