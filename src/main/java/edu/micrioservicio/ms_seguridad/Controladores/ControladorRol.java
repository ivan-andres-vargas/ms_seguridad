package edu.micrioservicio.ms_seguridad.Controladores;

import edu.micrioservicio.ms_seguridad.Modelos.Rol;
import edu.micrioservicio.ms_seguridad.Repositorios.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class ControladorRol {
    @Autowired
    private RepositorioRol miRepoRol;

    @GetMapping("/listar")
    public List<Rol> listar() {
        return miRepoRol.findAll();
    }

    @PostMapping("/crear")
    public Rol crearRol(@RequestBody Rol rolEntrada) {
        return miRepoRol.save(rolEntrada);
    }

    @DeleteMapping("/eliminar/{_idRol}")
    public String eliminarRol(@PathVariable String _idRol) {
        miRepoRol.deleteById(_idRol);
        return "Rol" + _idRol + " eliminado";
    }

    @PutMapping("/actualizar/{_idRol}")
    public String actualizarRol(@PathVariable String _idRol, @RequestBody Rol rolEntrada){
        Rol rolBusqueda = miRepoRol.findById(_idRol).orElse(null);
        rolBusqueda.setNombre(rolEntrada.getNombre());
        rolBusqueda.setDescripcion(rolEntrada.getDescripcion());
        miRepoRol.save(rolBusqueda);

        return "Rol" + _idRol + "actualizado";
    }

}



