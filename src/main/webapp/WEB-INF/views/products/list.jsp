<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Produtos</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="user"/>
	<div>Olá!! ${user }</div>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<c:url value="/products/form" var="formLink" />
	<a href="${formLink }">Cadastrar novo produto</a>
</sec:authorize>
<h1>Lista de produtos</h1>
<c:url value="/" var="contexto" />
<a href="${contexto }/logout">Logout</a>
${sucesso} 
<table border="1">
	<tr bgcolor="#AAAAAA">
		<td>Título</td>
		<td width="35%">Descrição</td>
		<td>No. de Páginas</td>
		<td>Data de Lançamento</td>
		<td>Valores</td>
		<td>Detalhar</td>
		
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
			<td>
				<c:url value="/products/${product.id }" var="linkDetalhar" />
				<a href="${linkDetalhar }">Detalhar</a>
			</td>
			
		</tr>
	</c:forEach>
</table>
<p><a href="http://localhost:8080/casadocodigo/products/form">FORM</a></p>
</body>
</html>