package com.literalura.repository;

import com.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByIdioma(String idioma);

    Optional<Livro> findByTitulo(String titulo);

    Optional<Object> findByTituloIgnoreCase(String titulo);
}
