package labs.sem1.lab10;

public class Main {
  public static void main(String[] args) {
    SynchroStack stack = new SynchroStack();
    Utils.initCSV();

    for (int i = 0; i < 100; i++) {
      stack.push(i);
    }

    Thread[] threads = new Thread[4];

    for (int i = 0; i < 2; i++) {
      threads[i * 2] = new Thread(() -> {
        Utils.printRow("START - Stack 10 pushes");
        for (int j = 0; j < 10; j++) {
          stack.push(j);
        }
        Utils.printRow("END - Stack 10 pushes");
      });

      threads[i * 2 + 1] = new Thread(() -> {
        Utils.printRow("START - Stack 10 pops");
        for (int j = 0; j < 10; j++) {
          stack.pop();
        }
        Utils.printRow("END - Stack 10 pops");
      });
    }

    for (int i = 0; i < threads.length; i++) {
      threads[i].start();
    }

    for (int i = 0; i < threads.length; i++) {
      try {
        threads[i].join();
        Utils.printRow("END - Thread " + i + " join");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
