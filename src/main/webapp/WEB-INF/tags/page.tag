<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title" required="true"%>
<%@attribute name="bodyClass" required="false"%>
<%@attribute name="extraScripts" fragment="true"%>

<!DOCTYPE html>
<html class="no-js" lang="pt">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>${title}</title>
	
	<c:url value="/" var="contextPath" />
	
	<link rel="icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/favicon.ico?11981592617154272979" type="image/ico" />
	<link href="https://plus.googlecom/108540024862647200608" rel="publisher"/>
	<link href="${contextPath}resources/css/cssbase-min.css" rel="stylesheet" type="text/css" media="all" />
	<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'/>
	<link href="${contextPath}resources/css/fonts.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/fontello-ie7.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/fontello-embedded.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/fontello.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/layout-colors.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/responsive-style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="${contextPath}resources/css/guia-do-programador-style.css" rel="stylesheet" type="text/css"  media="all"  />
  	<link href="${contextPath}resources/css/produtos.css" rel="stylesheet" type="text/css"  media="all"  />
	<link rel="canonical" href="http://www.casadocodigo.com.br/" />
	
	
	<%-- <link href="${contextPath}resources/cssbase-min.css" rel="stylesheet" type="text/css"  media="all"  />
	<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'>
	<link href="${contextPath}resources/css/fonts.css" rel="stylesheet" type="text/css"  media="all"  />
	<link href="${contextPath}resources/css/fontello-ie7.css" rel="stylesheet" type="text/css"  media="all"  />
	<link href="${contextPath}resources/css/fontello-embedded.css" rel="stylesheet" type="text/css"  media="all"  />
	<link href="${contextPath}resources/css/fontello.css" rel="stylesheet" type="text/css"  media="all"  />
    <link href="${contextPath}resources/css/book-collection.css" rel="stylesheet" type="text/css"  media="all"  />
    <link href="${contextPath}resources/css/style.css" rel="stylesheet" type="text/css"  media="all"  />
  	<link href="${contextPath}resources/css/layout-colors.css" rel="stylesheet" type="text/css"  media="all"  />
  	<link href="${contextPath}resources/css/responsivo-style.css" rel="stylesheet" type="text/css"  media="all"  /> --%>
</head>

<body class="${bodyClass}">
	<jsp:doBody />
	
	<jsp:invoke fragment="extraScripts" />
</body>
</html>
