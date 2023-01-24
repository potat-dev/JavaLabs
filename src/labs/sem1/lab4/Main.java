package labs.sem1.lab4;

// TODO проверки:
// - запрет повторений
// - add и remove
// - equals

public class Main {
  public static void main(String[] args) {
    SortedIntegerList list = new SortedIntegerList();

    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
    System.out.println(list);

    list.add(3);
    list.add(3);
    System.out.println(list);

    list.add(0);
    System.out.println(list);

    list.add(6);
    System.out.println(list);

    list.add(4);
    System.out.println(list);

    list.remove(3);
    System.out.println(list);

    list.remove(3);
    System.out.println(list);

    list.remove(4);
    System.out.println(list);

    list.remove(3);
    System.out.println(list);
  }
}
