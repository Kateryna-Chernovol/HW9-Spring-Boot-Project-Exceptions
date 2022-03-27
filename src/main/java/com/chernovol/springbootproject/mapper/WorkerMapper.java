package com.chernovol.springbootproject.mapper;

import com.chernovol.springbootproject.dto.WorkerDto;
import com.chernovol.springbootproject.entity.Worker;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper {

    public WorkerDto toDto(Worker worker) {
        WorkerDto workerDto = new WorkerDto()
                .setId(worker.getId())
                .setName(worker.getName())
                .setSalary(worker.getSalary());
        return workerDto;
    }

    public Worker toModel(WorkerDto dto) {
        Worker worker = new Worker()
                .setId(dto.getId())
                .setName(dto.getName())
                .setSalary(dto.getSalary());
        return worker;
    }
}
