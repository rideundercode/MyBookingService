package com.billing_api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
public class Billing {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(columnDefinition = "integer")
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer reservationId;

    @Column(nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private Boolean refunded = false;

    @Column(nullable = false)
    private Boolean cancelled = false;

    @Column(columnDefinition = "datetime")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedDate = LocalDateTime.now();

    public Billing(Integer id, Integer reservationId, Integer userId, Float price, Boolean refunded,
            Boolean cancelled, LocalDateTime creationDate, LocalDateTime updatedDate) {
        this.id = id;
        this.reservationId = reservationId;
        this.userId = userId;
        this.refunded = refunded;
        this.cancelled = cancelled;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    public Billing(Integer reservationId, Integer userId) {
        this.reservationId = reservationId;
        this.userId = userId;
    }

    public Billing() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    
}
