package com.example.demo.serviciosRest;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.accesodatos.UsuarioRepository;
import com.example.demo.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    UsuarioRepository usuarioRepository;

    //Insertar un usuario y Actualizar un usuario
    public Usuario insertar(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    //Borrar un usuario
    public boolean eliminar(int id){
        try {
            this.usuarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Consultar por id un usuario
    public Optional<Usuario> consultarPorId(int id){
        return this.usuarioRepository.findById(id);
    }

    //Consultar todos los usuarios
    public ArrayList<Usuario> consultarTodos(){
        return (ArrayList<Usuario>) this.usuarioRepository.findAll();
    }

    //Consultar por un parámetro en especifico un usuario
    public ArrayList<Usuario> consultarPorNombre(String nombre){
        return (ArrayList<Usuario>) this.usuarioRepository.findByNombre(nombre);
    }
}
