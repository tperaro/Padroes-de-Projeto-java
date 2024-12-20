package Main.Service;

import Main.Model.CoordenacaoUser;
import Main.Model.Usuario;
import Main.Model.UsuarioSystem;
import Main.Repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {
    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void testLoginComCredenciaisValidasCoordenacao() {
        when(usuarioRepository.buscarPorUsername("admin")).thenReturn(new String[]{"COORDENACAO", "admin123"});
        Usuario usuario = usuarioService.login("admin", "admin123");
        assertNotNull(usuario);
        assertTrue(usuario instanceof CoordenacaoUser);
        assertEquals("admin", usuario.getUsername());
        assertEquals("admin123", usuario.getSenha());
    }

    @Test
    void testLoginComCredenciaisValidasUsuarioSistema() {
        when(usuarioRepository.buscarPorUsername("docente")).thenReturn(new String[]{"DOCENTE", "docente123"});
        Usuario usuario = usuarioService.login("docente", "docente123");
        assertNotNull(usuario);
        assertTrue(usuario instanceof UsuarioSystem);
        assertEquals("docente", usuario.getUsername());
        assertEquals("docente123", usuario.getSenha());
        assertEquals("DOCENTE", usuario.getTipo());
    }
    @Test
    void testLoginComCredenciaisInvalidas() {
        when(usuarioRepository.buscarPorUsername("admin")).thenReturn(new String[]{"COORDENACAO", "admin123"});
        Usuario usuario = usuarioService.login("admin", "senhaerrada");
        assertNull(usuario);
    }

    @Test
    void testLoginUsuarioNaoEncontrado() {
        when(usuarioRepository.buscarPorUsername("usuarioInexistente")).thenReturn(null);
        Usuario usuario = usuarioService.login("usuarioInexistente", "senha");
        assertNull(usuario);
    }

    @Test
    void testSalvarUsuario() {
        Usuario usuario = new Usuario("testUser", "testSenha", "teste");
        usuarioService.salvarOuAtualizarUsuario(usuario);
        verify(usuarioRepository, times(1)).salvarOuAtualizarUsuario(any(Usuario.class));
    }
}