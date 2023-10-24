package labs.sem1.lab9;

// Написать программу, приводящую к ситуации взаимной блокировки (deadlock)

public class Deadlock {
  private static Object lock1 = new Object();
  private static Object lock2 = new Object();

  public static void main(String[] args) {
    Thread1 t1 = new Thread1();
    Thread2 t2 = new Thread2();

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Main thread finished"); // never reached
  }

  static class Thread1 extends Thread {
    public void run() {
      synchronized (lock1) {
        System.out.println("Thread 1: Holding lock 1");
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          System.out.println("Thread 1: Interrupted");
        }
        System.out.println("Thread 1: Waiting for lock 2");

        // deadlock here
        synchronized (lock2) {
          System.out.println("Thread 1: Holding lock 1 & 2"); // never reached
        }
      }
      System.out.println("Thread 1: Released lock 1 & 2"); // never reached
    }
  }

  static class Thread2 extends Thread {
    public void run() {
      synchronized (lock2) {
        System.out.println("Thread 2: Holding lock 2");
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          System.out.println("Thread 2: Interrupted");
        }
        System.out.println("Thread 2: Waiting for lock 1");

        // deadlock here
        synchronized (lock1) {
          System.out.println("Thread 2: Holding lock 1 & 2"); // never reached
        }
      }
      System.out.println("Thread 2: Released lock 1 & 2"); // never reached
    }
  }
}