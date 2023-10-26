package dev.potat.lab13;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "addressBook", value = "/book")
public class AddressBookServlet extends HttpServlet {
    private AddressBook addressBook;

    public void init() throws ServletException {
        addressBook = new AddressBook("contacts.json");
        loadAddressBookData();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Contact> contacts = addressBook.getContacts();
        request.setAttribute("contacts", contacts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addressbook.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("username");
        String phone = request.getParameter("phone");

        if (name != null && phone != null) {
            synchronized (addressBook) {
                Contact existingContact = findContactByName(name);
                if (existingContact != null) {
                    existingContact.addPhone(phone);
                } else {
                    Contact newContact = new Contact(name);
                    newContact.addPhone(phone);
                    addressBook.addContact(newContact);
                }
                saveAddressBookData();
            }
        }

        response.sendRedirect(request.getContextPath());
    }

    private Contact findContactByName(String name) {
        List<Contact> contacts = addressBook.getContacts();
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }


    private void loadAddressBookData() {
    }

    private void saveAddressBookData() {
    }
}
