package dev.potat.servlets.lab13;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.SneakyThrows;

@WebServlet(name = "ViewAddressBook", value = "/contacts")
public class AddressBookViewServlet extends HttpServlet {
    private AddressBookStore store;

    @SneakyThrows
    public void init() {
        store = AddressBookStore.getInstance("db.json");
        store.load();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        // PrintWriter out = response.getWriter();
        // out.println("<html><body>");
        // out.println("<h1>" + store.getAddressBook() + "</h1>");
        // out.println("</body></html>");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "My Address Book" + "</h1>");
        out.println("<ul>");
        for (Contact contact : store.getAddressBook().getContacts()) {
            out.println("<li>" + contact.getName() + "<ul>");
            for (String phone : contact.getPhones()) {
                out.println("<li>" + phone + "</li>");
            }
            out.println("</ul></li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}