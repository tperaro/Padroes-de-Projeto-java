public class GestaoAcademicaFacade {
    private UsuarioService usuarioService;

    public GestaoAcademicaFacade(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario efetuarLogin(String username, String senha) {
        return usuarioService.login(username, senha);
    }
}
