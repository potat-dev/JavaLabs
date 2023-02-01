package test.stack;

import labs.sem1.lab10.SynchroStack;
import labs.sem1.lab10.Utils;

public class MainNew {
  public static void mainOld(String[] args) {
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

  private double benchmarkStack(Object o, Runnable r, int times) {
    // all methods available for class Stack
    Runnable[] methods = {
      () -> {
        for (int i = 0; i < times; i++) {
          o.push(i);
        }
      },
      () -> {
        for (int i = 0; i < times; i++) {
          o.pop();
        }
      },
      () -> {
        for (int i = 0; i < times; i++) {
          o.equals(o);
        }
      },
      () -> {
        for (int i = 0; i < times; i++) {
          o.toString();
        }
      }
    };
  }

  private void testMultithreading(Object o) {
    // create list of 4 available methods for Stack class
    // create 2 threads for each method
    // with 10 iterations

    // list of 4 methods
    Runnable[] methods = {
      () -> {
        for (int i = 0; i < 10; i++) {
          stack.push(i);
        }
      },
      () -> {
        for (int i = 0; i < 10; i++) {
          stack.pop();
        }
      },
      () -> {
        for (int i = 0; i < 10; i++) {
          stack.equals(stack);
        }
      },
      () -> {
        for (int i = 0; i < 10; i++) {
          stack.toString();
        }
      }
    };
  }
    
}