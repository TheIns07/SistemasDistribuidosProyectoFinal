package com.example.demo.accesodatos;

import java.util.ArrayList;

import com.example.demo.entidades.Usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    public abstract ArrayList<Usuario> findByNombre(String nombre);
}
