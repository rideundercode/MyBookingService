package com.reservation_api.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(columnDefinition = "integer")
    private Integer id;
    @Column(nullable = false)
    private Integer hotelId;
    @Column(nullable = false)
    private Integer roomId;
    @Column(nullable = false)
    private Integer numberPeople;
    @Column(nullable = false)
    private Integer userId;
    @Column(nullable = false)
    private Boolean cancel = false;
    @Column(columnDefinition = "datetime")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedDate = LocalDateTime.now();

    @Column(columnDefinition = "datetime")
    private LocalDateTime reservationStart;

    @Column(columnDefinition = "datetime")
    private LocalDateTime reservationEnd;

    @Column(nullable = false)
    private String reduction;

    public Reservation(Integer hotelId, Integer roomId, Integer numberPeople, Integer userId, LocalDateTime reservationStart, LocalDateTime reservationEnd) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.numberPeople = numberPeople;
        this.userId = userId;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
    }


    public Reservation(Integer id, Integer hotelId, Integer roomId, Integer numberPeople, Integer userId, Boolean cancel, LocalDateTime creationDate, LocalDateTime updatedDate, LocalDateTime reservationStart, LocalDateTime reservationEnd) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.numberPeople = numberPeople;
        this.userId = userId;
        this.cancel = cancel;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
    }

    public Reservation(Integer hotelId, Integer roomId, Integer numberPeople, Integer userId, LocalDateTime reservationStart, LocalDateTime reservationEnd, String reduction) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.numberPeople = numberPeople;
        this.userId = userId;
        this.reservationStart = reservationStart;
        this.reservationEnd = reservationEnd;
        this.reduction = reduction;
    }

    public Reservation() {

    }




    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Integer getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(Integer numberPeople) {
        this.numberPeople = numberPeople;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
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

    public LocalDateTime getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(LocalDateTime reservationStart) {
        this.reservationStart = reservationStart;
    }

    public LocalDateTime getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(LocalDateTime reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation that)) return false;
        return getId().equals(that.getId()) && getHotelId().equals(that.getHotelId()) && getRoomId().equals(that.getRoomId()) && getNumberPeople().equals(that.getNumberPeople()) && getUserId().equals(that.getUserId()) && getCancel().equals(that.getCancel()) && getCreationDate().equals(that.getCreationDate()) && getUpdatedDate().equals(that.getUpdatedDate()) && getReservationStart().equals(that.getReservationStart()) && getReservationEnd().equals(that.getReservationEnd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getHotelId(), getRoomId(), getNumberPeople(), getUserId(), getCancel(), getCreationDate(), getUpdatedDate(), getReservationStart(), getReservationEnd());
    }

}
