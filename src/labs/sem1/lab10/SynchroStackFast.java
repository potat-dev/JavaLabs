package labs.sem1.lab10;

// SynchroStackFast - класс который реализует стек, используя связный список.
//  со стеком одновременно может работать либо один модифицирующий поток, либо несколько читающих потоков

// push и pop - write lock
// equals и toString - read lock

public class SynchroStackFast {
  private StackNode top;
  private Object lockRead = new Object();
  private Object lockWrite = new Object();

  public void push(int i) {
    synchronized (lockWrite) {
      System.out.println("push at " + Thread.currentThread().getName() + " locked");
      StackNode node = new StackNode(i);
      node.next = top;
      top = node;
      System.out.println("push at " + Thread.currentThread().getName() + " unlocked");
    }
  }

  public int pop() {
    synchronized (lockWrite) {
      System.out.println("pop at " + Thread.currentThread().getName() + " locked");
      if (top == null) {
        throw new RuntimeException("Stack is empty");
        // return 0;
      }
      int value = top.value;
      top = top.next;
      System.out.println("pop at " + Thread.currentThread().getName() + " unlocked");
      return value;
    }
  }

  public boolean equals(Object o) {
    synchronized (lockRead) {
      System.out.println("equals at " + Thread.currentThread().getName() + " locked");
      if (o == this) {
        return true;
      }
      if (o == null || !(o instanceof SynchroStackFast)) {
        return false;
      }
      SynchroStackFast other = (SynchroStackFast) o;
      StackNode node1 = top;
      StackNode node2 = other.top;
      while (node1 != null && node2 != null) {
        if (node1.value != node2.value) {
          return false;
        }
        node1 = node1.next;
        node2 = node2.next;
      }
      System.out.println("equals at " + Thread.currentThread().getName() + " unlocked");
      return node1 == null && node2 == null;
    }
  }

  public String toString() {
    synchronized (lockRead) {
      System.out.println("toString at " + Thread.currentThread().getName() + " locked");
      StringBuilder sb = new StringBuilder();
      sb.append("[");
      StackNode node = top;
      while (node != null) {
        sb.append(node.value);
        sb.append(", ");
        node = node.next;
      }
      if (sb.length() > 1) {
        sb.setLength(sb.length() - 2);
      }
      sb.append("]");
      System.out.println("toString at " + Thread.currentThread().getName() + " unlocked");
      return sb.toString();
    }
  }

  private static class StackNode {
    int value;
    StackNode next;

    public StackNode(int value) {
      this.value = value;
    }
  }

  public static void main(String[] args) {
    // multi-threaded test

    SynchroStackFast stack = new SynchroStackFast();
    for (int i = 0; i < 10; i++) {
      stack.push(i);
    }

    Thread t1 = new Thread(() -> {
      Thread.currentThread().setName("(WRITE) Push thread");
      for (int i = 0; i < 10; i++) {
        stack.push(i + 100);
      }
    });

    Thread t2 = new Thread(() -> {
      Thread.currentThread().setName("(WRITE) Pop thread");
      for (int i = 0; i < 10; i++) {
        stack.pop();
      }
    });

    Thread t3 = new Thread(() -> {
      Thread.currentThread().setName("(READ) Equals thread");
      for (int i = 0; i < 10; i++) {
        new SynchroStackFast().equals(stack);
      }
    });

    Thread t4 = new Thread(() -> {
      Thread.currentThread().setName("(READ) ToString thread");
      for (int i = 0; i < 10; i++) {
        stack.toString();
      }
    });

    t1.start();
    t2.start();
    t3.start();
    t4.start();

    try {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(stack);
  }
}
