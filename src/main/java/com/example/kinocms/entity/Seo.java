package com.example.kinocms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seo")
public class Seo {
    @Id
    @Column(name = "seo_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seoID;
    private String url;
    private String title;
    private String keywords;
    private String description;

    public Seo() {
    }

    public int getSeoID() {
        return seoID;
    }

    public void setSeoID(int seoID) {
        this.seoID = seoID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
