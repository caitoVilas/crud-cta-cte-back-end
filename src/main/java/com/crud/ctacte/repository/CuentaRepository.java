package com.crud.ctacte.repository;

import com.crud.ctacte.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    public boolean existsByTipo(String tipo);
    public boolean existsByTitular(Long titular);
    public boolean existsByMoneda(String moneda);
    public boolean existsByTipoAndTitular(String tipo, Long titular);
    public boolean existsByTipoAndTitularAndMoneda(String tipo, Long titular, String moneda);
}
