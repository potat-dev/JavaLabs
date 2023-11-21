package dev.potat.servlets.lab15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet(name = "AddServlet", value = "/add")
public class AddServlet extends HttpServlet {
    private AdBoardStore store;

    @SneakyThrows
    public void init() {
        store = AdBoardStore.getInstance("db.json");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);  // false = do not create new session

        if (session != null) {
            getServletContext().getRequestDispatcher("/add.jsp").forward(request, response);
        } else {
            returnUnauthorized(request, response);
        }
    }

    // Method to handle POST request from the form
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            String user = (String) session.getAttribute("username");
            String title = request.getParameter("title");
            String image = request.getParameter("image");
            String desc = request.getParameter("desc");

            store.add(new Advertisement(title, image, desc, user));

            request.setAttribute("status", "Ad was added successfully!");
            request.setAttribute("message", "Go to main page and check!");
            getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);
        } else {
            returnUnauthorized(request, response);
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
