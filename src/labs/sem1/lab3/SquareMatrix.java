package labs.sem1.lab3;

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
