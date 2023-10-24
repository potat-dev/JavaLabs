package labs.sem1.lab5;

// Напишите интерфейс IMatrix с несколькими реализациями - UsualMatrix и расширяющий его
// SquareMatrix из предыдущих заданий и SparseMatrix для разреженных матриц. SparseMatrix
// должен быть реализован с помощью LinkedList (возможно, вам потребуется создать какие-то еще
// дополнительные классы, которые должны быть вложенными/внутренними). Все общие методы должны быть
// представлены в интерфейсе IMatrix

// Напишите программу, создающую 2 случайные матрицы размером 1000x1000 с 1000 ненулевых элементов в
// каждой двумя способами - с помощью обычных и разреженных матриц. Проверьте, что сложение и
// умножение для разных видов матриц дает одинаковые результаты

import java.util.Locale;

public class Main {
  public static void main(String[] args) {
    // settings
    final int matrixSize = 200;
    final int elementsCount = 200;

    // helper variables for time measuring
    long startTime, middleTime, endTime;
    long delta1, delta2;
    String times;

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

    System.out.println("With matrix size " + matrixSize + " and " + elementsCount + " elements:");
    System.out.println();

    // сложение
    System.out.print("adding square matrices... ");
    startTime = System.currentTimeMillis();
    squareMatrices[2] = squareMatrices[0].add(squareMatrices[1]);
    middleTime = System.currentTimeMillis();
    delta1 = middleTime - startTime;
    System.out.println("done in " + delta1 + " ms");

    System.out.print("adding sparse matrices... ");
    middleTime = System.currentTimeMillis();
    sparseMatrices[2] = sparseMatrices[0].add(sparseMatrices[1]);
    endTime = System.currentTimeMillis();
    delta2 = endTime - middleTime;
    System.out.println("done in " + delta2 + " ms");

    times = String.format(Locale.US, "%.2f", (double) delta2 / delta1);
    System.out.println("sparse matrix is " + times + " times faster");

    // проверка на равенство
    System.out.print("square matrix sum equals sparse matrix sum: ");
    System.out.println(squareMatrices[2].equals((Matrix) sparseMatrices[2]));
    System.out.println();

    // умножение
    System.out.print("multiplying square matrices... ");
    startTime = System.currentTimeMillis();
    squareMatrices[3] = squareMatrices[0].product(squareMatrices[1]);
    middleTime = System.currentTimeMillis();
    delta1 = middleTime - startTime;
    System.out.println("done in " + delta1 + " ms");

    System.out.print("multiplying sparse matrices... ");
    middleTime = System.currentTimeMillis();
    sparseMatrices[3] = sparseMatrices[0].product(sparseMatrices[1]);
    endTime = System.currentTimeMillis();
    delta2 = endTime - middleTime;
    System.out.println("done in " + delta2 + " ms");

    times = String.format(Locale.US, "%.2f", (double) delta2 / delta1);
    System.out.println("sparse matrix is " + times + " times faster");

    // проверка на равенство
    System.out.print("square matrix product equals sparse matrix product: ");
    System.out.println(squareMatrices[3].equals((Matrix) sparseMatrices[3]));
  }
}
