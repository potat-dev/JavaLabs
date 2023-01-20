package labs.sem1.lab2;

import java.util.Arrays;

public class Matrix {
  private int size;
  private int[][] matrix;

  public Matrix() {
    this.size = 0;
    this.matrix = new int[0][0];
  }

  public Matrix(int size) {
    this.size = size;
    this.matrix = new int[size][size];
  }

  public void setElement(int row, int column, int value) {
    this.matrix[row][column] = value;
  }

  public int getElement(int row, int column) {
    return this.matrix[row][column];
  }

  // multiply this matrix with another matrix
  public Matrix multiply(Matrix other) {
    Matrix result = new Matrix(this.size);

    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        int sum = 0;

        for (int k = 0; k < this.size; k++) {
          sum += this.matrix[i][k] * other.matrix[k][j];
        }

        result.matrix[i][j] = sum;
      }
    }

    return result;
  }

  // sum this matrix with another matrix
  public Matrix add(Matrix other) {
    Matrix result = new Matrix(this.size);

    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
      }
    }

    return result;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for (int[] row : this.matrix) {
      builder.append(Arrays.toString(row));
      builder.append("\n");
    }

    return builder.toString();
  }
}
