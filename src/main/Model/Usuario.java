public class Usuario { //usuario nao pode ser classe abstrata, pois não teria como instanciar um tipo de abstract usuario
    private String username;
    private String senha;
    private String tipo; // Ex: "COORDENACAO", "SECRETARIA", "DOCENTE"

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
}
