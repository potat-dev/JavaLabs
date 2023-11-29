package dev.potat.servlets.lab15;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String username;
    private String password;  // хранить пароли в plaintext это круто!

    public User(String name) {
        this(name, name);
    }

    public User(String name, String pass) {
        this(NanoIdUtils.randomNanoId(), name, pass);
    }
}
