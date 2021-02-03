package com.crud.ctacte.dto;

public class CuentaDTO {

    private Float saldo;
    private String moneda;
    private Long titular;
    private String tipo;

    public CuentaDTO(Float saldo, String moneda, Long titular, String tipo) {
        this.saldo = saldo;
        this.moneda = moneda;
        this.titular = titular;
        this.tipo = tipo;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Long getTitular() {
        return titular;
    }

    public void setTitular(Long titular) {
        this.titular = titular;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
