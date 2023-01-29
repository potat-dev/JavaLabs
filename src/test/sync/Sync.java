package test.sync;

class PrintDemo {
  public void printCount() {
    try {
      for (int i = 5; i > 0; i--) {
        System.out.println("Счетчик   ---   " + i);
      }
    } catch (Exception e) {
      System.out.println("Поток прерван");
    }
  }
}

class ThreadDemo extends Thread {
  private Thread t;
  private String threadName;
  PrintDemo PD;

  ThreadDemo(String name, PrintDemo pd) {
    threadName = name;
    PD = pd;
  }

  public void run() {
    synchronized (PD) {
      PD.printCount();
    }
    System.out.println(threadName + " завершается");
  }

  public void start() {
    System.out.println("Запуск " + threadName);
    if (t == null) {
      t = new Thread(this, threadName);
      t.start();
    }
  }
}

public class Sync {

  public static void main(String args[]) {
    PrintDemo PD = new PrintDemo();

    ThreadDemo T1 = new ThreadDemo("Поток 1", PD);
    ThreadDemo T2 = new ThreadDemo("Поток 2", PD);

    T1.start();
    T2.start();

    // ждем, пока потоки завершатся
    try {
      T1.join();
      T2.join();
    } catch (Exception e) {
      System.out.println("Прерван");
    }
  }
}