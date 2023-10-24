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
      synchronized (logger) {
        logger.print("Push START");
      }
    super.push(i);
    if (debug)
      synchronized (logger) {
        logger.print("Push END");
      }
  }

  public int pop() {
    if (debug)
      synchronized (logger) {
        logger.print("Pop START");
      }
    int i = super.pop();
    if (debug)
      synchronized (logger) {
        logger.print("Pop END");
      }
    return i;
  }

  public boolean equals(Object o) {
    if (debug)
      synchronized (logger) {
        logger.print("Equals START");
      }
    boolean b = super.equals(o);
    if (debug)
      synchronized (logger) {
        logger.print("Equals END");
      }
    return b;
  }

  public String toString() {
    if (debug)
      synchronized (logger) {
        logger.print("toString START");
      }
    String s = super.toString();
    if (debug)
      synchronized (logger) {
        logger.print("toString END");
      }
    return s;
  }
}
