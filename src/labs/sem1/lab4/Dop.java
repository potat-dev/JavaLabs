package labs.sem1.lab4;

// добавить метод SortedList getLessThan(int value)
// возвращающий список элементов, меньших value

public class Dop {
  public static void main(String[] args) {
    SortedIntegerListDop list = new SortedIntegerListDop(true);
    for (int i = 0; i < 10; i++) {
      list.add(i);
      list.add(i + 10);
    }

    System.out.println(list);
    System.out.println(list.getLessThan(10));
  }
}

class SortedIntegerListDop extends SortedIntegerList {
  public SortedIntegerListDop(boolean allowDuplicates) {
    super(allowDuplicates);
  }

  public SortedIntegerListDop() {
    super();
  }

  public SortedIntegerList getLessThan(int value) {
    SortedIntegerList result = new SortedIntegerList(this.allowDuplicates);

    for (Integer i : list) {
      if (i < value) {
        result.add(i);
      }
    }

    return result;
  }
}