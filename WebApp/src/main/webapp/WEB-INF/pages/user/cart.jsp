<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/goods.css"/>">
    <link rel=" stylesheet" type="text/css" href="<c:url value="/css/body.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>">
</head>
<body>
<jsp:include page="../header.jsp"/>

<center>
    <div id=" table
    ">
        <table border="1" cellpadding="5">
            <tr>
                <th><fmt:message key="label.name"/></th>
                <th><fmt:message key="label.color"/></th>
                <th><fmt:message key="label.size"/></th>
                <th><fmt:message key="label.category"/></th>
                <th><fmt:message key="label.price"/></th>
                <th><fmt:message key="label.delete"/></th>
            </tr>

            <c:forEach items="${sessionScope.cartList}" var="entry">

                <tr>
                    <td>${entry.value.name} </td>

                    <td>${entry.value.color}</td>

                    <td> ${entry.value.size}</td>

                    <td>${entry.value.category}</td>

                    <td>${entry.value.price}</td>
                    <td><a href="deleteFromCart?id=${entry.value.id}">
                        <fmt:message key="label.delete"/>
                    </a></td>
                </tr>

            </c:forEach>


        </table>
    </div>

    <a></a>
    <form class="center" action="cart" method="post">
        <button type="submit"><fmt:message key="label.pay"/></button>
    </form>
    <br>
    <c:if test="${error}">
        <span class="error"><fmt:message key="cart_jsp.error"/></span>
    </c:if><br>
    <a href="${pageContext.servletContext.contextPath}/listAllGoods">
        <fmt:message key="label.back"/> </a>
</center>
</body>
</html>
