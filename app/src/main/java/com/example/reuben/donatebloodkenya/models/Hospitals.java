package com.example.reuben.donatebloodkenya.models;

public class Hospitals {
    private Integer id;
    private String hospital_name;
    private  String county_name;

    public Integer getId() {
        return id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public String getCounty_name() {
        return county_name;
    }

    public Hospitals(Integer id, String hospital_name, String county_name) {
        this.id = id;
        this.hospital_name = hospital_name;
        this.county_name = county_name;
    }
}
