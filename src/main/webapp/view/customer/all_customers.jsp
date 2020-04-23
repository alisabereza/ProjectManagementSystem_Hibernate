
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%@ page contentType="text/html;charset=UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <html lang="en-us">
        <head>
            <title>List of Customers</title>
            <style>
                <%@include file="/view/style.css" %>
            </style>
        </head>
<body>
<c:import url="/view/navibar.jsp"/>
<table>
    <tbody>

    <c:if test="${not empty customers}">
        <table class="zui-table myform">
            <thead>
            <tr>
                <th>Customer ID</th>
                <th>Customer Name</th>
                <th>Phone</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>
                        <c:out value="${customer.id}"/>
                    </td>
                    <td>
                        <c:out value="${customer.name}"/>
                    </td>
                    <td>
                        <c:out value="${customer.phone}"/>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    </tbody>
</table>

</body>
</html></title>
</head>
<body>

</body>
</html>
