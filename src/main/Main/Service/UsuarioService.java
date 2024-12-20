package Main.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
    /**
     * Salva ou atualiza um único usuário no arquivo.
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
    
    /**
     * Salva ou atualiza um único usuário no arquivo.
     */
    public void salvarOuAtualizarUsuario(Usuario usuario) {
        //Passa o objeto Usuario diretamente para o repositório
        usuarioRepository.salvarOuAtualizarUsuario(usuario);
    }

    
}