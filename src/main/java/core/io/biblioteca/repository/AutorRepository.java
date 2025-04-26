package core.io.biblioteca.repository;

import core.io.biblioteca.entity.Autor;
import core.io.biblioteca.entity.Livros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    List<Livros> findByLivrosId(Integer id);
}
