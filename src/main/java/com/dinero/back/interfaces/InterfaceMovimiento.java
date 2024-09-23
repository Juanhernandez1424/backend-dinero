package com.dinero.back.interfaces;

import com.dinero.back.modelo.Movimiento;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InterfaceMovimiento extends CrudRepository<Movimiento, Integer>{
    @Query("SELECT m FROM Movimiento m WHERE m.idUsuario = :idUsuario AND m.idTipoMovimiento=1")
    List<Movimiento> buscarMovimientoRecargaPorUsuario(@Param("idUsuario") int idUsuario);
    
    @Query("SELECT COALESCE(SUM(m.valorMovimiento), 0) FROM Movimiento m WHERE m.idTipoMovimiento = 1")
    Double sumaValoresRecarga(@Param("idUsuario") int idUsuario);
    
    @Query("SELECT m FROM Movimiento m WHERE m.idUsuario = :idUsuario AND m.idTipoMovimiento=2")
    List<Movimiento> buscarMovimientoRetiroPorUsuario(@Param("idUsuario") int idUsuario);
    
    @Query("SELECT COALESCE(SUM(m.valorMovimiento), 0) FROM Movimiento m WHERE m.idTipoMovimiento = 2")
    Double sumaValoresRetiro(@Param("idUsuario") int idUsuario);
    
    @Query("SELECT m FROM Movimiento m WHERE m.idUsuario = :idUsuario ORDER BY m.idMovimiento DESC")
    public Page<Movimiento> movimientosPorIdUsuario(@Param("idUsuario") int idUsuario, Pageable pageable);

    public Page<Movimiento> findAll(Pageable pageable);
}
