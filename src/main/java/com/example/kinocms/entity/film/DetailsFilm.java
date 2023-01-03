package com.example.kinocms.entity.film;

import jakarta.persistence.*;

@Entity
@Table(name="details_film")
public class DetailsFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "details_film_id")
    private int detailsFilmID;

    private String description;
    private String link;
    private boolean type2D;
    private boolean type3D;
    private boolean typeIMAX;

    public int getDetailsFilmID() {
        return detailsFilmID;
    }

    public void setDetailsFilmID(int detailsFilmID) {
        this.detailsFilmID = detailsFilmID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isType2D() {
        return type2D;
    }

    public void setType2D(boolean type2D) {
        this.type2D = type2D;
    }

    public boolean isType3D() {
        return type3D;
    }

    public void setType3D(boolean type3D) {
        this.type3D = type3D;
    }

    public boolean isTypeIMAX() {
        return typeIMAX;
    }

    public void setTypeIMAX(boolean typeIMAX) {
        this.typeIMAX = typeIMAX;
    }

    @Override
    public String toString() {
        return "DetailsFilm{" +
                "detailsFilmID=" + detailsFilmID +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", type2D=" + type2D +
                ", type3D=" + type3D +
                ", typeIMAX=" + typeIMAX +
                '}';
    }
}
