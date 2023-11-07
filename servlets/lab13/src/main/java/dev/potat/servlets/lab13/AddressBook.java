package dev.potat.servlets.lab13;

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

    public UpdateStatus update(String name, String phone) {
        if (!safetyCheck(name, phone)) return UpdateStatus.DECLINED;
        Contact contact = getContact(name);
        if (contact != null) {
            contact.addPhone(phone);
            return UpdateStatus.UPDATED;
        } else {
            addContact(new Contact(name, phone));
            return UpdateStatus.CREATED;
        }
    }

    private boolean safetyCheck(String name, String phone) {
        return true;
    }

    public static enum UpdateStatus {
        CREATED,
        UPDATED,
        DECLINED
    }
}
