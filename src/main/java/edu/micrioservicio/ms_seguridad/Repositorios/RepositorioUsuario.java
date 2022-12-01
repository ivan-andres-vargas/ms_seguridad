package edu.micrioservicio.ms_seguridad.Repositorios;

import edu.micrioservicio.ms_seguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositorioUsuario extends MongoRepository<Usuario, String> {

    @Query("{'email': ?0}")
    public Usuario buscarUsuarioPorEmail(String email);
}
