package com.reservation_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Service {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(columnDefinition = "integer")
    private Long id;

    private String name;

    private float price;
    private int day_early;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDay_early() {
        return day_early;
    }

    public void setDay_early(int day_early) {
        this.day_early = day_early;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Service(String name, float price, int day_early) {
        this.name = name;
        this.price = price;
        this.day_early = day_early;
    }

    public Service() {

    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", day_early=" + day_early +
                '}';
    }
}
