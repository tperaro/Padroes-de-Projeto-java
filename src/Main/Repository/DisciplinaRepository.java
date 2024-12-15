package Main.Repository;

import Main.Model.Disciplina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaRepository {
    private List<Disciplina> disciplinas = new ArrayList<>();
    private String arquivoDisciplinas;

    public DisciplinaRepository(String arquivoDisciplinas) {
        this.arquivoDisciplinas = arquivoDisciplinas;
    }

    public void carregarDados() {
        disciplinas.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoDisciplinas))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] parts = linha.split(";");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String nome = parts[1].trim();
                    int docenteId = Integer.parseInt(parts[2].trim());
                    Disciplina disc = new Disciplina(id, nome, docenteId);
                    disciplinas.add(disc);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Disciplina buscarPorId(int id) {
        for (Disciplina disc : disciplinas) {
            if (disc.getId() == id) {
                return disc;
            }
        }
        return null;
    }
}

