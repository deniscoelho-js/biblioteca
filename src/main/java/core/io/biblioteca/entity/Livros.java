package core.io.biblioteca.entity;

import core.io.biblioteca.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "livro_id")
    private Integer id;
    private String titulo;
    private String anoDePublicacao;
    @Enumerated(EnumType.ORDINAL)
    private Categoria categoria;
    private String editora;

    @ManyToMany
    @JoinTable(name = "autor_livro", joinColumns = @JoinColumn(name = "livro_id"),
    inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores = new ArrayList<>();
}
