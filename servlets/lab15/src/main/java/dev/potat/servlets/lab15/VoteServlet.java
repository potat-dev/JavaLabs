package dev.potat.servlets.lab15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet(name = "VoteServlet", value = "/vote")
public class VoteServlet extends HttpServlet {
    private AdBoardStore store;

    @SneakyThrows
    public void init() {
        store = AdBoardStore.getInstance("db.json");
    }

    // Method to handle POST request from the form
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("user") != null;

        if (!loggedIn) {
            returnUnauthorized(request, response);
        } else {
            String id = request.getParameter("id");
            String action = request.getParameter("action");
            String userId = (String) session.getAttribute("user");

            Advertisement ad = store.getById(id);
            ad.vote(userId, action);

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(action + ' ' + ad.getScore());
            store.save();
        }
    }

    public void returnUnauthorized(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("status", "Access denied!");
        request.setAttribute("message", "Authorization is required to access this page!");
        getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
