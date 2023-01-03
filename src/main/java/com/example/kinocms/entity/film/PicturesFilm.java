package com.example.kinocms.entity.film;

import jakarta.persistence.*;

@Entity
@Table(name = "pictures_films")
public class PicturesFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "picture_film_id")
    private int pictureFilmID;

    private String picture;

    @JoinColumn(name = "film_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Film filmID;

    public PicturesFilm() {}

    public PicturesFilm(String picture) {
        this.picture = picture;
    }

    public int getPictureFilmID() {
        return pictureFilmID;
    }

    public void setPictureFilmID(int pictureFilmID) {
        this.pictureFilmID = pictureFilmID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Film getFilmID() {
        return filmID;
    }

    public void setFilmID(Film filmID) {
        this.filmID = filmID;
    }

    @Override
    public String toString() {
        return "PicturesFilm{" +
                "pictureFilmID=" + pictureFilmID +
                ", picture='" + picture + '\'' +
                '}';
    }
}
