package labs.sem1.lab10;

public class SynchroStackFast extends DebugStack {
  // с этим классом одновременно может работать один записывающий поток
  // (push и pop) и несколько читающих (equals и toString)

  // для этого есть два объекта-монитора: для записи и для чтения
  // при записи блокируется монитор для записи, при чтении - монитор для чтения

  Object readLock = new Object();
  Object writeLock = new Object();

  public void push(int i) {
    synchronized (writeLock) {
      super.push(i);
    }
  }

  public int pop() {
    synchronized (writeLock) {
      return super.pop();
    }
  }

  public boolean equals(Object o) {
    synchronized (readLock) {
      return super.equals(o);
    }
  }

  public String toString() {
    synchronized (readLock) {
      return super.toString();
    }
  }
}
