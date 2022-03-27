package com.chernovol.springbootproject.repository;

import com.chernovol.springbootproject.entity.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartureRepository extends JpaRepository<Departure, Integer> {

    @Query("select d from Departure as d where d.name = :name")
    Departure findDepartureByName(@Param("name") String name);
}
