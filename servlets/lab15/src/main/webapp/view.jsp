<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Public Ad Board</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-900 text-slate-100">
<div class="container mx-auto max-w-6xl p-4">
    <h1 class="text-3xl font-bold text-slate-100 text-center my-4">
        Public Ad Board
    </h1>

    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-8">
        <c:forEach var="ad" items="${ads}">
            <div class="bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                <c:if test="${ad.getImage() != ''}">
                    <a href="${ad.getImage()}" target="_blank">
                        <img class="rounded-t-lg w-full aspect-[4/3]" src="${ad.getImage()}" alt="image"/>
                    </a>
                </c:if>
                <div class="p-5">
                    <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">${ad.getTitle()}</h5>
                    <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">${ad.getDesc()}</p>
                </div>
            </div>

        </c:forEach>
    </div>

    <div class="gap-4 pb-8 grid grid-flow-col justify-stretch md:flex md:justify-center">
        <c:if test="${login == true}">
            <a href="${pageContext.request.contextPath}/add"
               class="block rounded-md bg-blue-500 text-white font-bold text-center mt-4 py-2 px-4">
                New advertisement
            </a>
            <a href="${pageContext.request.contextPath}/logout"
               class="block rounded-md bg-red-500 text-white font-bold text-center mt-4 py-2 px-4">
                Logged in! Click to Logout
            </a>
        </c:if><c:if test="${login == false}">
        <a href="${pageContext.request.contextPath}/login"
           class="block rounded-md bg-red-500 text-white font-bold text-center mt-4 py-2 px-4">
            Login to post Ads!
        </a>
    </c:if>
    </div>
</div>
</body>
</html>
