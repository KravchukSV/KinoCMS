package com.example.kinocms.entity.cinemas;

import com.example.kinocms.entity.Seo;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="halls")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hall_id")
    private int hallID;

    @Column(name = "room_number")
    private String roomNumber;

    private String description;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "scheme_hall")
    private String schemeHall;

    @Column(name = "top_banner")
    private String topBanner;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hall_id")
    @Fetch(value = FetchMode.SELECT)
    private List<PicturesHall> listPicture = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seo_id")
    @Fetch(value = FetchMode.SELECT)
    private Seo seo;

    public int getHallID() {
        return hallID;
    }

    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getSchemeHall() {
        return schemeHall;
    }

    public void setSchemeHall(String schemeHall) {
        this.schemeHall = schemeHall;
    }

    public String getTopBanner() {
        return topBanner;
    }

    public void setTopBanner(String topBanner) {
        this.topBanner = topBanner;
    }

    public List<PicturesHall> getListPicture() {
        return listPicture;
    }

    public void addPicture(PicturesHall picturesHall){
        listPicture.add(picturesHall);
    }

    public void deletePicture(int deleteImage) {
        listPicture.remove(deleteImage);
    }

    public void setListPicture(List<PicturesHall> listPicture) {
        this.listPicture = listPicture;
    }

    public Seo getSeo() {
        return seo;
    }

    public void setSeo(Seo seo) {
        this.seo = seo;
    }


    @Override
    public String toString() {
        return "Hall{" +
                "hallID=" + hallID +
                ", roomNumber='" + roomNumber + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", schemeHall='" + schemeHall + '\'' +
                ", topBanner='" + topBanner + '\'' +
                ", listPicture=" + listPicture +
                ", seo=" + seo +
                '}';
    }
}
