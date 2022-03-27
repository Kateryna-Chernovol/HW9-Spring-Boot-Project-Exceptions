package com.chernovol.springbootproject.repository;

import com.chernovol.springbootproject.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    @Query("select w from Worker as w where w.departure.id = :departureId and w.name in :list ")
    List<Worker> findAllByDepartureId(@Param("departureId") int departureId);

    @Query("select w from Worker as w where w.name = :name")
    Worker findWorkerByName(@Param("name") String name);
}
