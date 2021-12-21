<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>
<jsp:include page="../header.jsp"/>
<html>
<head>
    <title><fmt:message key="label.edit"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/goods.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/body.css"/> ">
</head>
<body>
<center>
    <h1><fmt:message key="label.edit.goods"/></h1>
    <br>
    <c:if test="${error}">
        <span class="error"><fmt:message key="edit_goods_jsp.error"/></span>
    </c:if>

    <form action="${pageContext.servletContext.contextPath}/editGoods" method="POST">
        <table>
            <tr style="display:none;">
                <td align="right"> id :</td>
                <td>
                    <input type="text" required name="goodsId" value=${requestScope.goods.id}>
                </td>
            </tr>
            <tr>
                <td align="right"><fmt:message key="label.name"/> :</td>
                <td>
                    <input type="text" required name="goodsName" value=${requestScope.goods.name}>
                </td>
            </tr>
            <tr>
                <td align="right"><fmt:message key="label.category"/> :</td>
                <td>
                    <input type="text" required name="categoryName" value=${requestScope.goods.category}>
                </td>
            </tr>

            <tr>
                <td align="right"><fmt:message key="label.price"/> :</td>
                <td>
                    <input type="number" min="0" required name="price" value=${requestScope.goods.price}>
                </td>
            </tr>

            <tr>
                <td align="right"><fmt:message key="label.color"/> :</td>
                <td>
                    <input type="text" required name="color" value=${requestScope.goods.color}>
                </td>
            </tr>
            <tr>
                <td align="right"><fmt:message key="label.size"/>:</td>
                <td>
                    <input type="number" min="0" required name="size" value=${requestScope.goods.size}>
                </td>
            </tr>
            <tr>
                <td align="right"><fmt:message key="label.available"/> :</td>
                <td>
                    <input type="number" min="0" required name="available" value=${requestScope.goods.available}>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" align="center" value="<fmt:message key="label.edit" />">
                </td>
            </tr>
        </table>
    </form>

    <a href="${pageContext.servletContext.contextPath}/listAllGoods">
        <fmt:message key="label.back"/> </a>
</center>

</body>
</html>
