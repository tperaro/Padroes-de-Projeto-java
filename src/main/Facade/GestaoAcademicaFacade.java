public class GestaoAcademicaFacade {
    private UsuarioService loginService;

    public GestaoAcademicaFacade() {
        loginService = new UsuarioService();
    }

    // Método para efetuar login
    public Usuario efetuarLogin(String username, String senha) {
        return loginService.validarLogin(username, senha);
    }
}