package com.crud.ctacte.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PersonaJuridica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String RazonSocial;
    private Integer foundation;
    @Column(unique = true, length = 14)
    private String rut;

    public PersonaJuridica() {
    }

    public PersonaJuridica(String razonSocial, Integer foundation, String rut) {
        RazonSocial = razonSocial;
        this.foundation = foundation;
        this.rut = rut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String razoSocial) {
        RazonSocial = razoSocial;
    }

    public Integer getFoundation() {
        return foundation;
    }

    public void setFoundation(Integer foundation) {
        this.foundation = foundation;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
