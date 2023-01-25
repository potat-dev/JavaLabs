package labs.sem1.lab5;

public class SquareMatrix extends UsualMatrix {
  public SquareMatrix(int size) {
    super(size, size);
  }

  public Matrix createMatrix(int rows, int columns) {
    return new SquareMatrix(rows);
  }
}
