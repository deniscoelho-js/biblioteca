package core.io.biblioteca.service.impl;

import core.io.biblioteca.dto.UsuarioRequestDTO;
import core.io.biblioteca.dto.UsuarioResponseDTO;
import core.io.biblioteca.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return null;
    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorId(Integer id) {
        return null;
    }

    @Override
    public List<UsuarioResponseDTO> buscarUsuarios() {
        return List.of();
    }

    @Override
    public void deletarUsuario(Integer id) {

    }

    @Override
    public UsuarioResponseDTO editarUsuario(Integer id, Map<String, Object> updates) {
        return null;
    }
}
