package labs.sem1.lab8;

// class for measuring time of execution of a function
class MeasureTime {
  public static void measureTime(Runnable runnable) {
    long start = System.currentTimeMillis();
    runnable.run();
    long end = System.currentTimeMillis();
    System.out.println("Time: " + (end - start) + " ms");
  }
}
