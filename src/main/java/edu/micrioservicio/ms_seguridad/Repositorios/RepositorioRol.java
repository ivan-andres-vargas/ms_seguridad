package edu.micrioservicio.ms_seguridad.Repositorios;

import edu.micrioservicio.ms_seguridad.Modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRol extends MongoRepository<Rol, String> {
}
