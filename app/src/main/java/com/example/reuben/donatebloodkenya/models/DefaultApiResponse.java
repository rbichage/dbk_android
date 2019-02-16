package com.example.reuben.donatebloodkenya.models;

public class DefaultApiResponse {

    private Donor donor;
    private String message;
    private boolean error;

    public DefaultApiResponse(Donor donor, String message, boolean error) {
        this.donor = donor;
        this.message = message;
        this.error = error;
    }

    public Donor getDonor() {
        return donor;
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }
}
