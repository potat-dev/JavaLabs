package labs.sem1.lab7;

// Реализуйте класс для хранения настроек Settings, в котором хранятся пары
// (имя параметра, значение). Имя параметра задается строкой, а значение - целым числом.
// Реализация должна использовать класс HashMap.

// В классе Settings должны быть определены:
// equals()
// toString()
// put(String, int)
// int get(String)
// delete(String)
// loadFromBinaryFile(String filename)
// saveToBinaryFile(String filename)
// loadFromTextFile(String filename)
// saveToTextFile(String filename)

import java.io.*;

public class Main {
  public static void main(String[] args) {
    Settings settings = new Settings();

    settings.put("a", 1);
    settings.put("b", 2);
    settings.put("c", 3);
    settings.put("d", 4);
    settings.put("answer", 42);
    settings.put("number", 618);

    System.out.println(settings);

    settings.delete("d");
    settings.put("kek", 7);

    System.out.println(settings);
    System.out.print("settings.get(\"answer\") = ");
    System.out.println(settings.get("answer"));

    String basePath = "src/labs/sem1/lab7/";

    try {
      // save to binary file and load to new object
      settings.saveToBinaryFile(basePath + "settings.bin");
      Settings settings2 = new Settings();
      settings2.loadFromBinaryFile(basePath + "settings.bin");
      System.out.print("Read from binary file: ");
      System.out.println(settings2);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    try {
      // save to text file and load to new object
      settings.saveToTextFile(basePath + "settings.txt");
      Settings settings3 = new Settings();
      settings3.loadFromTextFile(basePath + "settings.txt");
      System.out.print("Read from text file: ");
      System.out.println(settings3);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
