package com.crud.ctacte.dto;

import com.crud.ctacte.entity.Cuenta;

import java.util.Date;

public class MovimientoDTO {

    private Date fecha;
    private String tipo;
    private String descripcion;
    private Float importe;
    private Long idcuenta;

    public MovimientoDTO(Date fecha, String tipo, String descripcion, Float importe, Long id_cuenta) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.importe = importe;
        this.idcuenta = id_cuenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public Long getIdcuenta() {
        return idcuenta;
    }

    public void setId_cuenta(Long id_cuenta) {
        this.idcuenta = id_cuenta;
    }
}
