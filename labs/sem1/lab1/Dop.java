package labs.sem1.lab1;

// Доп:
// Создать класс Circle для работы с фигурой круг
// Мтоды: bool isInside(int x, y) и zoom(int amount)

public class Dop {
  public static void main(String[] args) {
    Circle circle = new Circle(0, 0, 10);

    System.out.println(circle.isInside(0, 0));
    System.out.println(circle.isInside(0, 10));

    System.out.println(circle.isInside(0, 12));

    circle.zoom(2);
    System.out.println(circle.isInside(0, 12));
  }
}

class Circle {
  private int x, y, r;

  public Circle() {
    x = 0;
    y = 0;
    r = 0;
  }

  public Circle(int x, int y, int r) {
    this.x = x;
    this.y = y;
    this.r = r;
  }

  public boolean isInside(int x, int y) {
    return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)) <= r;
  }

  public void zoom(int amount) {
    r *= amount;
  }
}