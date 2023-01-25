package labs.sem1.lab5;

public class Main {
  public static void main(String[] args) {
    // Напишите программу, создающую 2 случайные матрицы размером 1000x1000 с 1000
    // ненулевых элементов в каждой двумя способами - с помощью обычных и
    // разреженных матриц. Проверьте, что сложение и умножение для разных видов
    // матриц дает одинаковые результаты

    final int matrixSize = 200;
    final int elementsCount = 200;

    SquareMatrix[] squareMatrices = new SquareMatrix[4];
    SparseMatrix[] sparseMatrices = new SparseMatrix[4];
    for (int i = 0; i < 4; i++) {
      squareMatrices[i] = new SquareMatrix(matrixSize);
      sparseMatrices[i] = new SparseMatrix(matrixSize);
    }

    // первые две матрицы - исходные, вторые две - результаты
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < elementsCount; j++) {
        int row = (int) (Math.random() * matrixSize);
        int column = (int) (Math.random() * matrixSize);
        double value = Math.random() * 1000;
        squareMatrices[i].setElement(row, column, value);
        sparseMatrices[i].setElement(row, column, value);
      }
    }

    // square matrices operations
    System.out.print("adding... ");
    squareMatrices[2] = squareMatrices[0].add(squareMatrices[1]);
    System.out.println("done");
    System.out.print("multiplying... ");
    squareMatrices[3] = squareMatrices[0].multiply(squareMatrices[1]);
    System.out.println("done");

    // sparse matrices operations
    System.out.print("adding... ");
    sparseMatrices[2] = sparseMatrices[0].add(sparseMatrices[1]);
    System.out.println("done");
    System.out.print("multiplying... ");
    sparseMatrices[3] = sparseMatrices[0].multiply(sparseMatrices[1]);
    System.out.println("done");

    // проверка на равенство
    for (int i = 0; i < 4; i++) {
      System.out.print("square matrix " + i + " equals sparse matrix " + i + ": ");
      System.out.println(squareMatrices[i].equals((Matrix) sparseMatrices[i]));
    }
  }
}
