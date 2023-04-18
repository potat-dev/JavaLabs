package labs.sem1.lab4;

// Напишите класс SortedIntegerList, который хранит отсортированный в порядке возрастания список
// целых чисел. Внутри класса список должен храниться с помощью LinkedList

// В классе SortedInteger List должны быть определены:
// - Конструктор с булевским параметром.
//   Если этот параметр принимает значение true, то в создаваемом списке разрешены повторяющиеся
//   элементы, иначе - нет
// - Методы add(int) и remove(int), которые, добавляют число в список / удаляют число из списка;
//   если добавление (удаление) невозможно - метод не делает ничего. Операции добавления / удаления
//   должны требовать не более чем одного прохода по списку
// - Метод equals()

// Примечание: везде использовать итератор
// Напишите программу, проверяющую работу класса SortedIntegerList

public class Main {
  public static void main(String[] args) {
    // проверка запрета повторений
    SortedIntegerList uniqueList = new SortedIntegerList(false);
    SortedIntegerList originalList = new SortedIntegerList(true);

    // проверка добавления
    for (int i = 0; i < 10; i++) {
      uniqueList.add(i);
      uniqueList.add(i);
      originalList.add(i);
      originalList.add(i);
    }

    // проверка equals
    System.out.println("uniqueList: " + uniqueList);
    System.out.println("originalList: " + originalList);
    System.out.println("uniqueList.equals(originalList): " + uniqueList.equals(originalList));

    // проверка удаления
    for (int i = 0; i < 10; i++) {
      originalList.remove(i);
    }

    System.out.println("originalList after remove: " + originalList);
    System.out.println("uniqueList.equals(originalList): " + uniqueList.equals(originalList));

    System.out.println("uniqueList.equals(uniqueList): " + uniqueList.equals(uniqueList));
  }
}
