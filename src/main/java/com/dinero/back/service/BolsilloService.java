package com.dinero.back.service;

import com.dinero.back.interfaces.InterfaceBolsillo;
import com.dinero.back.interfacesServices.InterfaceBolsilloService;
import com.dinero.back.modelo.Bolsillo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BolsilloService implements InterfaceBolsilloService {
    
    @Autowired
    private InterfaceBolsillo data;
    
    @Override
    public List<Bolsillo> listar() {
        return (List<Bolsillo>) data.findAll();
    }

    @Override
    public Optional<Bolsillo> listarPorId(int id) {
        return data.findById(id);
    }

    @Override
    public int save(Bolsillo bolsillo) {
        Bolsillo savedBolsillo = data.save(bolsillo);
        return savedBolsillo.getIdBolsillo();
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }

    @Override
    public Double obtenerSumaBolsillos(int idUsuario) {
        return data.sumaValoresBolsillo(idUsuario);
    }

    @Override
    public void actualizar(Bolsillo bolsillo) {
        Optional<Bolsillo> optionalBolsillo = data.findById(bolsillo.getIdBolsillo());
        if(optionalBolsillo.isPresent()){
            Bolsillo bolsilloCreado = optionalBolsillo.get();
            bolsilloCreado.setDescripcionBolsillo(bolsillo.getDescripcionBolsillo());
            bolsilloCreado.setValorBolsillo(bolsillo.getValorBolsillo());
            data.save(bolsilloCreado);
        } else{
            throw new RuntimeException("Bolsillo con id: " + bolsillo.getIdBolsillo() + "no econtrado");
        }
    }

    @Override
    public Page<Bolsillo> obtenerValorBolsilloPorIdUsuarioPaginado(int idUsuario, Pageable pageable) {
        return data.buscarBolsilloPorIdUsuario(idUsuario, pageable);
    }

    @Override
    public Page<Bolsillo> listarPaginado(Pageable pageable) {
        return data.findAll(pageable);
    }
    
}
