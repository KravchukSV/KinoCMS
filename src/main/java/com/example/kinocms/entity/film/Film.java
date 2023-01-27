package com.example.kinocms.entity.film;
import com.example.kinocms.entity.Seo;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "film_id")
    private int filmID;

    private String name;

    @Column(name = "main_picture")
    private String mainPicture;

    @JoinColumn(name = "details_film_id")
    @OneToOne(cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SELECT)
    private DetailsFilm detailsFilm;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "film_id")
    @Fetch(value = FetchMode.SELECT)
    private List<PicturesFilm> listPicture = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    @Fetch(value = FetchMode.SELECT)
    private Seo seo;

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }

    public DetailsFilm getDetailsFilm() {
        return detailsFilm;
    }

    public void setDetailsFilm(DetailsFilm detailsFilm) {
        this.detailsFilm = detailsFilm;
    }

    public List<PicturesFilm> getListPicture() {
        return listPicture;
    }

    public void setListPicture(List<PicturesFilm> listPicture) {
        this.listPicture = listPicture;
    }

    public void addPicture(PicturesFilm picturesFilm){
        listPicture.add(picturesFilm);
    }

    public void deletePicture(int idPicture){
        listPicture.remove(idPicture);
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmID=" + filmID +
                ", name='" + name + '\'' +
                ", mainPicture='" + mainPicture + '\'' +
                ", detailsFilm=" + detailsFilm +
                ", listPicture=" + listPicture +
                ", seo=" + seo +
                '}';
    }
}
