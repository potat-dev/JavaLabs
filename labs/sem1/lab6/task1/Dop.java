package labs.sem1.lab6.task1;

// Доп:
// Создать новый спецификатор, %a, который будет позволять вводить массивы
// Введенный массив может быть любой длины
// Окончание массива - конец строки или 'не число'

import java.util.Iterator;
import java.util.List;

public class Dop {
  public static void main(String[] args) {
    String format = "%d %s %c %a %s %s";
    String in = "1 lol 3 4 5 6 7 8 9 10 kek test";

    System.out.println("Format: " + format);
    System.out.println("Input: " + in);
    System.out.println("Output:");

    Object vals[] = ExtFormattedInput.sscanf(format, in);

    for (Object val : vals) {
      System.out.print(val.getClass().getName());
      System.out.print(": ");
      System.out.println(val);
    }
  }
}

// FormattedInputArr - класс, который позволяет считывать массивы
class ExtFormattedInput extends FormattedInput {
  static List<String> availableTokens = List.of("%d", "%f", "%s", "%c", "%a");

  // sscanf
  public static Object[] sscanf(String format, String in) {
    List<String> input = List.of(in.split(" "));
    List<String> tokens = List.of(format.split(" "));

    // check if format is valid
    for (String token : tokens) {
      if (!availableTokens.contains(token)) {
        throw new IllegalArgumentException("Invalid format");
      }
    }

    // create list to store values
    List<Object> values = new java.util.ArrayList<Object>();

    // iterators
    Iterator<String> inputIterator = input.iterator();
    Iterator<String> tokensIterator = tokens.iterator();

    // parse input
    String value = "";
    boolean getNextValue = true;

    // parse input (including arrays) with try-catch
    while (inputIterator.hasNext() && tokensIterator.hasNext()) {
      String token = tokensIterator.next();
      if (getNextValue) { // after array token, do not get next value
        value = inputIterator.next();
      }

      try {
        switch (token) {
          case "%d":
            values.add(Integer.parseInt(value));
            break;
          case "%f":
            values.add(Double.parseDouble(value));
            break;
          case "%s":
            values.add(value);
            break;
          case "%c":
            if (value.length() != 1) {
              throw new RuntimeException("Input does not match format");
            } else {
              values.add(value.charAt(0));
            }
            break;
          case "%a": // array of integers terminated by non-integer or end of input
            List<Integer> arr = new java.util.ArrayList<Integer>();
            while (inputIterator.hasNext()) {
              value = inputIterator.next();
              try {
                arr.add(Integer.parseInt(value));
              } catch (Exception e) {
                break;
              }
            }
            if (arr.size() > 0) {
              values.add(arr);
            }
            break;
        }
        getNextValue = !token.equals("%a");
      } catch (Exception e) {
        throw new RuntimeException("Input does not match format");
      }
    }

    // return values if no exception is thrown
    return values.toArray();
  }
}