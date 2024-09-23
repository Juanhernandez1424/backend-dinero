package com.dinero.back.controlador;

import com.dinero.back.interfacesServices.InterfaceBolsilloService;
import com.dinero.back.modelo.Bolsillo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bolsillo")
@CrossOrigin("*")
public class ControladorBolsillo {
    @Autowired
    private InterfaceBolsilloService service;
    
    @GetMapping()
    public List<Bolsillo> listar(){
        return service.listar();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bolsillo> obtenerMovimientoPorId(@PathVariable int id){
        Optional<Bolsillo> bolsillo = service.listarPorId(id);
        return bolsillo.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/paginado")
    public ResponseEntity<Page<Bolsillo>> listarPaginado(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "8") int tamaño){
        Pageable pageable = PageRequest.of(page, tamaño);
        Page<Bolsillo> bolsillos = service.listarPaginado(pageable);
        return ResponseEntity.ok(bolsillos);
    }
    
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Page<Bolsillo>> obtenerBolsillosPorIdUsuario(@PathVariable("idUsuario") int idUsuario, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "8") int tamaño){
        Pageable pageable = PageRequest.of(page, tamaño);
        Page<Bolsillo> bolsillos = service.obtenerValorBolsilloPorIdUsuarioPaginado(idUsuario, pageable);
        return ResponseEntity.ok(bolsillos);
    }
    
    @PostMapping()
    public ResponseEntity<Bolsillo> save(@RequestBody Bolsillo bolsillo){
        try{
            service.save(bolsillo);
            
            return ResponseEntity.status(201).body(bolsillo);
        } catch(Exception e){
            return ResponseEntity.status(500).build();
        }
    }
    
    @PutMapping("/update/{idBolsillo}") 
    public void actualizarBolsilloPorId(@PathVariable("idBolsillo") int idBolsillo, @RequestBody Bolsillo bolsillo){
        if(idBolsillo != bolsillo.getIdBolsillo()){
            throw new IllegalArgumentException("El id de la url no coincide con el id de la solicitud");
        } else{
            service.actualizar(bolsillo);
        }
    }
    
    @DeleteMapping("/delete/{idBolsillo}")
    public ResponseEntity<Void> eliminarBolsillo(@PathVariable("idBolsillo") int idBolsillo){
        try{
            service.delete(idBolsillo);
            return ResponseEntity.status(204).build();
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
