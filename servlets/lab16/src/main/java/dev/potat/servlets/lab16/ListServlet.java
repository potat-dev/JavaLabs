package dev.potat.servlets.lab16;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ListServlet", value = "/")
public class ListServlet extends HttpServlet {
    private ListStore store;

    public void init() {
        store = ListStore.getInstance("db.yaml");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("list", store.getItems());
        getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
    }

    public void destroy() {
    }
}