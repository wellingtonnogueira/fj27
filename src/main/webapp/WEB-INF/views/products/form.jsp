<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Livros</title>
</head>
<body>

	<c:url value="/products" var="url" />
	<form:form action="${url}" method="post" commandName="product" enctype="multipart/form-data"> --%>
<%-- 	<form:form servletRelativeAction="${url}" method="post" commandName="product" enctype="multipart/form-data"> --%>
<%-- 	<form:form action="${spring:mvcUrl('PC#save').build()}" method="post" commandName="product" enctype="multipart/form-data"> --%>
		<div>
			<label for="title">Título</label>
			<form:input path="title" id="title" />
			<strong><form:errors path="title" /></strong>
		</div>
		<div>
			<label for="description">Descrição</label>
			<form:textarea path="description" id="description" rows="10" cols="20"/>
			<strong><form:errors path="description" /></strong>
		</div>
		<div>
			<label for="numberOfPages">Número de páginas</label>
			<form:input path="numberOfPages" id="numberOfPages" />
			<strong><form:errors path="numberOfPages" /></strong>
		</div>
		<div>
			<label for="releaseDate">Data de lançamento</label>
			<form:input path="releaseDate" id="releaseDate" />
			<strong><form:errors path="releaseDate" /></strong>
		</div>
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="${bookType}">${bookType}</label>
				<form:input path="prices[${status.index}].value" id="${bookType}" />
<!--
				<form:hidden path="prices[${status.index}].bookType" id="${bookType}" />
 				<input type="text" id="prices_${bookType}" name="prices[${status.index}].value" />
-->
				<input type="hidden" id="prices_${bookType}" name="prices[${status.index}].bookType" value="${bookType}" />
			</div>
		</c:forEach>
		<div>
			<label for="summary">Sumário do livro</label>
			<input type="file" id="summary" name="summary" />
			<strong><form:errors path="summaryPath" /></strong>
		</div>
		<div>
			<input type="submit" value="Enviar" />
		</div>
	
	</form:form>

</body>
</html>