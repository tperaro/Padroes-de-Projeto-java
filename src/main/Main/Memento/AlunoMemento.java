package Main.Memento;

import java.util.Map;

public class AlunoMemento {
    private String nome;
    private String endereco;
    private Map<Integer, Double> notasSnapshot;
    //Map<DisciplinaId, Nota> para capturar o estado atual das notas do aluno.

    public AlunoMemento(String nome, String endereco, Map<Integer, Double> notasSnapshot) {
        this.nome = nome;
        this.endereco = endereco;
        this.notasSnapshot = notasSnapshot;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Map<Integer, Double> getNotasSnapshot() {
        return notasSnapshot;
    }
}