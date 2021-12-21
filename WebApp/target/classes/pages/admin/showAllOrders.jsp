<%--
  Created by IntelliJ IDEA.
  User: pmo70
  Date: 22.03.2019
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../header.jsp"/>

<c:forEach var="order" items="${requestScope.orders}">
<table>
    <fmt:message key="label.order.id" />${order.key.id}|
    <fmt:message key="label.status" />:${order.key.statusName} |
    <c:forEach items="${order.value}" var="good">
        <tr>
            <td>${good.name} </td>

            <td>${good.color}</td>

            <td> ${good.size}</td>

            <td>${good.category}</td>

            <td>${good.price}</td>
        </tr>
    </c:forEach>


    </c:forEach>

</table>
</div>

</body>
</html>
