package labs.sem1.lab6.task1;

public class FormattedInput {
  public static void main(String[] args) {
    Object vals[] = scanf("%d %s %c");
    for (Object val : vals) {
      System.out.print(val.getClass().getName());
      System.out.print(": ");
      System.out.println(val);
    }
  }

  public static Object[] sscanf(String format, String in) {
    // tokenize input and format
    String[] input = in.split(" ");
    String[] tokens = format.split(" ");

    // check if input and format have same length
    if (tokens.length != input.length) {
      throw new RuntimeException("Input does not match format");
    }

    // check if format is valid
    for (String token : tokens) {
      if (!token.equals("%d") && !token.equals("%f") && !token.equals("%s") && !token.equals("%c")) {
        throw new IllegalArgumentException("Invalid format");
      }
    }

    // create array to store values
    Object[] values = new Object[tokens.length];

    // parse input with try-catch
    for (int i = 0; i < tokens.length; i++) {
      try {
        switch (tokens[i]) {
          case "%d":
            values[i] = Integer.parseInt(input[i]);
            break;
          case "%f":
            values[i] = Double.parseDouble(input[i]);
            break;
          case "%s":
            values[i] = input[i];
            break;
          case "%c":
            if (input[i].length() != 1) {
              throw new RuntimeException("Input does not match format");
            } else {
              values[i] = input[i].charAt(0);
            }
            break;
        }
      } catch (Exception e) {
        throw new RuntimeException("Input does not match format");
      }
    }

    // return values if no exception is thrown
    return values;
  }

  public static Object[] scanf(String format) {
    java.util.Scanner scanner = new java.util.Scanner(System.in);
    while (true) {
      try {
        Object[] input = sscanf(format, scanner.nextLine());
        scanner.close();
        return input;
      } catch (Exception e) {
        System.out.println("Invalid input for format: " + format + ". Try again.");
      }
    }
  }
}
