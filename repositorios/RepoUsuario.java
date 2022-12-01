package edu.micrioservicio.ms_seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.micrioservicio.ms_seguridad.modelos.Usuario;

public interface RepoUsuario extends MongoRepository<Usuario, String>{
    
    /*

    @Query(value = "{}".fields = "{seudonimno:0, rol:0}")
    List<Usuario> index();

    List<Usuario> findByEmail(String email);

    */

    /*

    @Aggregation(pipeline = {
        "{'$lookup': {from:'rol', localfield: 'rol.$id', foreignField: '_id', as: 'rol'}}"
        "{'$match: {'rol.tipo': ?0}}"
    })

    List<Usuario> finByRol(String rol);

     */
    
}
