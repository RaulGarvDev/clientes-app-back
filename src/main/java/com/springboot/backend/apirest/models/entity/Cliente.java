package com.springboot.backend.apirest.models.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="clientes")
public class Cliente implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date  createAt;

    @PrePersist
    public void prePersist(){

        createAt = new Date();
    }
}