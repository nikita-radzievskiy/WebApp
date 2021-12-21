<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>
<jsp:include page="../header.jsp"/>
<html lang="${param.lang}">
<head>
    <title>Edit role</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/goods.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/body.css"/> ">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/> ">

</head>
<body>

<center>
    <form action="setUserRoleByLogin" method="post">
        <h1><fmt:message key="label.ban"/>/<fmt:message key="label.unban"/></h1>
        <p><fmt:message key="label.login"/>:
            <input type="text" name="login" required></p>

        <select size="2" name="role">
            <option value="blocked user"><fmt:message key="label.ban"/></option>
            <option selected value="user"><fmt:message key="label.unban"/></option>
        </select>
        <p><input type="submit" value="<fmt:message key="label.ok" />"></p>
    </form>
    <c:if test="${error}">
        <span class="error"><fmt:message key="set_user_role_by_login_jsp.error"/></span>
    </c:if><br>
    <a href="${pageContext.servletContext.contextPath}/listAllGoods">
        <fmt:message key="label.back"/> </a>
</center>
</body>
</html>
