<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Public Ad Board</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body class="bg-slate-900 text-slate-100">
<div class="container mx-auto max-w-6xl p-4">
    <h1 class="text-3xl font-bold text-slate-100 text-center my-4">
        Public Ad Board
    </h1>

    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-8">
        <c:forEach var="ad" items="${ads}">
            <div class="flex flex-col h-full border rounded-lg shadow bg-gray-800 border-gray-700">
                <c:if test="${ad.getImage() != ''}">
                    <a href="${ad.getImage()}" target="_blank">
                        <img class="rounded-t-lg w-full aspect-[4/3]" src="${ad.getImage()}" alt="image"/>
                    </a>
                </c:if>
                <div class="flex-grow flex flex-col h-full p-5">
                    <h5 class="mb-2 text-2xl font-bold tracking-tight text-white">${ad.getTitle()}</h5>
                    <p class="mb-3 font-normal text-gray-400">${ad.getDesc()}</p>

                    <div class="flex-grow h-full flex flex-col justify-items-end">
                        <p class="mt-auto mb-3 font-normal text-gray-400">
                            Posted by ${ad.getUser()} • ${ad.getDate()}
                        </p>
                        <c:if test="${login == true}">
                            <div class="inline-flex rounded-md shadow-sm" role="group">
                                <button type="button" id="like-${ad.getId()}"
                                        class="inline-flex items-center px-4 py-2 text-sm font-medium border rounded-s-lg focus:z-10 bg-gray-700 border-gray-600 text-white hover:text-white hover:bg-gray-600 focus:text-white">
                                        ${ad.likedBy(user) ? "Liked" : "Like"} ${ad.getScore()}
                                </button>
                                <button type="button" id="dislike-${ad.getId()}"
                                        class="inline-flex items-center px-4 py-2 text-sm font-medium border rounded-e-lg focus:z-10 bg-gray-700 border-gray-600 text-white hover:text-white hover:bg-gray-600 focus:text-white">
                                        ${ad.dislikedBy(user) ? "Disliked" : "Dislike"}
                                </button>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="gap-4 pb-8 pt-4 grid grid-flow-col justify-stretch md:flex md:justify-center">
        <c:if test="${login == true}">
            <a href="${pageContext.request.contextPath}/add"
               class="block rounded-md bg-blue-500 text-white font-bold text-center mt-4 py-2 px-4">
                New advertisement
            </a>
            <a href="${pageContext.request.contextPath}/logout"
               class="block rounded-md bg-red-500 text-white font-bold text-center mt-4 py-2 px-4">
                Logged in as ${user}, click to logout
            </a>
        </c:if>
        <c:if test="${login == false}">
            <a href="${pageContext.request.contextPath}/login"
               class="block rounded-md bg-red-500 text-white font-bold text-center mt-4 py-2 px-4">
                Login to post ads!
            </a>
        </c:if>
    </div>
</div>


<c:if test="${login == true}">
    <script>
        $('[id^="like-"]').on('click', function () {
            const postId = this.id.substring(this.id.indexOf('-') + 1)  // извлекаем id поста
            const isLiked = $(this).text().includes('Liked');
            sendVote(postId, isLiked ? 'remove' : 'like');
        });

        $('[id^="dislike-"]').on('click', function () {
            const postId = this.id.substring(this.id.indexOf('-') + 1)  // извлекаем id поста
            const isDisliked = $(this).text().includes('Disliked');
            sendVote(postId, isDisliked ? 'remove' : 'dislike');
        });

        function sendVote(id, action) {
            $.ajax({
                type: 'POST',
                url: '${pageContext.request.contextPath}/vote',
                data: {id: id, action: action},
                success: function (data) {
                    try {
                        const state = data.split(' ')[0];
                        const score = data.split(' ')[1];
                        setButtonState(id, state, score);
                    } catch (e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        function setButtonState(id, state, score) {
            $('#like-' + id).text((state === 'like' ? 'Liked ' : 'Like ') + score);
            $('#dislike-' + id).text(state === 'dislike' ? 'Disliked' : 'Dislike');
        }
    </script>
</c:if>
</body>
</html>
