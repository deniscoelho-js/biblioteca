package core.io.biblioteca.service.impl;

import core.io.biblioteca.dto.AutorRequestDTO;
import core.io.biblioteca.dto.AutorResponseDTO;
import core.io.biblioteca.dto.mapper.AutorMapper;
import core.io.biblioteca.entity.Autor;
import core.io.biblioteca.repository.AutorRepository;
import core.io.biblioteca.repository.LivrosRepository;
import core.io.biblioteca.service.AutorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AutorServiceImpl implements AutorService {

    private final AutorMapper autorMapper;
    private final AutorRepository autorRepository;
    private final LivrosRepository livrosRepository;

    @Transactional
    @Override
    public AutorResponseDTO salvarAutor(AutorRequestDTO autorRequestDTO) {
        Autor autor = autorRepository.save(autorMapper.toAutor(autorRequestDTO));
        return autorMapper.toAutorResponseDTO(autor);
    }

    @Transactional
    @Override
    public AutorResponseDTO buscarAutorPorId(Integer id) {
        Autor autor = autorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Autor com id %s não encontrado", id)));
        return autorMapper.toAutorResponseDTO(autor);
    }

    @Override
    public List<AutorResponseDTO> buscarAutores() {
        List<Autor> autores = autorRepository.findAll();
        return autorMapper.toAutorListResponseDTO(autores);
    }

    @Override
    public void deletarAutor(Integer id) {
        AutorResponseDTO autor = buscarAutorPorId(id);
        autorRepository.deleteById(autor.getId());
    }

    @Override
    public AutorResponseDTO editarAutor(Integer id, Map<String, Object> updates) {
        // Busca o autor pelo ID no repositório
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Autor com id %s não encontrado", id)));

        // Atualiza os campos da entidade Autor com base no Map
        updates.forEach((key, value) -> {
            switch (key) {
                case "nome" -> autor.setNome((String) value);
                case "livros" -> autor.setLivros(
                        ((List<Map<String, Object>>) value).stream()
                                .map(livroData -> {
                                    Integer livroId = (Integer) livroData.get("id");
                                    return livrosRepository.findById(livroId)
                                            .orElseThrow(() -> new EntityNotFoundException(String.format("Livro com id %s não encontrado", livroId)));
                                })
                                .collect(Collectors.toList())
                ); // Atualiza a lista de livros a partir do Map
                default ->
                        throw new IllegalArgumentException(String.format("Campo %s não é formatável ou não existe", key));
            }
        });
        Autor autorAtualizado = autorRepository.save(autor);
        return autorMapper.toAutorResponseDTO(autorAtualizado);
    }
}
