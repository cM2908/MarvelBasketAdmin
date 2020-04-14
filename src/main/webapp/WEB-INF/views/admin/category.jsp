<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title>Product Category</title>
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
			            	<h1 class="m-0 text-dark">Shop Categories</h1>
			          	</div>
			          	<div class="col-sm-6">
				        	<ol class="breadcrumb float-sm-right">
				        		<li class="breadcrumb-item active">Home</li>
				        	</ol>
			       		</div>
					</div>
				</div>
			</div>
			<section class="content">
				<div class="container-fluid">
					<div class="row">
			        	<div class="col-12 col-sm-6 col-md-3">
		            		<sf:form role="form" action="/admin/addcategory" modelAttribute="categoryCommand" method="post">
				            	<div class="info-box">
				            		<span class="info-box-icon bg-info elevation-1"><button class="btn"><i class="fas fa-plus"></i></button></span>
				              		<div class="info-box-content">
				                		<span class="info-box-number text-info"><h5><b>Add Category</b></h5></span>
				                		<sf:input type="text" path="categoryName" class="form-control" id="categoryName" placeholder="Category Name" style="border-radius:5px;" required="required"/>
				              		</div>
				            	</div>
		            		</sf:form>	
			          	</div>
			          	<myTag:forEach var="i" items="${map}">
				          	<div class="col-12 col-sm-6 col-md-3">
				            	<div class="info-box bg-info">
				              		<div class="info-box-content">
				                		<span class="info-box-number"><h4><b>${i.key.categoryName}</b></h4></span> 
				                		<span class="info-box-text">Sellers : ${i.value}</span>
				              		</div>
				            	</div>
				          	</div>
			          	</myTag:forEach>
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
	    $("#category").addClass("active");
	  });
	</script>
	<script type="text/javascript">
		    $(document).ready(function() {
			    var a = "${categorySuccess}";
			    var b = "${categoryError}";
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
</body>
</html>