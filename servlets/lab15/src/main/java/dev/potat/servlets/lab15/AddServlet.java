package dev.potat.servlets.lab15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        getServletContext().getRequestDispatcher("/add.jsp").forward(request, response);
    }

    // Method to handle POST request from the form
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    }

    public void destroy() {
    }
}
