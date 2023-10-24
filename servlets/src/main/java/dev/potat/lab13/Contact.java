package dev.potat.lab13;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
public class Contact {
    private final String name;
    private final List<String> phones = new ArrayList<>();

    public void addPhone(@NonNull String phone) {
        phones.add(phone);
    }
}
