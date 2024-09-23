package com.dinero.back.interfaces;

import com.dinero.back.modelo.Usuario;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceUsuario extends CrudRepository<Usuario, Integer>{
    Optional<Usuario> findByNumeroDocumento(int numeroDocumento);
}
