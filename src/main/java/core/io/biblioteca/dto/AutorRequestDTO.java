package core.io.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AutorRequestDTO {

    private String nome;
    private String nacionalidade;
    private String dataDeNascimento;

}
