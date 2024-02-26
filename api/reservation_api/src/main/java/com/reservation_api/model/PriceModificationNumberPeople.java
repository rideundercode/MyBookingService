package com.reservation_api.model;

import com.reservation_api.enums.Days;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PriceModificationNumberPeople {


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(columnDefinition = "integer")
    private Long id;

    @Column(unique=true)
    private int numberPeople;

    private float priceReduction;

    public PriceModificationNumberPeople() {

    }

    public PriceModificationNumberPeople(int numberPeople, float priceReduction) {
        this.numberPeople = numberPeople;
        this.priceReduction = priceReduction;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }

    public float getPriceReduction() {
        return priceReduction;
    }

    public void setPriceReduction(float priceReduction) {
        this.priceReduction = priceReduction;
    }

}
