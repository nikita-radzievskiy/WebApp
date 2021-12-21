<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pmo70
  Date: 06.03.2019
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="resources"/>
<jsp:include page="header.jsp"/>
<html>
<head>
    <title><fmt:message key="label.List.all.goods" /></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/goods.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/body.css"/> ">
</head>
<body>

<form action="listAllGoods">

    <p><fmt:message key="label.sort" /><br>
        <select size="1" name="sortType">\
            <option selected value="default" disabled><fmt:message key="label.sort" /></option>
            <option value="nameAZ"><fmt:message key="label.name(az)" /></option>
            <option value="nameZA"><fmt:message key="label.name(za)" /></option>
            <option value="priceLowToHigh"><fmt:message key="label.price(low-high)" /></option>
            <option value="priceHighToLow"><fmt:message key="label.price(high-low)" /></option>
            <option value="newToOld"><fmt:message key="label.new-old" /></option>
            <option value="oldToNew"><fmt:message key="label.old-new" /></option>
        </select><input type="submit" value="<fmt:message key="label.sort" />"></p>


</form>
<form action="listAllGoods">
    <div>
        <fmt:message key="label.price" /><br>
        <input type="number" min="0" name="varPrice1" placeholder="<fmt:message key="label.from" />"><br>
        <input type="number" min="0" name="varPrice2" placeholder="<fmt:message key="label.to" />">
        <input type="submit" value="<fmt:message key="label.ok" />">
    </div>
</form>
<form action="listAllGoods">
    <div>
        <fmt:message key="label.category" /><br>
        <input type="text" name="varCategory">
        <input type="submit" value="<fmt:message key="label.ok" />">
    </div>
</form>
<form action="listAllGoods">
    <div>
        <fmt:message key="label.color" /><br>
        <input type="text" name="varColor">
        <input type="submit" value="<fmt:message key="label.ok" />">
    </div>
</form>
<form action="listAllGoods">
    <div>
        <fmt:message key="label.size" /><br>
        <input type="number" min="0" name="varSize1" placeholder="<fmt:message key="label.from" />"><br>
        <input type="number" min="" name="varSize2" placeholder="<fmt:message key="label.to" />">
        <input type="submit" value="<fmt:message key="label.ok" />">
    </div>
</form>

<form>
    <div id="table">
        <table border="1" cellpadding="5">
            <tr>
                <th><fmt:message key="label.name" /></th>
                <th><fmt:message key="label.color" /></th>
                <th><fmt:message key="label.size" /></th>
                <th><fmt:message key="label.category" /></th>
                <th><fmt:message key="label.price" /></th>
                <th><fmt:message key="label.toDo" /></th>
            </tr>
            <c:forEach var="good" items="${goodsList}">
                <tr>
                    <td>${good.name} </td>

                    <td>${good.color}</td>

                    <td> ${good.size}</td>

                    <td>${good.category}</td>

                    <td>${good.price}</td>
                    <td>
                        <c:choose>
                            <c:when test="${sessionScope.role=='admin'}">
                                <a href="editGoods?id=${good.id}">
                                    <fmt:message key="label.edit" />
                                </a>
                                <a href="deleteGoods?id=${good.id}">
                                    <fmt:message key="label.delete" />
                                </a>
                            </c:when>

                            <c:when test="${sessionScope.role=='user'}">
                                <a href="addToCart?id=${good.id}">
                                    <fmt:message key="label.add.to.cart" />
                                </a>
                            </c:when>

                            <c:when test="${sessionScope.role=='blocked user'}">
                                <a><fmt:message key="label.banned" /></a>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>

            </c:forEach>
        </table>
    </div>
</form>
</body>
</html>
