package Main;

import javax.swing.*;
import Main.Service.*;
import Main.Repository.*;
import LoginFrame.LoginFrame;
import GestaoAcademicaFacade.GestaoAcademicaFacade;


public class Main {
    public static void main(String[] args) {
        // Arquivos de dados (ajuste os caminhos conforme sua estrutura)
        String arquivoUsuarios = "usuarios.txt";
        String arquivoAlunos = "alunos.dat";
        String arquivoDocentes = "docentes.dat";
        String arquivoDisciplinas = "disciplinas.dat";
        String arquivoMatriculas = "matriculas.dat";

        // Criação dos repositórios
        UsuarioRepository usuarioRepository = new UsuarioRepository(arquivoUsuarios);
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);

        AlunoRepository alunoRepository = new AlunoRepository(arquivoAlunos);
        DocenteRepository docenteRepository = new DocenteRepository(arquivoDocentes);
        DisciplinaRepository disciplinaRepository = new DisciplinaRepository(arquivoDisciplinas);
        MatriculaRepository matriculaRepository = new MatriculaRepository(arquivoMatriculas);

        // Cria o Facade com todos os serviços e repositórios
        GestaoAcademicaFacade facade = new GestaoAcademicaFacade(
                usuarioService,
                alunoRepository,
                docenteRepository,
                disciplinaRepository,
                matriculaRepository);

        // Carregar dados dos arquivos
        facade.inicializarDados();

        // Exemplo: Após carregar, você pode listar os alunos como teste
        // System.out.println("Alunos Carregados: " + alunoRepository.getAlunos().size());

        // Agora inicia a UI de login (da etapa 1)
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(facade);
            loginFrame.setVisible(true);
        });
    }
}