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
</head>
<body class="hold-transition sidebar-mini layout-fixed layout-navbar-fixed" onload="renderBarChart(${approvedProductsCountJsonForSeller},${stoppedProductsCountJsonForSeller},${requestedProductsCountJsonForSeller},${productCountJsonForSeller})">
	<div class="wrapper">
		<jsp:include page="parts/header.jsp"></jsp:include>
		<jsp:include page="parts/sidebar.jsp"></jsp:include>
		
		<div class="content-wrapper">
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
			        	<div class="col-sm-6">
			            	<h1 class="m-0 text-dark">Dashboard</h1>
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
          				<div class="col-lg-3 col-6">
            				<div class="small-box bg-info">
              					<div class="inner">
                					<h3>${sellerCount}</h3>
                					<p>Sellers</p>
              					</div>
              					<div class="icon">
                					<i class="ion ion-person"></i>
              					</div>
				              	<a href="/sellers" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
				            </div>
						</div>
          				<div class="col-lg-3 col-6">
            				<div class="small-box bg-warning">
              					<div class="inner">
                					<h3>${categoryCount}</h3>
                					<p>Categories</p>
              					</div>
              					<div class="icon">
                					<i class="ion ion-bag"></i>
              					</div>
              					<a href="/admin/category" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            				</div>
          				</div>
          				<div class="col-lg-3 col-6">
            				<div class="small-box bg-success">
              					<div class="inner">
                					<h3>${productCount}</h3>
                					<p>Products</p>
              					</div>	
              					<div class="icon">
                					<i class="ion ion-ios-locked"></i>
              					</div>
              					<a href="/electronics" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            				</div>
          				</div>
          				<div class="col-lg-3 col-6">
            				<div class="small-box bg-danger">
              					<div class="inner">
                					<h3>${userCount}</h3>
                					<p>Buyers</p>
              					</div>
              					<div class="icon">
                					<i class="ion ion-person-stalker"></i>
              					</div>
              					<a href="/users" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            				</div>
          				</div>
        			</div>
        			<div class="row">
						<div class="col-md-6">
							<div class="card card-info card-outline">
              					<div class="card-header">
									<div class="row">
										<div class="col-md-6">
											<h4>Bar Chart</h4>
										</div>
										<div class="col-md-6">
											<select id="barsellersel" class="form-control select2" onchange="barSellerChange(document.getElementById('barsellersel').value)" style="width:100%;">
												<myTag:forEach var="i" items="${sellerList}">							                  			
													<option value="${i.sellerId}">${i.sellerName}</option>
												</myTag:forEach>
			                  				</select>
										</div>
									</div>
                				</div>
                				<div class="card-body">
					                <div class="chart" id="barcanvasparent">
					                	<canvas id="barchart" style="height:220px; min-height:220px"></canvas>
					                </div>
              					</div>
                			</div>	
						</div>
						<div class="col-md-6">
							<div class="card card-info card-outline">
              					<div class="card-header">
                					<div class="row">
										<div class="col-md-6">
											<h4>Pie Chart</h4>
										</div>
										<div class="col-md-6">
											<select id="piesellersel" class="form-control select2" onchange="pieSellerChange(document.getElementById('piesellersel').value)" style="width:100%;">
												<myTag:forEach var="i" items="${sellerList}">							                  			
													<option value="${i.sellerId}">${i.sellerName}</option>
												</myTag:forEach>
			                  				</select>
										</div>
									</div>
                				</div>
                				<div class="card-body">
					                <div class="chart" id="piecanvasparent">
					                	<canvas id="piechart" style="height:220px; min-height:220px"></canvas>
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
	<script>
		function barSellerChange(sellerId){

			/* alert("barSellerChange"+"~~"+"sellerId : "+sellerId) */
			
			var approved = null; 
			var stopped = null;
			var requested = null;
			
			$.ajax({
				async : false,
				url : 'http://localhost:9191/productcount/approved/'+sellerId,
	    		success : function(response)
	    		{
		    		approved = response;
	    		}
	    	});
	    	
			$.ajax({
				async : false,
				url : 'http://localhost:9191/productcount/stopped/'+sellerId,
	    		success : function(response)
	    		{
	    			stopped = response;
	    		}
	    	});

			$.ajax({
				async : false,
				url : 'http://localhost:9191/productcount/requested/'+sellerId,
	    		success : function(response)
	    		{
	    			requested = response;
	    		}
	    	});
	    	
			$("#barchart").remove();
	    	$("#barcanvasparent").append("<canvas id='barchart' style='height:220px; min-height:220px'></canvas>");
	    	
			renderBarChart(approved,stopped,requested,null);
	    	
		}	
		function pieSellerChange(sellerId){

			/* alert("pieSellerChange"+"~~"+"sellerId : "+sellerId) */
			
			var productCount = null;
			
			$.ajax({
				async : false,
				url : 'http://localhost:9191/productcount/'+sellerId,
	    		success : function(response)
	    		{
	    			productCount = response;
	    		}
	    	});

			$("#piechart").remove();
			$("#piecanvasparent").append("<canvas id='piechart' style='height:220px; min-height:220px'></canvas>");

			renderPieChart(productCount);
		}
		function renderBarChart(approved,stopped,requested,productCount){

			/* alert("renderBarChart"+"~~"+"approved : "+approved+"~~"+"stopped : "+stopped+"~~"+"productCount : "+productCount) */
			
			var barChartContext = $('#barchart').get(0).getContext('2d');
			var barChartData = {
				labels  : [
					'Electronic', 
		          	'Book',
		          	'General',
		          	'Fashion', 
		      	],
				datasets: [
					{
			        	label               : 'APPROVED',
			          	backgroundColor     : '#28a745',
			          	borderColor         : '#28a745',
			            pointRadius          : false,
			            pointColor          : '#28a745',
			            pointStrokeColor    : '#28a745',
			            pointHighlightFill  : '#fff',
			            pointHighlightStroke: '#28a745',
			            data				:  approved ,
			        },
			        {
						label               : 'STOPPED',
						backgroundColor     : '#dc3545',
						borderColor         : '#dc3545',
						pointRadius         : false,
						pointColor          : '#dc3545',
						pointStrokeColor    : '#dc3545',
						pointHighlightFill  : '#fff',
						pointHighlightStroke: '#dc3545',
						data				: stopped , 
			        },
			        {
						label               : 'REQUESTED',
						backgroundColor     : '#ffc107',
						borderColor         : '#ffc107',
						pointRadius         : false,
						pointColor          : '#ffc107',
						pointStrokeColor    : '#ffc107',
						pointHighlightFill  : '#fff',
						pointHighlightStroke: '#ffc107',
						data				: requested , 
			        }
				]
			}
			var barChartOptions = {
				responsive              : true,
				maintainAspectRatio     : false,
				datasetFill             : false,
			}
		    var barChartCreate = new Chart(barchart, {
				type: 'bar', 
				data: barChartData,
				options: barChartOptions		        
			})

			if(productCount != null){
				renderPieChart(productCount);
			}
		}
		function renderPieChart(productCount)
		{
			/* alert("renderPieChart"+"~~"+"productCount : "+productCount) */
			
		    var pieChartCanvas = $('#piechart').get(0).getContext('2d')
		    var pieData = {
				labels: [
	          		'Electronic', 
	          		'Book',
	          		'General',
	          		'Fashion', 
	      		],
		      	datasets: [
		        	{
		          		data: productCount,
		          		backgroundColor : ['#f56954', '#00a65a', '#f39c12', '#00c0ef'],
		        	}
				]
		    }
		    var pieOptions = {
				maintainAspectRatio : false,
				responsive : true,
		    }
		    var pieChart = new Chart(pieChartCanvas, {
				type: 'pie',
				data: pieData,
				options: pieOptions      
		    })
		}
	</script>
</body>
</html>