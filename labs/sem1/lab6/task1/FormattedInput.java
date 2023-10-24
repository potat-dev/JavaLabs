package labs.sem1.lab6.task1;

import java.util.List;

public class FormattedInput {
  // supported formats: digit, float, string, character
  static List<String> availableTokens = List.of("%d", "%f", "%s", "%c");

  public static Object[] sscanf(String format, String input) {
    // tokenize input and format
    String[] inputTokens = input.split(" ");
    String[] formatTokens = format.split(" ");

    // check if input and format have same length
    if (formatTokens.length != inputTokens.length) {
      throw new RuntimeException("Input does not match format");
    }

    // check if format is valid
    for (String token : formatTokens) {
      if (!availableTokens.contains(token)) {
        throw new IllegalArgumentException("Invalid format");
      }
    }

    // create array to store values
    Object[] values = new Object[formatTokens.length];

    // parse input with try-catch
    for (int i = 0; i < formatTokens.length; i++) {
      try {
        switch (formatTokens[i]) {
          case "%d":
            values[i] = Integer.parseInt(inputTokens[i]);
            break;
          case "%f":
            values[i] = Double.parseDouble(inputTokens[i]);
            break;
          case "%s":
            values[i] = inputTokens[i];
            break;
          case "%c":
            if (inputTokens[i].length() != 1) {
              throw new RuntimeException("Input does not match format");
            } else {
              values[i] = inputTokens[i].charAt(0);
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
