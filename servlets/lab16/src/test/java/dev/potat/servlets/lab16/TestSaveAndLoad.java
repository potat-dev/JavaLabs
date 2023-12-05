package dev.potat.servlets.lab16;

import lombok.SneakyThrows;

public class TestSaveAndLoad {
    @SneakyThrows
    public static void main(String[] args) {
        ListStore store = ListStore.getInstance("test.yml");
        store.load();
        System.out.println(store);
        store.save();
    }
}
