package labs.sem1.lab5;

public class Main {
  public static void main(String[] args) {
    // Напишите программу, создающую 2 случайные матрицы размером 1000x1000 с 1000
    // ненулевых элементов в каждой двумя способами - с помощью обычных и
    // разреженных матриц. Проверьте, что сложение и умножение для разных видов
    // матриц дает одинаковые результаты

    final int matrixSize = 10;
    final int elementsCount = 10;

    Matrix[] matrices = new Matrix[4];
    for (int i = 0; i < 4; i++) {
      matrices[i] = new SparseMatrix(matrixSize);
    }

    for (int i = 0; i < matrices.length; i++) {
      for (int j = 0; j < elementsCount; j++) {
        matrices[i].setElement(
            (int) (Math.random() * matrixSize),
            (int) (Math.random() * matrixSize),
            (int) (Math.random() * 100));
      }
    }

    matrices[2] = matrices[0].add(matrices[1]);
    matrices[3] = matrices[0].multiply(matrices[1]);

    for (int i = 0; i < matrices.length; i++) {
      System.out.println("Matrix " + i + ":");
      System.out.println(matrices[i]);
      System.out.println();
    }
  }
}
