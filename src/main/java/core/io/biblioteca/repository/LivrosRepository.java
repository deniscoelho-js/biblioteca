package core.io.biblioteca.repository;

import core.io.biblioteca.entity.Autor;
import core.io.biblioteca.entity.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivrosRepository extends JpaRepository<Livros, Integer> {
    List<Autor> findByAutoresId(Integer id);
}
