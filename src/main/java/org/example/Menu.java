package org.example;

import java.util.Scanner;

public final class Menu {
  private final Library library;

  public Menu() {
    this.library = new Library();
  }

  public void PrintOptions() {
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

  public void ReadOption() {
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
            PrintOptions();
            option = sc.nextLine();
            break;
          }

        case "2":
          {
            System.out.println("Digite o nome do autor:");
            var name = sc.nextLine();
            createAuthor(name);
            PrintOptions();
            option = sc.nextLine();
            break;
          }

        case "3":
          {
            System.out.println("Digite o título do livro:");
            var title = sc.nextLine();

            listGenres();
            var genre = sc.nextLine();
            var parsedGenre = castGenreStrToEnum(genre);
            while (parsedGenre == null) {
              genre = sc.nextLine();
              parsedGenre = castGenreStrToEnum(genre);
            }

            System.out.println("Digite o nome do autor:");
            var author = sc.nextLine();
            var parsedAuthor = castAuthorStrToClass(author);
            while (parsedAuthor == null) {
              author = sc.nextLine();
              parsedAuthor = castAuthorStrToClass(author);
            }

            createBook(title, parsedGenre, parsedAuthor);

            break;
          }

        case "9":
          {
            getAllUsers();
            PrintOptions();
            option = sc.nextLine();
            break;
          }

        default:
          System.out.println("Opção inválida. Por favor tente novamente.");
          PrintOptions();
          option = sc.nextLine();
          break;
      }
    }
  }

  public void createUser(String name, String cpf) {
    library.addUser(name, cpf);
  }

  public void createAuthor(String name) {
    library.addAuthor(name);
  }

  public void createBook(String title, BookGenre genre, Author author) {
    library.addBook(title, genre, author);
  }

  public void listGenres() {
    System.out.println("Selecione o gênero: ");
    for (var i = 0; i < BookGenre.values().length; i++) {
      System.out.printf("Gênero: %s\n", BookGenre.values()[i].toString());
    }
  }

  public void getAllUsers() {
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

  private Author castAuthorStrToClass(String author) {
    try {
      var castedAuthor = Author.class.cast(author.trim());
      if (library.doesAuthorExist(author)) {
        return castedAuthor;
      } else {
        System.out.println("Nenhum autor com esse nome foi encontrado");
        return null;
      }
    } catch (ClassCastException e) {
      System.out.println("Autor inválido. Por favor tente novamente");
      return null;
    }
  }
}
