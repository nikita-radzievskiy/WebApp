<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/body.css"/> ">
</head>
<body>
<div style="margin: 20px; padding: 20px; float: right;">

    <h4>
        <fmt:message key="label.chooseRequestLocale"/>
    </h4>
    <ul>
        <li><a href="?lang=en"><fmt:message key="label.lang.en"/></a></li>
        <li><a href="?lang=ru"><fmt:message key="label.lang.ru"/></a></li>
    </ul>


</div>
<div class="main">
    <form action="login" method="post">
        <input type="text" class="inputText" name="login" placeholder=
        <fmt:message key="login_jsp.login"/> required>
        <input type="password" class="inputText" name="password" placeholder=
        <fmt:message key="login_jsp.password"/> required>
        <input type="submit" class="buttonSubmit" value=<fmt:message key="login_jsp.signIn"/>>
    </form>

</div>
<center>
    <c:if test="${error}">
        <span class="error"><fmt:message key="login_jsp.Invalid.login.or.password"/></span>
    </c:if>
</center>


</body>
</html>