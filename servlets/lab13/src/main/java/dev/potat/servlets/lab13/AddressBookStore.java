package dev.potat.servlets.lab13;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.potat.servlets.lab13.AddressBook.UpdateStatus;
import lombok.*;

import java.io.File;
import java.io.IOException;

@ToString
public class AddressBookStore {
    private static volatile AddressBookStore instance;

    private AddressBook addressBook = new AddressBook();

    @ToString.Exclude
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ToString.Exclude
    private final File database;

    public AddressBookStore(String databasePath) {
        this.database = new File(databasePath);
        try {
            this.database.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AddressBookStore getInstance(String databasePath) {
        if (instance == null) {
            synchronized (AddressBookStore.class) {
                if (instance == null) {
                    instance = new AddressBookStore(databasePath);
                }
            }
        }
        return instance;
    }


    @Synchronized
    public void load() throws IOException {
        if (database.length() == 0) return;
        addressBook = objectMapper.readValue(database, AddressBook.class);
    }

    @Synchronized
    public void save() throws IOException {
        objectMapper.writeValue(database, addressBook);
    }

    @Synchronized
    public AddressBook getAddressBook() {
        return addressBook;
    }

    @Synchronized
    public Contact getContact(@NonNull String name) {
        return addressBook.getContact(name);
    }

    @Synchronized
    public void addContact(Contact contact) {
        addressBook.addContact(contact);
    }

    @Synchronized
    public UpdateStatus update(@NonNull String name, @NonNull String phone) {
        return addressBook.update(name, phone);
    }
}
