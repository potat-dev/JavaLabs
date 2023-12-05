package dev.potat.servlets.lab16;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import lombok.Data;
import lombok.Synchronized;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListStore {
    private static volatile ListStore instance;

    private List<ListItem> items = new ArrayList<>();

    @ToString.Exclude
    private final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));

    @ToString.Exclude
    private final File database;

    public ListStore(String databasePath) {
        this.database = new File(databasePath);
        try {
            if (this.database.createNewFile()) {
                addDummyData();
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

    public static ListStore getInstance(String dbPath) {
        if (instance == null) {
            synchronized (ListStore.class) {
                if (instance == null) {
                    instance = new ListStore(dbPath);
                }
            }
        }
        return instance;
    }

    @Synchronized
    public void load() throws IOException {
        if (database.length() == 0) return;
        CollectionType itemsType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, ListItem.class);
        items = objectMapper.readValue(database, itemsType);
    }

    @Synchronized
    public void save() throws IOException {
        objectMapper.writeValue(database, items);
    }

    private void addDummyData() {
        items.add(new ListItem("Animals", List.of("Wolf", "Cow")));
        items.add(new ListItem("Birds", List.of("Chicken", "Eagle", "Parrot")));
        items.add(new ListItem("Fish", List.of("Salmon", "Tuna")));
        items.add(new ListItem("Insects", List.of("Ant", "Butterfly", "Spider")));
        items.add(new ListItem("Getz Family", List.of("Nastya", "Maksim", "Denis")));
    }
}
