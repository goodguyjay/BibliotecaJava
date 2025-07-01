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
        12. Verificar se o autor existe pelo nome
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

        case "6":
          {
            System.out.println("Digite o título do livro:");
            var bookTitle = sc.nextLine();
            var book = searchBookByTitle(bookTitle);

            for (var i = 0; i < 3 && book == null; i++) {
              System.out.println("Livro não encontrado. Por favor verifique o título:");
              bookTitle = sc.nextLine();
              book = searchBookByTitle(bookTitle);
            }

            if (book == null) {
              System.out.println("Máximo de tentativas alcançadas. Cancelando operação");
              printOptions();
              option = sc.nextLine();
              break;
            }

            System.out.println("Digite o novo título do livro:");
            var newBookTitle = sc.nextLine();

            System.out.println("Digite o novo gênero do livro:");
            BookGenre parsedGenre = null;
            for (var i = 0; i < 3; i++) {
              listGenres();
              var genre = sc.nextLine();
              parsedGenre = castGenreStrToEnum(genre);

              if (parsedGenre != null) {
                break;
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
            String authorName = null;
            for (var i = 0; i < 3; i++) {
              System.out.println("Digite o nome do autor:");
              authorName = sc.nextLine();
              parsedAuthor = searchAuthorByName(authorName);

              if (parsedAuthor != null) {
                break;
              }

              System.out.println("Autor não encontrado. Por favor tente novamente");
            }

            if (parsedAuthor == null) {
              System.out.println("Máximo de tentativas alcançadas. Cancelando operação");
              printOptions();
              option = sc.nextLine();
              break;
            }

            editBook(book, newBookTitle, parsedGenre, parsedAuthor);

            System.out.printf(
                "Livro editado\nTítulo antigo: %s\nTítulo novo: %s\nAutor antigo: %s\nAutor novo:%s\n",
                bookTitle, newBookTitle, authorName, parsedAuthor.getName());

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

        case "8":
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

            if (!user.hasAnyBorrowedBooks()) {
              System.out.println("O usuário não tem nenhum livro para devolver");
              break;
            }

            System.out.println("Digite o nome do livro:");
            var bookTitle = sc.nextLine();
            var book = searchBookByTitle(bookTitle);

            for (var i = 0; i < 3 && book == null; i++) {
              System.out.println("Livro não encontrado. Por favor tente outro livro");
              bookTitle = sc.nextLine();
              book = searchBookByTitle(bookTitle);
            }

            if (book == null) {
              System.out.println("Máximo de tentativas alcançadas. Cancelando operação");
              break;
            }

            returnBook(user, book);
            System.out.printf(
                "Livro retornado: %s\nUsuário: %s\n", book.getTitle(), user.getName());

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

        case "10":
          {
            System.out.println("Digite o CPF do usuário");
            var cpf = sc.nextLine();
            var user = searchUserByCpf(cpf);

            if (user == null) {
              System.out.printf("O usuário com o CPF: %s não existe nos dados do sistema\n", cpf);
              break;
            }

            if (user.hasAnyBorrowedBooks()) {
              System.out.printf(
                  "O usuário com o CPF: %s e nome %s está registrado no sistema e possui o livro %s emprestado\n",
                  user.getCpf(), user.getName(), user.getBorrowedBook().getTitle());
            } else {
              System.out.printf(
                  "O usuário com o CPF: %s e nome %s está registrado no sistema e não possui nenhum livro emprestado\n",
                  user.getCpf(), user.getName());
            }

            break;
          }

        case "11":
          {
            getAllAuthors();
            printOptions();
            option = sc.nextLine();
            break;
          }

        case "12":
          {
            System.out.println("Digite o nome do autor");
            var authorName = sc.nextLine();
            var author = searchAuthorByName(authorName);

            if (author == null) {
              System.out.printf(
                  "O autor com o nome: %s não existe nos dados do sistema\n", authorName);
              break;
            }

            System.out.printf("O autor com o nome: %s existe no sistema\n", author.getName());
          }

        case "13":
          {
            System.out.println("Digite o título do livro:");
            var bookTitle = sc.next();
            var book = searchBookByTitle(bookTitle);

            if (book == null) {
              System.out.printf(
                  "O livro com o nome: %s não existe nos dados do sistema\n", bookTitle);
              break;
            }

            if (book.isAvailable()) {
              System.out.printf(
                  "O livro com o nome: %s está disponível no sistema", book.getTitle());
            } else {
              System.out.printf(
                  "O livro com o nome: %s está indisponível no sistema", book.getTitle());
            }

            printOptions();
            option = sc.nextLine();
            break;
          }

        case "14":
          {
            System.out.println("Digite o CPF do usuário:");
            var cpf = sc.nextLine();
            var user = searchUserByCpf(cpf);

            for (var i = 0; i < 3 & user == null; i++) {
              System.out.println("Usuário não encontrado");
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
              var book = user.getBorrowedBook();
              System.out.printf(
                  "O usuário %s tem o livro emprestado %s\n", user.getName(), book.getTitle());
              break;
            }

            System.out.println("O usuário não possui nenhum livro emprestado");

            printOptions();
            option = sc.nextLine();
            break;
          }

        case "15":
          {
            System.out.println("Digite o título do livro:");
            var bookTitle = sc.nextLine();
            var book = searchBookByTitle(bookTitle);

            for (var i = 0; i < 3 && book == null; i++) {
              System.out.println("Livro não encontrado");
              bookTitle = sc.nextLine();
              book = searchBookByTitle(bookTitle);
            }

            if (book == null) {
              System.out.println("Máximo de tentativas alcançado. Cancelando operação");
              printOptions();
              option = sc.nextLine();
              break;
            }

            if (book.isAvailable()) {
              System.out.printf("O livro %s está disponível!\n", book.getTitle());
            } else {
              System.out.printf("O livro %s não está disponível\n", book.getTitle());
            }

            printOptions();
            option = sc.nextLine();
            break;
          }

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

  private void returnBook(User user, Book book) {
    library.returnBook(user, book);
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

  private void getAllAuthors() {
    for (var author : library.getAuthors()) {
      System.out.println("--- Autor ---");
      System.out.printf("Nome: %s\n", author.getName());
      System.out.println("--------------");
    }
  }

  private void createBook(String title, BookGenre genre, Author author) {
    library.addBook(title, genre, author);
  }

  private void editBook(Book book, String newTitle, BookGenre newGenre, Author author) {
    library.editBook(book, newTitle, newGenre, author);
  }

  private Book searchBookByTitle(String title) {
    return library.searchBookByTitle(title);
  }

  private void printAvailableBooks() {
    var books = library.getAvailableBooks();

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
