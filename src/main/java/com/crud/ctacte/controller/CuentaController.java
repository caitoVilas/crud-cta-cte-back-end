package com.crud.ctacte.controller;

import com.crud.ctacte.dto.CuentaDTO;
import com.crud.ctacte.dto.Mensaje;
import com.crud.ctacte.entity.Cuenta;
import com.crud.ctacte.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas/")
@CrossOrigin
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    // LISTADO DE CUENTAS PAGINADO
    @GetMapping
    public ResponseEntity<Page<Cuenta>> list(
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(defaultValue = "id") String order,
                            @RequestParam(defaultValue = "true") boolean asc ) {

        Page<Cuenta> cuentas = cuentaService.list(PageRequest.of(
                page,
                size,
                Sort.by(order)
        ));

        if (!asc) {
            cuentas = cuentaService.list(PageRequest.of(
                    page,
                    size,
                    Sort.by(order).descending()
            ));
        }

        return new ResponseEntity<Page<Cuenta>>(cuentas, HttpStatus.OK);
    }

    // CREAR CUENTA NUEVA
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CuentaDTO cuentaDTO){

        if (cuentaService.existTipo(cuentaDTO.getTipo()) && cuentaService.existTitular(
                cuentaDTO.getTitular()) && cuentaService.existMoneda(cuentaDTO.getMoneda())){

            return new ResponseEntity(new Mensaje("El Titular ya tiene Cuenta en esa Moneda"),
                    HttpStatus.BAD_REQUEST);
        }

        Cuenta nuevaCuenta = new Cuenta(cuentaDTO.getSaldo(),
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
}
