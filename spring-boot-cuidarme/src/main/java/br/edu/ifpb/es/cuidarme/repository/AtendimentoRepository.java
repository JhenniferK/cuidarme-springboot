package br.edu.ifpb.es.cuidarme.repository;

import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.model.Atendimento;
import br.edu.ifpb.es.cuidarme.rest.dto.Atendimento.AtendimentoBuscarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    @Query("SELECT a FROM Atendimento a WHERE a.lookupId = :lookupId")
    Optional<Atendimento> findByLookupId(@Param("lookupId") UUID lookupId);

    @Query("""
    SELECT a FROM Atendimento a
    WHERE (:#{#dto.data} IS NULL OR FUNCTION('DATE', a.data) = :#{#dto.data})
      AND (:#{#dto.tipo} IS NULL OR a.tipo = :#{#dto.tipo})
      AND (:#{#dto.localidade} IS NULL OR a.localidade = :#{#dto.localidade})
      AND (:#{#dto.status} IS NULL OR a.status = :#{#dto.status})
""")
    Page<Atendimento> buscarPor(@Param("dto") AtendimentoBuscarDTO dto, Pageable pageable);

}

