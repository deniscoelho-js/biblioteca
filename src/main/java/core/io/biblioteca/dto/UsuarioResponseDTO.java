package core.io.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioResponseDTO {
    private String nome;
    private String email;

    private List<LivrosResponseDTO> livros = new ArrayList<>();
}
