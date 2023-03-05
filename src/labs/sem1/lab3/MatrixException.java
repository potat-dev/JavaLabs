package labs.sem1.lab3;

class MatrixException extends RuntimeException {
  public MatrixException(String message) {
    super("Matrix Exception: " + message);
  }
}

class MatrixSumException extends MatrixException {
  public MatrixSumException(String message) {
    super("Matrix Sum Exception: " + message);
  }
}

class MatrixProductException extends MatrixException {
  public MatrixProductException(String message) {
    super("Matrix Product Exception: " + message);
  }
}
