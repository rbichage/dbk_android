package com.example.reuben.donatebloodkenya.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donor {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("birthdate")
    @Expose
    private String birthdate;
    @SerializedName("county_name")
    @Expose
    private String countyName;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("has_appointment")
    @Expose
    private Boolean hasAppointment;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;
    @SerializedName("date_donated")
    @Expose
    private String dateDonated;
    @SerializedName("image")
    @Expose
    private String image;

    public Donor(Integer id, String username, String email, String firstName, String token,
                 String gender, String lastName, String birthdate, String countyName, Integer age,
                 Boolean hasAppointment, String phoneNumber, String bloodGroup, String dateDonated,
                 String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.token = token;
        this.gender = gender;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.countyName = countyName;
        this.age = age;
        this.hasAppointment = hasAppointment;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = bloodGroup;
        this.dateDonated = dateDonated;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getHasAppointment() {
        return hasAppointment;
    }

    public void setHasAppointment(Boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDateDonated() {
        return dateDonated;
    }

    public void setDateDonated(String dateDonated) {
        this.dateDonated = dateDonated;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}