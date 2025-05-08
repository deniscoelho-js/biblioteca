package core.io.biblioteca.service;

import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.LivrosResponseDTO;
import core.io.biblioteca.dto.UsuarioRequestDTO;
import core.io.biblioteca.dto.UsuarioResponseDTO;

import java.util.List;
import java.util.Map;

public interface UsuarioService {
    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO usuarioRequestDTO);
    public UsuarioResponseDTO buscarUsuarioPorId(Integer id);
    public List<UsuarioResponseDTO> buscarUsuarios();
    public void deletarUsuario(Integer id);
    public UsuarioResponseDTO editarUsuario(Integer id, Map<String, Object> updates);
}
