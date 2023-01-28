package labs.sem1.lab8;

import labs.sem1.lab5.UsualMatrix;

public class ParallelMatrixProduct {
  private Thread[] threads;

  public ParallelMatrixProduct(int threadsCount) {
    threads = new Thread[threadsCount];
  }

  public ParallelMatrixProduct() {
    // get number of available processors
    int threadsCount = Runtime.getRuntime().availableProcessors();
    threads = new Thread[threadsCount];
  }

  // method for parallel matrix multiplication
  public UsualMatrix product(UsualMatrix m1, UsualMatrix m2) {
    if (m1.getColumns() != m2.getRows()) {
      throw new IllegalArgumentException("Matrix sizes are not equal");
    }

    UsualMatrix result = new UsualMatrix(m1.getRows(), m2.getColumns());

    // create threads
    for (int i = 0; i < threads.length; i++) {
      int startRow = i * m1.getRows() / threads.length;
      int endRow = (i + 1) * m1.getRows() / threads.length;
      threads[i] = new Thread(new MatrixProductJob(i, m1, m2, result, startRow, endRow));
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

class MatrixProductJob implements Runnable {
  UsualMatrix m1;
  UsualMatrix m2;
  UsualMatrix result;
  int startRow;
  int endRow;

  public MatrixProductJob(int id, UsualMatrix m1, UsualMatrix m2, UsualMatrix result, int startRow, int endRow) {
    this.m1 = m1;
    this.m2 = m2;
    this.result = result;
    this.startRow = startRow;
    this.endRow = endRow;
  }

  public void run() {
    for (int i = startRow; i < endRow; i++) {
      for (int j = 0; j < m2.getColumns(); j++) {
        double sum = 0;
        for (int k = 0; k < m1.getColumns(); k++) {
          sum += m1.getElement(i, k) * m2.getElement(k, j);
        }
        result.setElement(i, j, sum);
      }
    }
  }
}