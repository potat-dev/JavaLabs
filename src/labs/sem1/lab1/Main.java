package labs.sem1.lab1;

// Напишите класс, реализующий правильные с объектно-ориентированной точки зрения целые числа. При
// создании нового объекта должно создаваться число, равное нулю. В классе должны быть определены:

// - методы increment() и decrement(), соответственно увеличивающие и уменьшающие число на 1
// - методы add(Int n) и subtract(Int n), увеличивающие и уменьшающие число на n
// - метод toString()

// - в методах add и subtract передаются значения типа Int (с большой буквы), а не стандартный int
// - применить инкриминирование 1000 раз - плохой вариант
// - для сдачи основного задания нельзя придумывать свои методы и конструкторы, нужно использовать
// указанные выше

// Напишите наиболее короткую программу, которая используя только класс Int, выводит на экран число
// 1000. Программа должна быть чисто объектно-ориентированной. В частности, в ней нельзя
// использовать оператор присваивания.

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
