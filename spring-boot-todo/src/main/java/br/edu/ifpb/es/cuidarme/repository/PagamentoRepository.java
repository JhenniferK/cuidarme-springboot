package br.edu.ifpb.es.cuidarme.repository;

import java.util.Optional;
import java.util.UUID;

import br.edu.ifpb.es.cuidarme.model.Pagamento;
import br.edu.ifpb.es.cuidarme.rest.dto.Pagamento.PagamentoBuscarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    @Query("SELECT p FROM Pagamento p WHERE p.lookupId = :lookupId")
    Optional<Pagamento> findByLookupId(@Param("lookupId") UUID lookupId);

    @Query("""
    SELECT p FROM Pagamento p
    WHERE (:#{#dto.data} IS NULL OR FUNCTION('DATE', p.data) = :#{#dto.data})
      AND (:#{#dto.metodo} IS NULL OR p.metodo = :#{#dto.metodo})
      AND (:#{#dto.status} IS NULL OR p.statusPagamento = :#{#dto.status})
""")
    Page<Pagamento> buscarPor(@Param("dto") PagamentoBuscarDTO dto, Pageable pageable);

}

