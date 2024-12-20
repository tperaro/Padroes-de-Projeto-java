package Main.Repository;

import Main.Model.Docente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteRepository {
    private List<Docente> docentes = new ArrayList<>();
    private String arquivoDocentes;

    public DocenteRepository(String arquivoDocentes) {
        this.arquivoDocentes = arquivoDocentes;
        carregarDados();
    }

    public void carregarDados() {
        docentes.clear();
        File arquivo = new File(arquivoDocentes);

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
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoDocentes))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.split(";");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String nome = parts[1].trim();
                    String especialidade = parts[2].trim();
                    Docente d = new Docente(id, nome, especialidade);
                    docentes.add(d);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar dados do arquivo: " + e.getMessage());
        }
    }
    
 // Salvar todos os docentes no arquivo
    public void salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoDocentes))) {
            for (Docente docente : docentes) {
                bw.write(docente.getId() + ";" + docente.getNome() + ";" + docente.getEspecialidade());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    // Adicionar ou atualizar um docente
    public void salvarOuAtualizarDocente(Docente docente) {
        Docente existente = buscarPorId(docente.getId());
        if (existente != null) {
            // Atualiza docente existente
            existente.setNome(docente.getNome());
            existente.setEspecialidade(docente.getEspecialidade());
        } else {
            // Adiciona novo docente
            docentes.add(docente);
        }
        salvarDados();
    }


    public List<Docente> getDocentes() {
        return docentes;
    }

    public Docente buscarPorId(int id) {
        for (Docente d : docentes) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }
}
