<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Delete Contact</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-900 text-slate-100">
<div class="container mx-auto max-w-xl p-4">
    <h1 class="text-3xl font-bold text-slate-100 text-center my-4">Remove Contact or Phone</h1>

    <div class="bg-slate-800 rounded-lg shadow-md p-4 mt-8">
        <form action="${pageContext.request.contextPath}/contacts/delete" method="POST" class="m-0">
            <div class="mb-4">
                <label for="name" class="text-slate-100 block">Name:</label>
                <input type="text" id="name" name="name"
                       class="bg-slate-700 text-slate-100 p-2 w-full rounded-md"
                       required>
            </div>

            <div class="mb-4">
                <label for="phone" class="text-slate-100 block">Phone:</label>
                <input type="tel" id="phone" name="phone"
                       class="bg-slate-700 text-slate-100 p-2 w-full rounded-md">
            </div>

            <label for="submit" class="text-slate-500 block my-2">
                If phone entered, it will be removed from contact.
                Otherwise, contact will be removed completely.
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
