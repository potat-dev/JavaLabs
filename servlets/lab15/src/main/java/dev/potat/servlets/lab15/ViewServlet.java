package dev.potat.servlets.lab15;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet(name = "ViewAddressBook", value = "/contacts")
public class ViewServlet extends HttpServlet {
    private BulletinBoardStore store;

    @SneakyThrows
    public void init() {
        store = BulletinBoardStore.getInstance("db.json");
        store.load();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("ads", store.getAds());
        getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
    }

    public void destroy() {
    }
}