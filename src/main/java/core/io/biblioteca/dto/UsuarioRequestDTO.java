package core.io.biblioteca.dto;

import core.io.biblioteca.entity.Livros;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String senha;
    private LocalDateTime dataDeRegistro;

    private List<Livros> livros = new ArrayList<>();
}
