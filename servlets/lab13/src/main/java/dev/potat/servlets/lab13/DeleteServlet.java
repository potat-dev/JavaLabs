package dev.potat.servlets.lab13;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

@WebServlet(name = "DeleteContact", value = "/contacts/delete")
public class DeleteServlet extends HttpServlet {
    private AddressBookStore store;

    @SneakyThrows
    public void init() {
        store = AddressBookStore.getInstance("db.json");
        store.load();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/form_delete.jsp").forward(request, response);
    }

    // Method to handle POST request from the form
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        AddressBook.UpdateStatus result = store.remove(name, phone);

        String status, message;
        switch (result) {
            case PHONE_REMOVED:
                status = "Phone removed!";
                message = "Phone was successfully removed";
                break;
            case CONTACT_REMOVED:
                status = "Contact removed!";
                message = "Contact was successfully removed";
                break;
            case NOT_EXIST:
                status = "Contact does not exist";
                message = "You have entered the wrong contact info";
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