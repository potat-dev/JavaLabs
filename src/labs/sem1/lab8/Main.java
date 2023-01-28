package labs.sem1.lab8;

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
    MeasureTime.measureTime(() -> {
      matrices[2] = matrices[0].product(matrices[1]);
    });

    ParallelMatrixProduct pmp = new ParallelMatrixProduct(12);

    // measure time of execution of parallel matrix multiplication
    MeasureTime.measureTime(() -> {
      matrices[3] = pmp.product(matrices[0], matrices[1]);
    });
  }
}
