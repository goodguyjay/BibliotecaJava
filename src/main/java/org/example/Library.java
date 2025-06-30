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
    System.out.printf(
        "Novo livro cadastrado. Título: %s\nGênero: %s\nAutor: %s\n", title, genre, author);
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

  public List<Author> getAuthors() {
    return this.authors;
  }

  public boolean doesAuthorExist(String author) {
    for (var registeredAuthors : this.authors) {
      if (Objects.equals(registeredAuthors.getName(), author)) {
        return true;
      }
    }

    return false;
  }
}
