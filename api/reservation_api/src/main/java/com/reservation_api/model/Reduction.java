package com.reservation_api.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reduction {
    @Override
    public String toString() {
        return "{" +
                "services:" + Arrays.toString(services) +
                ", global_reduction:" + global_reduction +
                ", day_reduction:" + day_reduction +
                '}';
    }

    public Reduction() {
    }

    public Reduction(Service[] services, Float global_reduction, ArrayList<Float> day_reduction) {
        this.services = services;
        this.global_reduction = global_reduction;
        this.day_reduction = day_reduction;
    }

    private Service[] services;

    private Float global_reduction;

    private ArrayList<Float> day_reduction;

    public Float getGlobal_reduction() {
        return global_reduction;
    }

    public void setGlobal_reduction(Float global_reduction) {
        this.global_reduction = global_reduction;
    }

    public ArrayList<Float> getDay_reduction() {
        return day_reduction;
    }

    public void setDay_reduction(ArrayList<Float> day_reduction) {
        this.day_reduction = day_reduction;
    }

    public Service[] getServices() {
        return services;
    }

    public void setServices(Service[] services) {
        this.services = services;
    }

}
