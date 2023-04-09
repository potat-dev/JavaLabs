package labs.sem1.lab9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

// [Reader-Writer] Реализовать программу, которая подсчитывает статистику употребления слов в
// заданных текстовых файлах. Программа получает список текстовых файлов в качестве параметров
// командной строки. Каждый файл должен обрабатываться в отдельном потоке.. Для подсчета числа
// уникальных слов используется общий для всех потоков HashMaр (ключ - слово, значение количество
// употреблений).

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

    // counter.printWords(100, 50, true);
    counter.printWords(1000, 0, false);
  }
}

class WordCounter {
  private HashMap<String, Integer> map = new HashMap<>();

  public synchronized void add(String word) {
    if (map.containsKey(word)) {
      map.put(word, map.get(word) + 1);
    } else {
      map.put(word, 1);
    }
  }

  // get words HashMap with value >= n
  public Stream<Entry<String, Integer>> getWords(int largerThan, int limit, boolean reverse) {
    HashMap<String, Integer> result = new HashMap<>();

    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (entry.getValue() >= largerThan) {
        result.put(entry.getKey(), entry.getValue());
      }
    }

    Stream<Entry<String, Integer>> stream = result.entrySet().stream();
    stream = stream.sorted(reverse ? Collections.reverseOrder(Map.Entry.comparingByValue())
                                   : Map.Entry.comparingByValue());
    if (limit > 0) {
      stream = stream.limit(limit);
    }

    return stream;
  }

  public void printWords(int largerThan, int limit, boolean reverse) {
    Stream<Entry<String, Integer>> result = getWords(largerThan, limit, reverse);

    if (map.size() == 0) {
      System.out.println("No words found");
      return;
    }

    StringBuilder builder = new StringBuilder();
    result.forEach(
        entry -> { builder.append(String.format("(%d) %s\n", entry.getValue(), entry.getKey())); });

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

  @Override
  public void run() {
    // read file and count words
    BufferedReader reader = new BufferedReader(file);
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        String[] words = line.split(" ");
        for (String word : words) {
          counter.add(word);
        }
      }
    } catch (Exception e) {
      System.out.println("Error while reading file");
    }
  }
}