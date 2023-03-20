package labs.sem1.lab6.task1;

public class Main {
  public static void main(String[] args) {
    System.out.print("Enter a number, a string and a character: ");
    Object vals[] = FormattedInput.scanf("%d %s %c");
    for (Object val : vals) {
      System.out.print(val.getClass().getName());
      System.out.print(": ");
      System.out.println(val);
    }
  }
}
