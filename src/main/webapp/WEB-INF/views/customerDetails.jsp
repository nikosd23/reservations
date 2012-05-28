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
	<title>OAKA37 :: Customer Details</title>
</head>

<body>
<div id="main_wrapper">

<h1>Customer Details</h1>

<table>
	<tr><td>
		<table>
			<tr>
				<td>Customer:</td>
				<td>${customer.number}</td>
			</tr>
			<tr>
				<td>Name:</td>
				<td>${customer.name}</td>
			</tr>
		</table>
	</td></tr>
	<security:authorize ifAllGranted="ROLE_MEMBER">
		<tr><td>
			<table>
				<thead>
					<tr>
						<td>Beneficiaries:</td>
					</tr>
					<tr>
						<td>Name</td>
						<td>Allocation Percentage</td>
						<td>Savings</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customer.beneficiaries}" var="beneficiary">
						<tr>
							<td>${customer.name}</td>
							<td>${customer.allocationPercentage}</td>
							<td>${customer.savings}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</td></tr>
	</security:authorize>
</table>

<security:authorize ifAllGranted="ROLE_ADMIN">
	<p><a href="editCustomer?entityId=${customer.entityId}">Edit Customer</a></p>
</security:authorize>

<p><a href="<c:url value="/j_spring_security_logout"/>">Logout</a> (<security:authentication property="principal.username"/>)</p>

</div>
</body>

</html>
