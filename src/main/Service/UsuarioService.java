public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    // Construtor da classe UsuarioService
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método login que valida usuário e senha
    public Usuario login(String username, String senha) {
        for (Usuario usuario : usuarioRepository.getUsuarios()) {
            if (usuario.getUsername().equals(username) && usuario.getSenha().equals(senha)) {
                return usuario; // Retorna o usuário caso encontre a combinação correta
            }
        }
        return null; // Retorna null caso não encontre
    }
}
