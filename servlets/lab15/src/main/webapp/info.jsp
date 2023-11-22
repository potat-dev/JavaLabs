<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Update Contact</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-900 text-slate-100">
<div class="container mx-auto max-w-xl p-4">
    <h1 class="text-3xl font-bold text-slate-100 text-center my-4">${status}</h1>
    <h3 class="text-xl font-semibold text-slate-400 text-center my-4">${message}</h3>
    <p class="text-center">
        <a href="${pageContext.request.contextPath}/"
           class="text-blue-500 hover:underline">Homepage</a>
        ${" • "}
        <a href="${pageContext.request.contextPath}/add"
           class="text-blue-500 hover:underline">New advertisement</a>
    </p>
</div>
</body>
</html>
