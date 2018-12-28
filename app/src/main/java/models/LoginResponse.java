package models;

public class LoginResponse {


    private boolean success;

    private  Donor donor;

    private String message;



    public Donor getDonor() {
        return donor;
    }

    public String getMessage() {
        return message;
    }

    public LoginResponse(Donor donor, String message, boolean success) {
        super();
        this.donor = donor;
        this.message = message;
        this.success = success;
    }


    public boolean isSuccess() {
        return success;
    }
}
