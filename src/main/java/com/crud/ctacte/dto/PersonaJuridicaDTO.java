package com.crud.ctacte.dto;

public class PersonaJuridicaDTO {

    private Long id;
    private String razonSocial;
    private Integer foundation;
    private String rut;

    public PersonaJuridicaDTO(Long id, String razonSocial, Integer foundation, String rut) {
        this.id = id;
        this.razonSocial = razonSocial;
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
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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
