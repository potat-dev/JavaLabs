package dev.potat.servlets.lab13;

import dev.potat.servlets.lab13.AddressBook.UpdateStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateAddressBook", value = "/contacts/update")
public class UpdateServlet extends HttpServlet {
    private AddressBookStore store;

    @SneakyThrows
    public void init() {
        store = AddressBookStore.getInstance("db.json");
        store.load();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/form.jsp").forward(request, response);
    }

    // Method to handle form POST request.
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        UpdateStatus result = store.update(name, phone);

        String status, message;
        switch (result) {
            case CREATED:
                status = "Successfully created!";
                message = "Contact was successfully created";
                break;
            case UPDATED:
                status = "Added new phone!";
                message = "Contact was successfully updated";
                break;
            case DECLINED:
                status = "Contact was declined";
                message = "Contact was declined for some reason";
                break;
            default:
                status = "Something goes wrong";
                message = "And I don't know what is going on";
        }

        request.setAttribute("status", status);
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/info.jsp").forward(request, response);
        store.save();
    }

    public void destroy() {
    }
}
