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
        boolean loggedIn = session != null && session.getAttribute("name") != null;

        if (!loggedIn) {
            returnUnauthorized(request, response);
        } else {
            String id = request.getParameter("id");
            String action = request.getParameter("action");

            store.getById(id).vote(action);

            request.setAttribute("status", "Vote was added successfully!");
            request.setAttribute("message", "Go to main page and check!");
            getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);
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
