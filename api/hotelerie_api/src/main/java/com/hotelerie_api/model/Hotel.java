package com.hotelerie_api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(columnDefinition = "integer")
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private Integer nombrePlacesGarage;

    @Column(nullable = false)
    private Integer nombreLitsBebe;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id")
    private List<Chambre> chambres;

    public Hotel(String nom, String telephone, Integer nombrePlacesGarage, Integer nombreLitsBebe) {
        this.nom = nom;
        this.telephone = telephone;
        this.nombrePlacesGarage = nombrePlacesGarage;
        this.nombreLitsBebe = nombreLitsBebe;
    }

    public Hotel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Integer getNombrePlacesGarage() {
        return nombrePlacesGarage;
    }

    public void setNombrePlacesGarage(Integer nombrePlacesGarage) {
        this.nombrePlacesGarage = nombrePlacesGarage;
    }

    public Integer getNombreLitsBebe() {
        return nombreLitsBebe;
    }

    public void setNombreLitsBebe(Integer nombreLitsBebe) {
        this.nombreLitsBebe = nombreLitsBebe;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
