package labs.sem1.lab5;

import java.util.LinkedList;

public class SparseMatrix extends Matrix {
  private LinkedList<SparseMatrixRow> rows;

  public SparseMatrix(int rows, int columns) {
    super(rows, columns);
    this.rows = new LinkedList<SparseMatrixRow>();
  }

  public SparseMatrix(int size) {
    this(size, size);
  }

  public double getElement(int row, int column) {
    for (SparseMatrixRow currentRow : rows) {
      if (currentRow.getRow() == row) {
        for (SparseMatrixElement element : currentRow.getElements()) {
          if (element.getColumn() == column) {
            return element.getValue();
          }
        }
        return 0;
      }
    }
    return 0;
  }

  public void setElement(int row, int column, double value) {
    for (SparseMatrixRow currentRow : rows) {
      if (currentRow.getRow() == row) {
        for (SparseMatrixElement element : currentRow.getElements()) {
          if (element.getColumn() == column) {
            element.setValue(value);
            return;
          }
        }
        currentRow.addElement(new SparseMatrixElement(column, value));
        return;
      }
    }
    SparseMatrixRow newRow = new SparseMatrixRow(row);
    newRow.addElement(new SparseMatrixElement(column, value));
    rows.add(newRow);
  }

  public SparseMatrix add(SparseMatrix matrix) {
    return (SparseMatrix) super.add(matrix);
  }

  public SparseMatrix product(SparseMatrix matrix) {
    return (SparseMatrix) super.product(matrix);
  }

  public Matrix createMatrix(int rows, int columns) {
    return new SparseMatrix(rows, columns);
  }
}

class SparseMatrixRow {
  private int row;
  private LinkedList<SparseMatrixElement> elements;

  public SparseMatrixRow(int row) {
    this.row = row;
    elements = new LinkedList<SparseMatrixElement>();
  }

  public int getRow() {
    return row;
  }

  public LinkedList<SparseMatrixElement> getElements() {
    return elements;
  }

  public void addElement(SparseMatrixElement element) {
    elements.add(element);
  }
}

class SparseMatrixElement {
  private int column;
  private double value;

  public SparseMatrixElement(int column, double value) {
    this.column = column;
    this.value = value;
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
