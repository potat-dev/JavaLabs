package dev.potat.servlets.lab13;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Contact {
    private String name;
    private List<String> phones = new ArrayList<>();

    public Contact(String name, String phone) {
        setName(name);
        addPhone(phone);
    }

    public void addPhone(String phone) {
        phones.add(phone);
    }

    public void removePhone(String phone) {
        phones.remove(phone);
    }
}
