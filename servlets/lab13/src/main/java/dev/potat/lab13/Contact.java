package dev.potat.lab13;

import lombok.*;

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

    public void addPhone(@NonNull String phone) {
        phones.add(phone);
    }
}
