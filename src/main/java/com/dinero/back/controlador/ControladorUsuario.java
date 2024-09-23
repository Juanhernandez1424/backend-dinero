package com.dinero.back.controlador;

import com.dinero.back.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dinero.back.interfacesServices.InterfaceUsuarioService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class ControladorUsuario {

    @Autowired
    private InterfaceUsuarioService service;

    @GetMapping()
    public List<Usuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
        Optional<Usuario> usuario = service.listarId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Usuario usuario) {

        if (usuario.getContrasena() == null || usuario.getNumeroDocumento() == 0) {
            return ResponseEntity.badRequest().body(Map.of("message", "Número de documento y contraseña son requeridos"));
        }

        boolean autenticado = service.autenticar(usuario.getNumeroDocumento(), usuario.getContrasena());
        if (autenticado) {
            Optional<Usuario> usuarioAutenticado = service.obtenerIdPorNroDocumento(usuario.getNumeroDocumento());

            if (usuarioAutenticado.isPresent()) {
                Usuario usuarioEcontrado = usuarioAutenticado.get();
                Map<String, Object> response = new HashMap<>();
                response.put("id", usuarioEcontrado.getIdUsuario());
                response.put("nombre", usuarioEcontrado.getNombre());
                response.put("apellido", usuarioEcontrado.getApellido());

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(404).body(Map.of("message", "Usuario no encontrado"));
            }
        } else {
            return ResponseEntity.status(401).body(Map.of("message", "Credenciales inválidas"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Usuario usuario) {

        if (usuario.getNombre() == null || usuario.getApellido() == null || usuario.getTipoDocumento() == 0 || usuario.getNumeroDocumento() == 0 || usuario.getContrasena() == null || usuario.getRepetirContrasena()== null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Todos los campos son requeridos para el registro"));
        }

        try {
            Usuario usuarioCreado = service.guardar(usuario);
            Map<String, Object> response = new HashMap<>();
            //response.put("idUsuario", usuarioCreado.getIdUsuario());
            response.put("nombre", usuarioCreado.getNombre());
            response.put("apellido", usuarioCreado.getApellido());
            response.put("tipoDocumento", usuarioCreado.getTipoDocumento());
            response.put("numeroDocumento", usuarioCreado.getNumeroDocumento());
            response.put("contrasena", usuarioCreado.getContrasena());
            response.put("repetirContrasena", usuarioCreado.getRepetirContrasena());
            response.put("estado", usuarioCreado.getEstado());

            response.put("message", "Usuario creado con éxito");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("", "Error al registrar el usuario"));
        }
    }
}
