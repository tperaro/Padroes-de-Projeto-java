package Main.Repository;

import Main.Model.Aluno;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {
    private List<Aluno> alunos = new ArrayList<>();
    private String arquivoAlunos;

    public AlunoRepository(String arquivoAlunos) {
        this.arquivoAlunos = arquivoAlunos;
        carregarDados();
    }

    // Carregar alunos do arquivo
    public void carregarDados() {
        alunos.clear();
        File arquivo = new File(arquivoAlunos);

        // Verifica se o arquivo existe; caso contrário, cria um arquivo vazio
        if (!arquivo.exists()) {
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo criado: " + arquivo.getName());
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                return; // Interrompe a execução, já que o arquivo não pôde ser criado
            }
        }

        // Carrega os dados do arquivo existente
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
            System.err.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
    }

 // Salvar todos os alunos no arquivo
    public void salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoAlunos))) {
            for (Aluno aluno : alunos) {
                bw.write(aluno.getId() + ";" + aluno.getNome() + ";" + aluno.getEndereco());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    // Adicionar ou atualizar um aluno
    public void salvarOuAtualizarAluno(Aluno aluno) {
        Aluno existente = buscarPorId(aluno.getId());
        if (existente != null) {
            // Atualiza aluno existente
            existente.setNome(aluno.getNome());
            existente.setEndereco(aluno.getEndereco());
        } else {
            // Adiciona novo aluno
            alunos.add(aluno);
        }
        salvarDados();
    }

    // Buscar todos os alunos
    public List<Aluno> getAlunos() {
        return new ArrayList<>(alunos); // Retorna uma cópia para evitar modificações diretas
    }

    // Buscar aluno por ID
    public Aluno buscarPorId(int id) {
        for (Aluno a : alunos) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    // Gerar novo ID único
    public int gerarNovoId() {
        int maxId = 0;
        for (Aluno aluno : alunos) {
            if (aluno.getId() > maxId) {
                maxId = aluno.getId();
            }
        }
        return maxId + 1;
    }
}
