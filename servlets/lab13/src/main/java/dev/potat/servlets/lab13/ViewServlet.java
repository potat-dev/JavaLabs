package dev.potat.servlets.lab13;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.SneakyThrows;

@WebServlet(name = "ViewAddressBook", value = "/contacts")
public class ViewServlet extends HttpServlet {
    private AddressBookStore store;

    @SneakyThrows
    public void init() {
        store = AddressBookStore.getInstance("db.json");
        store.load();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("contacts", store.getAddressBook().getContacts());
        getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
    }

    public void destroy() {
    }
}