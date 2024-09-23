package com.dinero.back.service;

import com.dinero.back.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dinero.back.interfaces.InterfaceUsuario;
import com.dinero.back.interfacesServices.InterfaceUsuarioService;
import java.util.Objects;

@Service
public class UsuarioService implements InterfaceUsuarioService {
    
    @Autowired
    private InterfaceUsuario data;
    
    @Override
    public List<Usuario> listar() {
        return (List<Usuario>) data.findAll();
    }

    @Override
    public Optional<Usuario> listarId(int id) {
        return data.findById(id);
    }

    @Override
    public int save(Usuario usuario) {
        Usuario savedUsuario = data.save(usuario);
        return savedUsuario.getIdUsuario();
    }

    @Override
    public void delete(int id) {
        data.deleteById(id);
    }

    @Override
    public boolean autenticar(int numeroDocumento, String contrasena) {
            return data.findByNumeroDocumento(numeroDocumento).map(usuario -> Objects.equals(contrasena, usuario.getContrasena())).orElse(false);
    }

    @Override
    public Optional<Usuario> obtenerIdPorNroDocumento(int numeroDocumento) {
        return data.findByNumeroDocumento(numeroDocumento);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return data.save(usuario);
    }
}
