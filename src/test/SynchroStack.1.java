package test;

// SynchroStack:
// Реализовать класс Stack, используя связный список.
// Добавить synchronized ключевые слова к методам push() и pop() для защиты от многопоточной модификации.

// SynchroStackFast:
// Реализовать класс Stack, используя связный список.
// Добавить класс ReadWriteLock и экземпляры этого класса для защиты данных стека.
// Модифицирующий метод push() должен захватывать writeLock, а читающие методы (equals() и toString()) должны захватывать readLock.

// main:
// Запустить несколько потоков для модификации и чтения стека с помощью SynchroStack и SynchroStackFast.
// Замерить время работы каждого потока и показать, что SynchroStackFast работает быстрее, потому что несколько читающих потоков могут одновременно читать данные стека, а не только один модифицирующий поток.

class SynchroStack {
  class StackNode {
    int value;
    StackNode next;
  }

  private StackNode top;

  public void push(int i) {
    StackNode node = new StackNode();
    node.value = i;
    node.next = top;
    top = node;
  }

  public int pop() {
    if (top == null) {
      // throw new RuntimeException("Stack is empty");
      return 0;
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
    if (!(o instanceof SynchroStack)) {
      return false;
    }
    SynchroStack other = (SynchroStack) o;
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
      node = node.next;
      if (node != null) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

  public static void main(String[] args) {
    // SynchroStack stack = new SynchroStack();
    // stack.push(1);
    // stack.push(2);
    // stack.push(3);
    // System.out.println(stack);
    // System.out.println(stack.pop());
    // System.out.println(stack);
    // System.out.println(stack.pop());
    // System.out.println(stack);
    // System.out.println(stack.pop());
    // System.out.println(stack);
    // System.out.println(stack.equals(stack));
    // SynchroStack stack2 = new SynchroStack();
    // stack2.push(1);
    // stack2.push(2);
    // stack2.push(3);
    // System.out.println(stack.equals(stack2));
    // stack2.pop();
    // System.out.println(stack.equals(stack2));
    // stack2.pop();
    // System.out.println(stack.equals(stack2));
    // stack2.pop();
    // System.out.println(stack.equals(stack2));

    test();
  }

  static void test() {
    // multithreaded test
    SynchroStack stack = new SynchroStack();

    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        stack.push(i);
        System.out.println("push " + stack);
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 20; i++) {
        stack.pop();
        System.out.println("pop " + stack);
      }
    });

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(stack);
  }
}