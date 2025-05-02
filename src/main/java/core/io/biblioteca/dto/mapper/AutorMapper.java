package core.io.biblioteca.dto.mapper;

import core.io.biblioteca.dto.AutorRequestDTO;
import core.io.biblioteca.dto.AutorResponseDTO;
import core.io.biblioteca.entity.Autor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AutorMapper {

    public Autor toAutor(AutorRequestDTO autorRequestDTO) {
        return new ModelMapper().map(autorRequestDTO, Autor.class);
    }


    public AutorResponseDTO toAutorResponseDTO(Autor autor) {
        return new ModelMapper().map(autor, AutorResponseDTO.class);
    }

    public List<AutorResponseDTO> toAutorListResponseDTO(List<Autor> autores) {
        return autores.stream().map(autorRequestDTO -> new ModelMapper().
                map(autorRequestDTO, AutorResponseDTO.class)).collect(Collectors.toList());
    }
}
