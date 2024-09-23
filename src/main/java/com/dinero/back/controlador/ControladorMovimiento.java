package com.dinero.back.controlador;

import com.dinero.back.interfacesServices.InterfaceMovimientoService;
import com.dinero.back.modelo.Movimiento;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movimiento")
@CrossOrigin("*")
public class ControladorMovimiento {

    @Autowired
    private InterfaceMovimientoService service;

    @GetMapping()
    public List<Movimiento> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> obtenerMovimientoPorId(@PathVariable int id) {
        Optional<Movimiento> movimiento = service.listarPorId(id);
        return movimiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Movimiento>> listarPaginado(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int tamaño) {
        Pageable pageable = PageRequest.of(page, tamaño);
        Page<Movimiento> movimientos = service.listarPaginado(pageable);
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Page<Movimiento>> obtenerMovimientosPorIdUsuarioPaginado (@PathVariable("idUsuario") int idUsuario, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue ="8") int tamaño) {
        Pageable pageable = PageRequest.of(page, tamaño);
        Page<Movimiento> movimientos = (Page<Movimiento>) service.obtenerMovimientosPorIdUsuario(idUsuario, pageable);
        return ResponseEntity.ok(movimientos);
    }
    
    public ResponseEntity<Movimiento> save(@RequestBody Movimiento movimiento) {
        try {
            service.save(movimiento);

            return ResponseEntity.status(201).body(movimiento);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/recargas/usuario/{idUsuario}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientoRecargaPorIdUsuario(@PathVariable("idUsuario") int idUsuario) {
        List<Movimiento> recargas = service.obtenerValoresRecargaPorIdUsuario(idUsuario);
        return ResponseEntity.ok(recargas);
    }

    @GetMapping("/retiros/usuario/{idUsuario}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientoRetirosPorIdUsuario(@PathVariable("idUsuario") int idUsuario) {
        List<Movimiento> retiros = service.obtenerValoresRetiroPorIdUsuario(idUsuario);
        return ResponseEntity.ok(retiros);
    }

    /*@GetMapping("usuario/{idUsuario}")
    public ResponseEntity<List<Movimiento>> obtenerMovimientosPorIdUsuario(@PathVariable("idUsuario") int idUsuario) {
        List<Movimiento> movimientos = service.obtenerMovimientosPorIdUsuario(idUsuario);
        if (movimientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(movimientos);
        }
    }*/
}
