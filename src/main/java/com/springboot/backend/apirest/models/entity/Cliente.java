package com.springboot.backend.apirest.models.entity;

import lombok.Data;
import lombok.Generated;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="clientes")
public class Cliente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotEmpty(message = "No puede estar vacio")
    @Size(min = 4, max = 12, message = "Entre 4 y 12 carácteres")
    @Column (nullable=false)
    private String nombre;

    @NotEmpty(message = "No puede estar vacio")
    private String apellido;

    @NotEmpty(message = "No puede estar vacio")
    @Email(message = "No es una dirección de email")
    @Column (nullable=false)
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date  createAt;

    @PrePersist
    public void prePersist(){

        createAt = new Date();
    }
}
