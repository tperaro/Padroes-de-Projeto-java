package Main.Repository;

import Main.Model.Matricula;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatriculaRepository {
    private List<Matricula> matriculas = new ArrayList<>();
    private String arquivoMatriculas;

    public MatriculaRepository(String arquivoMatriculas) {
        this.arquivoMatriculas = arquivoMatriculas;
        carregarDados();
    }
    
 // Salvar todas as matrículas no arquivo
    public void salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoMatriculas))) {
            for (Matricula matricula : matriculas) {
                bw.write(matricula.getId() + ";" + matricula.getAlunoId() + ";" +
                         matricula.getDisciplinaId() + ";" + matricula.getNota());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    // Adicionar ou atualizar uma matrícula
    public void salvarOuAtualizarMatricula(Matricula matricula) {
        Matricula existente = buscarPorId(matricula.getId());
        if (existente != null) {
            // Atualiza matrícula existente
            existente.setAlunoId(matricula.getAlunoId());
            existente.setDisciplinaId(matricula.getDisciplinaId());
            existente.setNota(matricula.getNota());
        } else {
            // Adiciona nova matrícula
            matriculas.add(matricula);
        }
        salvarDados();
    }


    public void carregarDados() {
        matriculas.clear();
        File arquivo = new File(arquivoMatriculas);

        // Verifica se o arquivo existe; caso contrário, cria um arquivo vazio
        if (!arquivo.exists()) {
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo criado: " + arquivo.getName());
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                return; // Interrompe a execução caso o arquivo não possa ser criado
            }
        }

        // Carrega os dados do arquivo existente
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoMatriculas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.split(";");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0].trim());
                    int alunoId = Integer.parseInt(parts[1].trim());
                    int disciplinaId = Integer.parseInt(parts[2].trim());
                    double nota = Double.parseDouble(parts[3].trim());
                    Matricula m = new Matricula(id, alunoId, disciplinaId, nota);
                    matriculas.add(m);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public Matricula buscarPorId(int id) {
        for (Matricula m : matriculas) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }
    public Map<Integer, Double> obterNotasDoAluno(int alunoId) {
        Map<Integer, Double> notas = new HashMap<>();
        for (Matricula matricula : matriculas) {
            if (matricula.getAlunoId() == alunoId) {
                notas.put(matricula.getDisciplinaId(), matricula.getNota());
            }
        }
        return notas;
    }
    
    public int gerarNovoId() {
        int maiorId = 0;
        for (Matricula matricula : matriculas) {
            if (matricula.getId() > maiorId) {
                maiorId = matricula.getId();
            }
        }
        return maiorId + 1;
    }

    // Método para salvar uma nova matrícula
    public void salvar(Matricula matricula) {
        matriculas.add(matricula);
        salvarDados();
    }
    
    public Matricula buscarPorAlunoEDisciplina(int alunoId, int disciplinaId) {
        for (Matricula matricula : matriculas) {
            if (matricula.getAlunoId() == alunoId && matricula.getDisciplinaId() == disciplinaId) {
                return matricula;
            }
        }
        return null;
    }
}
