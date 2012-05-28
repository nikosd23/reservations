<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css"/>
	<link rel="stylesheet" href="<c:url value="/css/custom1.css"/>" type="text/css"/>
	<script type='text/javascript' src="<c:url value="/js/jquery-1.7.1.js"/>"></script>
	<script type='text/javascript' src="<c:url value="/js/bootstrap-alert-2.0.3.js"/>"></script>
	<title>OAKA37 :: Login</title>
</head>

<body>
	<div id="container" class="container">
		<div id="central-navbar" class="navbar navbar-static">
			<div class="navbar-inner">
				<div class="container" style="width: auto;">
					<a class="brand" href="https://www.facebook.com/pages/OAKA-37/289362791083716">OAKA37</a>
				</div>
			</div>
			<form class="well form-inline" action="<c:url value='/j_spring_security_check'/>" method="post">
				<input type="text" class="input-small" placeholder="Username..."name="j_username">
				<input type="text" class="input-small"  placeholder="Password..."name="j_password">
				<button class="btn btn-primary" type="submit">Sign in</button>			
			</form>
		</div>
		<div class="span4 offset4">
			<c:if test="${!empty param.login_error}">
				<div class="alert alert-block">
					<a class="close" data-dismiss="alert" href="#">x</a>
					<h4 class="alert-heading">Warning!</h4>
					<spring:message code="login.invalid"/>
				</div>
			</c:if>
		</div>
		
	</div>
</body>

</html>
