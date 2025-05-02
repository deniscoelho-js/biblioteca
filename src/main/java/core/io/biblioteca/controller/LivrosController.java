package core.io.biblioteca.controller;


import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.LivrosResponseDTO;
import core.io.biblioteca.entity.Livros;
import core.io.biblioteca.service.LivrosService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivrosController {
    private final LivrosService livrosService;

    @PostMapping
    public ResponseEntity<LivrosResponseDTO> create(@RequestBody LivrosRequestDTO livrosRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(livrosService.salvarLivros(livrosRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivrosResponseDTO> buscarLivroPorId(@PathVariable Integer id) {
        LivrosResponseDTO livrosResponseDTO = livrosService.buscarLivroPorId(id);
        return ResponseEntity.ok(livrosResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<LivrosResponseDTO>> buscarLivros() {
        List<LivrosResponseDTO> livros = livrosService.buscarLivros();
        return ResponseEntity.ok(livros);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Integer id) {
        try {
            livrosService.deletarLivro(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivrosResponseDTO> editarLivro(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            LivrosResponseDTO livroAtualizado = livrosService.editarLivro(id, updates);
            return ResponseEntity.ok(livroAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
