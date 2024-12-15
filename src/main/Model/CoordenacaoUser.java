package main.Model;

public class CoordenacaoUser extends Usuario {
    public CoordenacaoUser(String username, String senha) {
        super(username, senha, "COORDENACAO");
    }

    // Aqui poderíamos ter métodos específicos da coordenação
    // Por exemplo: void aprovarNovaDisciplina(), etc.
}
