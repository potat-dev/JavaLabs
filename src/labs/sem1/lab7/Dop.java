package labs.sem1.lab7;

import java.util.ArrayList;
import java.io.*;

public class Dop {
  public static void main(String[] args) {
    Library library = new Library();

    library.addBook("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979);
    library.addBook("The Lord of the Rings", "J. R. R. Tolkien", 1954);
    library.addBook("Harry Potter and the Philosopher's Stone", "J. K. Rowling", 1997);
    library.addBook("War and Peace", "Leo Tolstoy", 1869);
    library.addBook("The Little Prince", "Antoine de Saint-Exupery", 1943);

    System.out.println("All books:");
    System.out.println(library);
    System.out.println();

    String filePath = "src/labs/sem1/lab7/library.csv";
    library.saveToTextFile(filePath);

    Library library2 = new Library();
    library2.loadFromTextFile(filePath);

    System.out.println("Books after 1970:");
    System.out.println(library2.getBooksAfterYear(1970));
  }
}

class Library {
  private ArrayList<Book> books;

  public Library() {
    books = new ArrayList<Book>();
  }

  public void addBook(Book book) {
    books.add(book);
  }

  public void addBook(String name, String author, int year) {
    books.add(new Book(name, author, year));
  }

  public Book getBook(String name) {
    for (Book book : books) {
      if (book.name.equals(name)) {
        return book;
      }
    }

    return null;
  }

  public Library getBooksAfterYear(int year) {
    Library booksAfterYear = new Library();

    for (Book book : books) {
      if (book.year > year) {
        booksAfterYear.addBook(book);
      }
    }

    return booksAfterYear;
  }

  public void saveToTextFile(String filename) {
    try {
      PrintWriter out = new PrintWriter(new FileWriter(filename));

      for (Book book : books) {
        out.println(book.toCSV());
      }

      out.flush();
      out.close();
    } catch (IOException e) {
      throw new RuntimeException("Error while saving to text file: " + e.getMessage());
    }
  }

  public void loadFromTextFile(String filename) {
    try {
      BufferedReader in = new BufferedReader(new FileReader(filename));
      books.clear();

      String line;
      while ((line = in.readLine()) != null) {
        books.add(new Book(line));
      }

      in.close();
    } catch (IOException e) {
      throw new RuntimeException("Error while loading from text file: " + e.getMessage());
    }
  }

  @Override
  public String toString() {
    String str = "";

    for (Book book : books) {
      str += book.toString() + "\n";
    }

    // remove last newline
    return str.substring(0, str.length() - 1);
  }
}

class Book {
  protected String name, author;
  protected int year;

  public Book() {
    this(null, null, 0);
  }

  public Book(String name, String author, int year) {
    // check name and author not contains comma
    if (name.contains(",") || author.contains(",")) {
      throw new IllegalArgumentException("Name and author must not contain comma");
    }

    this.name = name;
    this.author = author;
    this.year = year;
  }

  public Book(String str) {
    fromString(str);
  }

  @Override
  public String toString() { // for output to console
    return "\"" + name + "\" - " + author + ", " + year;
  }

  public String toCSV() { // for output to file
    return name + "," + author + "," + year;
  }

  public void fromString(String str) {
    String[] parts = str.split(",");

    if (parts.length != 3) {
      throw new IllegalArgumentException("Invalid string format");
    }

    name = parts[0];
    author = parts[1];
    try {
      year = Integer.parseInt(parts[2]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid year format");
    }
  }
}