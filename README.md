
# Padroes-de-Projeto-java

This Java project serves as a practical study of the following design patterns: **Facade**, **Observer**, and **Memento**. It also includes aspects such as:

-   **Test Coverage:** Though not explicitly detailed in the file tree, test implementation and coverage are intended to be part of the project.
-   **Threads:** The project utilizes threads for tasks like data loading to improve performance and responsiveness.
-   **Exception Handling:** Robust exception handling is implemented throughout the application to manage runtime errors.
-   **Graphical User Interface (GUI):** A user-friendly interface is built with Swing to provide an interactive experience.

## Design Patterns Implemented

### Facade
The **Facade** pattern is implemented through the `GestaoAcademicaFacade` class. This facade simplifies the interaction with the system by providing a single entry point to perform complex tasks such as user login, student registration, course enrollment, and grade manipulation. It hides the underlying complexity of different services and repositories.

### Observer
The **Observer** pattern is used to notify the UI whenever a change occurs in the `Disciplina` (Subject), particularly regarding student grades. The `PainelNotasObserver` observes changes on grades. This allows the UI (or any other observer) to react to changes in real-time.

### Memento
The **Memento** pattern is used to keep track of the state of an `Aluno` object, allowing for undo actions. The `CadastroCaretaker` class is responsible for managing `AlunoMemento` instances, which store snapshots of an `Aluno`'s state (name, address, notes, etc.), which allow restoration.

## Project Structure

The project is structured as follows:

-   **.idea/**: Contains IntelliJ IDEA project settings.
-   **.settings/**: Contains Eclipse project settings.
-   **/dist/**: Contains the dependencies and distributions.
-   **/out/**: Contains compiled Java class files.
-   **/src/main/**: Contains the main Java source code:
    -   **/Facade/**: Contains the `GestaoAcademicaFacade` class.
    -   **/GUI/**: Contains the classes for the graphical user interface.
        -   **/LoginFrame/**: Classes related to the login window.
        -   **/assets/**: Images and other assets used in GUI.
        -   **/components/**: Custom UI components like buttons and text fields.
    -   **/Main/**: Core logic of the application.
        -   **/Memento/**: Classes related to the Memento pattern.
        -   **/Model/**: Data model classes such as `Aluno`, `Disciplina`, `Docente`, etc.
        -   **/Observer/**: Classes related to the Observer pattern.
        -   **/Repository/**: Data access layer classes for handling data persistence.
        -   **/Service/**: Service layer classes encapsulating business logic.
-   **alunos.txt**: Text file containing student data.
-   **disciplinas.txt**: Text file containing course data.
-   **docentes.txt**: Text file containing teacher data.
-   **matriculas.txt**: Text file containing student enrollment data.
-   **usuarios.txt**: Text file containing user login data.
-   **LICENSE**: Contains the project's MIT license.
-   **README.md**: This file.

## Key Features

-   **Login Functionality:** The application offers user authentication with different user types (`CoordenacaoUser` or `UsuarioSystem`).
-   **Student Management:** Features to add, update and restore student information using the Memento Pattern.
-   **Course Enrollment:** Capability to enroll students in courses and record grades.
-   **Data Loading:** Data is loaded from text files using Java's I/O classes, with multithreading for concurrent data loading and UI setup.
-   **GUI:** A graphical user interface built with Swing components.

## How to Run

1.  Clone this repository.
2.  Import the project into your IDE of choice (e.g., IntelliJ IDEA, Eclipse).
3.  Ensure you have a Java Development Kit (JDK) version 17 or higher installed.
4.  Build and run the `Main.java` file located in the `/src/main/Main/` directory.
5.  The login window will appear where you can log in with the default admin user with `admin` as username and `admin123` as password. You'll need to select the user type from the dropdown menu.

## Note about the Data Layer
The data layer is implemented using text files for persistence. This was done with educational purposes in mind for this project. The data layer might be replaced with a database layer for larger scale applications.

## Disclaimer

This project is intended for educational purposes and to explore the application of design patterns.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
content_copy
download
Use code with caution.
Markdown
