<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
	<title>Reset Password</title>
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
				<p class="login-box-msg">Reset Password</p>
			  	<sf:form action="/admin/resetpassword" modelAttribute="loginCommand" onsubmit="return validator()">
				    <div class="input-group mb-3">
				    	<sf:input path="email" type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" class="form-control" placeholder="Email" required="required" disabled="true"/>
				    	<sf:hidden path="email"/>
				      	<div class="input-group-append">
				        	<div class="input-group-text">
				          		<span class="fas fa-envelope"></span>
				        	</div>
				      	</div>
				    </div>
				    <div class="input-group mb-3">
				    	<sf:input path="password" type="password" class="form-control" id="inputPassword" placeholder="Password" required="required"/>
				      	<div class="input-group-append">
				        	<div class="input-group-text">
				          		<span class="fas fa-eye-slash" id="eye"></span>
				        	</div>
						</div>
				    </div>
				    <div class="input-group mb-3">
				    	<input type="password" class="form-control" id="inputPassword1" placeholder="Confirm Password" required="required"/>
						<div class="input-group-append">
				        	<div class="input-group-text">
				          		<span class="fas fa-eye-slash" id="eye1"></span>
				        	</div>
						</div>
				    </div>
				    <div class="row">
				    	<div class="col-4">
					    	<button type="submit" class="btn btn-primary btn-block btn-flat" style="margin-left:110%;">Reset</button>
				      	</div>
				    </div>
				</sf:form>
			</div>
		</div>
	</div>
	<%@ include file="parts/js_src.jsp"%>
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
		    $("#eye1").on('click', function(event) {
		        event.preventDefault();
		        if($('#inputPassword1').attr("type") == "text"){
		            $('#inputPassword1').attr('type', 'password');
		            $('#eye1').addClass( "fa-eye-slash" );
		            $('#eye1').removeClass( "fa-eye" );
		        }else if($('#inputPassword1').attr("type") == "password"){
		            $('#inputPassword1').attr('type', 'text');
		            $('#eye1').removeClass( "fa-eye-slash" );
		            $('#eye1').addClass( "fa-eye" );
		        }
		    });
		});
	</script>
	<script type="text/javascript">
		function validator() {
			var pwd = document.getElementById("inputPassword");
			var cpwd = document.getElementById("inputPassword1");
			if (pwd.value != cpwd.value) {
				alert("Password & Confirm password Must be Same");
				pwd.value = "";
				cpwd.value = "";
				return false;
			}
			return true;
		}
	</script>
</body>
</html>