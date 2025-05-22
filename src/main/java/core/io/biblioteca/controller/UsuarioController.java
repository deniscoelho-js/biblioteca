package core.io.biblioteca.controller;

import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.UsuarioRequestDTO;
import core.io.biblioteca.dto.UsuarioResponseDTO;
import core.io.biblioteca.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodos() {
        List<UsuarioResponseDTO> usuarios = usuarioService.buscarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Integer id) {
        UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> editarUsuario(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        try {
            UsuarioResponseDTO usuario = usuarioService.editarUsuario(id, updates);
            return ResponseEntity.ok(usuario);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
}
