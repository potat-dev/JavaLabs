package labs.sem1.lab8;

// Реализовать класс ParallelMatrixProduct для многопоточного умножения матриц UsualMatrix. В
// конструкторе класс получает число потоков, которые будут использованы для перемножения (число
// потоков может быть меньше, чем число строк у первой матрицы)

// В функции main сравнить время перемножения больших случайных матриц обычным и многопоточным
// способом. Получить текущее время можно с помощью методов класса System

import labs.sem1.lab5.UsualMatrix;

public class Main {
  public static void main(String[] args) {
    // create matrices
    UsualMatrix[] matrices = new UsualMatrix[4];
    for (int i = 0; i < matrices.length; i++) {
      matrices[i] = new UsualMatrix(1000);
    }

    matrices[0].randomize();
    matrices[1].randomize();

    // measure time of execution of matrix multiplication
    MeasureTime.measureTime(
        "Standard multiplication", () -> {
          matrices[2] = matrices[0].product(matrices[1]);
        }, 10);

    // create parallel matrix multiplication object
    // with number of threads equal to number of available processors
    ParallelMatrixProduct pmp = new ParallelMatrixProduct();

    // measure time of execution of parallel matrix multiplication
    MeasureTime.measureTime("Parallel multiplication",
        () -> {
          matrices[3] = pmp.product(matrices[0], matrices[1]);
        }, 10);

    // check if matrices are equal
    System.out.println("Matrices are equal: " + matrices[2].equals(matrices[3]));
  }
}
