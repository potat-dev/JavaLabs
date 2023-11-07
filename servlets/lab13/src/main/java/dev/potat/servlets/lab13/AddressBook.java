package dev.potat.servlets.lab13;

import lombok.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
public class AddressBook {
    private static final Pattern nameRegex = Pattern.compile("^[a-zA-Z]{2,}([-_ .][a-zA-Z]{2,}){0,2}$");
    private static final Pattern phoneRegex = Pattern.compile("^\\+?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$");

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
        if (name.isBlank() || name.length() > 50 || phone.isBlank() || phone.length() > 16) return false;
        return nameRegex.matcher(name).matches() && phoneRegex.matcher(phone).matches();
    }

    public enum UpdateStatus {
        CREATED, UPDATED, DECLINED
    }
}
