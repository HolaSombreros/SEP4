package com.example.farmerama.data.util;

import androidx.lifecycle.MutableLiveData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationLoginRegister {
    private MutableLiveData<String> errorMessage;

    public ValidationLoginRegister(){
        errorMessage = new MutableLiveData<>();
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public boolean verifyLogin(String email, String password) {
        return verifyEmail(email) && verifyPassword(password);
    }

    /**
     * Verify if email is entered correctly, if not, the error message is announcing
     * @param email
     * @return
     */
    private boolean verifyEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(email.trim().isEmpty()) {
            errorMessage.setValue("Email field cannot be empty");
            return false;
        }
        if(!matcher.matches()) {
            errorMessage.setValue("Email not valid");
            return false;
        }
        return true;
    }


    /**
     * Verify is password is entered
     * @param password
     * @return
     */
    private boolean verifyPassword(String password){
        if(password.trim().isEmpty()) {
            errorMessage.setValue("Password cannot be empty");
            return false;
        }
        if(password.length() < 6){
            errorMessage.setValue("Enter a password with at least 6 characters");
            return false;
        }
        return true;
    }

    /**
     * Verify the data for register
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param role
     * @return
     */
    public boolean verifyRegister(String firstName, String lastName, String email, String password, String role){
        return verifyEmail(email) && verifyDetails(firstName, lastName, role) && verifyPassword(password);
    }

    private boolean verifyDetails(String firstName, String lastName, String role){
        if(firstName.trim().isEmpty()) {
            errorMessage.setValue("First name missing");
            return false;
        }
        else if(lastName.trim().isEmpty()){
            errorMessage.setValue("Last name missing");
            return false;
        }
        else if(role.trim().isEmpty()){
            errorMessage.setValue("Role missing");
            return false;
        }
        return true;
    }
}
