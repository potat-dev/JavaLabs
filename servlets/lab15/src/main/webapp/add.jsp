<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>New advertisement</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-900 text-slate-100">
<div class="container mx-auto max-w-xl p-4">
    <h1 class="text-3xl font-bold text-slate-100 my-4">New advertisement</h1>

    <div class="bg-slate-800 rounded-lg shadow-md p-4 mt-8">
        <form action="${pageContext.request.contextPath}/add" method="POST">
            <div class="grid gap-6 mb-6 md:grid-cols-2">
                <div>
                    <label for="title" class="block mb-2 text-sm font-medium text-white">
                        Advertisement title
                    </label>
                    <input type="text" id="title" name="title"
                           class="border text-sm rounded-lg block w-full p-2.5 bg-gray-700 border-gray-600 placeholder-gray-400 text-white focus:border-blue-500"
                           placeholder="Enter advertisement title" required>
                </div>
                <div>
                    <label for="image" class="block mb-2 text-sm font-medium text-white">
                        Image URL (optional)
                    </label>
                    <input type="url" id="image" name="image"
                           class="border text-sm rounded-lg block w-full p-2.5 bg-gray-700 border-gray-600 placeholder-gray-400 text-white focus:border-blue-500"
                           placeholder="https://link.to/your/image.jpg">
                </div>
            </div>
            <div class="mb-6">
                <label for="desc" class="block mb-2 text-sm font-medium text-white">
                    Your message
                </label>
                <textarea id="desc" name="desc" rows="4"
                          class="block p-2.5 w-full text-sm rounded-lg border bg-gray-700 border-gray-600 placeholder-gray-400 text-white focus:border-blue-500"
                          placeholder="Write your offers here..."></textarea>
            </div>
            <button type="submit"
                    class="text-white focus:outline-none font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center bg-blue-600 hover:bg-blue-700">
                Submit advertisement
            </button>
        </form>
    </div>

</div>
</body>
</html>
