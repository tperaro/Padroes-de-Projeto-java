package Main.Model;

public class Usuario {
    private String username;
    private String senha;
    private String tipo;

    public Usuario(String username, String senha, String tipo) {
        this.username = username;
        this.senha = senha;
        this.tipo = tipo;
    }
    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }
	public void setSenha(String senha) {
		this.senha = senha;
		
	}
	
	public void setTipo (String tipo) {
		this.tipo = tipo;
	}
}