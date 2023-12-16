<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>List</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-900 text-slate-100">
<div class="container mx-auto max-w-6xl p-4">
    <h1 class="text-3xl font-bold text-slate-100 my-4">
        List of some things
    </h1>
    <hr class="h-px my-8 border-0 bg-gray-700">

    <button onclick="toggleAll()" type="button"
            class="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700">
        Toggle
    </button>

    <button onclick="deleteAllSubElements()" type="button"
            class="text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700">
        Clear
    </button>

    <ol class="mt-4 space-y-4 text-gray-300 list-decimal list-inside">
        <c:forEach var="item" items="${list}" varStatus="loop">
            <li>${item.getName()}
                <a href="#" id="toggle-${loop.index}" class="text-md inline-block transition-all"
                   onclick="toggle(${loop.index})">
                        ${"▶"}
                </a>
                <ul id="list-${loop.index}" class="hidden text-gray-500 ps-5 mt-2 space-y-1 list-disc list-inside">
                    <c:forEach var="i" items="${item.getItems()}">
                        <li>${i}</li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ol>

</div>

<script>
    let showAll = false;

    function toggle(param) {
        const button = document.getElementById('toggle-' + param);
        const list = document.getElementById('list-' + param);
        button.classList.toggle("rotate-90");
        list.classList.toggle("hidden");
    }

    function toggleAll() {
        showAll = !showAll;
        console.log("Set to " + showAll)
        const buttons = document.querySelectorAll('[id^="toggle-"]');
        const lists = document.querySelectorAll('[id^="list-"]');

        buttons.forEach((button, index) => {
            button.classList.toggle("rotate-90", showAll)
        });

        lists.forEach((list) => {
            list.classList.toggle("hidden", !showAll)
        });
    }

    function deleteAllSubElements() {
        const subLists = document.querySelectorAll('[id^="list-"]');

        subLists.forEach((list) => {
            list.innerHTML = ""; // Clear the content of each sub-list
        });
    }
</script>

</body>
</html>
