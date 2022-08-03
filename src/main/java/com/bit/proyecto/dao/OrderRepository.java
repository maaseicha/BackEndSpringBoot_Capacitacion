package com.bit.proyecto.dao;

import com.bit.proyecto.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT COUNT(p.PED_CODIGO) FROM pedido p WHERE p.PER_CODIGO = :perCodigo",nativeQuery = true)
    public Integer existByPerCodigo(@Param("perCodigo") Integer perCodigo);

    @Query(value = "SELECT * FROM pedido WHERE PER_CODIGO = :perCodigo",nativeQuery = true)
    public List<Order> findByPerCodigo(@Param("perCodigo") Integer perCodigo);
}
