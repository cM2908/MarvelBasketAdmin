<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<nav class="main-header navbar navbar-expand navbar-light">
	<ul class="navbar-nav">
		<li class="nav-item">
	    	<a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
	  	</li>
	  	<li class="nav-item d-none d-sm-inline-block">
        	<a href="/admin/dashboard" class="nav-link">Home</a>
      </li>
	</ul>
	<ul class="navbar-nav ml-auto">
	  <li class="nav-item dropdown">
	    <a class="nav-link" data-toggle="dropdown" href="#" id="notificationBell">
	      <i class="far fa-bell"></i>
	      <span class="badge badge-warning navbar-badge" id="notificationCount"></span>
	    </a>
	    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right" id="notification">
	      <div class="dropdown-divider"></div>
	      <a href="../admin/allnotifications" class="dropdown-item dropdown-footer">See All Notifications</a>
	    </div>
	  </li>
	</ul>
</nav>