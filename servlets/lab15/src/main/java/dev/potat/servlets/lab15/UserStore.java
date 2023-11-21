package dev.potat.servlets.lab15;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.Synchronized;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserStore {
    private static volatile UserStore instance;

    private ArrayList<User> users = new ArrayList<>();

    @ToString.Exclude
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ToString.Exclude
    private final File database;

    public UserStore(String databasePath) {
        this.database = new File(databasePath);
        try {
            if (this.database.createNewFile()) {
                addDummyUsers();
                save();
                System.out.println("Database created");
            } else {
                load();
                System.out.println("Database loaded");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserStore getInstance(String dbPath) {
        if (instance == null) {
            synchronized (UserStore.class) {
                if (instance == null) {
                    instance = new UserStore(dbPath);
                }
            }
        }
        return instance;
    }

    @Synchronized
    public void load() throws IOException {
        if (database.length() == 0) return;
        CollectionType usersType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, User.class);
        users = objectMapper.readValue(database, usersType);
    }

    @Synchronized
    public void save() throws IOException {
        objectMapper.writeValue(database, users);
    }

    @Synchronized
    public boolean check(User input) {
        for (User user : users) {
            if (user.equals(input)) return true;
        }
        return false;
    }

    private void addDummyUsers() {
        users.add(new User("root"));
        users.add(new User("demo"));
        users.add(new User("maksim"));
        users.add(new User("nastya"));
        users.add(new User("user", "abcd"));
        users.add(new User("potat", "1234"));
    }
}
