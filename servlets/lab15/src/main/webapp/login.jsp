<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Login</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-900 text-slate-100">
<div class="container mx-auto max-w-xl p-4">
    <h1 class="text-3xl font-bold text-slate-100 my-4">Login to this awesome site!</h1>

    <div class="bg-slate-800 rounded-lg shadow-md p-4 mt-8">
        <form action="${pageContext.request.contextPath}/login" method="POST">
            <div class="grid gap-6 mb-6 md:grid-cols-2">
                <div>
                    <label for="username" class="block mb-2 text-sm font-medium text-white">
                        Username
                    </label>
                    <input type="text" id="username" name="username"
                           class="border text-sm rounded-lg block w-full p-2.5 bg-gray-700 border-gray-600 placeholder-gray-400 text-white focus:border-blue-500"
                           placeholder="Enter username" required>
                </div>
                <div>
                    <label for="password" class="block mb-2 text-sm font-medium text-white">
                        Password
                    </label>
                    <input type="password" id="password" name="password"
                           class="border text-sm rounded-lg block w-full p-2.5 bg-gray-700 border-gray-600 placeholder-gray-400 text-white focus:border-blue-500"
                           placeholder="•••••••••" required>
                </div>
            </div>
            <button type="submit"
                    class="text-white focus:outline-none font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center bg-blue-600 hover:bg-blue-700">
                Login now!
            </button>
        </form>
    </div>

</div>
</body>
</html>
