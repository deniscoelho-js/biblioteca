package core.io.biblioteca.dto.mapper;

import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.LivrosResponseDTO;
import core.io.biblioteca.entity.Livros;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LivrosMapper {
    public Livros toLivros(LivrosRequestDTO livrosRequestDTO) {
        return new ModelMapper().map(livrosRequestDTO, Livros.class);
    }

    public LivrosResponseDTO toLivrosResponseDTO(Livros livros) {
        return new ModelMapper().map(livros, LivrosResponseDTO.class);
    }

    public List<LivrosResponseDTO> toLivrosListResponseDTO(List<Livros> livros) {
        return livros.stream()
                .map(livro -> new ModelMapper().map(livro, LivrosResponseDTO.class))
                .collect(Collectors.toList());
    }
}
