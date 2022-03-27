package com.chernovol.springbootproject.service;

import com.chernovol.springbootproject.dto.DepartureDto;
import com.chernovol.springbootproject.entity.Departure;
import com.chernovol.springbootproject.entity.Worker;
import com.chernovol.springbootproject.exception.DepartureNotFoundException;
import com.chernovol.springbootproject.logger.Logger;
import com.chernovol.springbootproject.mapper.DepartureMapper;
import com.chernovol.springbootproject.repository.DepartureRepository;
import com.chernovol.springbootproject.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DepartureService {

    @Autowired
    Logger logger;

    @Autowired
    DepartureRepository departureRepository;

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartureMapper departureMapper;

    public String getMessageWithRandomNUmber() {
        String message = "message with " + new Random().nextInt(10);
        logger.logMessage(message);
        return message;
    }

    public DepartureDto getByName(String name) {

        Departure departureByName = departureRepository.findDepartureByName(name);

        if (Objects.isNull(departureByName)) {
            throw new DepartureNotFoundException("NOT FOUND WITH NAME", name);
        }

        List<Worker> workers = workerRepository.findAllByDepartureId(departureByName.getId());

        DepartureDto departureDto = departureMapper.toDto(departureByName)
                .setWorkerNames(workers.stream().map(Worker::getName).collect(Collectors.toList()));

        return departureDto;
    }

    public Departure addDeparture(Departure departure) {
        Optional<Departure> departureById = departureRepository.findById(departure.getId());

        if (departureById.isEmpty()) {
            return departureRepository.save(departure);
        } else {
            logger.logMessage(String.format("Departure with id = %s already exists", departure.getId()));
            return null;
        }
    }

    public Departure updateDeparture(Departure departure) {
        Optional<Departure> departureById = departureRepository.findById(departure.getId());

        if (departureById.isEmpty()) {
            logger.logMessage(String.format("No such departure to update with id = %s", departure.getId()));
            return null;
        } else {
            return departureRepository.save(departure);
        }
    }

    public List<Departure> getDepartures(int pageNumber, int size, String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(sortField));
        Page<Departure> page = departureRepository.findAll(pageable);
        return page.getContent();
    }
}
