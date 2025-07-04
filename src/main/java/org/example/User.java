package org.example;

public final class User {
  private String name;
  private String cpf;
  private Book borrowedBook;

  public User(String name, String cpf) {
    this.name = name;
    this.cpf = cpf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Book getBorrowedBook() {
    return this.borrowedBook;
  }

  public void borrowBook(Book book) {
    if (book.isAvailable()) {
      this.borrowedBook = book;
      book.setAvailable(false);
    } else {
      System.out.println(
          "O livro está indisponível no momento. Por favor volte outro dia ou selecione outro livro");
    }
  }

  public boolean hasAnyBorrowedBooks() {
    return this.borrowedBook != null;
  }

  public void returnBook(Book book) {
    this.borrowedBook = null;
    book.setAvailable(true);
  }
}
