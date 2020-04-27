<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center" style="padding-top: 150px">
<table border="2" style="background-color: black">
<tr>
  <td>Account number</td>  
  <td ><c:out value="${customer.accountNumber}"></c:out></td> 
 </tr>
 <tr>
  <td>Name</td>  
  <td><c:out value="${customer.customerName}"></c:out></td> 
 </tr>
 <tr>
  <td>Amount</td>  
  <td><c:out value="${customer.borrowedAmount}"></c:out></td> 
 </tr>
 <tr>
  <td>tenure</td>  
  <td><c:out value="${customer.tenure}"></c:out></td> 
 </tr>
 <tr>
  <td>email</td>  
  <td><c:out value="${customer.email}"></c:out></td> 
 </tr>
  <tr>
  <td>mobile</td>  
  <td><c:out value="${customer.mobile}"></c:out></td> 
 </tr>
   <tr>
  <td>status</td>  
  <td><c:out value="${customer.status}"></c:out></td> 
  <td ><a herf="#"><button style="width: 80px;">validate</button></a></td>
   <td><a herf="#"><button>AutoWaver</button></a></td>
 </tr>
  <tr>
  <td>lastpaid on</td>  
  <td><c:out value="${customer.lastPaid}"></c:out></td> 
 </tr>
 <tr>
  <td>money paid</td>  
  <td><c:out value="${customer.moneyPaid}"></c:out></td> 
 </tr>
 <tr>
  <td>fine</td>  
  <td><c:out value="${customer.fine}"></c:out></td> 
 </tr>
 <tr>
  <td>Dues</td>  
  <td><c:out value="${customer.dues}"></c:out></td> 
 </tr>
  <tr>
  <td>card number</td>  
  <td><c:out value="${card.cardNumber}"></c:out></td> 
 </tr>
  <tr>
  <td>card status</td>  
  <td><c:out value="${card.cardStatus}"></c:out></td> 
  <td><a herf="#"><button>Re-activate</button></a></td>
   <td><a herf="#"><button>Deactivate</button></a></td>
 </tr>
</table>
<a href="/emp/alert"><button>alert</button></a>
<a href="/emp/charges?id=${customer.accountNumber}" ><button>charges</button></a>
<a href="/emp/pay?id=${customer.accountNumber}" ><button>Pay</button></a>
</div>

<%

RequestDispatcher rd = request.getRequestDispatcher("emphome.jsp");
rd.include(request, response);

%>
</body>
</html>