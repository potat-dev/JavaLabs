package labs.sem1.lab5;

public class UsualMatrix extends Matrix {
  private double[][] matrix;

  public UsualMatrix(int rows, int columns) {
    super(rows, columns);
    matrix = new double[rows][columns];
  }

  public double getElement(int row, int column) {
    return matrix[row][column];
  }

  public void setElement(int row, int column, double value) {
    matrix[row][column] = value;
  }

  public UsualMatrix add(UsualMatrix matrix) {
    return (UsualMatrix) super.add(matrix);
  }

  public UsualMatrix multiply(UsualMatrix matrix) {
    return (UsualMatrix) super.multiply(matrix);
  }

  public Matrix createMatrix(int rows, int columns) {
    return new UsualMatrix(rows, columns);
  }
}
