package labs.sem1.lab5;

import java.util.LinkedList;

// SparseMatrix должен быть реализован с помощью LinkedList
// возможно, вам потребуется создать какие-то еще дополнительные классы,
// которые должны быть вложенными / внутренними

class SparseMatrixElement {
  private int row;
  private int column;
  private double value;

  public SparseMatrixElement(int row, int column, double value) {
    this.row = row;
    this.column = column;
    this.value = value;
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }
}

public class SparseMatrix extends Matrix {
  private LinkedList<SparseMatrixElement> elements;

  public SparseMatrix(int rows, int columns) {
    super(rows, columns);
    elements = new LinkedList<SparseMatrixElement>();
  }

  public SparseMatrix(int size) {
    this(size, size);
  }

  public double getElement(int row, int column) {
    for (SparseMatrixElement element : elements) {
      if (element.getRow() == row && element.getColumn() == column) {
        return element.getValue();
      }
    }

    return 0;
  }

  public void setElement(int row, int column, double value) {
    for (SparseMatrixElement element : elements) {
      if (element.getRow() == row && element.getColumn() == column) {
        element.setValue(value);
        return;
      }
    }

    elements.add(new SparseMatrixElement(row, column, value));
  }

  public Matrix createMatrix(int rows, int columns) {
    return new SparseMatrix(rows, columns);
  }
}