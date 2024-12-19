package Main;

import javax.swing.*;

import Facade.GestaoAcademicaFacade.GestaoAcademicaFacade;
import Main.Memento.CadastroCaretaker;
import Main.Service.*;
import Main.Repository.*;
import LoginFrame.LoginFrame;
import Main.Model.*;

public class Main{
	public static void main(String[] args) {
		// Arquivos de dados (ajuste os caminhos conforme sua estrutura)
		String arquivoUsuarios = "usuarios.txt";
		String arquivoAlunos = "alunos.txt";
		String arquivoDocentes = "docentes.txt";
		String arquivoDisciplinas = "disciplinas.txt";
		String arquivoMatriculas = "matriculas.txt";

		// Criação dos repositórios
		UsuarioRepository usuarioRepository = new UsuarioRepository(arquivoUsuarios);
		UsuarioService usuarioService = new UsuarioService(usuarioRepository);

		AlunoRepository alunoRepository = new AlunoRepository(arquivoAlunos);
		DocenteRepository docenteRepository = new DocenteRepository(arquivoDocentes);
		DisciplinaRepository disciplinaRepository = new DisciplinaRepository(arquivoDisciplinas);
    	MatriculaRepository matriculaRepository = new MatriculaRepository(arquivoMatriculas);



		AlunoService alunoService = new AlunoService(alunoRepository, matriculaRepository, new CadastroCaretaker());
		DisciplinaService disciplinaService = new DisciplinaService(disciplinaRepository, matriculaRepository, alunoService);


    	// Adiciona dados nos arquivos (apenas para garantir que os arquivos tenham dados iniciais)
    	usuarioService.salvarOuAtualizarUsuario(new Usuario("admin", "admin123", "admin"));
    	alunoRepository.salvarOuAtualizarAluno(new Aluno(1, "João Silva", "Rua Principal, 123"));
    	docenteRepository.salvarOuAtualizarDocente(new Docente(1, "Dra. Maria", "Matemática"));
    	disciplinaRepository.salvarOuAtualizarDisciplina(new Disciplina(1, "Cálculo I", 60));
    	matriculaRepository.salvarOuAtualizarMatricula(new Matricula(1, 1, 1, 9.5));

    	// Cria o Facade com todos os serviços e repositórios
		GestaoAcademicaFacade facade = new GestaoAcademicaFacade(
            usuarioService,
            alunoRepository,
            docenteRepository,
            disciplinaRepository,
            matriculaRepository,
			alunoService,
			disciplinaService);

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
