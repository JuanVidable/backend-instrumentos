package com.example.demo.repositories;

import com.example.demo.entities.Instrumento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentoRepository extends BaseRepository<Instrumento, Long> {
    @Query(value = "SELECT i.* " +
            "FROM instrumento i " +
            "JOIN categoria c ON i.fk_categoria = c.id " +
            "WHERE c.denominacion = :palabra",
            nativeQuery = true)
    List<Instrumento> search(@Param("palabra") String palabra);
}
