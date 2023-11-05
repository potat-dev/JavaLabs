import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import dev.potat.lab13.*;

class AddressBookStoreSaveAndLoadTest {
    @Test
    void saveAndLoad() throws IOException {
        AddressBookStore store = new AddressBookStore("db.json");
        store.addContact(new Contact("Den", "88005553535"));
        store.addContact(new Contact("Maxim", "+78885553535"));
        store.addContact(new Contact("Nastya", "79991234567"));
        store.save();
        System.out.println("Saved to file!");

        FileReader fr = new FileReader("db.json");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();

        AddressBookStore newStore = new AddressBookStore("db.json");
        store.load();
        System.out.println(store.getContact("Nastya"));
        System.out.println(newStore);
    }
}