package Main.Model;

public class Docente {
    private int id;
    private String nome;
    private String especialidade;

    public Docente(int id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}


}