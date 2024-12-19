
package Main.Service;

import Main.Model.Disciplina;
import Main.Repository.DisciplinaRepository;
import Main.Repository.MatriculaRepository;
import Main.Memento.AlunoMemento;
import Main.Model.Matricula;

public class DisciplinaService {
    private Disciplina disciplinaRepository;
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
        Disciplina d = disciplinaRepository.buscarPorId(disciplinaId);
        if (d == null) {
            throw new IllegalArgumentException("Disciplina não encontrada");
        }

        // Criar matrícula
        int novaMatriculaId = matriculaRepository.gerarNovoId();
        Matricula m = new Matricula(novaMatriculaId, alunoId, disciplinaId, 0.0);
        matriculaRepository.salvar(m);

        // Notificar observadores da disciplina (se for relevante após matrícula)
        d.notificarObservadores();
    }

    public void alterarNota(int alunoId, int disciplinaId, double novaNota) {
        Disciplina d = disciplinaRepository.buscarPorId(disciplinaId);
        if (d == null) {
            throw new IllegalArgumentException("Disciplina não encontrada");
        }

        Matricula mat = matriculaRepository.buscarPorAlunoEDisciplina(alunoId, disciplinaId);
        if (mat == null) {
            throw new IllegalArgumentException("Matrícula não encontrada para este aluno e disciplina");
        }

        // Criar Memento antes da alteração de nota
        AlunoMemento mementoAnterior = alunoService.criarMementoAluno(alunoId);
        if (mementoAnterior != null) {
            // Salvar memento no caretaker
            // O caretaker já está dentro do AlunoService, então chamamos de lá.
            // Mas vamos expor um método para isso:
            alunoService.caretaker.salvarMemento(alunoId, mementoAnterior);
        }

        // Alterar a nota
        mat.setNota(novaNota);
        matriculaRepository.salvar(mat);

        // Notificar Observers da disciplina
        d.notificarObservadores();
    }
}
