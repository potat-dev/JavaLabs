package labs.sem1.lab4;

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
    System.out.println(
        "uniqueList.equals(originalList): " +
            uniqueList.equals(originalList));

    // проверка удаления
    for (int i = 0; i < 10; i++) {
      originalList.remove(i);
    }

    System.out.println("originalList after remove: " + originalList);
    System.out.println(
        "uniqueList.equals(originalList): " +
            uniqueList.equals(originalList));

    System.out.println(
        "uniqueList.equals(uniqueList): " +
            uniqueList.equals(uniqueList));
  }
}
