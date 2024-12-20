package Main.Service;

import Main.Memento.CadastroCaretaker;
import Main.Model.Aluno;
import Main.Repository.AlunoRepository;
import Main.Repository.MatriculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    private AlunoRepository alunoRepository;
    private MatriculaRepository matriculaRepository;
    private AlunoService alunoService;
    private CadastroCaretaker caretaker;


    @BeforeEach
    void setUp() {
        alunoRepository = Mockito.mock(AlunoRepository.class);
        matriculaRepository = Mockito.mock(MatriculaRepository.class);
        caretaker = Mockito.mock(CadastroCaretaker.class);
        alunoService = new AlunoService(alunoRepository, matriculaRepository, caretaker);
    }

    @Test
    void testCadastrarAlunoValido() {
        when(alunoRepository.gerarNovoId()).thenReturn(1);
        when(alunoRepository.buscarPorId(1)).thenReturn(null); //simular o primeiro cadastro
        int alunoId = alunoService.cadastrarAluno("Teste", "Endereco Teste");
        assertEquals(1, alunoId);
        verify(alunoRepository, times(1)).salvarOuAtualizarAluno(any(Aluno.class));
        verify(caretaker, times(1)).salvarMemento(eq(1), any());
    }
    @Test
    void testCadastrarAlunoInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> {
            alunoService.cadastrarAluno(null, "Endereco Teste");
        });

        assertTrue(ex.getMessage().contains("Nome ou endereço inválidos"));
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, ()-> {
            alunoService.cadastrarAluno("Teste", null);
        });

        assertTrue(ex2.getMessage().contains("Nome ou endereço inválidos"));
        IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class, ()-> {
            alunoService.cadastrarAluno("", "Endereco Teste");
        });

        assertTrue(ex3.getMessage().contains("Nome ou endereço inválidos"));
        IllegalArgumentException ex4 = assertThrows(IllegalArgumentException.class, ()-> {
            alunoService.cadastrarAluno("Teste", "");
        });

        assertTrue(ex4.getMessage().contains("Nome ou endereço inválidos"));
    }
    @Test
    void testRestaurarUltimoEstadoAlunoComMementoValido() {
        int alunoId = 1;
        Aluno aluno = new Aluno(alunoId, "nome velho", "endereco velho");
        Map<Integer, Double> notas = new HashMap<>();
        notas.put(1, 10.0);
        AlunoMemento memento = new AlunoMemento("nome novo", "endereco novo", notas);

        when(caretaker.restaurarMemento(alunoId)).thenReturn(memento);
        when(alunoRepository.buscarPorId(alunoId)).thenReturn(aluno);
        alunoService.restaurarUltimoEstadoAluno(alunoId);

        verify(alunoRepository).salvarOuAtualizarAluno(argThat(a ->
                a.getNome().equals("nome novo") &&
                        a.getEndereco().equals("endereco novo")
        ));
    }
    @Test
    void testRestaurarUltimoEstadoAlunoSemMemento() {
        int alunoId = 1;
        when(caretaker.restaurarMemento(alunoId)).thenReturn(null);
        when(alunoRepository.buscarPorId(alunoId)).thenReturn(new Aluno(alunoId, "nome", "endereco"));
        alunoService.restaurarUltimoEstadoAluno(alunoId);
        verify(alunoRepository, times(0)).salvarOuAtualizarAluno(any());
    }

    @Test
    void testCriarMementoAlunoValido() {
        int alunoId = 1;
        Aluno aluno = new Aluno(alunoId, "nome", "endereco");
        Map<Integer, Double> notas = new HashMap<>();
        notas.put(1, 10.0);

        when(alunoRepository.buscarPorId(alunoId)).thenReturn(aluno);
        when(matriculaRepository.obterNotasDoAluno(alunoId)).thenReturn(notas);
        AlunoMemento memento = alunoService.criarMementoAluno(alunoId);

        assertNotNull(memento);
        assertEquals(aluno.getNome(), memento.getNome());
        assertEquals(aluno.getEndereco(), memento.getEndereco());
        assertEquals(notas, memento.getNotasSnapshot());
    }

    @Test
    void testCriarMementoAlunoNaoValido() {
        int alunoId = 1;
        when(alunoRepository.buscarPorId(alunoId)).thenReturn(null);
        AlunoMemento memento = alunoService.criarMementoAluno(alunoId);
        assertNull(memento);
    }
}

/* Explicação
@BeforeEach: Este método é executado antes de cada teste. Usamos para configurar os objetos mocks.

@Test: Anotação para indicar um método que contém um caso de teste.
-assertEquals(esperado, atual): Verifica se o valor atual é igual ao valor esperado.
-assertNotNull(objeto): Verifica se o objeto não é nulo.

-assertTrue(condição): Verifica se a condição é verdadeira.
-assertThrows(TipoException.class, () -> { ... }): Verifica se o bloco de código lança a exceção esperada.

Mockito.mock(Classe.class): Cria um objeto "mock" (falso) de uma classe. Usamos para simular os comportamento do Repository,
Service ou qualquer outra classe que precise ter comportamento pré determinado para testar um componente em isolamento.

-when(mock.método()).thenReturn(valor): Define o comportamento do método do mock quando ele for chamado.
-verify(mock, times(n)).método(argumentos): Verifica se o método do mock foi chamado o número de vezes esperado com os argumentos corretos.
-any(): Qualquer valor.
-anyInt(): Qualquer inteiro.
-eq(valor): Valor exato esperado.
 */