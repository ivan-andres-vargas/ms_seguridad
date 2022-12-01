package edu.micrioservicio.ms_seguridad.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;

@Data
@Document
public class Usuario {
    @Id
    @Setter(AccessLevel.NONE)
    private String _id;

    private String seudonimo;
    @NonNull private String e_mail;
    @NonNull private String contrasena;
    //@NonNull @DBRef private Rol rol;

    @JsonIgnore
    public String getContrasena() {
        return contrasena;
    }

    @JsonProperty
    public void setContrasena(String contrasena){
    if(contrasena != null) this.contrasena = contrasena;
    }

}

