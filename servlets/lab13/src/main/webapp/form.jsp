<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Update Contact</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Add or update contact</h1>--%>
<%--<form action="${pageContext.request.contextPath}/contacts/update" method="GET">--%>
<%--    <label>--%>
<%--        Name:--%>
<%--        <input type="text" name="name">--%>
<%--    </label><br>--%>
<%--    <label>--%>
<%--        Phone:--%>
<%--        <input type="text" name="phone">--%>
<%--    </label><br>--%>
<%--    <input type="submit" value="Submit">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8"/>--%>
<%--    <title>Update Contact</title>--%>
<%--    <script src="https://cdn.tailwindcss.com"></script>--%>
<%--</head>--%>
<%--<body class="bg-slate-900 text-slate-100">--%>
<%--<div class="container mx-auto p-4">--%>

<%--    <h1 class="text-3xl font-bold text-slate-100 text-center my-4">--%>
<%--        Add or Update Contact--%>
<%--    </h1>--%>

<%--    <form action="${pageContext.request.contextPath}/contacts/update" method="GET" class="mt-4">--%>
<%--        &lt;%&ndash;        <div class="flex flex-col">&ndash;%&gt;--%>
<%--        &lt;%&ndash;        <div class="grid grid-cols-2 gap-4">&ndash;%&gt;--%>
<%--        <label class="text-slate-100">--%>
<%--            Name:--%>
<%--            <input type="text" name="name" class="bg-slate-800 text-slate-100 p-2 rounded-md">--%>
<%--        </label>--%>

<%--        <label class="text-slate-100">--%>
<%--            Phone:--%>
<%--            <input type="text" name="phone" class="bg-slate-800 text-slate-100 p-2 rounded-md">--%>
<%--        </label>--%>
<%--        &lt;%&ndash;        </div>&ndash;%&gt;--%>

<%--        <input type="submit" value="Submit"--%>
<%--               class="mt-4 bg-blue-500 text-white font-bold text-center py-2 px-4 rounded-md">--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8"/>--%>
<%--    <title>Update Contact</title>--%>
<%--    <script src="https://cdn.tailwindcss.com"></script>--%>
<%--</head>--%>
<%--<body class="bg-slate-900 text-slate-100">--%>
<%--<div class="container mx-auto max-w-2xl p-4">--%>
<%--    <div class="bg-slate-800 rounded-lg shadow-md p-4">--%>
<%--        <h1 class="text-3xl font-bold text-slate-100 text-center mb-4">Add or Update Contact</h1>--%>
<%--        <form action="${pageContext.request.contextPath}/contacts/update" method="GET">--%>
<%--            <div class="mb-4">--%>
<%--                <label for="name" class="text-slate-100 block">Name:</label>--%>
<%--                <input type="text" id="name" name="name" class="bg-slate-700 text-slate-100 p-2 w-full rounded-md">--%>
<%--            </div>--%>
<%--            <div class="mb-4">--%>
<%--                <label for="phone" class="text-slate-100 block">Phone:</label>--%>
<%--                <input type="text" id="phone" name="phone" class="bg-slate-700 text-slate-100 p-2 w-full rounded-md">--%>
<%--            </div>--%>
<%--            <button type="submit" class="bg-blue-500 text-white font-bold text-center py-2 px-4 rounded-md w-full">--%>
<%--                Submit--%>
<%--            </button>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


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
    <h1 class="text-3xl font-bold text-slate-100 text-center my-4">Add or Update Contact</h1>
    <div class="bg-slate-800 rounded-lg shadow-md p-4 mt-8">
        <form action="${pageContext.request.contextPath}/contacts/update" method="POST" class="m-0">
            <div class="mb-4">
                <label for="name" class="text-slate-100 block">Name:</label>
                <input type="text" id="name" name="name" class="bg-slate-700 text-slate-100 p-2 w-full rounded-md">
            </div>
            <div class="mb-4">
                <label for="phone" class="text-slate-100 block">Phone:</label>
                <input type="text" id="phone" name="phone" class="bg-slate-700 text-slate-100 p-2 w-full rounded-md">
            </div>
            <label for="submit" class="text-slate-500 block my-2">
                If contact already exists it will be updated with the new number.
                Otherwise, the new contact will be created.
            </label>
            <button type="submit" id="submit"
                    class="bg-blue-500 text-white font-bold text-center mt-4 py-2 px-4 rounded-md w-full">
                Submit
            </button>
        </form>
    </div>
</div>
</body>
</html>
