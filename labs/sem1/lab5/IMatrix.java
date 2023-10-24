package labs.sem1.lab5;

public interface IMatrix {
  // интерфейс, который должны реализовать все матрицы
  // содержит методы, которые должны быть реализованы во всех матрицах

  public int getRows();

  public int getColumns();

  public double getElement(int row, int column);

  public void setElement(int row, int column, double value);
}
