import javax.swing.*; 

public class Main {
	public static void main(String[] args) {
        GestaoAcademicaFacade facade = new GestaoAcademicaFacade();
        SwingUtilities.invokeLater(() -> {
            new LoginFrame(facade).setVisible(true);
        });
    }
}
