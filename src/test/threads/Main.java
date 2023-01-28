package test.threads;

public class Main {
  public static void main(String[] args) {
    Thread[] threads = new Thread[10];
    for (int i = 0; i < threads.length; i++) {
      MyRunnable runnable = new MyRunnable("Thread " + i, 10 + i, 1000);
      threads[i] = new Thread(runnable);
      threads[i].start();
    }

    System.out.println("Hello from main");

    // wait for all threads to finish
    for (int i = 0; i < threads.length; i++) {
      try {
        threads[i].join();
        System.out.println("Thread " + i + " finished");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

class MyRunnable implements Runnable {
  String name;
  private int iters;
  private int delay;

  public MyRunnable(String name, int iters, int delay) {
    this.name = name;
    this.iters = iters;
    this.delay = delay;
    // set name of thread
    Thread.currentThread().setName(name);
    System.out.println("Hello from constructor " + name);
  }

  @Override
  public void run() {
    System.out.println("Hello from " + name);
    for (int i = 0; i < iters; i++) {
      System.out.println("Hello from " + name + " iter " + i);
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}