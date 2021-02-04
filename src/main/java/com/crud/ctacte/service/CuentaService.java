package com.crud.ctacte.service;

import com.crud.ctacte.entity.Cuenta;
import com.crud.ctacte.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaService  {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional(readOnly = true)
    public Page<Cuenta> list(Pageable pageable) {

        return cuentaRepository.findAll(pageable);
    }

    @Transactional
    public Cuenta create(Cuenta cuenta) {

        return cuentaRepository.save(cuenta);
    }

    @Transactional(readOnly = true)
    public boolean existTipo(String tipo){

        return cuentaRepository.existsByTipo(tipo);
    }

    @Transactional(readOnly = true)
    public boolean existTitular( long titular){

        return cuentaRepository.existsByTitular(titular);
    }

    @Transactional(readOnly = true)
    public boolean existMoneda(String moneda){

        return cuentaRepository.existsByMoneda(moneda);
    }

    @Transactional(readOnly = true)
    public  boolean existByTypoAndTitular(String tipo, Long titular) {

        return cuentaRepository.existsByTipoAndTitular(tipo, titular);
    }

    @Transactional(readOnly = true)
    public Cuenta get(Long id) {

        return cuentaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Cuenta update(Cuenta cuenta){

        return cuentaRepository.save(cuenta);
    }

}
