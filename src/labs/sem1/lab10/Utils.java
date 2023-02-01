package labs.sem1.lab10;

public class Utils {
  private static int threadsCount = 4;

  public static void initCSV() {
    // generate string with headers (e.g. "main,Thread-0,Thread-1,...")
    String[] headers = new String[threadsCount + 1];
    headers[0] = "main";
    for (int i = 1; i < headers.length; i++) {
      headers[i] = "Thread-" + (i - 1);
    }
    System.out.println(String.join(",", headers));
  }

  public static void printRow(String s) {
    // print s at the column of the current thread
    // other columns should be empty
    String[] row = new String[threadsCount + 1];
    for (int i = 0; i < row.length; i++) {
      row[i] = "\"\"";
    }
    // get current thread id from "Thread-0" -> 1 (main -> 0)
    String threadName = Thread.currentThread().getName();
    if (threadName.equals("main")) {
      row[0] = s;
    } else {
      row[Integer.parseInt(threadName.substring(7)) + 1] = s;
    }
    System.out.println(String.join(",", row));
  }
}
