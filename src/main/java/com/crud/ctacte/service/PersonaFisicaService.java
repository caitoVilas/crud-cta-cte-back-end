package com.crud.ctacte.service;

import com.crud.ctacte.entity.PersonaFisica;
import com.crud.ctacte.repository.PersonaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaFisicaService {

    @Autowired
    PersonaFisicaRepository fisicaRepository;

    @Transactional(readOnly = true)
    public Page<PersonaFisica> list(Pageable pageable){

        return fisicaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<PersonaFisica> listAll(){

        return fisicaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PersonaFisica> view(Long id){

        return fisicaRepository.findById(id);
    }

    @Transactional
    public PersonaFisica create(PersonaFisica personaFisica){

        return fisicaRepository.save(personaFisica);
    }

    @Transactional(readOnly = true)
    public boolean exist(String rut) {

        return fisicaRepository.existsByRut(rut);
    }

    @Transactional
    public void delete(Long id){

        fisicaRepository.deleteById(id);
    }
}
