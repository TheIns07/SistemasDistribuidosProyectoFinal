package com.example.demo.controladores;

import com.example.demo.serviciosRest.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entidades.Usuario;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    @Autowired
    UsuarioServicio usuarioServicio;

    // Insertar un usuario (se mandan todos los datos menos el id) y Actualizar un usuario (se mandan todos los datos junto con el id para actualizar al usuario con el id que se mando)
    @PostMapping()
    public ResponseEntity<Usuario> insertarUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(this.usuarioServicio.insertar(usuario), HttpStatus.CREATED);
    }

    // Borrar un usuario
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> eliminarUsuario(@PathVariable("id") int id) {
        if (this.usuarioServicio.eliminar(id)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.NOT_FOUND);
    }

    // Consultar por id un usuario
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Usuario>> consultarPorIdUsuario(@PathVariable("id") int id) {
        Optional<Usuario> usuario = this.usuarioServicio.consultarPorId(id);
        if (!usuario.isEmpty()) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuario, HttpStatus.NOT_FOUND);
    }

    // Consultar todos los usuarios
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ArrayList<Usuario> consultarTodosUsuario() {
        return this.usuarioServicio.consultarTodos();
    }

    // Consultar por un parámetro en especifico un usuario (por su nombre)
    // query?nombre=Jaime, por ejemplo
    @GetMapping("/query")
    public ResponseEntity<ArrayList<Usuario>> consultarPorNombreUsuario(@RequestParam("nombre") String nombre) {
        ArrayList<Usuario> usuarios = this.usuarioServicio.consultarPorNombre(nombre);
        if (!usuarios.isEmpty()) {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.NOT_FOUND);
    }

}
