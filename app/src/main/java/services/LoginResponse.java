package services;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import models.Donor;


public class LoginResponse {
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private String detail;
}
