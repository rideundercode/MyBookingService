package com.hotelerie_api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Chambre {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(columnDefinition = "integer")
    private Long id;

    private boolean deleted = false;


    private float price;

    public Chambre(float price, Hotel hotel, CategorieChambre categorieChambre) {
        this.price = price;
        this.hotel = hotel;
        this.categorieChambre = categorieChambre;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Chambre() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @ManyToOne
    private Hotel hotel;

    @ManyToOne
    private CategorieChambre categorieChambre;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public CategorieChambre getCategorieChambre() {
        return categorieChambre;
    }

    public void setCategorieChambre(CategorieChambre categorieChambre) {
        this.categorieChambre = categorieChambre;
    }

}
