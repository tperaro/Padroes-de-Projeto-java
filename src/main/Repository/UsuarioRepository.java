import Model.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    public Usuario[] getUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoUsuarios))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.split(";");
                if (parts.length == 3) {
                    String username = parts[0].trim();
                    String senha = parts[1].trim();
                    String tipo = parts[2].trim();

                    // Cria o objeto Usuario e adiciona na lista
                    listaUsuarios.add(new Usuario(username, senha, tipo));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Converte a lista em um array e retorna
        return listaUsuarios.toArray(new Usuario[0]);
    }

    /**
     * Retorna um array contendo [tipoUsuario, senhaCorreta] se encontrado,
     * ou null se não encontrado.
     */
    public String[] buscarPorUsername(String username) {
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
            e.printStackTrace();
        }
        return null;
    }
}
