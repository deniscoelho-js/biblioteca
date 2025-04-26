package core.io.biblioteca.dto;

import core.io.biblioteca.entity.Autor;
import core.io.biblioteca.enums.Categoria;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class LivrosResponseDTO {
    private Integer id;
    private String titulo;
    private String anoDePublicacao;
    private Categoria categoria;
    private String editora;
    private List<AutorResponseDTO> autores = new ArrayList<>();
}
