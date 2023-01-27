package com.example.kinocms.service;

import com.example.kinocms.entity.cinemas.Cinema;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {
    private static Cinema cinema = new Cinema();

    private static boolean isCreateHall = false;

    public static Cinema getCinema() {
        return cinema;
    }

    public static void setCinema(Cinema cinema) {
        CinemaService.cinema = cinema;
    }

    public static boolean isCreateHall() {
        return isCreateHall;
    }

    public static void setCreateHall(boolean createHall) {
        isCreateHall = createHall;
    }
}
