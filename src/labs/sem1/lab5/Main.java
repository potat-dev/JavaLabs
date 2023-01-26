package labs.sem1.lab5;

public class Main {
  public static void main(String[] args) {
    final int matrixSize = 300;
    final int elementsCount = 300;

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

    // сложение
    System.out.print("adding square matrices... ");
    squareMatrices[2] = squareMatrices[0].add(squareMatrices[1]);
    System.out.println("done");

    System.out.print("adding sparse matrices... ");
    sparseMatrices[2] = sparseMatrices[0].add(sparseMatrices[1]);
    System.out.println("done");

    // проверка на равенство
    System.out.print("square matrix sum equals sparse matrix sum: ");
    System.out.println(squareMatrices[2].equals((Matrix) sparseMatrices[2]));

    // умножение
    System.out.print("multiplying square matrices... ");
    squareMatrices[3] = squareMatrices[0].product(squareMatrices[1]);
    System.out.println("done");

    System.out.print("multiplying sparse matrices... ");
    sparseMatrices[3] = sparseMatrices[0].product(sparseMatrices[1]);
    System.out.println("done");

    // проверка на равенство
    System.out.print("square matrix product equals sparse matrix product: ");
    System.out.println(squareMatrices[3].equals((Matrix) sparseMatrices[3]));
  }
}
