package Facade.GestaoAcademicaFacade;

import Main.Repository.AlunoRepository;
import Main.Repository.DisciplinaRepository;
import Main.Repository.DocenteRepository;
import Main.Repository.MatriculaRepository;
import Main.Service.AlunoService;
import Main.Service.DisciplinaService;
import Main.Service.UsuarioService;
import Main.Model.Usuario;

public class GestaoAcademicaFacade {

    private AlunoRepository alunoRepository;
    private DocenteRepository docenteRepository;
    private DisciplinaRepository disciplinaRepository;
    private MatriculaRepository matriculaRepository;
    private UsuarioService usuarioService;
    private AlunoService alunoService;
    private DisciplinaService disciplinaService;

    public GestaoAcademicaFacade(
            UsuarioService usuarioService,
            AlunoRepository alunoRepository,
            DocenteRepository docenteRepository,
            DisciplinaRepository disciplinaRepository,
            MatriculaRepository matriculaRepository,
            AlunoService alunoService,
            DisciplinaService disciplinaService) {

        this.usuarioService = usuarioService;
        this.alunoRepository = alunoRepository;
        this.docenteRepository = docenteRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.matriculaRepository = matriculaRepository;
        this.alunoService = alunoService;
        this.disciplinaService = disciplinaService;
    }

    public Usuario efetuarLogin(String username, String senha) {
        return usuarioService.login(username, senha);
    }
    public int cadastrarAluno(String nome, String endereco) {
        return alunoService.cadastrarAluno(nome, endereco);
    }
    public void matricularAluno(int alunoId, int disciplinaId) {
        disciplinaService.matricularAlunoEmDisciplina(alunoId, disciplinaId);
    }

    public void alterarNota(int alunoId, int disciplinaId, double nota) {
        disciplinaService.alterarNota(alunoId, disciplinaId, nota);
    }
    public void desfazerUltimaAcaoAluno(int alunoId) {
        alunoService.restaurarUltimoEstadoAluno(alunoId);
    }
    public void inicializarDados() {
        alunoRepository.carregarDados();
        docenteRepository.carregarDados();
        disciplinaRepository.carregarDados();
        matriculaRepository.carregarDados();
    }

    //No futuro, métodos para acessar dados carregados, cadastrar novos registros, dentre outros que aplicam melhor .
    //o poder total do facade com interfaces e mistura de subserviços
}