package Main.Service;

import Main.Model.Disciplina;
import Main.Model.Matricula;
import Main.Repository.DisciplinaRepository;
import Main.Repository.MatriculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class DisciplinaServiceTest {

    private DisciplinaRepository disciplinaRepository;
    private MatriculaRepository matriculaRepository;
    private AlunoService alunoService;
    private DisciplinaService disciplinaService;

    @BeforeEach
    void setUp() {
        disciplinaRepository = Mockito.mock(DisciplinaRepository.class);
        matriculaRepository = Mockito.mock(MatriculaRepository.class);
        alunoService = Mockito.mock(AlunoService.class);
        disciplinaService = new DisciplinaService(disciplinaRepository, matriculaRepository, alunoService);
    }

    @Test
    void testMatricularAlunoEmDisciplinaValida() {
        Disciplina disciplina = new Disciplina(1, "Teste", 60);
        when(disciplinaRepository.buscarPorId(1)).thenReturn(Optional.of(disciplina));
        when(matriculaRepository.gerarNovoId()).thenReturn(1);
        disciplinaService.matricularAlunoEmDisciplina(1, 1);
        verify(matriculaRepository, times(1)).salvar(any(Matricula.class));
        verify(disciplinaRepository, times(1)).buscarPorId(1);
        verify(disciplinaRepository, times(1)).buscarPorId(anyInt());
        verify(disciplinaRepository, times(1)).buscarPorId(1);
        verify(matriculaRepository, times(1)).salvar(any());
    }

    @Test
    void testMatricularAlunoEmDisciplinaInexistente() {
        when(disciplinaRepository.buscarPorId(anyInt())).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> {
            disciplinaService.matricularAlunoEmDisciplina(1, 1);
        });
        assertTrue(ex.getMessage().contains("Disciplina não encontrada"));
        verify(matriculaRepository, times(0)).salvar(any(Matricula.class));
    }

    @Test
    void testAlterarNotaValido() {
        Disciplina disciplina = new Disciplina(1, "Teste", 60);
        Matricula matricula = new Matricula(1, 1, 1, 5.0);

        when(disciplinaRepository.buscarPorId(1)).thenReturn(Optional.of(disciplina));
        when(matriculaRepository.buscarPorAlunoEDisciplina(1, 1)).thenReturn(matricula);
        disciplinaService.alterarNota(1,1, 7.0);
        verify(matriculaRepository, times(1)).salvar(any(Matricula.class));
        assertEquals(7.0, matricula.getNota());

    }

    @Test
    void testAlterarNotaDisciplinaInexistente() {
        when(disciplinaRepository.buscarPorId(anyInt())).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> {
            disciplinaService.alterarNota(1,1, 7.0);
        });
        assertTrue(ex.getMessage().contains("Recurso não encontrado"));
        verify(matriculaRepository, times(0)).salvar(any(Matricula.class));

    }

    @Test
    void testAlterarNotaMatriculaInexistente() {
        Disciplina disciplina = new Disciplina(1, "Teste", 60);
        when(disciplinaRepository.buscarPorId(1)).thenReturn(Optional.of(disciplina));
        when(matriculaRepository.buscarPorAlunoEDisciplina(1, 1)).thenReturn(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> {
            disciplinaService.alterarNota(1,1, 7.0);
        });
        assertTrue(ex.getMessage().contains("Recurso não encontrado"));
        verify(matriculaRepository, times(0)).salvar(any(Matricula.class));
    }
}