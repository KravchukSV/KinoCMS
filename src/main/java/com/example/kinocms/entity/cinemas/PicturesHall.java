package com.example.kinocms.entity.cinemas;

import jakarta.persistence.*;

@Entity
@Table(name = "pictures_halls")
public class PicturesHall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "picture_hall_id")
    private int pictureHallID;

    private String picture;

    @JoinColumn(name = "hall_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Hall hallID;

    public PicturesHall() {}

    public PicturesHall(String picture) {
        this.picture = picture;
    }

    public int getPictureHallID() {
        return pictureHallID;
    }

    public void setPictureHallID(int pictureHallID) {
        this.pictureHallID = pictureHallID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Hall getHallID() {
        return hallID;
    }

    public void setHallID(Hall hallID) {
        this.hallID = hallID;
    }
}
