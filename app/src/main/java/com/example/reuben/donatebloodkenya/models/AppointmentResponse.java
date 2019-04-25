package com.example.reuben.donatebloodkenya.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppointmentResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("donor")
    @Expose
    private Integer donor;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("county_name")
    @Expose
    private String countyName;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("schedule_date")
    @Expose
    private String scheduleDate;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("has_appointment")
    @Expose
    private Boolean hasAppointment;

    public AppointmentResponse(String countyName, String scheduleDate, String hospitalName, Boolean hasAppointment) {
        this.countyName = countyName;
        this.scheduleDate = scheduleDate;
        this.hospitalName = hospitalName;
        this.hasAppointment = hasAppointment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDonor() {
        return donor;
    }

    public void setDonor(Integer donor) {
        this.donor = donor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public Boolean getHasAppointment() {
        return hasAppointment;
    }

    public void setHasAppointment(Boolean hasAppointment) {
        this.hasAppointment = hasAppointment;
    }
}
