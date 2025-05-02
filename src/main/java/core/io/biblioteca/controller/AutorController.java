package core.io.biblioteca.controller;

import core.io.biblioteca.dto.AutorRequestDTO;
import core.io.biblioteca.dto.AutorResponseDTO;
import core.io.biblioteca.entity.Autor;
import core.io.biblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
