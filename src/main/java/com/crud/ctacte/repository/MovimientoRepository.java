package com.crud.ctacte.repository;

import com.crud.ctacte.entity.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    public Page<Movimiento> findAllByIdcuenta(Long id_cuenta, Pageable pageable);
    public List<Movimiento> findAllByIdcuenta(Long id);
    public boolean existsByIdcuenta(Long id);
}
