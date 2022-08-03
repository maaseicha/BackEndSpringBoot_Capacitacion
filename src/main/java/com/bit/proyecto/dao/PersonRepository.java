package com.bit.proyecto.dao;

import com.bit.proyecto.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query(value = "SELECT COUNT(p.PER_CODIGO) FROM persona p WHERE p.PER_IDENTIFICACION LIKE %:perCedula%",nativeQuery = true)
    public Integer existByPerCedula(@Param("perCedula") String perCedula);
    @Query(value = "SELECT COUNT(p.PER_CODIGO) FROM persona p WHERE p.PER_EMAIL LIKE %:perEmail%",nativeQuery = true)
    public Integer existByPerEmail(@Param("perEmail") String perEmail);
    @Query(value = "SELECT * FROM persona p WHERE p.PER_EMAIL LIKE %:perEmail%",nativeQuery = true)
    public Person findByPerEmail(@Param("perEmail") String perEmail);
    @Query(value = "SELECT COUNT(p.PER_CODIGO) FROM persona p WHERE p.PER_EMAIL = :perEmail AND p.PER_PASSWORD = :perPassword",nativeQuery = true)
    public Integer existByLogin(@Param("perEmail") String perEmail, @Param("perPassword") String perPassword);
}
