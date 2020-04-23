<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en-us">
<head>
    <title>Create developer</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navibar.jsp"/>
<form method="post" action="createProject">
    <table>
        <tbody>
        <tr>
            <td>
                <p>Name</p>
            </td>
            <td>

                <input type="text" name="name" tabindex="1"></td>
        </tr>
        <tr>
            <td>
                <p>Start Date</p>
            </td>
            <td>
                <input type="date" name="date" tabindex="2">
            </td>
        </tr>
        <tr>
            <td>
                <p>Company Name</p>
            </td>
            <td>
                <input type="text" name="companyName" tabindex="1"></td>
        </tr>
        <tr>
            <td>
                <p>Cost</p>
            </td>
            <td>
                <input type="number" name="cost" tabindex="2">
            </td>
        </tr>
        </tbody>
    </table>
    <button type="submit" class="button">Create</button>
</form>
<c:if test="${not empty errors}">
    <c:forEach items="${errors}" var="error">
        <p style="color: red">${error.field} ${error.error}</p><br>
    </c:forEach>
</c:if>
<c:if test="${not empty message}">
    <p style="color: darkslateblue">${message}</p><br>

</c:if>


</body>
</html>
