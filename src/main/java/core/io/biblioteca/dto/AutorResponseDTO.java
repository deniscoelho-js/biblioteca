package core.io.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @JsonIgnore
//    private List<LivrosResponseDTO> livros = new ArrayList();
}
