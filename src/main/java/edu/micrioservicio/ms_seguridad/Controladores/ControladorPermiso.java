package edu.micrioservicio.ms_seguridad.Controladores;

import edu.micrioservicio.ms_seguridad.Modelos.Permiso;
import edu.micrioservicio.ms_seguridad.Repositorios.RepositorioPermiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos")
public class ControladorPermiso {
    @Autowired
    private RepositorioPermiso miRepoPermiso;

    @PostMapping("/crear")
    public Permiso crearPermiso(@RequestBody Permiso permisoEntrada) {
        return miRepoPermiso.save(permisoEntrada);
    }

    @GetMapping("/listar")
    public List<Permiso> listarPermiso() {
        return miRepoPermiso.findAll();
    }

    @DeleteMapping("/eliminar/{_idPermiso}")
    public String eliminarPermiso(@PathVariable String _idPermiso) {
        miRepoPermiso.deleteById(_idPermiso);
        return "Permiso" + _idPermiso + " eliminado";
    }

    @PutMapping("/actualizar/{_idPermiso}")
    public String actualizarPermiso(@PathVariable String _idPermiso, @RequestBody Permiso permisoEntrada) {
        Permiso permisoBusqueda = miRepoPermiso.findById(_idPermiso).orElse(null);
        permisoBusqueda.setUrl(permisoEntrada.getUrl());
        permisoBusqueda.setMetodo(permisoEntrada.getMetodo());
        miRepoPermiso.save(permisoBusqueda);

        return "Permiso" + _idPermiso + "actualizado";
    }
}

