package com.example.reuben.donatebloodkenya.models;

import java.io.Serializable;

public class Hospitals implements Serializable {
    private Integer id;
    private String hospital_name;
    private  String county_name;
    private String phone_number;

    public Integer getId() {
        return id;
    }


    public Hospitals(Integer id, String hospital_name, String county_name, String phone_number)  {
        this.id = id;
        this.hospital_name = hospital_name;
        this.county_name = county_name;
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getHospital_name() {
        return hospital_name;
    }

    public String getCounty_name() {
        return county_name;
    }

}
