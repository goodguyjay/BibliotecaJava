## Descrição

Pequeno projeto de uma biblioteca hipotética feito em Java.

---

## Ferramentas utilizadas

- **IDE:** Intellij IDE Ultimate
- **Linter:** Linter integrado do Intellij
- **Formatador de código:** google-java-format
- **IA:** ChatGPT o3 para debugging e code review (não foi utilizado na produção do código)
- **Build tool:** Maven
- **JDK:** OpenJDK 21 (Microsoft Build)

---

## Como executar

1. Ter o Java 21 instalado na máquina
2. Clone o repositório
3. Compile e execute com o maven:
   `
   mvn compile && mvn exec:java -Dexec.mainClass="org.example.Main"
   `
4. Siga o menu pelo terminal.

---

## Estrutura do Projeto

- `Author` - Classe modelo para autores
- `User` - classe modelo para Usuários
- `Book` - Classe modelo para livros
- `BookGenre` - Enum dos gêneros literários
- `Library` - Classe de serviço/responsável por gerenciar dados e regras de negócio
- `Menu` - Camada de interface: IO, validação e fluxo
- `Main` - Inicializa o sistema e exibe o menu principal

---

## Decisões e Escolhas de Arquitetura

- Separação de responsabilidades:
    - Lógica de negócio (`Library`) separada da interação com usuário (`Menu`)
- Validação
    - Todo input crítico permite até 3 tentativas antes de cancelar a operação
- Enum para gêneros:
    - Gêneros dos livros implementados como Enum para facilitar expansão
- Evitar duplicidade
    - Cadastro de usuários e autores previne duplicidade via validação de CPF/nome
- Keyword `final` em todas as classes, pois o sistema não foi desenhado com herança em mente
- Feedback
    - O sistema sempre informa o sucesso ou erro das operações

---

## Possíveis melhorias

- Refatorar o `Menu` utilizando métodos helper para reduzir a duplicidade e facilitar a manutenção
- Utilizar Java Streams para tornar buscas e filtragens mais idiomáticas
- Implementar testes unitários
- Separar completamente a lógica de apresentação (`Menu`) da lógica de negócio (`Library`), seguindo o padrão MVC
  simples
- Adicionar persistência de dados com um banco de dados SQL (no escopo desse projeto, SQLite)
- Evoluir para interface gráfica (Swing ou outra)
- Melhorar a lógica de seleção de gênero do livro, no momento o nome precisa ser exatamente igual ao nome do Enum
- Evoluir System.out.println e logging para uma biblioteca dedicada que suporta tipos diferentes de formatação e cores
- Adicionar bibliotecas como Lombok para reduzir a verbosidade dos Getters e Setters e permitir anotações como
  `@NonNull`
- Melhorias em padronização e organização
