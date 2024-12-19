
package Main.Service;

import Main.Model.Disciplina;
import Main.Repository.DisciplinaRepository;
import Main.Repository.MatriculaRepository;
import Main.Memento.AlunoMemento;
import Main.Model.Matricula;

public class DisciplinaService {
    private DisciplinaRepository disciplinaRepository;
    private MatriculaRepository matriculaRepository;
    private AlunoService alunoService;

    public DisciplinaService(DisciplinaRepository disciplinaRepository,
                             MatriculaRepository matriculaRepository,
                             AlunoService alunoService) {
        this.disciplinaRepository = disciplinaRepository;
        this.matriculaRepository = matriculaRepository;
        this.alunoService = alunoService;
    }
    
    

    public void matricularAlunoEmDisciplina(int alunoId, int disciplinaId) {
        try {
            Disciplina d = disciplinaRepository.buscarPorId(disciplinaId);

            // Se `buscarPorId` lançar NoSuchElementException, o catch irá capturar.

            int novaMatriculaId = matriculaRepository.gerarNovoId();
            Matricula m = new Matricula(novaMatriculaId, alunoId, disciplinaId, 0.0);
            matriculaRepository.salvar(m);

            d.notificarObservadores();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Disciplina não encontrada", e);
        }
    }
    
    public void alterarNota(int alunoId, int disciplinaId, double novaNota) {
        try {
            Disciplina d = disciplinaRepository.buscarPorId(disciplinaId);
            // Caso a disciplina não exista, NoSuchElementException será lançada e tratada pelo catch.

            Matricula mat = matriculaRepository.buscarPorAlunoEDisciplina(alunoId, disciplinaId);
            // Caso a matrícula não exista, NoSuchElementException será lançada e tratada pelo catch.

            // Criar Memento antes da alteração de nota
            AlunoMemento mementoAnterior = alunoService.criarMementoAluno(alunoId);
            if (mementoAnterior != null) {
                alunoService.getCaretaker().salvarMemento(alunoId, mementoAnterior);
            }

            mat.setNota(novaNota);
            matriculaRepository.salvar(mat);

            d.notificarObservadores();
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Recurso não encontrado: " + e.getMessage(), e);
        }
    }
}
