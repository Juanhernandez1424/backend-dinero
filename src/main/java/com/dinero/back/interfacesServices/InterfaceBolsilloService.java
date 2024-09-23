package com.dinero.back.interfacesServices;

import com.dinero.back.modelo.Bolsillo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterfaceBolsilloService {
    public List<Bolsillo> listar();
    public Optional<Bolsillo> listarPorId(int idBolsillo);
    public int save(Bolsillo bolsillo);
    public void actualizar(Bolsillo bolsillo);
    public void delete(int id);
    public Double obtenerSumaBolsillos(int idUsuario);
    public Page<Bolsillo> listarPaginado(Pageable pageable);
    public Page<Bolsillo> obtenerValorBolsilloPorIdUsuarioPaginado(int idUsuario, Pageable pageable);
}
