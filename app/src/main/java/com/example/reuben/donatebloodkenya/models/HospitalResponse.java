package com.example.reuben.donatebloodkenya.models;

import java.util.List;

public class HospitalResponse {
private List<Hospitals> hospitals;

    public HospitalResponse(List<Hospitals> hospitals) {
        this.hospitals = hospitals;
    }

    public List<Hospitals> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<Hospitals> hospitals) {
        this.hospitals = hospitals;
    }
}
