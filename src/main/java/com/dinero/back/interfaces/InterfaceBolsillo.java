package com.dinero.back.interfaces;

import com.dinero.back.modelo.Bolsillo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InterfaceBolsillo extends CrudRepository<Bolsillo, Integer>{
    @Query("SELECT b FROM Bolsillo b WHERE b.idUsuario = :idUsuario ORDER BY idBolsillo DESC")
    Page<Bolsillo> buscarBolsilloPorIdUsuario(@Param("idUsuario") int idUsuario, Pageable pageable);
    
    @Query("SELECT COALESCE(SUM(b.valorBolsillo), 0) FROM Bolsillo b WHERE b.idUsuario = :idUsuario")
    Double sumaValoresBolsillo(@Param("idUsuario") int idUsuario);

    public Page<Bolsillo> findAll(Pageable pageable);
}
