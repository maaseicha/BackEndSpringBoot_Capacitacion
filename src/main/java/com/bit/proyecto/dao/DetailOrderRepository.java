package com.bit.proyecto.dao;

import com.bit.proyecto.model.DetailOrder;
import com.bit.proyecto.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailOrderRepository extends JpaRepository<DetailOrder, Integer> {
    @Query(value = "SELECT * FROM detalle_pedido WHERE PED_CODIGO = :pedCodigo",nativeQuery = true)
    public List<DetailOrder> findByPedCodigo(@Param("pedCodigo") Integer pedCodigo);
}
