package labs.sem1.lab3;

// Создать класс FourPartMatrix, расширяющий класс SquareMatrix.
// Вторая четвертинка равна первой, четвертая - третьей.
// Добавить MatrixSumException, MatrixProductException

public class Dop {
  public static void main(String[] args) {
    FourPartMatrix matrix = new FourPartMatrix(6);

    matrix.setElement(0, 0, 1);
    matrix.setElement(0, 1, 2);
    matrix.setElement(1, 0, 3);
    matrix.setElement(1, 1, 4);

    System.out.println(matrix);

    matrix.setElement(3, 0, 7);
    matrix.setElement(4, 1, 7);
    matrix.setElement(5, 2, 7);

    System.out.println(matrix);
  }
}
