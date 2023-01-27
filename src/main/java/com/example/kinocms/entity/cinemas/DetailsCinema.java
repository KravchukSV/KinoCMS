package com.example.kinocms.entity.cinemas;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="details_cinema")
public class DetailsCinema {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "details_cinema_id")
    private int detailsCinemaID;

    private String description;

    private String conditions;

    @Column(name = "top_banner")
    private String topBanner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "details_cinema_id")
    @Fetch(value = FetchMode.SELECT)
    private List<Hall> listHall = new ArrayList<>();


    public int getDetailsCinemaID() {
        return detailsCinemaID;
    }

    public void setDetailsCinemaID(int detailsCinemaID) {
        this.detailsCinemaID = detailsCinemaID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getTopBanner() {
        return topBanner;
    }

    public void setTopBanner(String topBanner) {
        this.topBanner = topBanner;
    }

    public List<Hall> getListHall() {
        return listHall;
    }

    public void setListHall(List<Hall> listHall) {
        this.listHall = listHall;
    }

    public void addHall(Hall hall){
        listHall.add(hall);
    }

    public void deleteHall(int idHall){
        listHall.remove(idHall);
    }

    public Hall getHall(int idHall) {
        return listHall.get(idHall);
    }

    @Override
    public String toString() {
        return "DetailsCinema{" +
                "detailsCinemaID=" + detailsCinemaID +
                ", description='" + description + '\'' +
                ", conditions='" + conditions + '\'' +
                ", topBanner='" + topBanner + '\'' +
                ", listHall=" + listHall +
                '}';
    }
}
