<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center" style="padding-top:50px;background-color:burlywood;height:520px;">
<h1 align="center" style="font-family:monospace;color:red;font-size:25px;">Over all Report</h1>
<table border="2">
<tr>
  <td>no.of defaulters</td>  
  <td>${calul}</td>
 </tr>
<tr>
  <td>no.of autodefaulters</td>  
  <td style="width: 30px;">${auto}</td> 
 </tr>
 <tr>
  <td>no.of manualdefaulters</td>  
  <td>${manual}</td> 
 </tr>
 
 <tr>
  <td>no.of Re-activations</td>  
  <td>${req}</td> 
 </tr>
</table>
</div>

<%

RequestDispatcher rd = request.getRequestDispatcher("sampleadminhome.jsp");
rd.include(request, response);

%>
</body>
</html>