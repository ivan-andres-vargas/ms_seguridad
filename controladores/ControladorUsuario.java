package edu.micrioservicio.ms_seguridad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import edu.micrioservicio.ms_seguridad.modelos.Usuario;
import edu.micrioservicio.ms_seguridad.repositorios.RepoUsuario;
import lombok.extern.apachecommons.CommonsLog;


@CommonsLog
@RestController
@CrossOrigin
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @Autowired
    private RepoUsuario repositorio;

    /**
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Usuario> index(){
        log.debug("[GET /usuarios]");
        List<Usuario> l = null;
        l = repositorio.findAll();
        return l;
    } 

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public Usuario retrieve(@PathVariable String id){
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario create(@RequestBody Usuario infoUsuario){
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public Usuario update(@PathVariable String id, @RequestBody Usuario infoUsuario){
        return null;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){

    }
}
