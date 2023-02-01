package labs.sem1.lab10;

public class DebugStack extends Stack {
  protected boolean debug = false;

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public void push(int i) {
    if (debug)
      Utils.printRow("Push START");
    super.push(i);
    if (debug)
      Utils.printRow("Push END");
  }

  public int pop() throws RuntimeException {
    if (debug)
      Utils.printRow("Pop START");
    int i = super.pop();
    if (debug)
      Utils.printRow("Pop END");
    return i;
  }

  public boolean equals(Object o) {
    if (debug)
      Utils.printRow("Equals START");
    boolean b = super.equals(o);
    if (debug)
      Utils.printRow("Equals END");
    return b;
  }

  public String toString() {
    if (debug)
      Utils.printRow("toString START");
    String s = super.toString();
    if (debug)
      Utils.printRow("toString END");
    return s;
  }
}
