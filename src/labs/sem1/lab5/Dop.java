package labs.sem1.lab5;

import java.util.LinkedList;
import java.util.ArrayList;

// доп: написать реализацию SparseMatrix на основе ArrayList<LinkedList<Element>>
// и сравнить время работы с реализацией на основе LinkedList<LinkedList<Element>>

public class Dop {
  public static void main(String[] args) {
    final int matrixSize = 200;
    final int elementsCount = 200;

    SparseMatrix sparseMatrix = new SparseMatrix(matrixSize);
    SparseMatrixFast sparseMatrixFast = new SparseMatrixFast(matrixSize);

    for (int i = 0; i < elementsCount; i++) {
      int row = (int) (Math.random() * matrixSize);
      int column = (int) (Math.random() * matrixSize);
      int value = (int) (Math.random() * 9);
      sparseMatrix.setElement(row, column, value);
      sparseMatrixFast.setElement(row, column, value);
    }

    long startTime = System.currentTimeMillis();
    sparseMatrix.product(sparseMatrix);
    long mediumTime = System.currentTimeMillis();
    sparseMatrixFast.product(sparseMatrixFast);
    long endTime = System.currentTimeMillis();

    long delta1 = mediumTime - startTime;
    long delta2 = endTime - mediumTime;

    System.out.println("SparseMatrix time: " + delta1);
    System.out.println("SparseMatrixFast time: " + delta2);
    System.out.println("SparseMatrixFast is " + (double) delta1 / delta2 + " times faster");
  }
}

class SparseMatrixFast extends Matrix {
  private ArrayList<LinkedList<SparseMatrixElement>> rows;

  public SparseMatrixFast(int rows, int columns) {
    super(rows, columns);
    this.rows = new ArrayList<LinkedList<SparseMatrixElement>>();
    for (int i = 0; i < rows; i++) {
      this.rows.add(new LinkedList<SparseMatrixElement>());
    }
  }

  public SparseMatrixFast(int size) {
    this(size, size);
  }

  public double getElement(int row, int column) {
    for (SparseMatrixElement element : rows.get(row)) {
      if (element.getColumn() == column) {
        return element.getValue();
      }
    }
    return 0;
  }

  public void setElement(int row, int column, double value) {
    for (SparseMatrixElement element : rows.get(row)) {
      if (element.getColumn() == column) {
        element.setValue(value);
        return;
      }
    }
    rows.get(row).add(new SparseMatrixElement(column, value));
  }

  public SparseMatrixFast add(SparseMatrixFast matrix) {
    return (SparseMatrixFast) super.add(matrix);
  }

  public SparseMatrixFast product(SparseMatrixFast matrix) {
    return (SparseMatrixFast) super.product(matrix);
  }

  public Matrix createMatrix(int rows, int columns) {
    return new SparseMatrixFast(rows, columns);
  }
}
