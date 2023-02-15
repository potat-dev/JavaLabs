package labs.sem1.lab1;

public class Main {
  public static void main(String[] args) {
    test1();
  }

  public static void test1() {
    Int a = new Int();
    a.increment();

    for (int i = 0; i < 7; i++) {
      a.add(a);
      System.out.println(a);
    }

    for (int i = 0; i < 3; i++) {
      a.decrement();
    }

    System.out.println(a);

    for (int i = 0; i < 3; i++) {
      a.add(a);
      System.out.println(a);
    }
  }

  public static void test2() {
    Int a = new Int();
    a.increment();

    for (int i = 0; i < 10; i++) {
      a.add(a);
      System.out.println(a);
    }

    Int b = new Int();

    for (int i = 0; i < 3; i++) {
      b.increment();
    }

    for (int i = 0; i < 3; i++) {
      b.add(b);
    }

    a.subtract(b);
    System.out.println(a);
  }
}
