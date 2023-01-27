package com.example.kinocms.entity.cinemas;

import com.example.kinocms.entity.Seo;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cinema_id")
    private int cinemaID;

    private String name;

    private String logotype;

    @JoinColumn(name = "details_cinema_id")
    @OneToOne(cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    private DetailsCinema detailsCinema;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cinema_id")
    @Fetch(value = FetchMode.SELECT)
    private List<PicturesCinema> listPicture = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    @Fetch(value = FetchMode.SELECT)
    private Seo seo;


    public int getCinemaID() {
        return cinemaID;
    }

    public void setCinemaID(int cinemaID) {
        this.cinemaID = cinemaID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogotype() {
        return logotype;
    }

    public void setLogotype(String logotype) {
        this.logotype = logotype;
    }

    public DetailsCinema getDetailsCinema() {
        return detailsCinema;
    }

    public void setDetailsCinema(DetailsCinema detailsCinema) {
        this.detailsCinema = detailsCinema;
    }

    public List<PicturesCinema> getListPicture() {
        return listPicture;
    }

    public void setListPicture(List<PicturesCinema> listPicture) {
        this.listPicture = listPicture;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    public void addPicture(PicturesCinema picturesCinema) {
        listPicture.add(picturesCinema);
    }

    public void deletePicture(int deleteImage) {
        listPicture.remove(deleteImage);
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "cinemaID=" + cinemaID +
                ", name='" + name + '\'' +
                ", logotype='" + logotype + '\'' +
                ", detailsCinema=" + detailsCinema +
                ", listPicture=" + listPicture +
                ", seo=" + seo +
                '}';
    }
}
