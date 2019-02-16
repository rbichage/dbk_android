package com.example.reuben.donatebloodkenya.models;

public class Donor {

    private  Integer id;
    private String username;

    private String email;
    private String first_name;

    private String token;

    private String last_name;

    private String birthdate;

    private String county_name;

    private String image;

    private String blood_group;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private Integer age;

    public Donor(Integer id, String username, String email, String first_name, String token, String last_name, String birthdate, Integer age, String county_name, String image, String blood_group, String phone_number, String date_donated) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.token = token;
        this.last_name = last_name;
        this.birthdate = birthdate;
        this.age = age;
        this.county_name = county_name;
        this.image = image;
        this.blood_group = blood_group;
        this.phone_number = phone_number;
        this.date_donated = date_donated;
    }


    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getToken() {
        return token;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getCounty_name() {
        return county_name;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public String getDate_donated() {
        return date_donated;
    }

    private String phone_number;

    private String date_donated;


    public String getImage() {
        return image;
    }

    public String getBlood_group() {
        return blood_group;
    }
}