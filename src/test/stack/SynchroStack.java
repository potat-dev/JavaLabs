package test.stack;

// SynchroStack:
// Реализовать класс Stack, используя связный список.
// Добавить synchronized ключевые слова к методам push() и pop() для защиты от многопоточной модификации.

class Stack {
  private StackNode top;

  public void push(int i) {
    StackNode node = new StackNode(i);
    node.next = top;
    top = node;
  }

  public int pop() {
    if (top == null) {
      throw new RuntimeException("Stack is empty");
      // return 0;
    }
    int value = top.value;
    top = top.next;
    return value;
  }

  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (o == this) {
      return true;
    }
    if (!(o instanceof Stack)) {
      return false;
    }
    Stack other = (Stack) o;
    StackNode node1 = top;
    StackNode node2 = other.top;
    while (node1 != null && node2 != null) {
      if (node1.value != node2.value) {
        return false;
      }
      node1 = node1.next;
      node2 = node2.next;
    }
    return node1 == null && node2 == null;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    StackNode node = top;
    while (node != null) {
      sb.append(node.value);
      sb.append(", ");
      node = node.next;
    }
    if (top != null) {
      sb.delete(sb.length() - 2, sb.length());
    }
    sb.append("]");
    return sb.toString();
  }

  class StackNode {
    int value;
    StackNode next;

    StackNode(int value) {
      this.value = value;
    }
  }
}

public class SynchroStack {
  private Stack stack = new Stack();

  public synchronized void push(int i) {
    System.out.println("Thread: " + Thread.currentThread().getName() + " locked stack");
    synchronized (stack) {
      stack.push(i);
      System.out.println("Thread: " + Thread.currentThread().getName() + " unlocked stack");
    }
  }

  public synchronized int pop() {
    System.out.println("Thread: " + Thread.currentThread().getName() + " locked stack");
    synchronized (stack) {
      int value = stack.pop();
      System.out.println("Thread: " + Thread.currentThread().getName() + " unlocked stack");
      return value;
    }
  }

  public boolean equals(Object o) {
    synchronized (stack) {
      return stack.equals(o);
    }
  }

  public String toString() {
    synchronized (stack) {
      return stack.toString();
    }
  }
}
