package labs.sem1.lab10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Logger {
  StringBuilder sb;
  String[] row;

  public Logger(int threadsCount) {
    sb = new StringBuilder();
    // header of the CSV file
    row = new String[threadsCount + 1];
    row[0] = "main";
    for (int i = 1; i < row.length; i++) {
      row[i] = "Thread " + (i - 1);
    }
    // append header to the string
    sb.append(String.join(",", row) + "\n");
    // empty row for next lines
    for (int i = 0; i < row.length; i++) {
      row[i] = "\"\"";
    }
  }

  public void print(String s) {
    // print s at the column of the current thread (other columns should be empty)
    // get current thread id from "Thread 0" -> 1 (main -> 0)
    String threadName = Thread.currentThread().getName();
    int threadId = (threadName.equals("main")) ? 0 : Integer.parseInt(threadName.split(" ")[1]) + 1;
    // add s to the its column and append to the string
    row[threadId] = s;
    sb.append(String.join(",", row) + "\n");
    // empty row for next lines
    row[threadId] = "\"\"";
  }

  public void writeFile(String filename) {
    // if file or folder doesn't exist, create it
    try {
      if (!Files.exists(Paths.get(filename))) {
        Files.createDirectories(Paths.get(filename).getParent());
        Files.createFile(Paths.get(filename));
      }
      // write the string to the file
      Files.write(Paths.get(filename), sb.toString().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
