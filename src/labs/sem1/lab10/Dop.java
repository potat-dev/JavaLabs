package labs.sem1.lab10;

// Доп:
// Реализовать класс iterativeParallelism, который будет обрабатывать списки в несколько потоков
// должны быть реализованы следующие методы:

// minimum(threads, list, comparator) - первый минимум
// maximum(threads, list, comparator) - первый максимум
// all(threads, list, predicate) - проверка, что все элементы списка удовлетворяют предикату
// any(threads, list, predicate) - проверка, что есть элемент списка, удовлетворяющий предикату

// Во все функции передается параметр threads - сколько потоков надо использовать при вычислении. Вы
// можете рассчитывать, что число потоков не велико. Не следует рассчитывать на то, что переданные
// компараторы, предикаты и функции работают быстро.

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Dop {
  public static void main(String[] args) {
    List<Integer> list = List.of(1, 2, 3, 4, -5, 6, 7, 8, -9, 10, 100);

    System.out.println(iterativeParallelism.minimum(3, list));
    System.out.println(iterativeParallelism.maximum(3, list));

    System.out.println(iterativeParallelism.all(3, list, x -> x > 0));
    System.out.println(iterativeParallelism.any(3, list, x -> x < 0));
  }
}

class iterativeParallelism {
  public static Integer minimum(Integer threadsCount, List<Integer> values) {
    AtomicInteger currentMin = new AtomicInteger(Integer.MAX_VALUE);

    // create threads (pass currentMin to each thread as global variable)
    Thread[] threads = new Thread[threadsCount];

    for (int i = 0; i < threadsCount; i++) {
      int startIdx = i * values.size() / threadsCount;
      int endIdx = (i + 1) * values.size() / threadsCount;

      threads[i] = new Thread(() -> {
        for (int j = startIdx; j < endIdx; j++) {
          if (values.get(j) < currentMin.get()) {
            currentMin.set(values.get(j));
          }
        }
      });
    }

    // start threads
    for (int i = 0; i < threadsCount; i++) {
      threads[i].start();
    }

    // wait for threads to finish
    for (int i = 0; i < threadsCount; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return currentMin.get();
  }

  public static Integer maximum(Integer threadsCount, List<Integer> values) {
    AtomicInteger currentMax = new AtomicInteger(Integer.MIN_VALUE);

    // create threads (pass currentMax to each thread as global variable)
    Thread[] threads = new Thread[threadsCount];

    for (int i = 0; i < threadsCount; i++) {
      int startIdx = i * values.size() / threadsCount;
      int endIdx = (i + 1) * values.size() / threadsCount;

      threads[i] = new Thread(() -> {
        for (int j = startIdx; j < endIdx; j++) {
          if (values.get(j) > currentMax.get()) {
            currentMax.set(values.get(j));
          }
        }
      });
    }

    // start threads
    for (int i = 0; i < threadsCount; i++) {
      threads[i].start();
    }

    // wait for threads to finish
    for (int i = 0; i < threadsCount; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return currentMax.get();
  }

  public static Boolean all(
      Integer threadsCount, List<Integer> values, Predicate<Integer> predicate) {
    AtomicInteger currentResult = new AtomicInteger(1);

    // create threads (pass currentResult to each thread as global variable)
    Thread[] threads = new Thread[threadsCount];

    for (int i = 0; i < threadsCount; i++) {
      int startIdx = i * values.size() / threadsCount;
      int endIdx = (i + 1) * values.size() / threadsCount;

      threads[i] = new Thread(() -> {
        for (int j = startIdx; j < endIdx; j++) {
          if (!predicate.test(values.get(j))) {
            currentResult.set(0);
          }
        }
      });
    }

    // start threads
    for (int i = 0; i < threadsCount; i++) {
      threads[i].start();
    }

    // wait for threads to finish
    for (int i = 0; i < threadsCount; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return currentResult.get() == 1;
  }

  public static Boolean any(
      Integer threadsCount, List<Integer> values, Predicate<Integer> predicate) {
    AtomicInteger currentResult = new AtomicInteger(0);

    // create threads (pass currentResult to each thread as global variable)
    Thread[] threads = new Thread[threadsCount];

    for (int i = 0; i < threadsCount; i++) {
      int startIdx = i * values.size() / threadsCount;
      int endIdx = (i + 1) * values.size() / threadsCount;

      threads[i] = new Thread(() -> {
        for (int j = startIdx; j < endIdx; j++) {
          if (predicate.test(values.get(j))) {
            currentResult.set(1);
          }
        }
      });
    }

    // start threads
    for (int i = 0; i < threadsCount; i++) {
      threads[i].start();
    }

    // wait for threads to finish
    for (int i = 0; i < threadsCount; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return currentResult.get() == 1;
  }
}