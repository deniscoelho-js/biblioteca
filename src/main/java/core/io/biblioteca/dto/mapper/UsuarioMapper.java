package core.io.biblioteca.dto.mapper;

import core.io.biblioteca.dto.UsuarioRequestDTO;
import core.io.biblioteca.dto.UsuarioResponseDTO;
import core.io.biblioteca.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public Usuario toUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return new ModelMapper().map(usuarioRequestDTO, Usuario.class);
    }

    public UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
        return new ModelMapper().map(usuario, UsuarioResponseDTO.class);
    }

    public List<UsuarioResponseDTO> toUsuarioListResponseDTOS(List<Usuario> usuarios) {
        return usuarios.stream().map(usuario -> new ModelMapper().map(usuario, UsuarioResponseDTO.class))
                .collect(Collectors.toList());
    }
}
