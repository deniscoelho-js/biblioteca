package core.io.biblioteca.service;

import core.io.biblioteca.dto.AutorRequestDTO;
import core.io.biblioteca.dto.AutorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface AutorService {
    public AutorResponseDTO salvarAutor(AutorRequestDTO autorRequestDTO);
    public AutorResponseDTO buscarAutorPorId(Integer id);
    public List<AutorResponseDTO> buscarAutores();
    public void deletarAutor(Integer id);
    public AutorResponseDTO editarAutor(Integer id, Map<String, Object> updates);
}
