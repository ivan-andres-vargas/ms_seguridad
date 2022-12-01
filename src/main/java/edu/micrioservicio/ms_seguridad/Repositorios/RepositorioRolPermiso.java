package edu.micrioservicio.ms_seguridad.Repositorios;

import edu.micrioservicio.ms_seguridad.Modelos.RolPermiso;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import static javax.management.Query.and;
import static javax.management.Query.eq;

public interface RepositorioRolPermiso extends MongoRepository<RolPermiso, String> {

    @Query("{\"rol.$id\":ObjectId(?0),\"permiso.$id\":ObjectId(?1)}")
    public RolPermiso consultarRolPermiso(String idRol, String idPermiso);

}
