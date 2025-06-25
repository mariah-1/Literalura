package com.literalura.repository;

import com.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNascimentoLessThanEqualAndFalecimentoGreaterThanEqual(int nascimento, int falecimento);

    Optional<Autor> findByNomeIgnoreCase(String trim);
}
