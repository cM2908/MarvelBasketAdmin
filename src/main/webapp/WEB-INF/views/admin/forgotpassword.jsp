<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title>Forgot Password</title>
	<%@ include file="parts/meta.jsp"%>
	<%@ include file="parts/css_links.jsp"%>
</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
		  <a href="#"><b>Marvel Basket</b></a>
		</div>
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Forgot Password</p>
			  	<sf:form action="/admin/fpmail" modelAttribute="adminCommand">
				    <div class="input-group mb-3">
				    	<sf:input path="adminEmail" type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" class="form-control" placeholder="Email" required="required"/>
				      	<div class="input-group-append">
				        	<div class="input-group-text">
				          		<span class="fas fa-envelope"></span>
				        	</div>
				      	</div>
				    </div>
				    <div class="row">
				    	<div class="col-4">
					    	<button type="submit" class="btn btn-primary btn-block btn-flat" style="margin-left:50%;">Send</button>
				      	</div>
				      	<div class="col-4">
					    	<a href="/admin/login" class="btn btn-default btn-block btn-flat" style="margin-left:50%;">Back</a>
				      	</div>
				    </div>
				</sf:form>
			</div>
		</div>
	</div>
	<%@ include file="parts/js_src.jsp"%>
	<script type="text/javascript">
		    $(document).ready(function() {
			    var a = "${emailError}";
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
			    	toastr["error"](a)
			    }
		    });
	</script>
</body>
</html>