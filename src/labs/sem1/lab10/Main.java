package labs.sem1.lab10;

public class Main {
  public static void main(String[] args) {
    // classes to benchmark
    Class<?>[] classes = {
        Stack.class,
        DebugStack.class,
        SynchroStack.class,
        SynchroStackFast.class
    };

    int threadsPerMethod = 24; // threads per method
    int itersPerMethod = 1000; // iterations per method

    // logs file path
    String logsPath = "src/labs/sem1/lab10/results/%s.csv";

    // initial size of stacks (to avoid empty stack errors)
    int initialSize = itersPerMethod * threadsPerMethod;

    // create stacks of each class
    Stack[] stacks = new Stack[classes.length];
    for (int i = 0; i < classes.length; i++) {
      try {
        stacks[i] = (Stack) classes[i].getConstructor().newInstance();
        for (int j = 0; j < initialSize; j++) {
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
            for (int i = 0; i < itersPerMethod; i++) {
              stack.push(i);
            }
          },
          () -> { // pop (write operation)
            for (int i = 0; i < itersPerMethod; i++) {
              stack.pop();
            }
          },
          () -> { // equals (read operation, light)
            for (int i = 0; i < itersPerMethod * 20; i++) {
              stack.equals(stack);
            }
          },
          () -> { // toString (read operation, heavy)
            for (int i = 0; i < itersPerMethod / 5; i++) {
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
          threads[index].setName("Thread " + index);
        }
      }

      Logger log = null;
      // set logger if stack is subclass of DebugStack and not DebugStack itself
      // because DebugStack is not synchronized (and therefore not thread-safe)
      if (stack instanceof DebugStack) {
        log = new Logger(threads.length);
        ((DebugStack) stack).setLogger(log);
      }

      // print stack type
      System.out.print("Benchmarking: " + stack.getClass().getSimpleName() + " ");

      // start measuring time
      long start = System.currentTimeMillis();

      // start threads
      for (Thread thread : threads) {
        thread.start();
      }

      // wait for threads to finish
      for (Thread thread : threads) {
        try {
          thread.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      // stop measuring time
      long end = System.currentTimeMillis();
      System.out.println("- took " + (end - start) + " ms");

      // write to file
      if (log != null) {
        log.writeFile(String.format(logsPath, stack.getClass().getSimpleName()));
      }
    }
  }
}

// example output:

// Benchmarking: Stack - took 669 ms
// Benchmarking: DebugStack - took 3725 ms
// Benchmarking: SynchroStack - took 3845 ms
// Benchmarking: SynchroStackFast - took 2180 ms
