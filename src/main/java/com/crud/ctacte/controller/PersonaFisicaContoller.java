package com.crud.ctacte.controller;

import com.crud.ctacte.dto.Mensaje;
import com.crud.ctacte.dto.PersonaFisicaDTO;
import com.crud.ctacte.entity.PersonaFisica;
import com.crud.ctacte.service.CuentaService;
import com.crud.ctacte.service.PersonaFisicaService;
import com.crud.ctacte.service.PersonaJuridicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persona-fisica")
@CrossOrigin
public class PersonaFisicaContoller {

    @Autowired
    private PersonaFisicaService personaFisicaService;
    @Autowired
    private PersonaJuridicaService personaJuridicaService;
    @Autowired
    private CuentaService cuentaService;

    // LISTADO DE PERSONAS FISICAS PAGINADO
    @GetMapping
    public ResponseEntity<Page<PersonaFisica>> view(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ) {

        Page<PersonaFisica> personas = personaFisicaService.list(PageRequest.of(
                page,
                size,
                Sort.by(order)
        ));

        if (!asc) {
            personas = personaFisicaService.list(PageRequest.of(
                    page,
                    size,
                    Sort.by(order).descending()
            ));
        }


        return new ResponseEntity<Page<PersonaFisica>>(personas, HttpStatus.OK);

    }

    //LISTADO DE PERSONAS FISICAS
    @GetMapping("/all")
    public ResponseEntity<List<PersonaFisica>> viewAll() {

        List<PersonaFisica> personas = personaFisicaService.listAll();

        return new ResponseEntity<List<PersonaFisica>>(personas, HttpStatus.OK);
    }

    // CREACION DE NUEVA PERSONA FISICA
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonaFisicaDTO personaFisicaDTO) {

        if (personaFisicaService.exist(personaFisicaDTO.getRut()) ||
                personaJuridicaService.exist(personaFisicaDTO.getRut())) {

            return new ResponseEntity(new Mensaje("El RUT ya Existe"), HttpStatus.BAD_REQUEST);
        }

        PersonaFisica persona = new PersonaFisica(personaFisicaDTO.getName(),
                personaFisicaDTO.getLastName(),
                personaFisicaDTO.getRut());
        personaFisicaService.create(persona);

        return new ResponseEntity(new Mensaje("Persona Fisica Creada"), HttpStatus.OK);

    }

    // BUSQUEDA DE PERSONA FISICA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> view(@PathVariable("id") Long id) {

        PersonaFisica persona = personaFisicaService.view(id).orElse(null);

        if (persona == null) {

            new ResponseEntity(new Mensaje("La Persona Fisica No Existe"),HttpStatus.BAD_REQUEST);

        }


        return new ResponseEntity(persona, HttpStatus.OK);
    }

    // ELIMINA PERSONA FISICA SI NO TIENE CUENTA
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        String tipo = "F";

        if (cuentaService.existByTipoAndTitular(tipo,id)) {

            return new ResponseEntity(new Mensaje("La Persona Tiene Cuenta/s"),
                    HttpStatus.BAD_REQUEST);
        }

        personaFisicaService.delete(id);

        return new ResponseEntity(new Mensaje("Persona Fisica Eliminada"), HttpStatus.OK);
    }
}