package com.crud.ctacte.controller;

import com.crud.ctacte.dto.Mensaje;
import com.crud.ctacte.dto.MovimientoDTO;
import com.crud.ctacte.entity.Cuenta;
import com.crud.ctacte.entity.Movimiento;
import com.crud.ctacte.service.CuentaService;
import com.crud.ctacte.service.MovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(MovimientoController.class);

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

        Float maxPeso = -1000f;
        Float maxDolar = -300f;
        Float maxEuro = -150f;
        Float viejoSaldo = 0f;
        Float nuevoSaldo = 0f;
        boolean permiso = false;



        Cuenta cuenta = cuentaService.get(movimientoDTO.getIdcuenta());
        viejoSaldo = cuenta.getSaldo();

        if (movimientoDTO.getTipo().equals("Debito") ){
            nuevoSaldo = viejoSaldo + movimientoDTO.getImporte();

            if (cuenta.getMoneda().equals("Peso")){

                if(nuevoSaldo < maxPeso){

                    permiso = false;
                }else {
                    permiso = true;

                }
            }
            if (cuenta.getMoneda().equals("Dolar")){

                if (nuevoSaldo < maxDolar){

                    permiso = false;
                }else {
                    permiso = true;

                }

            }
            if (cuenta.getMoneda().equals("Euro")){
                logger.info("cuenta en Euro");

               if (nuevoSaldo < maxEuro){
                   permiso = false;
               }else {
                   permiso = true;

               }

            }
            if (!permiso) {
                return new ResponseEntity(new Mensaje("Excede el Limite de descubierto"),
                        HttpStatus.BAD_REQUEST);
            }
        }
        if (movimientoDTO.getTipo().equals("Credito")){
            logger.info("Dentro del if credito");
            //logger.info(viejoSaldo.toString());
            //logger.info(movimientoDTO.getImporte().toString());
            nuevoSaldo = viejoSaldo + movimientoDTO.getImporte();
            //logger.info(nuevoSaldo.toString());
        }


        cuenta.setSaldo(nuevoSaldo);

        cuentaService.update(cuenta);

        Movimiento nuevoMovimiento = new Movimiento(movimientoDTO.getFecha(),
                                                    movimientoDTO.getTipo(),
                                                    movimientoDTO.getDescripcion(),
                                                    movimientoDTO.getImporte(),
                                                    movimientoDTO.getIdcuenta());
        movimientoService.add(nuevoMovimiento);

        return new ResponseEntity(new Mensaje("Movimiento Inputado"), HttpStatus.OK);
    }
}
