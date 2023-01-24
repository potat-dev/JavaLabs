package labs.sem1.lab4;

import java.util.LinkedList;
import java.util.ListIterator;

class SortedIntegerList {
  // список, в котором хранятся элементы
  private LinkedList<Integer> list;
  boolean allowDuplicates;

  public SortedIntegerList(boolean allowDuplicates) {
    this.allowDuplicates = allowDuplicates;
    list = new LinkedList<Integer>();
  }

  // конструктор по умолчанию
  public SortedIntegerList() {
    this(true);
  }

  public void add(int value) {
    if (list.isEmpty()) {
      list.add(value);
    } else {
      ListIterator<Integer> iterator = list.listIterator();
      while (iterator.hasNext()) {
        Integer current = iterator.next();

        if (current > value) {
          iterator.previous();
          iterator.add(value);
          return;
        }

        if (current == value && !allowDuplicates) {
          return;
        }
      }
      iterator.add(value);
    }
  }

  public void remove(int value) {
    // удаляем первое вхождение элемента
    list.removeFirstOccurrence(value);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj == this) {
      return true;
    }

    if (!(obj instanceof SortedIntegerList)) {
      return false;
    }

    SortedIntegerList other = (SortedIntegerList) obj;

    if (this.list.size() != other.list.size()) {
      return false;
    }

    ListIterator<Integer> iterator1 = this.list.listIterator();
    ListIterator<Integer> iterator2 = other.list.listIterator();

    while (iterator1.hasNext()) {
      if (iterator1.next() != iterator2.next()) {
        return false;
      }
    }

    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    for (Integer i : list) {
      sb.append(i);
      sb.append(", ");
    }

    if (sb.length() > 1) {
      sb.delete(sb.length() - 2, sb.length());
    }

    sb.append("]");
    return sb.toString();
  }
}