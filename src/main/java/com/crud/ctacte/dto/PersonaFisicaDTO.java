package com.crud.ctacte.dto;

public class PersonaFisicaDTO {

    private Long id;
    private String name;
    private String lastName;
    private String rut;

    public PersonaFisicaDTO(Long id, String name, String lastName, String rut) {
        this.id = id;
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
