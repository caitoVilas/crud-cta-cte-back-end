package com.crud.ctacte.repository;

import com.crud.ctacte.entity.PersonaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaFisicaRepository extends JpaRepository<PersonaFisica, Long> {

    public boolean existsByRut(String rut);
}
