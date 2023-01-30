package labs.sem1.lab10;

public class Main {
  public static void main(String[] args) {
    SynchroStack stack = new SynchroStack();
    for (int i = 0; i < 10; i++) {
      stack.push(i);
    }

    System.out.println(stack);

    // multiple threads

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          stack.push(i);
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 10; i++) {
          stack.pop();
        }
      }
    });

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(stack);
  }
}
