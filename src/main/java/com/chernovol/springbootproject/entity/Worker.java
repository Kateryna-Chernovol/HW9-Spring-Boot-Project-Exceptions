package com.chernovol.springbootproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Accessors(chain = true)
public class Worker {

    @Id
    private int id;
    private String name;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "departure_id", nullable = false)
    private Departure departure;
}
