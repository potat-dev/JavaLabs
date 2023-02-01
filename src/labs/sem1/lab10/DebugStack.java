package labs.sem1.lab10;

public class DebugStack extends Stack {
  protected boolean debug = false;
  Logger logger;

  public void setLogger(Object logger) {
    if (logger instanceof Logger) {
      this.logger = (Logger) logger;
      debug = true;
    } else {
      debug = false;
    }
  }

  public void push(int i) {
    if (debug)
      logger.printRow("Push START");
    super.push(i);
    if (debug)
      logger.printRow("Push END");
  }

  public int pop() {
    if (debug)
      logger.printRow("Pop START");
    int i = super.pop();
    if (debug)
      logger.printRow("Pop END");
    return i;
  }

  public boolean equals(Object o) {
    if (debug)
      logger.printRow("Equals START");
    boolean b = super.equals(o);
    if (debug)
      logger.printRow("Equals END");
    return b;
  }

  public String toString() {
    if (debug)
      logger.printRow("toString START");
    String s = super.toString();
    if (debug)
      logger.printRow("toString END");
    return s;
  }
}
