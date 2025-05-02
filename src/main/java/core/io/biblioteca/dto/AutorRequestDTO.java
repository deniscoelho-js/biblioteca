package core.io.biblioteca.dto;

import core.io.biblioteca.entity.Livros;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutorRequestDTO {

    private String nome;
    private String nacionalidade;
    private String dataDeNascimento;
    private List<Livros> livros = new ArrayList<>();
}
