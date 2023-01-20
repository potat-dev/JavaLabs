package labs.sem1.lab2;

public class Lab2 {
  public static void main(String[] args) {
    // Напишите программу, выводящую первые 10 степеней матрицы:
    // [1 1]
    // [1 0]

    // create a matrix
    Matrix inputMatrix = new Matrix(2);

    // set some values
    inputMatrix.setElement(0, 0, 1);
    inputMatrix.setElement(0, 1, 1);
    inputMatrix.setElement(1, 0, 1);
    inputMatrix.setElement(1, 1, 0);

    // print the matrix
    System.out.println("Power 1:");
    System.out.println(inputMatrix);

    // print the first 10 powers of the matrix
    Matrix matrix = inputMatrix;
    for (int i = 2; i <= 10; i++) {
      matrix = matrix.multiply(inputMatrix);
      System.out.println("Power " + i + ":");
      System.out.println(matrix);
    }
  }

  public static void test() {
    // create a matrix
    Matrix matrix = new Matrix(3);

    // set some values
    matrix.setElement(0, 0, 1);
    matrix.setElement(0, 1, 2);
    matrix.setElement(0, 2, 3);
    matrix.setElement(1, 0, 4);
    matrix.setElement(1, 1, 5);
    matrix.setElement(1, 2, 6);
    matrix.setElement(2, 0, 7);
    matrix.setElement(2, 1, 8);
    matrix.setElement(2, 2, 9);

    // print the matrix
    System.out.println(matrix);
  }
}
