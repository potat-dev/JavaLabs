package labs.sem1.lab7;

import java.io.*;
import java.util.HashMap;

public class Settings implements Serializable {
  // используем HashMap для хранения настроек
  private HashMap<String, Integer> settings;

  public Settings() {
    settings = new HashMap<String, Integer>();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    Settings settings = (Settings) obj;
    return settings.settings.equals(this.settings);
  }

  @Override
  public String toString() {
    return "Settings " + settings.toString();
  }

  public void put(String name, int value) {
    settings.put(name, value);
  }

  public int get(String name) {
    return settings.get(name);
  }

  public void delete(String name) {
    settings.remove(name);
  }

  public void saveToBinaryFile(String filename) throws IOException {
    try {
      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
      out.writeObject(this);
      out.flush();
      out.close();
    } catch (Exception e) {
      throw new IOException("Error while saving to binary file: " + e.getMessage());
    }
  }

  public void loadFromBinaryFile(String filename) throws IOException {
    try {
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
      Settings settings = (Settings) in.readObject();
      in.close();
      this.settings = settings.settings;
    } catch (Exception e) {
      throw new IOException("Error while loading from binary file: " + e.getMessage());
    }
  }

  public void saveToTextFile(String filename) throws IOException {
    try {
      PrintWriter out = new PrintWriter(new FileWriter(filename));
      out.println(this);
      out.flush();
      out.close();
    } catch (Exception e) {
      throw new IOException("Error while saving to text file: " + e.getMessage());
    }
  }

  public void loadFromTextFile(String filename) throws IOException {
    try {
      BufferedReader in = new BufferedReader(new FileReader(filename));
      String line = in.readLine();
      in.close();
      this.settings = new HashMap<String, Integer>();
      String[] pairs = line.substring(10, line.length() - 1).split(", ");
      for (String pair : pairs) {
        String[] keyValue = pair.split("=");
        this.settings.put(keyValue[0], Integer.parseInt(keyValue[1]));
      }
    } catch (Exception e) {
      throw new IOException("Error while loading from text file: " + e.getMessage());
    }
  }
}
