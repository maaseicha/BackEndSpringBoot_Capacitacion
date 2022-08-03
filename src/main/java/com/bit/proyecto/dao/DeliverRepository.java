package com.bit.proyecto.dao;

import com.bit.proyecto.model.Deliver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverRepository extends JpaRepository<Deliver, Integer> {
}
