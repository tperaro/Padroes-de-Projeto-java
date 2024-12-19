
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
<<<<<<< HEAD
        
        // Criar matrícula
        int novaMatriculaId = matriculaRepository.gerarNovoId();
        Matricula m = new Matricula(novaMatriculaId, alunoId, disciplinaId, 0.0);
        matriculaRepository.salvar(m);

        // Notificar observadores da disciplina (se for relevante após matrícula)
        d.notificarObservadores();
=======
>>>>>>> 44790cdd19814bed3389b553bb6a8218001d251c
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
<<<<<<< HEAD

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
        matriculaRepository.salvarOuAtualizarMatricula(mat);

        // Notificar Observers da disciplina
        d.notificarObservadores();
=======
>>>>>>> 44790cdd19814bed3389b553bb6a8218001d251c
    }
}
