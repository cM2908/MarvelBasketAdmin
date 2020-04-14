<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<script src="../js/jquery.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/jquery.vmap.min.js"></script>
<script src="../js/jquery.vmap.world.js"></script>
<script src="../js/jquery.knob.min.js"></script>
<script src="../js/jquery.dataTables.js"></script>
<script>$.widget.bridge('uibutton', $.ui.button)</script>
<script src="../js/bootstrap.bundle.min.js"></script>
<script src="../js/Chart.min.js"></script>
<script src="../js/sparkline.js"></script>
<script src="../js/moment.min.js"></script>
<script src="../js/daterangepicker.js"></script>
<script src="../js/tempusdominus-bootstrap-4.min.js"></script>
<script src="../js/summernote-bs4.min.js"></script>
<script src="../js/ekko-lightbox.min.js"></script>
<script src="../js/jquery.overlayScrollbars.min.js"></script>
<script src="../js/adminlte.js"></script>
<script src="../js/dashboard.js"></script>
<script src="../js/demo.js"></script>
<script src="../js/toastr.min.js"></script>
<script src="../js/dataTables.bootstrap4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url : 'http://localhost:9191/admin/notifications',
			success : function(responseText)
			{
				var notificationCount = 0;
				$.each(responseText, function(i, result) {
					var html = "<a href='#' class='dropdown-item'><div class='media'><img src='../images/avatar5.png' alt='User Avatar' class='img-size-50 mr-3 img-circle'><div class='media-body'><h3 class='dropdown-item-title'>"+ result.from.sellerName +"<span class='float-right text-sm text-danger'><i class='fas fa-star'></i></span></h3><p class='text-sm'>" + result.description + "</p><p class='text-sm text-muted'><i class='far fa-clock mr-1'></i> " +result.notification_time[1] +" "+ result.notification_time[0] +" Ago</p></div></div></a>";
					$('#notification').prepend(html);
					notificationCount++;
				});				
				$('#notificationCount').text(notificationCount);
				if(notificationCount == 0)
				{
					/* var html = "<a href='#' class='dropdown-item dropdown-footer'>No new notifications</a>";
					$('#notification').prepend(html); */
				}
			}
		});
	});

	$("#notificationBell").click(function(){
		$.ajax({
			url : 'http://localhost:9191/admin/readnotifications',
		});						
	});		
</script>