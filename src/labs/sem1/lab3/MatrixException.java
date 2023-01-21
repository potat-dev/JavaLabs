package labs.sem1.lab3;

class MatrixException extends RuntimeException {
  public MatrixException(String message) {
    super("MatrixException: " + message);
  }
}
