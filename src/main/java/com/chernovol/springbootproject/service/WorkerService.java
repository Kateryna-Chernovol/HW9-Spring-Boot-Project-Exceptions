package com.chernovol.springbootproject.service;

import com.chernovol.springbootproject.dto.WorkerDto;
import com.chernovol.springbootproject.entity.Worker;
import com.chernovol.springbootproject.exception.WorkerNotFoundException;
import com.chernovol.springbootproject.logger.Logger;
import com.chernovol.springbootproject.mapper.WorkerMapper;
import com.chernovol.springbootproject.repository.DepartureRepository;
import com.chernovol.springbootproject.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WorkerService {
    @Autowired
    Logger logger;

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartureRepository departureRepository;

    @Autowired
    WorkerMapper workerMapper;

    public WorkerDto getByName(String name) {
        Worker workerByName = workerRepository.findWorkerByName(name);

        if (Objects.isNull(workerByName)) {
            throw new WorkerNotFoundException("NOT FOUND WITH NAME", name);
        }

        WorkerDto workerDto = workerMapper.toDto(workerByName);
        return workerDto;
    }
}
