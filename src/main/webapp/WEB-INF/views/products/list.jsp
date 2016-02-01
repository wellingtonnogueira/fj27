<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Produtos</title>
</head>
<body>
<h1>Lista de produtos</h1>
${sucesso} 
<table border="1">
	<tr bgcolor="#AAAAAA">
		<td>Título</td>
		<td width="35%">Descrição</td>
		<td>No. de Páginas</td>
		<td>Data de Lançamento</td>
		<td>Valores</td>
	</tr>
	<c:set var="incluido" value="${incluido}"/>
	<c:forEach items="${products}" var="product">
		<tr>
			<td>${product.title }</td>
			<td>${product.description }</td>
			<td>${product.numberOfPages }</td>
			<td><fmt:formatDate value="${product.releaseDate.time }" pattern="dd/MM/yyyy"/></td>
			<td>
				<c:forEach items="${product.prices }" var="price">
					${price.bookType} - ${price.value }
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>
<p><a href="http://localhost:8080/casadocodigo/products/form">FORM</a></p>
</body>
</html>