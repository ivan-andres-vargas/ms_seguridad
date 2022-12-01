package edu.micrioservicio.ms_seguridad.Repositorios;

import edu.micrioservicio.ms_seguridad.Modelos.Permiso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RepositorioPermiso extends MongoRepository<Permiso, String> {

    @Query("{\"url\":\"?0\",\"metodo\":\"?1\"}")
    public Permiso consultarPermiso(String url, String metodo);
}
