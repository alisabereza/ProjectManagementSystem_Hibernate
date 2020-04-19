<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Companies</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<table>
    <tbody>
    <c:if test="${not empty companies}">
        <table class="zui-table myform">
            <thead>
            <tr>
                <th>Company Name</th>
                <th>Start Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${companies}" var="company">
                <tr>
                    <td>
                        <c:out value="${company.name}"/>
                    </td>
                    <td>
                        <c:out value="${company.startDate}"/>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    </tbody>
</table>

</body>
</html>