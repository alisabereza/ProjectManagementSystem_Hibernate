<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Developers</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<table>
    <tbody>

    <c:if test="${not empty developers}">
        <table class="zui-table myform">
            <thead>
            <tr>
                <th>Developer Name</th>
                <th>Age</th>
                <th>Salary</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${developers}" var="developer">
                <tr>
                    <td>
                        <c:out value="${developer.name}"/>
                    </td>
                    <td>
                        <c:out value="${developer.age}"/>
                    </td>
                    <td>
                        <c:out value="${developer.salary}"/>
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