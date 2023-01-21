package labs.sem1.lab3;

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
