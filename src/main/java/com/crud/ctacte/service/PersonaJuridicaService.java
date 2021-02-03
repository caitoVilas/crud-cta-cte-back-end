package com.crud.ctacte.service;

import com.crud.ctacte.entity.PersonaJuridica;
import com.crud.ctacte.repository.PersonaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaJuridicaService {

    @Autowired
    PersonaJuridicaRepository juridicaRepository;

    @Transactional(readOnly = true)
    public Page<PersonaJuridica> list( Pageable pageable){

        return juridicaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<PersonaJuridica> listAll(){

        return juridicaRepository.findAll();
    }

    @Transactional
    public PersonaJuridica ceate(PersonaJuridica persona){

        return juridicaRepository.save(persona);
    }

    @Transactional(readOnly = true)
    public Optional<PersonaJuridica> view(Long id){

        return juridicaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public boolean exist(String rut) {

        return juridicaRepository.existsByRut(rut);
    }

    @Transactional
    public void delete(Long id) {

        juridicaRepository.deleteById(id);
    }
}
