package dev.potat.servlets.lab15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserStore userStore;

    public void init() {
        userStore = UserStore.getInstance("users.json");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    // Method to handle POST request from the form
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + " - " + password);

        String status, message;

        if (userStore.check(new User(username, password))) {
            HttpSession session = request.getSession(false);
            if (session != null) session.setAttribute("username", username);
            // hehe
            status = "Login success!";
            message = "Now you can post your ads :)";
        } else {
            // not hehe
            status = "Login failed!";
            message = "Incorrect username or password :(";
        }

        request.setAttribute("status", status);
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
