package labs.sem1.lab3;

// Создать класс FourPartMatrix, расширяющий класс SquareMatrix
// Вторая четвертинка равна первой, четвертая - третьей
// Добавить MatrixSumException, MatrixProductException

public class FourPartMatrix extends SquareMatrix {
  protected int[][] second;

  public FourPartMatrix() {
    this(2);
  }

  public FourPartMatrix(int size) {
    super(size / 2, 0);
    second = new int[size / 2][size / 2];
    rows = size;
    columns = size;
    if (size % 2 != 0) {
      throw new MatrixException("Size must be even");
    }
  }

  public int getElement(int row, int column) {
    if (row < 0 || row >= rows || column < 0 || column >= columns) {
      throw new MatrixException("Index out of bounds");
    }
    column = column < columns / 2 ? column : column - columns / 2;
    if (row < rows / 2) {
      return matrix[row][column];
    } else {
      return second[row - rows / 2][column];
    }
  }

  public void setElement(int row, int column, int value) {
    if (row < 0 || row >= rows || column < 0 || column >= columns) {
      throw new MatrixException("Index out of bounds");
    }
    column = column < columns / 2 ? column : column - columns / 2;
    if (row < rows / 2) {
      matrix[row][column] = value;
    } else {
      second[row - rows / 2][column] = value;
    }
  }
}
