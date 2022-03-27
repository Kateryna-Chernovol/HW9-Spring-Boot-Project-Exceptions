package com.chernovol.springbootproject.controller;

import com.chernovol.springbootproject.dto.WorkerDto;
import com.chernovol.springbootproject.entity.Worker;
import com.chernovol.springbootproject.repository.WorkerRepository;
import com.chernovol.springbootproject.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    WorkerRepository repository;

    @Autowired
    WorkerService workerService;

    @PostMapping("/workers")
    public ResponseEntity<Worker> addWorker(@RequestBody Worker worker) {
        return ResponseEntity.ok(repository.save(worker));
    }

    @GetMapping("/workers")
    public List<Worker> getAllWorkers() {
        return repository.findAll();
    }

    @GetMapping("/workers/{name}")
    public ResponseEntity<WorkerDto> getWorkerByName(@PathVariable String name) {
        WorkerDto returnValue = workerService.getByName(name);
        return ResponseEntity.ok(returnValue);
    }
}

