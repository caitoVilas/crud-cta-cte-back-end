package com.crud.ctacte.repository;

import com.crud.ctacte.entity.PersonaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaJuridicaRepository extends JpaRepository<PersonaJuridica, Long> {

    public boolean existsByRut(String rut);
}
