package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Library {
  private final List<Book> books;
  private final List<User> users;
  private final List<Author> authors;

  public Library() {
    this.books = new ArrayList<>();
    this.users = new ArrayList<>();
    this.authors = new ArrayList<>();
  }

  public void addBook(String title, BookGenre genre, Author author) {
    books.add(new Book(title, genre, author));
  }

  public void addUser(String name, String cpf) {
    for (var registeredUser : this.users) {
      if (Objects.equals(registeredUser.getCpf(), cpf)) {
        System.out.println("Um usuário já existe com esse CPF. Cadastro abortado.");
        return;
      }
    }

    this.users.add(new User(name, cpf));
    System.out.printf("Novo usuário cadastrado. Nome: %s\nCPF:%s\n", name, cpf);
  }

  public List<User> getUsers() {
    return this.users;
  }

  public User searchUserByCpf(String cpf) {
    for (var registeredUser : this.users) {
      if (Objects.equals(registeredUser.getCpf(), cpf)) {
        return registeredUser;
      }
    }

    return null;
  }

  public void borrowBook(User user, Book book) {
    for (var registeredUser : this.users) {
      if (Objects.equals(registeredUser.getCpf(), user.getCpf())) {
        registeredUser.borrowBook(book);
        return;
      }
    }
  }

  public void returnBook(User user, Book book) {
    for (var registeredUser : this.users) {
      if (Objects.equals(registeredUser.getCpf(), user.getCpf())) {
        registeredUser.returnBook(book);
        return;
      }
    }
  }

  public ArrayList<Book> getAvailableBooks() {
    var availableBooks = new ArrayList<Book>();
    for (var book : books) {
      if (book.isAvailable()) {
        availableBooks.add(book);
      }
    }

    if (availableBooks.isEmpty()) {
      System.out.println("Nenhum livro disponível para empréstimo");
    }

    return availableBooks;
  }

  public Book searchBookByTitle(String title) {
    for (var book : books) {
      if (Objects.equals(book.getTitle(), title)) {
        return book;
      }
    }
    return null;
  }

  public void editBook(Book book, String newTitle, BookGenre genre, Author author) {
    if (books.contains(book)) {
      book.setTitle(newTitle);
      book.setGenre(genre);
      book.setAuthor(author);
    }
  }

  public List<Book> getBooks() {
    return this.books;
  }

  public void editUser(User user, String newCpf, String newName) {
    if (users.contains(user)) {
      user.setName(newName);
      user.setCpf(newCpf);
    }
  }

  public void addAuthor(String name) {
    for (var registeredAuthors : this.authors) {
      if (Objects.equals(registeredAuthors.getName(), name)) {
        System.out.println("Um autor já existe com esse nome. Cadastro abortado.");
        return;
      }
    }

    authors.add(new Author(name));
    System.out.printf("Novo autor registrado. Nome: %s\n", name);
  }

  public void editAuthor(Author author, String newName) {
    if (authors.contains(author)) {
      author.setName(newName);
    }
  }

  public List<Author> getAuthors() {
    return this.authors;
  }

  public Author searchAuthorByName(String author) {
    for (var registeredAuthors : this.authors) {
      if (Objects.equals(registeredAuthors.getName(), author)) {
        return registeredAuthors;
      }
    }

    return null;
  }
}
