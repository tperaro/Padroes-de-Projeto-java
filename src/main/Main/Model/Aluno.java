package Main.Model;

public class Aluno {
    private int id;
    private String nome;
    private String endereco;

    public Aluno(int id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

	public void setEndereco(String endereco) {
		// TODO Auto-generated method stub
		this.endereco = endereco;
		
	}

	public void setNome(String nome) {
		// TODO Auto-generated method stub
		this.nome = nome;
		
	}
	
}