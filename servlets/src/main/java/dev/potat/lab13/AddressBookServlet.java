package dev.potat.lab13;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddressBookServlet extends HttpServlet {
    private AddressBook addressBook;

    @Override
    public void init() throws ServletException {
        // Инициализация записной книжки и загрузка данных из файла
        addressBook = new AddressBook("contacts.json");
        loadAddressBookData();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Отобразить список контактов
        List<Contact> contacts = addressBook.getContacts();
        request.setAttribute("contacts", contacts);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addressbook.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Добавить нового контакта или телефон к существующему
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
                // Сохранить данные в файл
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

    // Здесь нужно добавить методы для сохранения и загрузки данных из файла

    private void loadAddressBookData() {
        // Загрузка данных из файла и инициализация записной книжки
        // Синхронизировать доступ к файлу
    }

    private void saveAddressBookData() {
        // Сохранение данных в файл
        // Синхронизировать доступ к файлу
    }
}
