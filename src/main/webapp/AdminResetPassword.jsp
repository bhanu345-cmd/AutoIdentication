<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript">
function Adminresetpassword(){
	 var password = document.adminreserpwd.password.value;
	 var cnfpassword = document.adminreserpwd.confirmationpassword.value;

	 if(password ==""){
		 alert("enter new password");
		 document.getElementById('passwd').style.borderColor = "red";
		 return false;
		 }
	 if(cnfpassword ==""){
		 alert("enter confirmation password");
		 document.getElementById('cpwd').style.borderColor = "red";
		 return false;
		 }
}
</script>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<body>
<%
String username =(String) session.getAttribute("userid");
%>
<div class="header1" align="center">
<h1>Reset Password</h1>
</div>
<div align="center">
<div class="forms-data">
<form name="adminreserpwd" action="/admin/resetpassword" method="post" onsubmit="return Adminresetpassword()">
<table>
<tr>
<td>New Password:</td>
<td>
<input type="password" name="password" id="passwd" class="form-control" onfocus="validatepwd()">
</td>
</tr>
<tr>
<td>Confirmation Password:</td>
<td>
<input type="password" name="confirmationpassword" id="cpwd" class="form-control" onfocus="validatecpwd()">
<br>
<span>${message}</span>
</td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit" value="submit" class="formsubmitbutton">
</td>
</tr>
</table>
</form>
For Login-><a href="/admin/" style="color:yellow;">Login</a>
</div>
</div>
<script type="text/javascript">
function validatepwd(){
	document.getElementById('passwd').style.borderColor = "grey";
}
function validatecpwd(){
	document.getElementById('cpwd').style.borderColor = "grey";
}
</script>
</body>
</html>