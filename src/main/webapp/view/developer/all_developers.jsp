<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en-us">
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
                <th>ID</th>
                <th>Developer Name</th>
                <th>Age</th>
                <th>Salary</th>
                <th>Company</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${developers}" var="developer">
                <tr>
                    <td>
                        <c:out value="${developer.id}"/>
                    </td>
                    <td>
                        <c:out value="${developer.name}"/>
                    </td>
                    <td>
                        <c:out value="${developer.age}"/>
                    </td>
                    <td>
                        <c:out value="${developer.salary}"/>
                    </td>
                    <td>
                        <c:out value="${developer.company.name}"/>
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