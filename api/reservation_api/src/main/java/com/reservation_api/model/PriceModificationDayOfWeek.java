package com.reservation_api.model;

import com.reservation_api.enums.Days;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PriceModificationDayOfWeek {


    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(columnDefinition = "integer")
    private Long id;


    private float priceReduction;

    @Column(unique=true)
    private Days day;

    public PriceModificationDayOfWeek() {

    }

    public PriceModificationDayOfWeek( float priceReduction, Days day) {
        this.priceReduction = priceReduction;
        this.day = day;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public float getPriceReduction() {
        return priceReduction;
    }

    public void setPriceReduction(float priceReduction) {
        this.priceReduction = priceReduction;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
    }
}
