package dev.potat.lab13;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.Synchronized;
import lombok.ToString;

import java.io.File;
import java.io.IOException;

@ToString
public class AddressBookStore {
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

    @Synchronized
    public void load() throws IOException {
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
    public void update(@NonNull String name, @NonNull String phone) {
        addressBook.update(name, phone);
    }
}
