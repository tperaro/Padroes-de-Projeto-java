
# Padrões de Projeto em Java

Autores:
Thiago de Jesus Peraro - 201904260 - peraro.thiago@discente.ufg.br
Lucas Santos Soares - 202307089 - lucas_santos23@discente.ufg.br
João Vitor Borges Tavares - 202307400 - joao2345678@discente.ufg.br

user e senha programados no arquivo : admin   admin123

Este projeto em Java serve como um estudo prático dos seguintes padrões de projeto: **Facade**, **Observer** e **Memento**. Também inclui aspectos como:

- **Cobertura de Testes:** Embora não esteja explicitamente detalhado na árvore de arquivos, a implementação e cobertura de testes fazem parte do projeto.
- **Threads:** O projeto utiliza threads para tarefas como carregamento de dados para melhorar o desempenho e a responsividade.
- **Tratamento de Exceções:** Um robusto tratamento de exceções é implementado em toda a aplicação para gerenciar erros em tempo de execução.
- **Interface Gráfica do Usuário (GUI):** Uma interface amigável construída com Swing para fornecer uma experiência interativa.

## Padrões de Projeto Implementados

### Facade
O padrão **Facade** é implementado através da classe `GestaoAcademicaFacade`. Esta fachada simplifica a interação com o sistema fornecendo um único ponto de entrada para executar tarefas complexas como login de usuário, cadastro de alunos, matrícula em disciplinas e manipulação de notas. Ela oculta a complexidade subjacente dos diferentes serviços e repositórios.

### Observer
O padrão **Observer** é usado para notificar a interface do usuário sempre que ocorre uma mudança na `Disciplina`, particularmente em relação às notas dos alunos. O `PainelNotasObserver` observa mudanças nas notas. Isso permite que a interface do usuário (ou qualquer outro observador) reaja às mudanças em tempo real.

### Memento
O padrão **Memento** é usado para manter o registro do estado de um objeto `Aluno`, permitindo ações de desfazer. A classe `CadastroCaretaker` é responsável por gerenciar instâncias de `AlunoMemento`, que armazenam instantâneos do estado de um `Aluno` (nome, endereço, notas, etc.), permitindo restauração.

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- **.idea/**: Contém as configurações do projeto IntelliJ IDEA.
- **.settings/**: Contém as configurações do projeto Eclipse.
- **/dist/**: Contém as dependências e distribuições.
- **/out/**: Contém os arquivos de classe Java compilados.
- **/src/main/**: Contém o código-fonte principal em Java:
  - **/Facade/**: Contém a classe `GestaoAcademicaFacade`.
  - **/GUI/**: Contém as classes para a interface gráfica do usuário.
    - **/LoginFrame/**: Classes relacionadas à janela de login.
    - **/assets/**: Imagens e outros recursos usados na GUI.
    - **/components/**: Componentes personalizados da UI como botões e campos de texto.
  - **/Main/**: Lógica principal da aplicação.
    - **/Memento/**: Classes relacionadas ao padrão Memento.
    - **/Model/**: Classes de modelo de dados como `Aluno`, `Disciplina`, `Docente`, etc.
    - **/Observer/**: Classes relacionadas ao padrão Observer.
    - **/Repository/**: Classes da camada de acesso a dados para manipulação de persistência.
    - **/Service/**: Classes da camada de serviço encapsulando lógica de negócios.
- **alunos.txt**: Arquivo de texto contendo dados dos alunos.
- **disciplinas.txt**: Arquivo de texto contendo dados das disciplinas.
- **docentes.txt**: Arquivo de texto contendo dados dos professores.
- **matriculas.txt**: Arquivo de texto contendo dados de matrículas dos alunos.
- **usuarios.txt**: Arquivo de texto contendo dados de login dos usuários.
- **LICENSE**: Contém a licença MIT do projeto.
- **README.md**: Este arquivo.

## Funcionalidades Principais

- **Funcionalidade de Login:** A aplicação oferece autenticação de usuário com diferentes tipos de usuário (`CoordenacaoUser` ou `UsuarioSystem`).
- **Gestão de Alunos:** Recursos para adicionar, atualizar e restaurar informações de alunos usando o Padrão Memento.
- **Matrícula em Disciplinas:** Capacidade de matricular alunos em disciplinas e registrar notas.
- **Carregamento de Dados:** Os dados são carregados de arquivos de texto usando as classes de E/S do Java, com multithreading para carregamento concorrente de dados e configuração da UI.
- **GUI:** Uma interface gráfica construída com componentes Swing.

## Como Executar

1. Clone este repositório.
2. Importe o projeto para sua IDE de preferência (ex: IntelliJ IDEA, Eclipse).
3. Certifique-se de ter o Java Development Kit (JDK) versão 17 ou superior instalado.
4. Compile e execute o arquivo `Main.java` localizado no diretório `/src/main/Main/`.
5. A janela de login aparecerá onde você pode entrar com o usuário admin padrão com `admin` como nome de usuário e `admin123` como senha. Você precisará selecionar o tipo de usuário no menu suspenso.

## Nota sobre a Camada de Dados
A camada de dados é implementada usando arquivos de texto para persistência. Isso foi feito tendo em mente propósitos educacionais para este projeto. A camada de dados pode ser substituída por uma camada de banco de dados para aplicações em maior escala.

## Aviso

Este projeto é destinado a fins educacionais e para explorar a aplicação de padrões de projeto.

## Licença

Este projeto está licenciado sob a Licença MIT - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.

