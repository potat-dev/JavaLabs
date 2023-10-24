package labs.sem1.lab6.task1;

// Реализовать класс FormattedInput с двумя статическими функциями:

// Object[] scanf(String format) // Читает с System.in
// Object[] sscanf(String format, String in) // Читает из строки in

// format - строка со спецификацией формата ввода (может быть несколько спецификаторов в одной
// строке, например, %d %d %f)

// Список спецификаторов:
// %d - целое int
// %f - дробное double
// %s - строка
// %c - символ

// Если ввод пользователя не соответствует спецификации,
// то функция запрашивает ввод повторно

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
