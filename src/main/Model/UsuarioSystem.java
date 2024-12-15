package main.Model;

import main.Model.Usuario;

public class UsuarioSystem extends Usuario {
    public UsuarioSystem(String username, String senha, String tipo) {
        super(username, senha, tipo);
    }

    // Usuário genérico do sistema (por exemplo SECRETARIA ou DOCENTE)
}
