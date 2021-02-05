package com.crud.ctacte.controller;

import com.crud.ctacte.dto.CuentaDTO;
import com.crud.ctacte.dto.Mensaje;
import com.crud.ctacte.entity.Cuenta;
import com.crud.ctacte.service.CuentaService;
import com.crud.ctacte.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas/")
@CrossOrigin
public class CuentaController {

    private static final Logger logger = LoggerFactory.getLogger(CuentaController.class);

    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private MovimientoService movimientoService;

    // LISTADO DE CUENTAS
    @GetMapping
    public ResponseEntity<List<Cuenta>> list() {

        List<Cuenta> cuentas = cuentaService.list();

        return new ResponseEntity<List<Cuenta>>(cuentas, HttpStatus.OK);
    }

    // CREAR CUENTA NUEVA
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CuentaDTO cuentaDTO){

        if (cuentaService.existCuenta(cuentaDTO.getTipo(),
                                      cuentaDTO.getTitular(),
                                      cuentaDTO.getMoneda())) {

            return new ResponseEntity(new Mensaje("El Titular ya tiene Cuenta en esa Moneda"),
                                     HttpStatus.BAD_REQUEST);

        }

        Cuenta nuevaCuenta = new Cuenta( 0l,
                                         cuentaDTO.getSaldo(),
                                         cuentaDTO.getMoneda(),
                                         cuentaDTO.getTitular(),
                                         cuentaDTO.getTipo());

        cuentaService.create(nuevaCuenta);

        return new ResponseEntity(new Mensaje("Cuenta Creada"), HttpStatus.OK);
    }

    // DEVOLVER UNA CUENTA X ID
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> get(@PathVariable("id") Long id){

        Cuenta cuenta = cuentaService.get(id);

        return new ResponseEntity<Cuenta>(cuenta, HttpStatus.OK);
    }

    // ELIMINAR CUENTA SI NO TIENE MOVIMIENTOS

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        if (movimientoService.tieneMovimientos(id)){

            return new ResponseEntity(new Mensaje("La Cuenta Tiene Movimientos"),
                                      HttpStatus.BAD_REQUEST);
        }

        cuentaService.delete(id);

        return new ResponseEntity(new Mensaje("Cuenta Eliminada"), HttpStatus.OK);
    }
}
