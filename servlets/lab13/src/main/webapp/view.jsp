<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html class="dark">
<head>
    <meta charset="UTF-8"/>
    <title>Address Book</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-slate-900 text-slate-100">
<div class="container mx-auto max-w-6xl p-4">

    <h1 class="text-3xl font-bold text-slate-100 text-center my-4">
        Your Address Book
    </h1>

    <div class="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4 mt-8">
        <c:forEach var="contact" items="${contacts}">
            <div class="bg-slate-800 rounded-lg p-4 shadow-md">
                <p class="text-xl font-semibold">${contact.getName()}</p>
                <ul class="list-disc ml-5 mt-2">
                    <c:forEach var="phone" items="${contact.getPhones()}">
                        <li class="text-slate-400">${phone}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>
    </div>

    <div class="grid grid-flow-col justify-stretch md:flex md:justify-center">
        <a href="${pageContext.request.contextPath}/add_contact"
           class="block rounded-md bg-blue-500 text-white font-bold text-center mt-4 py-2 px-4">
            Add New Contact
        </a>
    </div>

</div>
</body>
</html>
