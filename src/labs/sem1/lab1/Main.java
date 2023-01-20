package labs.sem1.lab1;

public class Main {
  public static void main(String[] args) {
    // Create two Int objects
    Int a = new Int(5);
    System.out.println("a = " + a);

    Int b = new Int(10);
    System.out.println("b = " + b);
    System.out.println();

    // Test the methods
    a.increment();
    System.out.println("a++");
    System.out.println("a = " + a);
    System.out.println();

    b.decrement();
    System.out.println("b--");
    System.out.println("b = " + b);
    System.out.println();

    a.add(b);
    System.out.println("a = a + b");
    System.out.println("a = " + a);
    System.out.println();

    b.subtract(a);
    System.out.println("b = b - a");
    System.out.println("b = " + b);
    System.out.println();
  }
}
