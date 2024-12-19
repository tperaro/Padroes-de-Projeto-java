package Main.Repository;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import Main.Model.Disciplina;

public class DisciplinaRepository {
    private static final Logger LOGGER = Logger.getLogger(DisciplinaRepository.class.getName());
    private final List<Disciplina> disciplinas;
    private final Path arquivoDisciplinas;
    private static final String DELIMITER = ";";

    public DisciplinaRepository(String arquivoDisciplinas) {
        if (arquivoDisciplinas == null || arquivoDisciplinas.trim().isEmpty()) {
            throw new IllegalArgumentException("Caminho do arquivo não pode ser nulo ou vazio");
        }
        this.disciplinas = new ArrayList<>();
        // Convert the string path to an absolute, normalized path
        this.arquivoDisciplinas = new File(arquivoDisciplinas).toPath().normalize().toAbsolutePath();
        inicializarArquivo();
    }

    private void inicializarArquivo() {
        try {
            // First ensure parent directory exists
            Path parentDir = arquivoDisciplinas.getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                LOGGER.info("Diretório criado: " + parentDir);
            }

            // Then create the file if it doesn't exist
            if (!Files.exists(arquivoDisciplinas)) {
                Files.createFile(arquivoDisciplinas);
                LOGGER.info("Arquivo de disciplinas criado: " + arquivoDisciplinas);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao inicializar arquivo de disciplinas: " + e.getMessage(), e);
            throw new RuntimeException("Não foi possível inicializar o arquivo de disciplinas", e);
        }
    }

    public void carregarDados() {
        disciplinas.clear();
        try (BufferedReader br = Files.newBufferedReader(arquivoDisciplinas)) {
            String linha;
            int lineNumber = 0;
            while ((linha = br.readLine()) != null) {
                lineNumber++;
                processarLinha(linha, lineNumber);
            }
            LOGGER.info("Dados carregados com sucesso. Total de disciplinas: " + disciplinas.size());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar dados do arquivo", e);
        }
    }

    private void processarLinha(String linha, int lineNumber) {
        try {
            String[] parts = linha.split(DELIMITER);
            if (parts.length >= 3) {  // Changed from == to >= to be more flexible
                int id = Integer.parseInt(parts[0].trim());
                String nome = parts[1].trim();
                int docenteId = Integer.parseInt(parts[2].trim());

                // Additional fields can be added here as needed
                String descricao = parts.length > 3 ? parts[3].trim() : "";
                String horario = parts.length > 4 ? parts[4].trim() : "";
                int capacidadeMaxima = parts.length > 5 ? Integer.parseInt(parts[5].trim()) : 0;

                Disciplina disc = new Disciplina(id, nome, docenteId);
                disc.put("descricao", descricao);
                disc.put("horario", horario);
                disc.put("capacidadeMaxima", capacidadeMaxima);

                disciplinas.add(disc);
            } else {
                LOGGER.warning("Linha " + lineNumber + " ignorada: formato inválido");
            }
        } catch (NumberFormatException e) {
            LOGGER.warning("Erro ao processar linha " + lineNumber + ": " + e.getMessage());
        }
    }

    public Optional<Disciplina> buscarPorId(int id) {
        return disciplinas.stream()
                .filter(disc -> disc.getId() == id)
                .findFirst();
    }

    public synchronized void salvarOuAtualizarDisciplina(Disciplina disciplina) {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula");

        disciplinas.removeIf(d -> d.getId() == disciplina.getId());
        disciplinas.add(disciplina);

        salvarEmArquivo();
    }

    private void salvarEmArquivo() {
        try {
            // Create a temporary file for writing
            Path tempFile = Files.createTempFile(arquivoDisciplinas.getParent(), "temp_", ".txt");

            try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
                for (Disciplina disc : disciplinas) {
                    writer.write(String.format("%d%s%s%s%d%s%s%s%s%s%d%n",
                            disc.getId(), DELIMITER,
                            disc.getNome(), DELIMITER,
                            disc.getDocenteResponsavelId(), DELIMITER,
                            disc.getDescricao(), DELIMITER,
                            disc.getHorario(), DELIMITER,
                            disc.getCapacidadeMaxima()));
                }
            }

            // Atomic file replacement
            Files.move(tempFile, arquivoDisciplinas, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            LOGGER.info("Dados salvos com sucesso");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar dados no arquivo", e);
        }
    }

    public List<Disciplina> listarTodas() {
        return Collections.unmodifiableList(new ArrayList<>(disciplinas));
    }

    public boolean removerDisciplina(int id) {
        boolean removed = disciplinas.removeIf(d -> d.getId() == id);
        if (removed) {
            salvarEmArquivo();
            LOGGER.info("Disciplina removida com sucesso: " + id);
        }
        return removed;
    }
}