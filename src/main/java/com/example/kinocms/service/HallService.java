package com.example.kinocms.service;

import com.example.kinocms.entity.cinemas.Hall;
import org.springframework.stereotype.Service;

@Service
public class HallService {
    private static Hall hall = new Hall();

    private static boolean change;

    public static Hall getHall() {
        return hall;
    }

    public static void setHall(Hall hall) {
        HallService.hall = hall;
    }

    public static boolean isChange() {
        return change;
    }

    public static void setChange(boolean change) {
        HallService.change = change;
    }
}
