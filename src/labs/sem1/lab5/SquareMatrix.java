package labs.sem1.lab5;

public class SquareMatrix extends UsualMatrix {
  public SquareMatrix(int size) {
    super(size, size);
  }

  public SquareMatrix add(SquareMatrix matrix) {
    return (SquareMatrix) super.add(matrix);
  }

  public SquareMatrix multiply(SquareMatrix matrix) {
    return (SquareMatrix) super.multiply(matrix);
  }

  public Matrix createMatrix(int rows, int columns) {
    return new SquareMatrix(rows);
  }
}
