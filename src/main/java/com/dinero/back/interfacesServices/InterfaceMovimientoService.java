package com.dinero.back.interfacesServices;

import com.dinero.back.modelo.Movimiento;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterfaceMovimientoService {
    public List<Movimiento> listar();
    public Optional<Movimiento> listarPorId(int id);
    public int save(Movimiento movimiento);
    public void delete(int id);
    public Double obtenerSumaValoresRecarga(int idUsuario);
    public Double obtenerSumaValoresRetiro(int IdUsuario);
    public List<Movimiento> obtenerValoresRecargaPorIdUsuario(int idUsuario);
    public List<Movimiento> obtenerValoresRetiroPorIdUsuario(int idUsuario);
    public Page<Movimiento> listarPaginado(Pageable pageable);
    public Page<Movimiento> obtenerMovimientosPorIdUsuario(int idUsuario, Pageable pageable);
}
