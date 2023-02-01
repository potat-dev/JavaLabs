package labs.sem1.lab10;

public class SynchroStack extends DebugStack {

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
