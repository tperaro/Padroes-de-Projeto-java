package LoginFrame;
import javax.swing.*;

import GestaoAcademicaFacade.GestaoAcademicaFacade;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private JButton loginButton;

    private GestaoAcademicaFacade facade;

    public LoginFrame(GestaoAcademicaFacade facade) {
        super("Login - Gestão Acadêmica");
        this.facade = facade;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Usuário:"));
        usernameField = new JTextField();
        inputPanel.add(usernameField);

        inputPanel.add(new JLabel("Senha:"));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);

        inputPanel.add(new JLabel("Tipo de Usuário:"));
        userTypeComboBox = new JComboBox<>(new String[]{"Docente", "Discente"});
        inputPanel.add(userTypeComboBox);

        add(inputPanel, BorderLayout.CENTER);

        loginButton = new JButton("Login");
        add(loginButton, BorderLayout.SOUTH);

        // Ao clicar no botão, tentamos logar
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText().trim();
                String pass = new String(passwordField.getPassword()).trim();
                String userType = (String) userTypeComboBox.getSelectedItem();

                Usuario u = facade.efetuarLogin(user, pass);
                if (u != null) {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Login realizado com sucesso!\nTipo de Usuário: " + userType,
                            null,
                            JOptionPane.INFORMATION_MESSAGE);

                    if ("Discente".equals(userType)) {
                        abrirTelaDiscente();
                    } else if ("Docente".equals(userType)) {
                        abrirTelaDocente();
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Credenciais inválidas",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void abrirTelaDiscente() {
        JFrame discenteFrame = new JFrame("Painel do Discente");
        discenteFrame.setSize(400, 300);
        discenteFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton boletimButton = new JButton("Acessar Boletim");
        JButton matriculaButton = new JButton("Solicitar Matrícula");
        JButton historicoButton = new JButton("Consultar Histórico");
        JButton materiasButton = new JButton("Acessar Matérias");

        // Adicionando os botões ao painel
        panel.add(boletimButton);
        panel.add(matriculaButton);
        panel.add(historicoButton);
        panel.add(materiasButton);

        // Adicionando ações aos botões (você pode implementar as funcionalidades aqui)
        boletimButton.addActionListener(e -> JOptionPane.showMessageDialog(discenteFrame, "Acessando boletim..."));
        matriculaButton.addActionListener(e -> JOptionPane.showMessageDialog(discenteFrame, "Solicitando matrícula..."));
        historicoButton.addActionListener(e -> JOptionPane.showMessageDialog(discenteFrame, "Consultando histórico..."));
        materiasButton.addActionListener(e -> JOptionPane.showMessageDialog(discenteFrame, "Acessando matérias..."));

        discenteFrame.add(panel);
        discenteFrame.setVisible(true);
    }

    private void abrirTelaDocente() {
        JFrame docenteFrame = new JFrame("Painel do Docente");
        docenteFrame.setSize(400, 300);
        docenteFrame.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton notasButton = new JButton("Notas");
        JButton consultarDadosButton = new JButton("Consultar Dados");
        JButton conteudosButton = new JButton("Conteúdos");
        JButton frequenciaButton = new JButton("Frequência");
        JButton avisosButton = new JButton("Avisos");

        // Adicionando os botões ao painel
        panel.add(notasButton);
        panel.add(consultarDadosButton);
        panel.add(conteudosButton);
        panel.add(frequenciaButton);
        panel.add(avisosButton);

        // Adicionando ações aos botões (você pode implementar as funcionalidades aqui)
        notasButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Acessando notas..."));
        consultarDadosButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Consultando dados..."));
        conteudosButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Acessando conteúdos..."));
        frequenciaButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Consultando frequência..."));
        avisosButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Visualizando avisos..."));

        docenteFrame.add(panel);
        docenteFrame.setVisible(true);
    }
}
