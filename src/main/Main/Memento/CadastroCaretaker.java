package Main.Memento;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CadastroCaretaker {
    //Mantém um histórico de Mementos para cada Aluno (usando id do aluno como chave)
    private Map<Integer, Stack<AlunoMemento>> historico = new HashMap<>();

    public void salvarMemento(int alunoId, AlunoMemento m) {
        historico.putIfAbsent(alunoId, new Stack<>());
        historico.get(alunoId).push(m);
    }

    public AlunoMemento restaurarMemento(int alunoId) {
        if (!historico.containsKey(alunoId) || historico.get(alunoId).isEmpty()) {
            return null;
        }
        return historico.get(alunoId).pop();
    }
}