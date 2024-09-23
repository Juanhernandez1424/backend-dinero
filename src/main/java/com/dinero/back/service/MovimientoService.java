package com.dinero.back.service;

import com.dinero.back.interfaces.InterfaceMovimiento;
import com.dinero.back.interfacesServices.InterfaceMovimientoService;
import com.dinero.back.modelo.Movimiento;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService implements InterfaceMovimientoService{
    
    @Autowired
    private InterfaceMovimiento data;
    
    @Override
    public List<Movimiento> listar() {
        return (List<Movimiento>) data.findAll();
    }

    @Override
    public Optional<Movimiento> listarPorId(int id) {
        return data.findById(id);
    }

    @Override
    public int save(Movimiento movimiento) {
        Movimiento savedMovimiento = data.save(movimiento);
        return savedMovimiento.getIdMovimiento();
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }

    @Override
    public Double obtenerSumaValoresRecarga(int idUsuario) {
        return data.sumaValoresRecarga(idUsuario);
    }

    @Override
    public Double obtenerSumaValoresRetiro(int idUsuario) {
        return data.sumaValoresRetiro(idUsuario);
    }

    @Override
    public List<Movimiento> obtenerValoresRecargaPorIdUsuario(int idUsuario) {
        return data.buscarMovimientoRecargaPorUsuario(idUsuario);
    }

    @Override
    public List<Movimiento> obtenerValoresRetiroPorIdUsuario(int idUsuario) {
        return data.buscarMovimientoRetiroPorUsuario(idUsuario);
    }

    @Override
    public Page<Movimiento> listarPaginado(Pageable pageable) {
        return data.findAll(pageable);
    }

    @Override
    public Page<Movimiento> obtenerMovimientosPorIdUsuario(int idUsuario, Pageable pageable) {
        return data.movimientosPorIdUsuario(idUsuario, pageable);
    }
    
}
