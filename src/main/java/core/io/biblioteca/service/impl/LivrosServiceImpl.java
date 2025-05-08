package core.io.biblioteca.service.impl;

import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.LivrosResponseDTO;
import core.io.biblioteca.dto.mapper.AutorMapper;
import core.io.biblioteca.dto.mapper.LivrosMapper;
import core.io.biblioteca.entity.Autor;
import core.io.biblioteca.entity.Livros;
import core.io.biblioteca.enums.Categoria;
import core.io.biblioteca.repository.LivrosRepository;
import core.io.biblioteca.service.LivrosService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LivrosServiceImpl implements LivrosService {

    private final LivrosRepository livrosRepository;
    private final LivrosMapper livrosMapper;
    private final AutorMapper autorMapper;

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

    @Override
    public void deletarLivro(Integer id) {
        LivrosResponseDTO livro = buscarLivroPorId(id);
        livrosRepository.deleteById(livro.getId());
    }

    @Transactional
    @Override
    public LivrosResponseDTO editarLivro(Integer id, Map<String, Object> updates) {
        // Busca o livro por ID no repositório
        Livros livro = livrosRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Livro com id %s não encontrado", id)));

        // Atualiza os campos da entidade Livros com base no Map
        updates.forEach((key, value) -> {
            switch (key) {
                case "titulo" -> livro.setTitulo((String) value);
                case "anoDePublicacao" -> livro.setAnoDePublicacao((String) value);
                case "categoria" -> livro.setCategoria(Categoria.valueOf((String) value)); // Supondo que Categoria é um enum
                case "editora" -> livro.setEditora((String) value);
//                case "autores" -> livro.setAutores(
//                        ((List<Map<String, Object>>) value).stream()
//                                .map(autorData -> {
//                                    Autor autor = new Autor();
//                                    autor.setId((Integer) autorData.get("id"));
//                                    autor.setNome((String) autorData.get("nome"));
//                                    return autor;
//                                })
//                                .collect(Collectors.toList())
//                ); // Atualiza os autores a partir do Map
                default -> throw new IllegalArgumentException(String.format("Campo %s não é formatável ou não existe", key));
            }
        });

        // Salva o livro atualizado no banco de dados
        Livros livroAtualizado = livrosRepository.save(livro);

        // Converte a entidade atualizada para LivrosResponseDTO e retorna
        return livrosMapper.toLivrosResponseDTO(livroAtualizado);
    }
}