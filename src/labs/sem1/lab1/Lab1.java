package labs.sem1.lab1;

public class Lab1 {
  public static void main(String[] args) {
    // Create two Int objects
    Int a = new Int(5);
    Int b = new Int(10);

    System.out.println("a = " + a);
    System.out.println("b = " + b);

    // Test the methods
    a.increment();
    System.out.println("incrementing a: " + a);

    b.decrement();
    System.out.println("decrementing b: " + b);

    a.add(b);
    System.out.println("adding b to a: " + a);

    b.subtract(a);
    System.out.println("subtracting a from b: " + b);
  }
}
