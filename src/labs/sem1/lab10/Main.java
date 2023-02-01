package labs.sem1.lab10;

public class Main {
  public static void main(String[] args) {
    Class<?>[] classes = { // classes to benchmark
        Stack.class,
        SynchroStack.class,
        SynchroStackFast.class
    };

    int threadsPerMethod = 2; // threads per method

    // create stacks of each class
    Stack[] stacks = new Stack[classes.length];
    for (int i = 0; i < classes.length; i++) {
      try {
        stacks[i] = (Stack) classes[i].getConstructor().newInstance();
        // fill stacks with 200 elements
        for (int j = 0; j < 200; j++) {
          stacks[i].push(j);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // print type of stacks
    for (Stack stack : stacks) {
      Runnable[] methods = {
          () -> { // push (write operation)
            for (int i = 0; i < 1000; i++) {
              stack.push(i);
            }
          },
          () -> { // pop (write operation)
            for (int i = 0; i < 1000; i++) {
              stack.pop();
            }
          },
          () -> { // equals (read operation)
            for (int i = 0; i < 10000; i++) {
              stack.equals(stack);
            }
          },
          () -> { // toString (read operation)
            for (int i = 0; i < 100; i++) {
              stack.toString();
            }
          }
      };

      
      
      // create threads
      Thread[] threads = new Thread[methods.length * threadsPerMethod];
      for (int i = 0; i < methods.length; i++) {
        for (int j = 0; j < threadsPerMethod; j++) {
          int index = i * threadsPerMethod + j;
          threads[index] = new Thread(methods[i]);
          threads[index].setName("Thread-" + index);
        }
      }
      
      System.out.print("Benchmarking " + stack.getClass().getSimpleName());

      // start threads and measure time
      long start = System.currentTimeMillis();

      for (Thread thread : threads) {
        thread.start();
      }

      for (Thread thread : threads) {
        try {
          thread.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      long end = System.currentTimeMillis();

      System.out.println(" - took " + (end - start) + "ms");
    }
  }
}
