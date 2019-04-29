package com.example.reuben.donatebloodkenya.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Donation {

    @SerializedName("donor")
    @Expose
    private Integer donor;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("date_donated")
    @Expose
    private String dateDonated;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("county_name")
    @Expose
    private String countyName;
    @SerializedName("amount_donated")
    @Expose
    private Integer amountDonated;

    public Integer getDonor() {
        return donor;
    }

    public void setDonor(Integer donor) {
        this.donor = donor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateDonated() {
        return dateDonated;
    }

    public void setDateDonated(String dateDonated) {
        this.dateDonated = dateDonated;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Integer getAmountDonated() {
        return amountDonated;
    }

    public void setAmountDonated(Integer amountDonated) {
        this.amountDonated = amountDonated;
    }
}
