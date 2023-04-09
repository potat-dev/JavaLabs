package labs.sem1.lab9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

// Реализовать программу, которая подсчитывает статистику употребления слов в заданных текстовых
// файлах. Программа получает список текстовых файлов в качестве параметров командной строки. Каждый
// файл должен обрабатываться в отдельном потоке. Для подсчета числа уникальных слов используется
// общий для всех потоков HashMaр (ключ - слово, значение - количество употреблений)

public class Dop {
  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("No files to process");
      return;
    }

    // try to open all files
    WordCounter counter = new WordCounter();
    FileProcessor[] processors = new FileProcessor[args.length];

    for (int i = 0; i < args.length; i++) {
      processors[i] = new FileProcessor(args[i], counter);
      processors[i].start();
    }

    // wait for all threads to finish
    try {
      for (int i = 0; i < args.length; i++) {
        processors[i].join();
      }
    } catch (Exception e) {
      System.out.println("Error while waiting for threads");
    }

    // get top 25 words
    counter.descending().limit(25).print();

    // get all words with count > 100
    // counter.ascending().largerThan(100).print();
  }
}

class WordCounter {
  private HashMap<String, Integer> map = new HashMap<>();

  // settings for pretty print
  private int limit = 0;
  private int largerThan = 100;
  private boolean reverse = true;

  // add word to HashMap
  public synchronized void add(String word) {
    if (map.containsKey(word)) {
      map.put(word, map.get(word) + 1);
    } else {
      map.put(word, 1);
    }
  }

  public WordCounter largerThan(int n) {
    largerThan = n;
    return this;
  }

  public WordCounter limit(int n) {
    limit = n;
    return this;
  }

  // по возрастанию
  public WordCounter ascending() {
    reverse = false;
    return this;
  }

  // по убыванию
  public WordCounter descending() {
    reverse = true;
    return this;
  }

  public void print() {
    if (map.size() == 0) {
      System.out.println("No words found");
      return;
    }

    StringBuilder builder = new StringBuilder();
    Stream<Entry<String, Integer>> result = map.entrySet().stream();

    // filter map to get only words with value >= largerThan
    result = result.filter(entry -> entry.getValue() >= largerThan);

    // sort by value
    result = result.sorted(reverse ? Collections.reverseOrder(Map.Entry.comparingByValue())
                                   : Map.Entry.comparingByValue());

    if (limit > 0)
      result = result.limit(limit);

    result.forEach(
        entry -> builder.append(String.format("(%d) %s\n", entry.getValue(), entry.getKey())));

    System.out.println(builder.toString());
  }
}

class FileProcessor extends Thread {
  private WordCounter counter;
  private FileReader file;

  public FileProcessor(String fileName, WordCounter counter) {
    this.counter = counter;
    try {
      this.file = new FileReader(new File(fileName));
    } catch (Exception e) {
      System.out.println("File " + fileName + " not found");
    }
  }

  private String[] cleanUp(String[] words) {
    for (int i = 0; i < words.length; i++) {
      // remove all except letters, dashes and apostrophes
      // remove dashes from start and end of word
      // remove leading and trailing spaces
      words[i] = words[i].toLowerCase().replaceAll("[^a-zа-яё'-]", "");
      words[i] = words[i].replaceAll("^-|-$", "").trim();
    }
    // remove empty strings
    words = Stream.of(words).filter(word -> !word.isEmpty()).toArray(String[] ::new);
    return words;
  }

  @Override
  public void run() {
    // read file and count words
    BufferedReader reader = new BufferedReader(file);
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        String[] words = line.split(" ");
        for (String word : cleanUp(words)) {
          counter.add(word);
        }
      }
    } catch (Exception e) {
      System.out.println("Error while reading file");
    }
  }
}