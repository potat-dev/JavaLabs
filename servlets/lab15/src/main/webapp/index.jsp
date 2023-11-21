<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Bulletin Board</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-900 text-slate-100">
<div class="container mx-auto max-w-xl p-4">
    <h1 class="text-3xl font-bold text-slate-100 text-center my-4">
        Hello World!
    </h1>
    <h3>
        This is just a homepage. Please go to the servlet
        <a href="${pageContext.request.contextPath}/contacts"
           class="text-blue-500 hover:underline">
            Address Book Servlet
        </a>
    </h3>
</div>
</body>
</html>
