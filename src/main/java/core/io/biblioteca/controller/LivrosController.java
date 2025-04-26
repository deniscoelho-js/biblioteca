package core.io.biblioteca.controller;


import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.LivrosResponseDTO;
import core.io.biblioteca.service.LivrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
