package com.andres.practicoii.model;

import java.util.Date;

public class Vote {
    private Date birthDate;
    private String avenger;
    private String genre;

    public Vote() {
    }

    public Vote(Date birthDate, String avenger, String genre) {
        this.birthDate = birthDate;
        this.avenger = avenger;
        this.genre = genre;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvenger() {
        return avenger;
    }

    public void setAvenger(String avenger) {
        this.avenger = avenger;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
