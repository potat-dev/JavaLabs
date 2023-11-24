package dev.potat.servlets.lab15;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Synchronized;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@ToString
public class AdBoardStore {
    private static volatile AdBoardStore instance;

    private AdBoard board = new AdBoard();

    @ToString.Exclude
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ToString.Exclude
    private final File database;

    public AdBoardStore(String databasePath) {
        this.database = new File(databasePath);
        try {
            if (this.database.createNewFile()) {
                System.out.println("Database created");
            } else {
                load();
                System.out.println("Database loaded");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AdBoardStore getInstance(String dbPath) {
        if (instance == null) {
            synchronized (AdBoard.class) {
                if (instance == null) {
                    instance = new AdBoardStore(dbPath);
                }
            }
        }
        return instance;
    }

    @Synchronized
    public void load() throws IOException {
        if (database.length() == 0) return;
        board = objectMapper.readValue(database, AdBoard.class);
    }

    @Synchronized
    public void save() throws IOException {
        objectMapper.writeValue(database, board);
    }

    @Synchronized
    public ArrayList<Advertisement> getAds() {
        return board.getAds();
    }

    @Synchronized
    public void add(Advertisement ad) {
        board.add(ad);
    }

    @Synchronized
    public Advertisement getById(String id) {
        return board.getById(id);
    }
}
