package Main.Repository;
import Main.Model.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlunoRepositoryTest {
    private AlunoRepository alunoRepository;
    @TempDir
    Path tempDir;
    Path arquivoAlunosPath;

    @BeforeEach
    void setUp() {
        arquivoAlunosPath = tempDir.resolve("alunos_test.txt");
        alunoRepository = new AlunoRepository(arquivoAlunosPath.toString());
    }

    @Test
    void testCarregarDadosArquivoInexistente() {
        Path arquivoInexistente = tempDir.resolve("arquivo_inexistente.txt");
        alunoRepository = new AlunoRepository(arquivoInexistente.toString());
        alunoRepository.carregarDados();
        assertEquals(0, alunoRepository.getAlunos().size());
    }

    @Test
    void testCarregarDadosArquivoVazio() {
        alunoRepository.carregarDados();
        assertEquals(0, alunoRepository.getAlunos().size());
    }

    @Test
    void testCarregarDadosArquivoValido() throws IOException {
        Files.write(arquivoAlunosPath, List.of("1;Aluno 1;Endereco 1", "2;Aluno 2;Endereco 2"));
        alunoRepository.carregarDados();
        List<Aluno> alunos = alunoRepository.getAlunos();
        assertEquals(2, alunos.size());
        assertEquals(1, alunos.get(0).getId());
        assertEquals("Aluno 1", alunos.get(0).getNome());
        assertEquals("Endereco 1", alunos.get(0).getEndereco());
        assertEquals(2, alunos.get(1).getId());
        assertEquals("Aluno 2", alunos.get(1).getNome());
        assertEquals("Endereco 2", alunos.get(1).getEndereco());
    }

    @Test
    void testSalvarAlunoValido() throws IOException {
        Aluno aluno = new Aluno(1, "Novo Aluno", "Novo Endereço");
        alunoRepository.salvarOuAtualizarAluno(aluno);
        alunoRepository.carregarDados();
        List<Aluno> alunos = alunoRepository.getAlunos();
        assertEquals(1, alunos.size());
        assertEquals(1, alunos.get(0).getId());
        assertEquals("Novo Aluno", alunos.get(0).getNome());
        assertEquals("Novo Endereço", alunos.get(0).getEndereco());
    }

    @Test
    void testAtualizarAlunoExistente() throws IOException {
        Files.write(arquivoAlunosPath, List.of("1;Aluno 1;Endereco 1"));
        alunoRepository.carregarDados();
        Aluno aluno = new Aluno(1, "Aluno Atualizado", "Endereco Atualizado");
        alunoRepository.salvarOuAtualizarAluno(aluno);
        alunoRepository.carregarDados();
        List<Aluno> alunos = alunoRepository.getAlunos();
        assertEquals(1, alunos.size());
        assertEquals(1, alunos.get(0).getId());
        assertEquals("Aluno Atualizado", alunos.get(0).getNome());
        assertEquals("Endereco Atualizado", alunos.get(0).getEndereco());
    }

    @Test
    void testBuscarAlunoPorIdExistente() throws IOException {
        Files.write(arquivoAlunosPath, List.of("1;Aluno 1;Endereco 1", "2;Aluno 2;Endereco 2"));
        alunoRepository.carregarDados();
        Aluno aluno = alunoRepository.buscarPorId(2);
        assertNotNull(aluno);
        assertEquals("Aluno 2", aluno.getNome());
    }

    @Test
    void testBuscarAlunoPorIdInexistente() throws IOException {
        Files.write(arquivoAlunosPath, List.of("1;Aluno 1;Endereco 1"));
        alunoRepository.carregarDados();
        Aluno aluno = alunoRepository.buscarPorId(2);
        assertNull(aluno);
    }

    @Test
    void testGerarNovoIdComListaVazia() {
        int novoId = alunoRepository.gerarNovoId();
        assertEquals(1, novoId);
    }

    @Test
    void testGerarNovoIdComLista() throws IOException {
        Files.write(arquivoAlunosPath, List.of("1;Aluno 1;Endereco 1", "2;Aluno 2;Endereco 2"));
        alunoRepository.carregarDados();
        int novoId = alunoRepository.gerarNovoId();
        assertEquals(3, novoId);
    }

}