package GUI.LoginFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Facade.GestaoAcademicaFacade.GestaoAcademicaFacade;
import GUI.components.Button;
import GUI.components.TextField;
import Main.Model.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.components.*;

public class LoginFrame extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JLabel warningLabel;
    private GestaoAcademicaFacade facade;

    public LoginFrame(GestaoAcademicaFacade facade) {
        super("Login - Gestão Acadêmica");
        this.facade = facade;

        initComponents();
        getContentPane().setBackground(new Color(245, 245, 245));

        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setViewportBorder(null);
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

        //Adicionando os botões ao painel
        panel.add(boletimButton);
        panel.add(matriculaButton);
        panel.add(historicoButton);
        panel.add(materiasButton);

        //Adicionando ações aos botões (você pode implementar as funcionalidades aqui)
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

        //Adicionando os botões ao painel
        panel.add(notasButton);
        panel.add(consultarDadosButton);
        panel.add(conteudosButton);
        panel.add(frequenciaButton);
        panel.add(avisosButton);

        //Adicionando ações aos botões (você pode implementar as funcionalidades aqui)
        notasButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Acessando notas..."));
        consultarDadosButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Consultando dados..."));
        conteudosButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Acessando conteúdos..."));
        frequenciaButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Consultando frequência..."));
        avisosButton.addActionListener(e -> JOptionPane.showMessageDialog(docenteFrame, "Visualizando avisos..."));

        docenteFrame.add(panel);
        docenteFrame.setVisible(true);
    }

    private boolean signIn;

    private void initComponents() {

        background1 = new Background();
        panelLogin = new JPanel();
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        cmdSignIn = new Button();
        txtUser = new TextField();
        txtPass = new PasswordField();
        panelBody = new PanelTransparent();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jButton1 = new JButton();
        header1 = new Header();

        warningLabel = new JLabel("TODO: REMOVER ESSA SELEÇÃO APÓS IMPLEMENTAR A LÓGICA CORRETA DE LOGIN");
        warningLabel.setForeground(Color.YELLOW);
        warningLabel.setHorizontalAlignment(SwingConstants.CENTER);
        warningLabel.setFont(warningLabel.getFont().deriveFont(Font.BOLD));
        userTypeComboBox = new JComboBox<>(new String[]{"Docente", "Discente"});
        userTypeComboBox.setBackground(new Color(245, 245, 245));
        userTypeComboBox.setForeground(new Color(100, 100, 100));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background1.setLayout(new CardLayout());

        panelLogin.setOpaque(false);

        jPanel1.setOpaque(false);

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/GUI/assets/logo.png"))); // NOI18N

        cmdSignIn.setBackground(new Color(157, 153, 255));
        cmdSignIn.setForeground(new Color(255, 255, 255));
        cmdSignIn.setText("Sign In");
        cmdSignIn.setEffectColor(new Color(199, 196, 255));
        cmdSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdSignInActionPerformed(evt);
            }
        });

        txtUser.setBackground(new Color(245, 245, 245));
        txtUser.setLabelText("User Name");
        txtUser.setLineColor(new Color(131, 126, 253));
        txtUser.setSelectionColor(new Color(157, 153, 255));

        txtPass.setBackground(new Color(245, 245, 245));
        txtPass.setLabelText("Password");
        txtPass.setLineColor(new Color(131, 126, 253));
        txtPass.setSelectionColor(new Color(157, 153, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(warningLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(userTypeComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmdSignIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addGap(20, 20, 20)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(warningLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(cmdSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );


        GroupLayout panelLoginLayout = new GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
                panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLoginLayout.createSequentialGroup()
                                .addContainerGap(427, Short.MAX_VALUE)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(428, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
                panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelLoginLayout.createSequentialGroup()
                                .addContainerGap(63, Short.MAX_VALUE)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(233, Short.MAX_VALUE))
        );

        background1.add(panelLogin, "card2");

        jTable1.setModel(new DefaultTableModel(
                new Object [][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String [] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Sign Out");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        GroupLayout panelBodyLayout = new GroupLayout(panelBody);
        panelBody.setLayout(panelBodyLayout);
        panelBodyLayout.setHorizontalGroup(
                panelBodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelBodyLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelBodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(GroupLayout.Alignment.TRAILING, panelBodyLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jButton1)))
                                .addContainerGap())
                        .addComponent(header1, GroupLayout.DEFAULT_SIZE, 1205, Short.MAX_VALUE)
        );
        panelBodyLayout.setVerticalGroup(
                panelBodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelBodyLayout.createSequentialGroup()
                                .addComponent(header1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addContainerGap())
        );

        background1.add(panelBody, "card3");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void cmdSignInActionPerformed(java.awt.event.ActionEvent evt) {
        String user = txtUser.getText().trim();
        String pass = String.valueOf(txtPass.getPassword());
        String userType = (String) userTypeComboBox.getSelectedItem();
        boolean isValid = true;

        if (user.isEmpty()) {
            txtUser.setHelperText("Please input user name");
            txtUser.grabFocus();
            isValid = false;
        }

        if (pass.isEmpty()) {
            txtPass.setHelperText("Please input password");
            if (isValid) {
                txtPass.grabFocus();
            }
            isValid = false;
        }

        if (isValid) {
            enableLogin(false);

            Usuario u = facade.efetuarLogin(user, pass);
            if (u != null) {
                JOptionPane.showMessageDialog(this,
                        "Login realizado com sucesso!\nTipo de Usuário: " + userType,
                        null,
                        JOptionPane.INFORMATION_MESSAGE);

                if ("Discente".equals(userType)) {
                    abrirTelaDiscente();
                } else if ("Docente".equals(userType)) {
                    abrirTelaDocente();
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Credenciais inválidas",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                enableLogin(true);
                clearLogin();
            }
        }
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        signIn = false;
        clearLogin();
    }

    private void enableLogin(boolean action) {
        txtUser.setEditable(action);
        txtPass.setEditable(action);
        cmdSignIn.setEnabled(action);
    }

    public void clearLogin() {
        txtUser.setText("");
        txtPass.setText("");
        txtUser.setHelperText("");
        txtPass.setHelperText("");
    }

    private Background background1;
    private Button cmdSignIn;
    private Header header1;
    private JButton jButton1;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private PanelTransparent panelBody;
    private JPanel panelLogin;
    private PasswordField txtPass;
    private TextField txtUser;
}
