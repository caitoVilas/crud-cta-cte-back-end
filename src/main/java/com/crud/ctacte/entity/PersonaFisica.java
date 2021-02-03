package com.crud.ctacte.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PersonaFisica implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 80)
    private String name;
    private String lastName;
    @Column(unique = true, length = 14)
    private String rut;

    public PersonaFisica() {
    }

    public PersonaFisica(String name, String lastName, String rut) {
        this.name = name;
        this.lastName = lastName;
        this.rut = rut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
