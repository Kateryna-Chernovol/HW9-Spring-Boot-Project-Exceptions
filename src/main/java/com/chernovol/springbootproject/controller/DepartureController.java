package com.chernovol.springbootproject.controller;

import com.chernovol.springbootproject.dto.DepartureDto;
import com.chernovol.springbootproject.entity.Departure;
import com.chernovol.springbootproject.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class DepartureController {

    @Autowired
    DepartureService departureService;

    @GetMapping("/hello")
    public String hello() {
        return "HELLO DDD";
    }

    @PostMapping("/departures")
    public ResponseEntity<Departure> addDeparture(@RequestBody Departure departure) {
        Departure returnValue = departureService.addDeparture(departure);

        if (Objects.isNull(returnValue)) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(returnValue);
        }
    }

    @GetMapping("/departures")
    public List<Departure> getAllDepartures(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                            @RequestParam(value = "size", required = false, defaultValue = "100") int size,
                                            @RequestParam(value = "sortField", required = false, defaultValue = "id") String sortField) {
        return departureService.getDepartures(page, size, sortField);
    }

    @GetMapping("/departures/{name}")
    public ResponseEntity<DepartureDto> getDepartureByName(@PathVariable String name) {
        DepartureDto departureByName = departureService.getByName(name);

        if (Objects.isNull(departureByName)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(departureByName);
        }
    }

    @PutMapping("/departures/{id}")
    public ResponseEntity<Departure> updateDeparture(@RequestBody Departure departure, @PathVariable int id) {
        departure.setId(id);
        Departure returnValue = departureService.updateDeparture(departure);

        if (Objects.isNull(returnValue)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(returnValue);
        }
    }
}