package labs.sem1.lab5;

public interface IMatrix {
  // setElement, getElement, getRows, getColumns

  public int getRows();

  public int getColumns();

  public double getElement(int row, int column);

  public void setElement(int row, int column, double value);
}
