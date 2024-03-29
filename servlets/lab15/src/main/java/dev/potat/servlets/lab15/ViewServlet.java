package dev.potat.servlets.lab15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet(name = "ViewServlet", value = "/")
public class ViewServlet extends HttpServlet {
    private AdBoardStore store;

    @SneakyThrows
    public void init() {
        store = AdBoardStore.getInstance("db.json");
        store.load();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("ads", store.getAds());
        HttpSession session = request.getSession(false);  // false = do not create new session
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        request.setAttribute("login", loggedIn);
        if (loggedIn) {
            String username = (String) session.getAttribute("user");
            request.setAttribute("user", username);
        }
        getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
    }

    public void destroy() {
    }
}