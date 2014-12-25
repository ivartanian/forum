<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forum</title>
    <link rel="stylesheet" type="text/css" href="info.css"/>
</head>
<body>

<div>
    Current user: <c:out value="${sessionScope.user}"/>
</div>
<div>
  <a href="logoff">Logoff</a>
</div>
<hr>
<div>
    Users in: <c:out value="${applicationScope.users}"/>
</div>
<hr>
<div>

    <table>

        <tr>
            <td>Date</td>
            <td>Login</td>
            <td>Massage</td>
        </tr>

        <c:forEach var="item" items="${requestScope.top10}">
        <tr>
            <td>${item.date}</td>
            <td>${item.login}</td>
            <td>${item.massage}</td>
        </tr>
        </c:forEach>
    </table>

</div>

<form action="insertmessage" method="post" >
    <input name="text" type="text"/>
    <input value="send" type="submit"/>
</form>
<div>
    <a href="report">Report</a>
</div>


</body>
</html>
