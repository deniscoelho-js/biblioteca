package core.io.biblioteca.service.impl;

import core.io.biblioteca.dto.LivrosRequestDTO;
import core.io.biblioteca.dto.LivrosResponseDTO;
import core.io.biblioteca.dto.UsuarioRequestDTO;
import core.io.biblioteca.dto.UsuarioResponseDTO;
import core.io.biblioteca.dto.mapper.LivrosMapper;
import core.io.biblioteca.dto.mapper.UsuarioMapper;
import core.io.biblioteca.entity.Livros;
import core.io.biblioteca.entity.Usuario;
import core.io.biblioteca.repository.LivrosRepository;
import core.io.biblioteca.repository.UsuarioRepository;
import core.io.biblioteca.service.LivrosService;
import core.io.biblioteca.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final LivrosRepository livrosRepository;
    private final LivrosService livrosService;
    private final LivrosResponseDTO livrosResponseDTO;
    private final LivrosMapper livrosMapper;

    @Override
    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.save(usuarioMapper.toUsuario(usuarioRequestDTO));
        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    @Transactional
    @Override
    public UsuarioResponseDTO buscarUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuario com id %s não encontrado", id)));
        return usuarioMapper.toUsuarioResponseDTO(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> buscarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toUsuarioListResponseDTOS(usuarios);
    }

    @Override
    public void deletarUsuario(Integer id) {
        UsuarioResponseDTO usuario = buscarUsuarioPorId(id);
        usuarioRepository.deleteById(usuario.getId());
    }

    @Override
    public UsuarioResponseDTO editarUsuario(Integer id, Map<String, Object> updates) {
        // Busca o usuário por ID no repositório
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário com id %s não encontrado", id)));

        // Atualiza apenas os campos nome, email e senha
        updates.forEach((key, value) -> {
            switch (key) {
                case "nome" -> usuario.setNome((String) value);
                case "email" -> usuario.setEmail((String) value);
                case "senha" -> usuario.setSenha((String) value);
                default ->
                        throw new IllegalArgumentException(String.format("Campo %s não é formatável ou não existe", key));
            }
        });

        // Salva o usuário atualizado no banco de dados
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        // Converte a entidade atualizada para UsuarioResponseDTO e retorna
        return usuarioMapper.toUsuarioResponseDTO(usuarioAtualizado);
    }

//    @Override
//    public UsuarioResponseDTO adicionarLivroParaUsuario(Integer id, LivrosRequestDTO livrosRequestDTO) {
//        Livros livroEntity = livrosMapper.toLivros(livrosRequestDTO);
//
//        UsuarioResponseDTO usuario = buscarUsuarioPorId(id);
//        LivrosService livroId = (LivrosService) livrosService.buscarLivroPorId(livroEntity.getId());
//
//        LivrosResponseDTO livros = livrosService.buscarLivroPorId(livroEntity.getId());
//
//        if (!usuario.getLivros().contains(livros)) {
//            usuario.getLivros().add(livros);
//        } else {
//            throw new IllegalArgumentException(String.format("O livro com id %s já está na lista do usuário", livros.getId()));
//        }
//
//        usuarioRepository.save(usuarioMapper.toUsuario(usuario));
//    }


    public UsuarioResponseDTO adicionarLivroParaUsuario(Integer id, LivrosRequestDTO livrosRequestDTO) {
        // Busca a entidade Usuario diretamente do repositório
        Livros livroEntity = livrosMapper.toLivros(livrosRequestDTO);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário com id %s não encontrado", id)));

        // Busca a entidade Livros diretamente do repositório
        Livros livro = livrosRepository.findById(livroEntity.getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Livro com id %s não encontrado", livroEntity.getId())));

        // Verifica se o livro já está na lista do usuário
        if (!usuario.getLivros().contains(livro)) {
            usuario.getLivros().add(livro);
        } else {
            throw new IllegalArgumentException(String.format("O livro com id %s já está na lista do usuário", livro.getId()));
        }

        // Salva a entidade atualizada no banco de dados
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        // Retorna a versão DTO do usuário atualizado
        return usuarioMapper.toUsuarioResponseDTO(usuarioAtualizado);
    }
}
