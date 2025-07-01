package org.example;

import java.util.Scanner;

public final class Menu {
  private final Library library;

  public Menu() {
    this.library = new Library();
  }

  public void printOptions() {
    var menu =
        """
        Bem vindo a livraria. Por favor, selecione uma opção:

        -- Criação --
        1. Criar Usuário
        2. Criar Autor
        3. Criar Livro
        --------------

        -- Edição --
        4. Editar Usuário
        5. Editar Autor
        6. Editar Livro
        --------------

        -- Gerenciamento de Livros --
        7. Pegar livro emprestado
        8. Devolver livro
        --------------

        -- Gerenciamento de Usuários --
        9. Verificar todos usuários
        10. Verificar se o usuário existe pelo CPF
        --------------

        -- Gerenciamento de Autores --
        11. Verificar todos autores
        12. Verificar se o usuário existe pelo nome
        --------------

        -- Visualização de dados --
        13. Verificar livros emprestados
        14. Verificar se o usuário tem algum livro emprestado
        15. Verificar se o livro está disponível para empréstimo
        --------------

        16. Sair
        """;

    System.out.println(menu);
  }

  public void readOption() {
    var sc = new Scanner(System.in);
    var option = sc.nextLine();

    while (!option.equals("16")) {
      switch (option) {
        case "1":
          {
            System.out.print("Digite o nome do novo usuário: ");
            var name = sc.nextLine();
            System.out.println("Digite o CPF do novo usuário: ");
            var cpf = sc.nextLine();
            createUser(name, cpf);
            printOptions();
            option = sc.nextLine();
            break;
          }

        case "2":
          {
            System.out.println("Digite o nome do autor:");
            var name = sc.nextLine();
            createAuthor(name);
            printOptions();
            option = sc.nextLine();
            break;
          }

        case "3":
          {
            System.out.println("Digite o título do livro:");
            var title = sc.nextLine();

            BookGenre parsedGenre = null;
            for (var i = 0; i < 3; i++) {
              listGenres();
              var genre = sc.nextLine();
              parsedGenre = castGenreStrToEnum(genre);

              if (parsedGenre != null) {
                break; // gênero válido, sai do loop
              }

              System.out.println("Gênero inválido. Por favor tente novamente");
            }

            if (parsedGenre == null) {
              System.out.println("Máximo de tentativas alcançadas. Cancelando operação");
              printOptions();
              option = sc.nextLine();
              break;
            }

            Author parsedAuthor = null;
            for (var i = 0; i < 3; i++) {
              System.out.println("Digite o nome do autor:");
              var author = sc.nextLine();
              parsedAuthor = searchAuthorByName(author);

              if (parsedAuthor != null) {
                break; // autor válido, sai do loop
              }

              System.out.println("Autor não encontrado. Por favor tente novamente");
            }

            if (parsedAuthor == null) {
              System.out.println("Máximo de tentativas alcançadas. Cancelando operação");
              printOptions();
              option = sc.nextLine();
              break;
            }

            createBook(title, parsedGenre, parsedAuthor);

            printOptions();
            option = sc.nextLine();
            break;
          }

        case "4":
          {
            System.out.println("Digite o atual CPF do usuário: ");
            var cpf = sc.nextLine();
            var user = searchUserByCpf(cpf);

            // tenta no máximo 3 vezes para não ficar preso no loop
            for (int i = 0; i < 3 && user == null; i++) {
              System.out.println("Usuário não encontrado. Por favor verifique o CPF:");
              cpf = sc.nextLine();
              user = searchUserByCpf(cpf);
            }

            if (user == null) {
              System.out.println("Máximo de tentativas alcançadas. Cancelando operação");
              printOptions();
              option = sc.nextLine();
              break;
            }

            System.out.println("Digite o novo CPF:");
            var newCpf = sc.nextLine();

            System.out.println("Digite o novo nome:");
            var newName = sc.nextLine();

            editUser(user, newCpf, newName);

            printOptions();
            option = sc.nextLine();
            break;
          }

        case "5":
          {
            System.out.println("Digite o nome do autor:");
            var name = sc.nextLine();
            var author = searchAuthorByName(name);

            for (int i = 0; i < 3 && author == null; i++) {
              System.out.println("Autor não encontrado. Por favor verifique o nome:");
              name = sc.nextLine();
              author = searchAuthorByName(name);
            }

            if (author == null) {
              System.out.println("Máximo de tentativas alcançadas. Cancelando operação");
              printOptions();
              option = sc.nextLine();
              break;
            }

            System.out.println("Digite o novo nome:");
            var newName = sc.nextLine();

            editAuthor(author, newName);

            printOptions();
            option = sc.nextLine();
            break;
          }

        case "7":
          {
            System.out.println("Digite o CPF do usuário:");
            var cpf = sc.nextLine();
            var user = searchUserByCpf(cpf);

            for (var i = 0; i < 3 & user == null; i++) {
              System.out.println("Usuário não encontrado. Por favor verifique o nome");
              cpf = sc.nextLine();
              user = searchUserByCpf(cpf);
            }

            if (user == null) {
              System.out.println("Máximo de tentativas alcançadas. Cancelando operação");
              printOptions();
              option = sc.nextLine();
              break;
            }

            if (user.hasAnyBorrowedBooks()) {
              System.out.println("O usuário já tem um livro emprestado");
              break;
            }

            System.out.println("Digite o nome do livro:");
            printAvailableBooks();
            var bookTitle = sc.nextLine();
            var book = searchBookByTitle(bookTitle);

            for (var i = 0; i < 3 && book == null; i++) {
              System.out.println("Livro não encontrado. Por favor tente outro livro");
              printAvailableBooks();
              bookTitle = sc.nextLine();
              book = searchBookByTitle(bookTitle);
            }

            if (book == null) {
              System.out.println("Máximo de tentativas alcançadas. Cancelando operação");
              break;
            }

            borrowBook(user, book);
            System.out.printf(
                "Livro emprestado: %s\nAutor:%s\nUsuário: %s\n",
                book.getTitle(), book.getAuthor(), user.getName());

            printOptions();
            option = sc.nextLine();
            break;
          }

        case "9":
          {
            getAllUsers();
            printOptions();
            option = sc.nextLine();
            break;
          }

        case "19":
          System.out.println("Adeus :)");
          break;

        default:
          {
            System.out.println("Opção inválida. Por favor tente novamente.");
            printOptions();
            option = sc.nextLine();
            break;
          }
      }
    }
  }

  private void createUser(String name, String cpf) {
    library.addUser(name, cpf);
  }

  private void borrowBook(User user, Book book) {
    library.borrowBook(user, book);
  }

  private void editUser(User user, String newCpf, String newName) {
    library.editUser(user, newCpf, newName);
  }

  private User searchUserByCpf(String cpf) {
    return library.searchUserByCpf(cpf);
  }

  private void createAuthor(String name) {
    library.addAuthor(name);
  }

  private void editAuthor(Author autor, String name) {
    library.editAuthor(autor, name);
  }

  private void createBook(String title, BookGenre genre, Author author) {
    library.addBook(title, genre, author);
  }

  private Book searchBookByTitle(String title) {
    return library.searchBookByTitle(title);
  }

  private void printAvailableBooks() {
    var books = library.getBooks();

    for (var book : books) {
      System.out.println("-- Livro --");
      System.out.printf("Book %s\n", book.getTitle());
      System.out.println("--------------");
    }
  }

  private void listGenres() {
    System.out.println("Selecione o gênero: ");
    for (var i = 0; i < BookGenre.values().length; i++) {
      System.out.printf("Gênero: %s\n", BookGenre.values()[i].toString());
    }
  }

  private void getAllUsers() {
    for (var user : library.getUsers()) {
      System.out.println("--- Usuário ---");
      System.out.printf("Nome: %s\nCPF: %s\n", user.getName(), user.getCpf());
      System.out.println("--------------");
    }
  }

  // helper
  private BookGenre castGenreStrToEnum(String genre) {
    try {
      return BookGenre.valueOf(genre.trim());
    } catch (IllegalArgumentException e) {
      System.out.println("Gênero inválido. Por favor tente novamente");
      return null;
    }
  }

  private Author searchAuthorByName(String author) {
    return library.searchAuthorByName(author);
  }
}
