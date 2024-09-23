package com.dinero.back.interfacesServices;

import com.dinero.back.modelo.Usuario;
import java.util.List;
import java.util.Optional;

public interface InterfaceUsuarioService {
    public List<Usuario>listar();
    public Optional<Usuario>listarId(int id);
    public int save(Usuario usuario);
    public void delete(int id);
    public boolean autenticar(int numeroDocumento, String contrasena);
    public Usuario guardar(Usuario usuario);
    public Optional<Usuario> obtenerIdPorNroDocumento(int numeroDocumento);
}
