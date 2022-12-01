package edu.micrioservicio.ms_seguridad.Controladores;

import edu.micrioservicio.ms_seguridad.Modelos.Rol;
import edu.micrioservicio.ms_seguridad.Modelos.Usuario;
import edu.micrioservicio.ms_seguridad.Repositorios.RepositorioRol;
import edu.micrioservicio.ms_seguridad.Repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {
    @Autowired
    private RepositorioUsuario miRepoUsuario;
    @Autowired
    private RepositorioRol miRepoRol;

    @GetMapping("/listar")
    public List<Usuario> listar() {
        return miRepoUsuario.findAll();
    }

    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuarioEntrada) {
        usuarioEntrada.setContrasena(convertirSHA256(usuarioEntrada.getContrasena()));
        return miRepoUsuario.save(usuarioEntrada);
    }

    @DeleteMapping("/eliminar/{_idUsuario}")
    public String eliminarUsuario(@PathVariable String _idUsuario) {
        miRepoUsuario.deleteById(_idUsuario);
        return "Usuario" + _idUsuario + " eliminado";
    }

    @PutMapping("/actualizar/{_idUsuario}")
    public String actualizarUsuario(@PathVariable String _idUsuario, @RequestBody Usuario usuarioEntrada) {
        Usuario usuarioBusqueda = miRepoUsuario.findById(_idUsuario).orElse(null);
        usuarioBusqueda.setNombre_usuario(usuarioEntrada.getNombre_usuario());
        usuarioBusqueda.setEmail(usuarioEntrada.getEmail());
        usuarioBusqueda.setContrasena(convertirSHA256(usuarioEntrada.getContrasena()));
        miRepoUsuario.save(usuarioBusqueda);

        return "Usuario" + _idUsuario + "actualizado";
    }

    @PutMapping("{idUsuario}/rol/{idRol}")
    public String asignarRol(@PathVariable String idUsuario, @PathVariable String idRol) {
        Usuario usuarioBusqueda = miRepoUsuario.findById(idUsuario).orElse(null);
        Rol rolBusqueda = miRepoRol.findById(idRol).orElse(null);
        usuarioBusqueda.setRol(rolBusqueda);
        miRepoUsuario.save(usuarioBusqueda);

        return "Usuario" + idUsuario + "actualizado";
    }

    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @PostMapping("/login")
    public Usuario iniciarSesion(@RequestBody Usuario usuarioEntrada, HttpServletResponse codResHttp) throws IOException {

        String email = usuarioEntrada.getEmail();
        Usuario usuarioConsulta = miRepoUsuario.buscarUsuarioPorEmail(email);

        if (usuarioConsulta != null && usuarioConsulta.getContrasena().equals(convertirSHA256(usuarioEntrada.getContrasena()))){
            System.out.println("Inicio de sesión EXITOSO");
            usuarioConsulta.setContrasena("");
            return usuarioConsulta;
        } else {
                System.out.println("Datos Incorrectos");
                codResHttp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
        }
    }
