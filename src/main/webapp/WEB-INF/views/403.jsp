<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="cdc" %>


<cdc:page title="ERRO">
	<jsp:attribute name="extraScripts" >
		<script type="text/JavaScript">
		<c:url value="/products" var="url" />
		<!--
		setTimeout("location.href = '${url}';",1500);
		-->
		</script>
	</jsp:attribute>
	<jsp:body>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal" var="user"/>
			<div>
				Olá ${user.name}
			</div>
		</sec:authorize>
		
		
		<img alt="sirene" src="https://montanhasrn.files.wordpress.com/2015/09/sirene.gif?w=604" />
		<p>Acesso negado</p>
	</jsp:body>

</cdc:page>


<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ERRO</title>
<script type="text/JavaScript">
<c:url value="/products" var="url" />

setTimeout("location.href = '${url}';",1500);
</script>
</head>
<body>
<img alt="sirene" src="https://montanhasrn.files.wordpress.com/2015/09/sirene.gif?w=604" />
<p>Acesso negado</p>

</body>
</html> -->
