import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private GestaoAcademicaFacade facade;

    public LoginFrame(GestaoAcademicaFacade facade) {
        super("Login - Gestão Acadêmica");
        this.facade = facade;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Usuário:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        add(inputPanel, BorderLayout.CENTER);

        loginButton = new JButton("Login");
        add(loginButton, BorderLayout.SOUTH);

        // Ao clicar no botão, tentamos logar
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText().trim();
                String pass = new String(passwordField.getPassword()).trim();

                Usuario u = facade.efetuarLogin(user, pass);
                if (u != null) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Login realizado com sucesso! Tipo: " + u.getTipo(),
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                    // Aqui poderíamos abrir a tela principal dependendo do tipo de usuário
                    // Ex: if (u instanceof CoordenacaoUser) abrirTelaCoordenacao();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Credenciais inválidas",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
