package labs.sem1.lab8;

import labs.sem1.lab5.UsualMatrix;

// класс для умножения вектора на матрицу.
// в нем, в отличие от класса ParallelMatrixProduct,
// параллелизация происходит не по строкам матрицы, а по столбцам
// вектор представляется в виде матрицы, у которой одна строка

class ParallelVectorMatrixProduct {
  private Thread[] threads;

  public ParallelVectorMatrixProduct(int threadsCount) {
    threads = new Thread[threadsCount];
  }

  public ParallelVectorMatrixProduct() {
    // get number of available processors
    int threadsCount = Runtime.getRuntime().availableProcessors();
    threads = new Thread[threadsCount];
  }

  // method for parallel matrix multiplication
  // вектор представляется в виде матрицы, у которой один столбец
  public UsualMatrix product(UsualMatrix m1, UsualMatrix m2) {
    if (m1.getColumns() != m2.getRows()) {
      throw new IllegalArgumentException("Matrix sizes are not equal");
    }

    UsualMatrix result = new UsualMatrix(m1.getRows(), m2.getColumns());

    // create threads
    for (int i = 0; i < threads.length; i++) {
      int startColumn = i * m2.getColumns() / threads.length;
      int endColumn = (i + 1) * m2.getColumns() / threads.length;
      threads[i] =
          new Thread(new VectorMatrixProductJob(i, m1, m2, result, startColumn, endColumn));
      threads[i].start();
    }

    // wait for threads to finish
    for (int i = 0; i < threads.length; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return result;
  }
}

class VectorMatrixProductJob implements Runnable {
  UsualMatrix m1;
  UsualMatrix m2;
  UsualMatrix result;
  int startColumn;
  int endColumn;

  public VectorMatrixProductJob(
      int id, UsualMatrix m1, UsualMatrix m2, UsualMatrix result, int startColumn, int endColumn) {
    this.m1 = m1;
    this.m2 = m2;
    this.result = result;
    this.startColumn = startColumn;
    this.endColumn = endColumn;
  }

  public void run() {
    for (int i = 0; i < m1.getRows(); i++) {
      for (int j = startColumn; j < endColumn; j++) {
        double sum = 0;
        for (int k = 0; k < m1.getColumns(); k++) {
          sum += m1.getElement(i, k) * m2.getElement(k, j);
        }
        result.setElement(i, j, sum);
      }
    }
  }
}

// TODO: можно создать класс MatrixProduct, который будет автоматически выбирать какой метод
// распараллеливания использовать: по строкам или по столбцам, в зависимости от размера матрицы