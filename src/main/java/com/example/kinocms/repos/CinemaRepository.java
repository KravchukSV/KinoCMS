package com.example.kinocms.repos;

import com.example.kinocms.entity.cinemas.Cinema;
import org.springframework.data.repository.CrudRepository;

public interface CinemaRepository extends CrudRepository<Cinema, Integer> {
}
