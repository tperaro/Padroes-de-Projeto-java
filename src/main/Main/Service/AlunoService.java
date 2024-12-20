package Main.Service;

import Main.Memento.CadastroCaretaker;
import Main.Repository.AlunoRepository;
import Main.Repository.MatriculaRepository;
import Main.Memento.AlunoMemento;
import Main.Model.Aluno;

import java.util.HashMap;
import java.util.Map;

public class AlunoService {
    private AlunoRepository alunoRepository;
    private MatriculaRepository matriculaRepository;
    public CadastroCaretaker caretaker; //verificar se pode deixar public!

    public AlunoService(AlunoRepository alunoRepository, MatriculaRepository matriculaRepository, CadastroCaretaker caretaker) {
        this.alunoRepository = alunoRepository;
        this.matriculaRepository = matriculaRepository;
        this.caretaker = caretaker;
    }

    public int cadastrarAluno(String nome, String endereco) {
        // Validações simples
        if (nome == null || nome.isBlank() || endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Nome ou endereço inválidos");
        }

        // Criar objeto Aluno (ID pode ser gerado pelo repositorio ou manualmente)
        int novoId = alunoRepository.gerarNovoId();
        Aluno aluno = new Aluno(novoId, nome, endereco);

        // Salvar aluno no repositório (arquivo)
        alunoRepository.salvarOuAtualizarAluno(aluno);

        // Criar Memento inicial do estado do aluno (no momento sem notas)
        Map<Integer, Double> notasVazias = new HashMap<>();
        AlunoMemento m = new AlunoMemento(nome, endereco, notasVazias);
        caretaker.salvarMemento(novoId, m);

        return novoId;
    }

    public void restaurarUltimoEstadoAluno(int alunoId) {
        AlunoMemento m = caretaker.restaurarMemento(alunoId);
        if (m != null) {
            // Restaurar o estado do aluno
            Aluno aluno = alunoRepository.buscarPorId(alunoId);
            if (aluno != null) {
                aluno.setNome(m.getNome());
                aluno.setEndereco(m.getEndereco());
                // As notas também podem ser restauradas,
                // mas isso dependeria da lógica de como as notas estão armazenadas.

                // Atualizar o repositório
                alunoRepository.salvarOuAtualizarAluno(aluno);
            }
        }
    }

    public AlunoMemento criarMementoAluno(int alunoId) {
        Aluno aluno = alunoRepository.buscarPorId(alunoId);
        if (aluno == null) return null;

        // Capturar as notas atuais do aluno:
        Map<Integer, Double> notasAtuais = matriculaRepository.obterNotasDoAluno(alunoId);

        return new AlunoMemento(aluno.getNome(), aluno.getEndereco(), notasAtuais);
    }
}