package labs.sem1.lab2;

// доп. добавить в класс метод rotate(int dir)
// dir - направление вращения
// 1 - 90 градусов, 2 - 180 градусов, 3 - 270 градусов

class MatrixDop extends Matrix {
  public MatrixDop(int size) {
    super(size);
  }

  public void rotate(int direction) {
    Matrix result = new Matrix(this.size);
    switch (direction % 4) {
      case 1:
        for (int i = 0; i < this.size; i++) {
          for (int j = 0; j < this.size; j++) {
            result.matrix[i][j] = this.matrix[this.size - j - 1][i];
          }
        }
        break;
      case 2:
        for (int i = 0; i < this.size; i++) {
          for (int j = 0; j < this.size; j++) {
            result.matrix[i][j] = this.matrix[this.size - i - 1][this.size - j - 1];
          }
        }
        break;
      case 3:
        for (int i = 0; i < this.size; i++) {
          for (int j = 0; j < this.size; j++) {
            result.matrix[i][j] = this.matrix[j][this.size - i - 1];
          }
        }
        break;
    }
    this.matrix = result.matrix;
  }
}

public class Dop {
  public static void main(String[] args) {
    MatrixDop matrix = new MatrixDop(3);

    // set some values
    matrix.setElement(0, 0, 1);
    matrix.setElement(0, 1, 2);
    matrix.setElement(0, 2, 3);
    matrix.setElement(1, 0, 4);
    matrix.setElement(1, 1, 5);
    matrix.setElement(1, 2, 6);
    matrix.setElement(2, 0, 7);
    matrix.setElement(2, 1, 8);
    matrix.setElement(2, 2, 9);

    // print the matrix
    System.out.println(matrix);

    matrix.rotate(1);
    System.out.println(matrix);

    matrix.rotate(2);
    System.out.println(matrix);

    matrix.rotate(3);
    System.out.println(matrix);
  }
}
