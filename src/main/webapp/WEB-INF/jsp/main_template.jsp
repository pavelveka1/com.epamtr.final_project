<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>CSS Template</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
	<header>
		<jsp:include page="fragment/header.jsp" />
	</header>
	<div class="container-fluid">
		<div class="row">
			<aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
				<jsp:include page="fragment/aside.jsp" />
			</aside>
			<main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
				<jsp:include page="${currentPage }" />
			</main>
		</div>
	</div>
	<footer class="footer">
		<jsp:include page="fragment/footer.jsp" />
	</footer>
	<script src="/static/js/jquery.js"></script>
	<script src="/static/js/bootstrap.js"></script>
	<script src="/static/js/app.js"></script>
</body>
</html>