package com.example.kinocms.entity.cinemas;

import jakarta.persistence.*;

@Entity
@Table(name = "pictures_cinemas")
public class PicturesCinema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "picture_cinema_id")
    private int pictureCinemaID;

    private String picture;

    @JoinColumn(name = "cinema_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinemaID;

    public PicturesCinema() {}

    public PicturesCinema(String picture) {
        this.picture = picture;
    }

    public int getPictureCinemaID() {
        return pictureCinemaID;
    }

    public void setPictureCinemaID(int pictureCinemaID) {
        this.pictureCinemaID = pictureCinemaID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Cinema getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(Cinema cinemaID) {
        this.cinemaID = cinemaID;
    }
}
