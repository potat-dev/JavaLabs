package test;

// SynchroStack:
// Реализовать класс Stack, используя связный список.
// Добавить synchronized ключевые слова к методам push() и pop() для защиты от многопоточной модификации.

public class SynchroStack {
  private Node top;
  private int size;

  public SynchroStack() {
    top = null;
    size = 0;
  }

  public synchronized void push(int value, int thr) {
    if (thr > 0)
      System.out.println("Thread " + thr + " push - lock stack (inside push)");
    Node node = new Node(value);
    node.next = top;
    top = node;
    size++;
    if (thr > 0)
      System.out.println("Thread " + thr + " push - release stack (inside push)");
  }

  public synchronized int pop() {
    if (top == null) {
      return -1;
    }
    int value = top.value;
    top = top.next;
    size--;
    return value;
  }

  public synchronized boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }
    SynchroStack stack = (SynchroStack) obj;
    if (size != stack.size) {
      return false;
    }
    Node node1 = top;
    Node node2 = stack.top;
    while (node1 != null) {
      if (node1.value != node2.value) {
        return false;
      }
      node1 = node1.next;
      node2 = node2.next;
    }
    return true;
  }

  public synchronized String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    Node node = top;
    while (node != null) {
      sb.append(node.value);
      sb.append(", ");
      node = node.next;
    }
    if (size > 0) {
      sb.delete(sb.length() - 2, sb.length());
    }
    sb.append("]");
    return sb.toString();
  }

  private class Node {
    private int value;
    private Node next;

    public Node(int value) {
      this.value = value;
      next = null;
    }
  }

  public static void main(String[] args) {
    // проверка на многопоточность
    SynchroStack stack = new SynchroStack();
    for (int i = 0; i < 100; i++) {
      stack.push(i, 0);
    }

    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        System.out.println("Thread 1 push - lock stack");
        stack.push(i + 1000, 1);
        System.out.println("Thread 1 push - release stack");
      }
    });

    final Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 100; i++) {
        System.out.println("Thread 2 push - lock stack");
        stack.push(i + 10000, 2);
        System.out.println("Thread 2 push - release stack");
      }
    });

    thread1.start();
    thread2.start();

    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(stack);
  }
}