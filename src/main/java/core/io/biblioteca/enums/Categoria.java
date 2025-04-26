package core.io.biblioteca.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@AllArgsConstructor
public enum Categoria {
    FICCAO(0, "ficcao"),
    ROMANCE(1, "romance"),
    MISTERIO(2, "misterio"),
    AUTOAJUDA(3, "autoajuda");

    private final Integer codigo;
    private final String descricao;
}
