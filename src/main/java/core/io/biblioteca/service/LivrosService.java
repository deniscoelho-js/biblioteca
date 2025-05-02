package core.io.biblioteca.service;

import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.LivrosResponseDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface LivrosService {
    public LivrosResponseDTO salvarLivros(LivrosRequestDTO livrosRequestDTO);
    public LivrosResponseDTO buscarLivroPorId(Integer id);
    public List<LivrosResponseDTO> buscarLivros();
    public void deletarLivro(Integer id);
    public LivrosResponseDTO editarLivro(Integer id, Map<String, Object> updates);

}
