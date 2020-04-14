<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>    
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<a href="#" class="brand-link">
    	<img src="../images/avatar5.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
      	<span class="brand-text font-weight-light">${admin.adminName}</span>
    </a>
    <div class="sidebar">
    	<nav class="mt-2">
        	<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          		<li class="nav-item">
            		<a id="dashboard" href="/admin/dashboard" class="nav-link">
              			<i class="nav-icon fas fa-tachometer-alt"></i>
              			<p> Dashboard </p>
            		</a>
          		</li>
          		<li id="usermenu" class="nav-item has-treeview">
            		<a id="users" href="#" class="nav-link">
              			<i class="nav-icon fas fa-user-cog"></i>
              			<p>  Users  <i class="right fas fa-angle-left"></i> </p>
            		</a>
            		<ul class="nav nav-treeview">
              			<li class="nav-item">
                			<a id="buyers" href="/users" class="nav-link">
                  				<i class="far fa-user nav-icon"></i>
                  				<p>Buyers</p>
                			</a>
              			</li>
              			<li class="nav-item">
                			<a id="sellers" href="/sellers" class="nav-link">
                  				<i class="fas fa-user-secret nav-icon"></i>
                  				<p>Sellers</p>
                			</a>
              			</li>
              			<li class="nav-item">
                			<a id="sellerrequests" href="/sellerrequests" class="nav-link">
                  				<i class="fas fa-user-clock nav-icon"></i>
                  				<p>Seller Requests</p>
                			</a>
              			</li>
            		</ul>
          		</li>
          		<li class="nav-item">
            		<a id="category" href="/admin/category" class="nav-link">
              			<i class="nav-icon fas fa-shopping-basket"></i>
              			<p> Categories </p>
            		</a>
          		</li>
          		<li id="productmenu" class="nav-item has-treeview">
            		<a id="products" href="#" class="nav-link">
              			<i class="nav-icon fab fa-product-hunt"></i>
              			<p> Products <i class="right fas fa-angle-left"></i></p>
            		</a>
            		<ul class="nav nav-treeview">
              			<li class="nav-item">
                			<a id="electronics" href="/electronics" class="nav-link">
                  				<i class="fas fa-tv nav-icon"></i>
                  				<p>Electronics</p>
                			</a>
              			</li>
              			<li class="nav-item">
                			<a id="fashion" href="/fashion" class="nav-link">
                  				<i class="fas fa-shopping-bag nav-icon"></i>
                  				<p>Fashion</p>
                			</a>
              			</li>
              			<li class="nav-item">
                			<a id="books" href="/books" class="nav-link">
                  				<i class="fas fa-book-open nav-icon"></i>
                  				<p>Books</p>
                			</a>
              			</li>
              			<li class="nav-item">
                			<a id="general" href="/general" class="nav-link">
                  				<i class="fas fa-stroopwafel nav-icon"></i>
                  				<p>General</p>
                			</a>
              			</li>
              			<li class="nav-item">
                			<a id="productrequests" href="/productrequests" class="nav-link">
                  				<i class="fas fa-hourglass-half nav-icon"></i>
                  				<p>Product Requests</p>
                			</a>
              			</li>
            		</ul>
          		</li>
          		
          		<li class="nav-item">
            		<a id="profile" href="/admin/profile" class="nav-link">
              			<i class="nav-icon fas fa-id-card"></i>
              			<p> Profile </p>
            		</a>
          		</li>
          		<li class="nav-item">
            		<a href="/admin/logout" class="nav-link">
              			<i class="nav-icon fas fa-sign-out-alt"></i>
              			<p> Logout </p>
            		</a>
          		</li>
        	</ul>
      	</nav>
    </div>
</aside>