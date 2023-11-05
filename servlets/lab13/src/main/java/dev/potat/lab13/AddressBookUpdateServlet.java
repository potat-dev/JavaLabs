package dev.potat.lab13;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateAddressBook", value = "/contacts/update")
public class AddressBookUpdateServlet extends HttpServlet {
    private AddressBookStore store;

    @SneakyThrows
    public void init() {
        store = AddressBookStore.getInstance("db.json");
        store.load();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        store.update(name, phone);
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + store.getAddressBook() + "</h1>");
        out.println("<h2>" + store.getContact(name) + "</h2>");
        out.println("</body></html>");

        store.save();
    }

    public void destroy() {
    }
}