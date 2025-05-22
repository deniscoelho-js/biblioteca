package core.io.biblioteca.service;

import core.io.biblioteca.dto.AutorRequestDTO;
import core.io.biblioteca.entity.Autor;
import core.io.biblioteca.entity.Livros;
import core.io.biblioteca.entity.Usuario;
import core.io.biblioteca.enums.Categoria;
import core.io.biblioteca.repository.AutorRepository;
import core.io.biblioteca.repository.LivrosRepository;
import core.io.biblioteca.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DBService {

    private final AutorRepository autorRepository;
    private final LivrosRepository livrosRepository;
    private final UsuarioRepository usuarioRepository;

    public void  instanciaDB(){

        Autor aut1 = new Autor(null, "Koro sensei",new ArrayList<>() );
        Autor aut2 = new Autor(null, "Nagisa Shiota",new ArrayList<>() );
        Autor aut3 = new Autor(null, "Karma Akabane",new ArrayList<>() );
        Autor aut4 = new Autor(null, "Kaede Kayano",new ArrayList<>() );

        Livros liv1 = new Livros(null, "Título do Livro", "2025", Categoria.FICCAO, "Editora Fantástica", List.of(aut1), null);
        Livros liv2 = new Livros(null, "Título do Livro", "2025", Categoria.FICCAO, "Editora Fantástica", List.of(aut2), null);
        Livros liv3 = new Livros(null, "Título do Livro", "2025", Categoria.FICCAO, "Editora Fantástica", List.of(aut3), null);
        Livros liv4 = new Livros(null, "Título do Livro", "2025", Categoria.FICCAO, "Editora Fantástica", List.of(aut1, aut3), null);

        Usuario us1 = new Usuario(null, "Shay",  "shay@gmail.com", "123456", LocalDateTime.parse("2025-12-12T00:00:00"), List.of(liv1));
        Usuario us2 = new Usuario(null, "Irina Jelavió",  "irina@gmail.com", "123456", LocalDateTime.parse("2025-12-12T00:00:00"), new ArrayList<>());
        Usuario us3 = new Usuario(null, "Itona",  "itona@gmail.com", "123456", LocalDateTime.parse("2025-12-12T00:00:00"), List.of(liv1,liv3, liv4));
        Usuario us4 = new Usuario(null, "Yuuma Isogai",  "Yuuma@gmail.com", "123456", LocalDateTime.parse("2025-12-12T00:00:00"), new ArrayList<>());


        autorRepository.saveAll(List.of(aut1, aut2, aut3, aut4));
        livrosRepository.saveAll(List.of(liv1, liv2, liv3, liv4));
        usuarioRepository.saveAll(List.of(us1, us2, us3, us4));
    }
}
