
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/goods.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/body.css"/> ">
</head>
<body>
<div style="margin: 20px; padding: 20px; float: right;">
    <h4>
        <fmt:message key="label.chooseRequestLocale" />
    </h4>
    <ul>
        <li><a href="?lang=en"><fmt:message key="label.lang.en" /></a></li>
        <li><a href="?lang=ru"><fmt:message key="label.lang.ru" /></a></li>
    </ul>
</div>
<div class="header">
    <h2><fmt:message key="label.hello" />, ${sessionScope.login}</h2>
    <form style="margin: 0; padding: 0; float: right;" action="${contextPath}/logout">
        <button id="logoutButton" type="submit"><fmt:message key="label.logout" /></button>
    </form>

    <div class="centered">

        <c:choose>
            <c:when test="${sessionScope.role=='admin'}">
                <a href="${contextPath}/listAllGoods" class="active"><fmt:message key="label.home" />|</a>
                <a href="${contextPath}/createGoods" class="active"><fmt:message key="label.create.goods" />|</a>
                <a href="${contextPath}/setUserRoleByLogin" class="active"><fmt:message key="label.ban_or_unban user" />|</a>
                <a href="${contextPath}/setStatusByOrderId" class="active"><fmt:message key="label.change.order.status" /> |</a>
                <a href="${contextPath}/listAllOrders" class="active"><fmt:message key="label.List.all.goods" />|</a>

            </c:when>
            <c:when test="${sessionScope.role=='user'}">
                <a href="${contextPath}/listAllGoods" class="homePage"><fmt:message key="label.home" /></a>
                <a href="${contextPath}/listOldOrders" class="homePage"><fmt:message key="label.previous.orders" /></a>
                <form style="margin: 0; padding: 0; float: right;" action="${contextPath}/cart">
                    <button id="cartButton" type="submit"><fmt:message key="label.cart" /></button>
                </form>
            </c:when>
            <c:when test="${sessionScope.role=='blocked user'}">
                <a href="${contextPath}/listAllGoods" class="homePage"><fmt:message key="label.home" /></a>
                <a href="${contextPath}/listOldOrders" class="homePage"><fmt:message key="label.previous.orders" /></a>

            </c:when>
        </c:choose>
    </div>
</div>
<hr>

</body>
</html>
