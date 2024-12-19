package Main.Service;

import Main.Model.CoordenacaoUser;
import Main.Model.UsuarioSystem;
import Main.Repository.UsuarioRepository;
import Main.Model.Usuario;
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Verifica credenciais do usuário. Se corretas, retorna instância de Usuario (CoordenacaoUser ou UsuarioSistema).
     * Caso contrário, retorna null.
     */
    public Usuario login(String username, String senhaDigitada) {
        String[] dados = usuarioRepository.buscarPorUsername(username);
        if (dados != null) {
            String tipo = dados[0];
            String senhaCorreta = dados[1];

            if (senhaCorreta.equals(senhaDigitada)) {
                if ("COORDENACAO".equalsIgnoreCase(tipo)) {
                    return new CoordenacaoUser(username, senhaCorreta);
                } else {
                    // Outros tipos de usuários genéricos
                    return new UsuarioSystem(username, senhaCorreta, tipo);
                }
            }
        }
        return null;
    }
}