package com.example.kinocms.repos;

import com.example.kinocms.entity.film.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Integer> {
}
