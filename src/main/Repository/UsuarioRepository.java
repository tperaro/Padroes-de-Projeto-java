package main.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UsuarioRepository {
    private String arquivoUsuarios;

    public UsuarioRepository(String arquivoUsuarios) {
        this.arquivoUsuarios = arquivoUsuarios;
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
            // Em um sistema mais robusto, poderíamos lançar exceções customizadas.
        }
        return null;
    }
}
