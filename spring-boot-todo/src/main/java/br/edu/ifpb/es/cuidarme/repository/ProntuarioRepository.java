package br.edu.ifpb.es.cuidarme.repository;

import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.model.Prontuario;
import br.edu.ifpb.es.cuidarme.rest.dto.Prontuario.ProntuarioBuscarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {

    @Query("SELECT p FROM Prontuario p WHERE p.lookupId = :lookupId")
    Optional<Prontuario> findByLookupId(@Param("lookupId") UUID lookupId);

    @Query("""
    SELECT p FROM Prontuario p
    WHERE (:#{#dto.dataRegistro} IS NULL OR p.dataRegistro = :#{#dto.dataRegistro})
""")
    Page<Prontuario> buscarPor(@Param("dto") ProntuarioBuscarDTO dto, Pageable pageable);

}

