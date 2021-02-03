package com.crud.ctacte.controller;

import com.crud.ctacte.dto.Mensaje;
import com.crud.ctacte.dto.PersonaJuridicaDTO;
import com.crud.ctacte.entity.PersonaJuridica;
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
@RequestMapping("/api/persona-juridica")
@CrossOrigin
public class PersonaJuridicaController {

    @Autowired
    PersonaJuridicaService personaJuridicaService;
    @Autowired
    PersonaFisicaService personaFisicaService;
    @Autowired
    CuentaService cuentaService;

    //LISTA DE PERSONAS JURIDICAS PAGINADO
    @GetMapping
    public ResponseEntity<Page<PersonaJuridica>> view(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ){

        Page<PersonaJuridica> personas = personaJuridicaService.list(PageRequest.of(
                page,
                size,
                Sort.by(order)
        ));

        if (!asc){
            personas = personaJuridicaService.list(PageRequest.of(
                    page,
                    size,
                    Sort.by(order).descending()
            ));
        }

        return new ResponseEntity<Page<PersonaJuridica>>(personas, HttpStatus.OK);
    }

    //LISTA DE PERSONAS JURIDICAS
    @GetMapping("/all")
    public ResponseEntity<List<PersonaJuridica>> listAll(){

        List<PersonaJuridica> personas = personaJuridicaService.listAll();

        return new ResponseEntity<List<PersonaJuridica>>(personas, HttpStatus.OK);
    }

    // CREAR NUEVA PESONA FISICA
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonaJuridicaDTO personaJuridicaDTO){

        if (personaJuridicaService.exist(personaJuridicaDTO.getRut()) ||
            personaFisicaService.exist(personaJuridicaDTO.getRut())){

            return new ResponseEntity(new Mensaje("El RUT ya Existe"), HttpStatus.BAD_REQUEST);
        }

        PersonaJuridica persona = new PersonaJuridica(personaJuridicaDTO.getRazonSocial(),
                                      personaJuridicaDTO.getFoundation(),
                                      personaJuridicaDTO.getRut());

        personaJuridicaService.ceate(persona);

        return new ResponseEntity(new Mensaje("Persona Juridica Creada"), HttpStatus.OK);
    }

    //CONSULTA PErSONA FISICA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonaJuridica> view(@PathVariable("id") long id) {

        PersonaJuridica persona = personaJuridicaService.view(id).orElse(null);

        return new ResponseEntity<PersonaJuridica>(persona, HttpStatus.OK);
    }

    // ELIMINA PERSONA JURIDCA SI NO TIENE CUENTAS
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        String tipo = "J";

        if (cuentaService.existByTypoAndTitular(tipo,id)) {
            return new ResponseEntity(new Mensaje("La Persona Tiene Cuenta/s"),
                    HttpStatus.BAD_REQUEST);
        }

        personaJuridicaService.delete(id);

        return new ResponseEntity(new Mensaje("Persona Juridica Eliminada"), HttpStatus.OK);
    }
}
