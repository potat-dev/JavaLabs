package dev.potat.lab13;

import lombok.NonNull;
import lombok.Synchronized;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AddressBook {
    private final Object readlock = new Object();
    private final List<Contact> contacts = new ArrayList<>();
    private final File database;

    public AddressBook(String databasePath) {
        this.database = new File(databasePath);
    }

    @Synchronized("readlock")
    public void load() {
        // load from text file
    }

    @Synchronized
    public void save() {
        // save to text file
    }

    @Synchronized("readlock")
    public List<Contact> getContacts() {
        return contacts;
    }

    @Synchronized("readlock")
    public Contact getContact(@NonNull String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    @Synchronized  // writelock
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Synchronized  // writelock
    public void update(@NonNull String name, @NonNull String phone) {
        Contact contact = getContact(name);
        if (contact != null) {
            contact.addPhone(phone);
        } else {
            contact = new Contact(name);
            contact.addPhone(phone);
            addContact(contact);
        }
    }
}
