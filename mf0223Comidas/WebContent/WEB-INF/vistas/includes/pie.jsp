<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

</main>

<br />
<br />

<footer>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="principal">&copy; 2021 Jaime Quintana</a>
	</nav>
</footer>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/dataTables.bootstrap4.min.js"></script>

<script>
      bsCustomFileInput.init();
</script>

<script>
	$(document).ready(function() {
		$('table').DataTable({
			"language" : {
				"url" : "js/Spanish.json"
			}
		});
	});
</script>
</body>
</html>