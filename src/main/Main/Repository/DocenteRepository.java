package Main.Repository;

import Main.Model.Docente;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocenteRepository {
    private List<Docente> docentes = new ArrayList<>();
    private String arquivoDocentes;

    public DocenteRepository(String arquivoDocentes) {
        this.arquivoDocentes = arquivoDocentes;
    }

    public void carregarDados() {
        docentes.clear();
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
            e.printStackTrace();
        }
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