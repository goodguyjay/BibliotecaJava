package org.example;

import java.util.Objects;

public final class Book {
  private String title;
  // um livro pode ter mais de um gênero, mas por padrão vou utilizar o gênero primário
  private BookGenre genre;
  private boolean isAvailable;
  private Author author;

  public Book(String title, BookGenre genre, Author author) {
    this.title = title;
    // caso o gênero seja passado como null, o assign vai para o gênero "Outro"
    this.genre = Objects.requireNonNullElse(genre, BookGenre.Other);
    this.isAvailable = true;
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BookGenre getGenre() {
    return genre;
  }

  public void setGenre(BookGenre genre) {
    this.genre = genre;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }
}
