package labs.sem1.lab3;

class SquareMatrix extends Matrix {
  public SquareMatrix(int size) {
    super(size, size);
    for (int i = 0; i < size; i++) {
      matrix[i][i] = 1;
    }
  }

  public SquareMatrix() {
    this(1);
  }

  public SquareMatrix(int size, int fill) {
    super(size, size);
    if (fill == 0) {
      return;
    }
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        matrix[i][j] = fill;
      }
    }
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
        result.setElement(i, j, this.getElement(i, j) + matrix.getElement(i, j));
      }
    }

    return result;
  }
}
