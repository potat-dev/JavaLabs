package labs.sem1.lab10;

public class SynchroStack extends Stack {

  public synchronized void push(int i) {
    Utils.printRow("START - Push");
    super.push(i);
    Utils.printRow("END - Push");
  }

  public synchronized int pop() {
    Utils.printRow("START - Pop");
    int i = super.pop();
    Utils.printRow("END - Pop");
    return i;
  }

  public synchronized boolean equals(Object o) {
    Utils.printRow("START - Equals");
    boolean b = super.equals(o);
    Utils.printRow("END - Equals");
    return b;
  }

  public synchronized String toString() {
    Utils.printRow("START - toString");
    String s = super.toString();
    Utils.printRow("END - toString");
    return s;
  }
}
