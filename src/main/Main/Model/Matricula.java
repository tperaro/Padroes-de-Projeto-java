package Main.Model;

public class Matricula {
    private int id;
    private int alunoId;
    private int disciplinaId;
    private double nota;

    public Matricula(int id, int alunoId, int disciplinaId, double nota) {
        this.id = id;
        this.alunoId = alunoId;
        this.disciplinaId = disciplinaId;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public int getDisciplinaId() {
        return disciplinaId;
    }

    public double getNota() {
        return nota;
    }
}