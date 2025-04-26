package core.io.biblioteca.dto;

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
public class AutorResponseDTO {
    private Integer id;
    private String nome;
    private String nacionalidade;
    private String dataDeNascimento;
    private List<LivrosResponseDTO> livros = new ArrayList();
}
