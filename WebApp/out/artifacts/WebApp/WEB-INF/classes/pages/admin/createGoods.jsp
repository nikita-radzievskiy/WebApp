
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<jsp:include page="../header.jsp"/>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title><fmt:message key="label.create.goods"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/goods.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/body.css"/> ">

</head>
<body>
<center>
    <h1><fmt:message key="label.create.goods"/></h1> <br>
    <c:if test="${error}">
        <span class="error"><fmt:message key="create_goods_jsp.error"/></span>
    </c:if>

    <form action="${pageContext.servletContext.contextPath}/createGoods" method="POST">
        <div id="table">
            <table>
                <tr>
                    <td align="right"><fmt:message key="label.name"/>:</td>
                    <td>
                        <input type="text" name="name" value="test" required>
                    </td>
                </tr>
                <tr>
                    <td align="right"><fmt:message key="label.category"/> :</td>
                    <td>
                        <input type="text" name="categoryName" value="car" required>
                    </td>
                </tr>

                <tr>
                    <td align="right"><fmt:message key="label.price"/>:</td>
                    <td>
                        <input type="number" min="0" name="price" value="111" required>
                    </td>

                </tr>
                <tr>
                    <td align="right"><fmt:message key="label.color"/>:</td>
                    <td>
                        <input type="text" name="color" value="test" required>
                    </td>
                </tr>
                <tr>
                    <td align="right"><fmt:message key="label.size"/>:</td>
                    <td>
                        <input type="number" min="0" name="size" value="123" required>
                    </td>
                </tr>
                <tr>
                    <td align="right"><fmt:message key="label.available"/>:</td>
                    <td>
                        <input type="number" min="0" name="available" value="111" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" align="center" value="<fmt:message key="label.add" />" required>
                    </td>
                </tr>
            </table>
        </div>
    </form>

    <a href="${pageContext.servletContext.contextPath}/listAllGoods">
        <fmt:message key="label.back"/> </a>
</center>
</body>
</html>
