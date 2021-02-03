package com.crud.ctacte.service;

import com.crud.ctacte.dto.MovimientoDTO;
import com.crud.ctacte.entity.Movimiento;
import com.crud.ctacte.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Transactional(readOnly = true)
    public Page<Movimiento> list(Long id, Pageable pageable) {

        return movimientoRepository.findAllByIdcuenta(id, pageable);
    }

    /*@Transactional(readOnly = true)
    public List<Movimiento> list(Long id) {

        return movimientoRepository.findAllById(id);
    }*/

    @Transactional
    public Movimiento add(Movimiento movimiento){

        return movimientoRepository.save(movimiento);

    }
}
