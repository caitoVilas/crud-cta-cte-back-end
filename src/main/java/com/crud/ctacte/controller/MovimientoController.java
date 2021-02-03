package com.crud.ctacte.controller;

import com.crud.ctacte.dto.Mensaje;
import com.crud.ctacte.dto.MovimientoDTO;
import com.crud.ctacte.entity.Cuenta;
import com.crud.ctacte.entity.Movimiento;
import com.crud.ctacte.service.CuentaService;
import com.crud.ctacte.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/movimiento")
@CrossOrigin
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;
    @Autowired
    private CuentaService cuentaService;

    // LISTA DE MOVIMIENTOS PAGINADA
    @GetMapping("/{id}")
    public ResponseEntity<Page<Movimiento>> list(@PathVariable("id") Long id,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10")int size,
                                                 @RequestParam(defaultValue = "id") String order,
                                                 @RequestParam(defaultValue = "false") boolean asc){

        Page<Movimiento> movimientos = movimientoService.list(id, PageRequest.of(
                page,
                size,
                Sort.by(order).descending()
        ));

        return new ResponseEntity<Page<Movimiento>>(movimientos, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> add(@RequestBody MovimientoDTO movimientoDTO) {

         Float maxPeso = 1000f;
         Float maxDolar = 300f;
         Float maxEuro = 150f;
         Float nuevoSaldo =0f;
         boolean enabled = false;

        Cuenta cuenta = cuentaService.get(movimientoDTO.getIdcuenta());

        if (movimientoDTO.getTipo() == "Debito"){

            nuevoSaldo = cuenta.getSaldo() - movimientoDTO.getImporte();

           if (cuenta.getMoneda() == "Peso") {
               if (nuevoSaldo < maxPeso) {
                   enabled = false;
               }
           }else if (cuenta.getMoneda() == "Dolar"){
                if (nuevoSaldo < maxDolar){
                     enabled = false;
                }
           }else if (cuenta.getMoneda() =="Euro"){
                if (nuevoSaldo < maxEuro) {
                    enabled = false;
                }
           }

           if(!enabled){

               return new ResponseEntity(new Mensaje("Supera el Descubiero Permitido"),
                       HttpStatus.BAD_REQUEST);
           }
        }

        if (movimientoDTO.getTipo() == "Credito"){

            nuevoSaldo = cuenta.getSaldo() + movimientoDTO.getImporte();
        }

        cuenta.setSaldo(nuevoSaldo);

        Movimiento nuevo = new Movimiento(movimientoDTO.getFecha(),
                                          movimientoDTO.getTipo(),
                                          movimientoDTO.getDescripcion(),
                                          movimientoDTO.getImporte(),
                                          movimientoDTO.getIdcuenta());

        movimientoService.add(nuevo);


        return new ResponseEntity(new Mensaje("Movimiento registrado"),HttpStatus.OK);
    }
}
