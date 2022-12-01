package edu.micrioservicio.ms_seguridad.Controladores;

import edu.micrioservicio.ms_seguridad.Modelos.Permiso;
import edu.micrioservicio.ms_seguridad.Modelos.Rol;
import edu.micrioservicio.ms_seguridad.Modelos.RolPermiso;
import edu.micrioservicio.ms_seguridad.Repositorios.RepositorioPermiso;
import edu.micrioservicio.ms_seguridad.Repositorios.RepositorioRol;
import edu.micrioservicio.ms_seguridad.Repositorios.RepositorioRolPermiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RequestMapping("/rolpermiso")
@RestController
public class ControladorRolPermiso {
    @Autowired
    RepositorioRolPermiso miRepoRolPermiso;
    @Autowired
    RepositorioRol miRepoRol;
    @Autowired
    RepositorioPermiso miRepoPermiso;

    @GetMapping("")
    public List<RolPermiso> listarRolPermisos() {
        return miRepoRolPermiso.findAll();
    }

    @PostMapping("/{idRol}/{idPermiso}")
    public RolPermiso crearRolPermiso(@PathVariable String idRol, @PathVariable String idPermiso) {

        Rol rolConsulta = miRepoRol.findById(idRol).orElse(null);
        Permiso permisoConsulta = miRepoPermiso.findById(idPermiso).orElse(null);
        RolPermiso rolPermiso = new RolPermiso(rolConsulta, permisoConsulta);

        return miRepoRolPermiso.save(rolPermiso);
    }

    @DeleteMapping("{idRolPermiso}")
    public String eliminarRolPermiso(@PathVariable String idRolPermiso){
        miRepoRolPermiso.deleteById(idRolPermiso);
        return "Se eliminó el permiso asignado";
    }

    @PutMapping("/{idRolPermiso}/{idRol}/{idPermiso}")
    public String actualizarRolPermiso(@PathVariable String idRolPermiso, @PathVariable String idRol,
                                       @PathVariable String idPermiso) {
        Rol rolConsulta = miRepoRol.findById(idRol).orElse(null);
        Permiso permisoConsulta = miRepoPermiso.findById(idPermiso).orElse(null);
        RolPermiso rolPermiso = miRepoRolPermiso.findById(idRolPermiso).orElse(null);
        rolPermiso.setRol(rolConsulta);
        rolPermiso.setPermiso(permisoConsulta);
        miRepoRolPermiso.save(rolPermiso);
        return "Se actualizó el permiso del perfil";
    }

    @PostMapping("{idRol}")
    public RolPermiso obtenerPermiso(@PathVariable String idRol,
                                     @RequestBody Permiso permisoEntrada,
                                     HttpServletResponse respuesta) throws IOException {
        Permiso permisoConsulta = miRepoPermiso.consultarPermiso(permisoEntrada.getUrl(),
                permisoEntrada.getMetodo());
        if (permisoConsulta != null){
            return miRepoRolPermiso.consultarRolPermiso(idRol,
                    permisoConsulta.get_id());
        } else {
            respuesta.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }
}
