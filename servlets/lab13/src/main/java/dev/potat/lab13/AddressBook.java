package dev.potat.lab13;

import lombok.*;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class AddressBook {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public Contact getContact(@NonNull String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void update(@NonNull String name, @NonNull String phone) {
        Contact contact = getContact(name);
        if (contact != null) {
            contact.addPhone(phone);
        } else {
            addContact(new Contact(name, phone));
        }
    }
}
