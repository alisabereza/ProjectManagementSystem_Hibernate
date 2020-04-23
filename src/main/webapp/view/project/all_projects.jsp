<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en-us">
<head>
    <title>List of Projects</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<table>
    <tbody>

    <c:if test="${not empty projects}">
        <table class="zui-table myform">
            <thead>
            <tr>
                <th>ID</th>
                <th>Project Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Cost</th>
                <th>Company</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${projects}" var="project">
                <tr>
                    <td>
                        <c:out value="${project.id}"/>
                    </td>
                    <td>
                        <c:out value="${project.name}"/>
                    </td>
                    <td>
                        <c:out value="${project.start_date}"/>
                    </td>
                    <td>
                        <c:out value="${project.end_date}"/>
                    </td>
                    <td>
                        <c:out value="${project.cost}"/>
                    </td>
                    <td>
                        <c:out value="${project.company.name}"/>
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