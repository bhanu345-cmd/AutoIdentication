<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>
<body>
<div class="header1">
<h1>Bank Employee - Login</h1>
${message }
</div>
<div align="center">
<div class="forms-data">
<form:form  action="/emp/verifylogin" method="post" modelAttribute="bankemployeelogin" style="margin-top:50px;margin-left:30px;">
<table>
<tr>
<td style="color: white">User Id</td>
<td>
<form:input path="userId" class="form-control"/>
<br>
<form:errors path="userId" cssClass="errors"></form:errors>
<br>
</td>
</tr>
<tr>
<td style="color: white">Password</td>
<td>
<form:input path="password" class="form-control"/>
<br>
<form:errors path="password" cssClass="errors"></form:errors>
<br>
</td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="Login" class="formsubmitbutton" />
</tr>
</table>
</form:form>
New Bank Employee?<a href="/emp/register" style="color:white;">Registration</a>
</div>
<a href="/" style="color:white;">Home Page</a>
</div>
</body>
</html>