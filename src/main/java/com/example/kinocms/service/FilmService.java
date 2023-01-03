package com.example.kinocms.service;

import com.example.kinocms.entity.film.Film;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    private static Film film = new Film();

    public static void setFilm(Film film) {
        FilmService.film = film;
    }

    public static Film getFilm() {
        return film;
    }
}
