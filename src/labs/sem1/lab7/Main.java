package labs.sem1.lab7;

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

    try {
      // save to binary file and load to new object
      settings.saveToBinaryFile("src/labs/sem1/lab7/settings.bin");
      Settings settings2 = new Settings();
      settings2.loadFromBinaryFile("src/labs/sem1/lab7/settings.bin");
      System.out.println(settings2);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
