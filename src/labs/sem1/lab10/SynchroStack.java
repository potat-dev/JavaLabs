package labs.sem1.lab10;

// с этим классом одновременно может работать один поток (для всех методов)

public class SynchroStack extends Stack {
  // change this to extends DebugStack / Stack to enable / disable debug

  public synchronized void push(int i) {
    super.push(i);
  }

  public synchronized int pop() {
    return super.pop();
  }

  public synchronized boolean equals(Object o) {
    return super.equals(o);
  }

  public synchronized String toString() {
    return super.toString();
  }
}
