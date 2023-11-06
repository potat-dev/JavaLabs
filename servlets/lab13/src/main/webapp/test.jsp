<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 11/6/23
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>User Info</title>
</head>
<body>
<ul>
    <c:forEach var="user" items="${users}">
        <li><c:out value="${user}" /></li>
    </c:forEach>
</ul>
</body>
</html>