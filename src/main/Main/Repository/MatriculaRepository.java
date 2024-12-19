package Main.Repository;

import Main.Model.Matricula;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaRepository {
    private List<Matricula> matriculas = new ArrayList<>();
    private String arquivoMatriculas;

    public MatriculaRepository(String arquivoMatriculas) {
        this.arquivoMatriculas = arquivoMatriculas;
    }

    public void carregarDados() {
        matriculas.clear();
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
            e.printStackTrace();
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
}