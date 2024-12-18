import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private List<Usuario> usuarios;

    public UsuarioService() {
        usuarios = new ArrayList<>();
        // Adicionando usuários de exemplo (simula um "banco de dados")
        usuarios.add(new Usuario("admin", "1234", "COORDENACAO"));
        usuarios.add(new Usuario("secretaria", "1234", "SECRETARIA"));
        usuarios.add(new Usuario("professor", "1234", "DOCENTE"));
    }

    // Método para validar o login
    public Usuario validarLogin(String username, String senha) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getSenha().equals(senha)) {
                return u; // Usuário encontrado e validado
            }
        }
        return null; // Credenciais inválidas
    }
}
