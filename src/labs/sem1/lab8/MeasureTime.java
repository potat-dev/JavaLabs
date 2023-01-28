package labs.sem1.lab8;

// class for measuring time of execution of a function
class MeasureTime {
  public static void measureTime(String message, Runnable runnable, int times) {
    System.out.print("Measuring " + message + " " + times + " times... ");

    long start = System.currentTimeMillis();
    for (int i = 0; i < times; i++) {
      runnable.run();
    }
    long end = System.currentTimeMillis();
    long time = (end - start) / times;

    System.out.println("done");
    System.out.println(message + " time: " + time + " ms");
  }
}
