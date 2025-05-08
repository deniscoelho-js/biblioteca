package core.io.biblioteca.controller;

import core.io.biblioteca.dto.AutorRequestDTO;
import core.io.biblioteca.dto.AutorResponseDTO;
import core.io.biblioteca.service.AutorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/autores")
public class AutorController {
    private final AutorService autorService;

    @PostMapping
    public ResponseEntity<AutorResponseDTO> salvarAutor(@RequestBody AutorRequestDTO autorRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.salvarAutor(autorRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<AutorResponseDTO>> buscarTodos(){
        List<AutorResponseDTO> autores = autorService.buscarAutores();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> buscarAutorPorId(@PathVariable Integer id) {
        AutorResponseDTO autor = autorService.buscarAutorPorId(id);
        return ResponseEntity.ok(autor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable Integer id){
        try {
            autorService.deletarAutor(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDTO> editarAutor(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            AutorResponseDTO autorAtualizado = autorService.editarAutor(id, updates);
            return ResponseEntity.ok(autorAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}

