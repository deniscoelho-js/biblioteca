package core.io.biblioteca.service;

import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.LivrosResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LivrosService {
    public LivrosResponseDTO salvarLivros(LivrosRequestDTO livrosRequestDTO);
    public LivrosResponseDTO buscarLivroPorId(Integer id);
    public List<LivrosResponseDTO> buscarLivros();
}
