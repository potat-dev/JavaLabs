package labs.sem1.lab3;

class MatrixException extends RuntimeException {
  public MatrixException(String message) {
    super("MatrixException: " + message);
  }
}

class Matrix {
  protected int rows;
  protected int columns;
  protected int[][] matrix;

  public Matrix(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    this.matrix = new int[rows][columns];
  }

  public Matrix(int size) {
    this(size, size);
  }

  public Matrix() {
    this(1, 1);
  }

  public Matrix sum(Matrix matrix) {
    if (this.rows != matrix.rows || this.columns != matrix.columns) {
      throw new MatrixException("Matrices have different sizes");
    }

    Matrix result = new Matrix(this.rows, this.columns);

    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        result.matrix[i][j] = this.matrix[i][j] + matrix.matrix[i][j];
      }
    }

    return result;
  }

  public Matrix product(Matrix matrix) {
    if (this.columns != matrix.rows) {
      throw new MatrixException("Matrices have different sizes");
    }

    Matrix result = new Matrix(this.rows, matrix.columns);

    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < matrix.columns; j++) {
        for (int k = 0; k < this.columns; k++) {
          result.matrix[i][j] += this.matrix[i][k] * matrix.matrix[k][j];
        }
      }
    }

    return result;
  }

  public void setElement(int row, int column, int value) {
    if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
      throw new MatrixException("Index out of bounds");
    }

    this.matrix[row][column] = value;
  }

  public int getElement(int row, int column) {
    if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
      throw new MatrixException("Index out of bounds");
    }

    return this.matrix[row][column];
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        result.append(this.matrix[i][j]).append(" ");
      }
      result.append("\n");
    }

    return result.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }

    Matrix matrix = (Matrix) obj;

    if (this.rows != matrix.rows || this.columns != matrix.columns) {
      return false;
    }

    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        if (this.matrix[i][j] != matrix.matrix[i][j]) {
          return false;
        }
      }
    }

    return true;
  }
}

class SquareMatrix extends Matrix {
  public SquareMatrix(int size) {
    super(size, size);
    for (int i = 0; i < size; i++) {
      this.matrix[i][i] = 1;
    }
  }

  public SquareMatrix() {
    this(1);
  }

  public SquareMatrix sum(SquareMatrix matrix) {
    // return (SquareMatrix) super.sum(matrix);
    // - is not correct, because we can't cast Matrix to SquareMatrix
    // because Matrix can be not SquareMatrix

    if (this.rows != matrix.rows) {
      throw new MatrixException("Matrices have different sizes");
    }

    SquareMatrix result = new SquareMatrix(this.rows);

    for (int i = 0; i < this.rows; i++) {
      for (int j = 0; j < this.columns; j++) {
        result.matrix[i][j] = this.matrix[i][j] + matrix.matrix[i][j];
      }
    }

    return result;
  }
}

public class Main {
  public static void main(String[] args) {

    SquareMatrix matrix1 = new SquareMatrix(2);
    SquareMatrix matrix2 = new SquareMatrix(2);

    matrix1.setElement(0, 0, 1);
    matrix1.setElement(0, 1, 2);
    matrix1.setElement(1, 0, 3);
    matrix1.setElement(1, 1, 4);

    matrix2.setElement(0, 0, 5);
    matrix2.setElement(0, 1, 6);
    matrix2.setElement(1, 0, 7);
    matrix2.setElement(1, 1, 8);

    System.out.println("matrix 1:");
    System.out.println(matrix1);

    System.out.println("matrix 2:");
    System.out.println(matrix2);

    SquareMatrix matrix3 = matrix1.sum(matrix2);

    System.out.println("matrix 1 + matrix 2:");
    System.out.println(matrix3);

    SquareMatrix matrix4 = matrix1;
    System.out.println("matrix 4:");
    System.out.println(matrix4);

    System.out.println("test of equals:");
    System.out.println("matrix 1 equals matrix 2: " + matrix1.equals(matrix2));
    System.out.println("matrix 1 equals matrix 4: " + matrix1.equals(matrix4));

    System.out.println();
    System.out.println("test of exceptions:");

    SquareMatrix matrix5 = new SquareMatrix(3);
    SquareMatrix matrix6 = new SquareMatrix(2);

    try {
      SquareMatrix matrix7 = matrix5.sum(matrix6);
      System.out.println(matrix7);
    } catch (MatrixException e) {
      System.out.println(e.getMessage());
    }

    try {
      SquareMatrix matrix7 = matrix5.sum(matrix5);
      System.out.println(matrix7.getElement(42, 42));
    } catch (MatrixException e) {
      System.out.println(e.getMessage());
    }
  }
}