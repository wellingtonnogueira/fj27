<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h3>Efetue o login</h3>
	<form:form servletRelativeAction="/login" method="POST" commandName="principal">
		<table>
			<tr>
				<td><label for="username">Login</label></td>
				<td><input type="text" name="username" id="username" /></td>
<%-- 				<td><form:input path="username" id="username" /></td> --%>
			</tr>
			<tr>
				<td><label for="password">Senha</label></td>
				<td><input type="password" name="password" id="password" /></td> 
<%-- 				<td><form:password path="password" id="password" /></td> --%>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" name="submit" value="Logar" /></td>
			
		</table>
	
	</form:form>

</body>
</html>