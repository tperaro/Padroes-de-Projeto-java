package Main.Repository;

import Main.Model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private String arquivoUsuarios;

    public UsuarioRepository(String arquivoUsuarios) {
        this.arquivoUsuarios = arquivoUsuarios;
    }

    /**
     * Retorna um array contendo todos os usuários encontrados no arquivo.
     */
    
 //Salvar todos os usuários no arquivo
    public void salvarDados(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoUsuarios))) {
            for (Usuario usuario : usuarios) {
                bw.write(usuario.getUsername() + ";" + usuario.getSenha() + ";" + usuario.getTipo());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    //Adicionar ou atualizar um usuário
    public void salvarOuAtualizarUsuario(Usuario usuario) {
        List<Usuario> usuarios = new ArrayList<>(List.of(getUsuarios()));
        boolean encontrado = false;

        for (Usuario u : usuarios) {
            if (u.getUsername().equals(usuario.getUsername())) {
                u.setSenha(usuario.getSenha());
                u.setTipo(usuario.getTipo());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            usuarios.add(usuario);
        }

        salvarDados(usuarios);
    }

    public Usuario[] getUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        File arquivo = new File(arquivoUsuarios);

        //Verifica se o arquivo existe; caso contrário, cria um arquivo vazio
        if (!arquivo.exists()) {
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo criado: " + arquivo.getName());
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                return new Usuario[0]; //Retorna um array vazio caso o arquivo não seja criado
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoUsuarios))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.split(";");
                if (parts.length == 3) {
                    String username = parts[0].trim();
                    String senha = parts[1].trim();
                    String tipo = parts[2].trim();

                    //Cria o objeto Usuario e adiciona na lista
                    listaUsuarios.add(new Usuario(username, senha, tipo));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }

        //Converte a lista em um array e retorna
        return listaUsuarios.toArray(new Usuario[0]);
    }

    /**
     * Retorna um array contendo [tipoUsuario, senhaCorreta] se encontrado,
     * ou null se não encontrado.
     */
    public String[] buscarPorUsername(String username) {
        File arquivo = new File(arquivoUsuarios);

        //Verifica se o arquivo existe; caso contrário, cria um arquivo vazio
        if (!arquivo.exists()) {
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo criado: " + arquivo.getName());
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                return null; //Retorna null caso o arquivo não seja criado
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoUsuarios))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.split(";");
                if (parts.length == 3) {
                    String user = parts[0].trim();
                    String senha = parts[1].trim();
                    String tipo = parts[2].trim();

                    if (user.equals(username)) {
                        return new String[]{tipo, senha};
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
        return null;
    }
}