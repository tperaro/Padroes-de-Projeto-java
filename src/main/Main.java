import main.Facade.GestaoAcademicaFacade;
import main.GUI.LoginFrame;
import main.Repository.UsuarioRepository;
import main.Service.UsuarioService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Cria o repository apontando para o arquivo usuarios.txt
        UsuarioRepository usuarioRepository = new UsuarioRepository("usuarios.txt");
        UsuarioService usuarioService = new UsuarioService(usuarioRepository);
        GestaoAcademicaFacade facade = new GestaoAcademicaFacade(usuarioService);

        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(facade);
            loginFrame.setVisible(true);
        });
    }
}
