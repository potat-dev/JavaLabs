package labs.sem1.lab8;

import labs.sem1.lab5.UsualMatrix;

// Реализуйте многопоточную версию умножения вектора на матрицу.
// Функция умножения в качестве параметра получает число потоков.
// Подберите этот параметр так, что на длине вектора 1000,
// многопоточная версия выиграла у однопоточной

public class Dop {
  public static void main(String[] args) {
    int iters = 200;
    int length = 1000;

    // вектор
    UsualMatrix a = new UsualMatrix(1, length);
    a.randomize();

    // матриица на которую умножается вектор
    UsualMatrix b = new UsualMatrix(length, length);
    b.randomize();

    // массив для результатов
    int max_threads = Runtime.getRuntime().availableProcessors();
    UsualMatrix[] results = new UsualMatrix[max_threads + 1];

    // measure time of execution of matrix multiplication
    MeasureTime.measureTime("Standard multiplication", () -> {
      results[0] = a.product(b);
    }, iters);

    for (int i = 0; i < max_threads; i++) {
      final int t = i + 1;
      System.out.println("Threads: " + t);

      // create parallel matrix multiplication object
      // ParallelMatrixProduct pmp = new ParallelMatrixProduct(t);
      ParallelVectorMatrixProduct pvmp = new ParallelVectorMatrixProduct(t);

      // measure time of execution of parallel matrix multiplication
      MeasureTime.measureTime("Parallel multiplication", () -> {
        results[t] = pvmp.product(a, b);
      }, iters);
    }

    // check if matrices are equal
    boolean equal = true;
    for (int i = 0; i < max_threads; i++) {
      if (!results[0].equals(results[i + 1])) {
        equal = false;
        break;
      }
    }

    System.out.println();
    System.out.println("Matrices are equal: " + equal);
  }
}
