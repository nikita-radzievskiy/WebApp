<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title><fmt:message key="label.previous.orders"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/goods.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/body.css"/> ">
</head>
<body>
<br>
<c:forEach var="order" items="${requestScope.ordersUser}">
<center>
    <div>
        <h2><fmt:message key="label.order.id"/>${order.key.id}|
            <fmt:message key="label.status"/>:${order.key.statusName} |
            <fmt:message key="label.cteatedAt"/>: ${order.key.createdAt}</h2>
        <div id="table">
            <table border="1" cellpadding="5">
                <tr>
                    <th><fmt:message key="label.name"/></th>
                    <th><fmt:message key="label.color"/></th>
                    <th><fmt:message key="label.size"/></th>
                    <th><fmt:message key="label.category"/></th>
                    <th><fmt:message key="label.price"/></th>
                </tr>
                <c:forEach items="${order.value}" var="good">
                    <tr>
                        <td>${good.name} </td>

                        <td>${good.color}</td>

                        <td> ${good.size}</td>

                        <td>${good.category}</td>

                        <td>${good.price}</td>
                    </tr>
                    <c:set var="total" value="${total + good.price}"/>
                </c:forEach>
            </table>
        </div>
    </div>

    <tr><h2><fmt:message key="label.total"/>: ${total}</h2></tr>


    <hr>
    <c:set var="total" value="${total*0}"/>
    </c:forEach>
</center>
</body>
</html>
