package Main.Repository;

import Main.Model.Aluno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {
    private List<Aluno> alunos = new ArrayList<>();
    private String arquivoAlunos;

    public AlunoRepository(String arquivoAlunos) {
        this.arquivoAlunos = arquivoAlunos;
    }

    public void carregarDados() {
        alunos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoAlunos))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.split(";");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String nome = parts[1].trim();
                    String endereco = parts[2].trim();
                    Aluno a = new Aluno(id, nome, endereco);
                    alunos.add(a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Aluno buscarPorId(int id) {
        for (Aluno a : alunos) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
}