package core.io.biblioteca.service.impl;

import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.LivrosResponseDTO;
import core.io.biblioteca.dto.mapper.LivrosMapper;
import core.io.biblioteca.entity.Livros;
import core.io.biblioteca.repository.LivrosRepository;
import core.io.biblioteca.service.LivrosService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LivrosServiceImpl implements LivrosService {

    private final LivrosRepository livrosRepository;
    private final LivrosMapper livrosMapper;

    @Transactional
    @Override
    public LivrosResponseDTO salvarLivros(LivrosRequestDTO livrosRequestDTO) {
        Livros livros = livrosRepository.save(livrosMapper.toLivros(livrosRequestDTO));
        return livrosMapper.toLivrosResponseDTO(livros);
    }

    @Transactional
    @Override
    public LivrosResponseDTO buscarLivroPorId(Integer id) {
        Livros livros = livrosRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Livro não encontrado", id))
        );
        return livrosMapper.toLivrosResponseDTO(livros);
    }

    @Transactional
    @Override
    public List<LivrosResponseDTO> buscarLivros() {
        List<Livros> livros = livrosRepository.findAll();
        return livrosMapper.toLivrosListResponseDTO(livros);
    }

}
