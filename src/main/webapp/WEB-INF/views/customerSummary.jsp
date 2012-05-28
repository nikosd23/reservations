<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="/styles/springsource.css"/>" type="text/css"/>
	<title>OAKA37 :: Customer Summary</title>
</head>

<body>
<div id="main_wrapper">

<h1>Customer Summary</h1>

<ul>
	<c:forEach items="${customers}" var="customer">
		<li><a href="customerDetails?entityId=${customer.entityId}">${customer.name}</a></li>
	</c:forEach>
</ul>

<p><a href="<c:url value="/j_spring_security_logout"/>">Logout</a> (<security:authentication property="principal.username"/>)</p>

</div>
</body>

</html>
