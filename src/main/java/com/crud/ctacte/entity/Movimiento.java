package com.crud.ctacte.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Movimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private String tipo;
    @Column(length = 200)
    private String descripcion;
    private Float importe;
    private Long idcuenta;

    public Movimiento() {
    }

    public Movimiento(Date fecha, String tipo, String descripcion, Float importe, long idcuenta) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.importe = importe;
        this.idcuenta = idcuenta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setIdcuenta(Long id_cuenta) {
        this.idcuenta = id_cuenta;
    }
}
