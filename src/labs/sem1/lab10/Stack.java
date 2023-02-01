package labs.sem1.lab10;

// class Stack - parent class for SynchroStack and SynchroStackFast

public class Stack {
  protected StackNode head;
  protected int size = 0;

  public void push(int i) {
    StackNode node = new StackNode(i);
    node.next = head;
    head = node;
    size++;
  }

  public int pop() {
    if (head == null) {
      throw new RuntimeException("Stack is empty");
    }
    int value = head.value;
    head = head.next;
    size--;
    return value;
  }

  public int size() {
    return size;
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
    StackNode node1 = head;
    StackNode node2 = other.head;
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
    StackNode node = head;
    while (node != null) {
      sb.append(node.value);
      sb.append(", ");
      node = node.next;
    }
    if (head != null) {
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
