package com.example.farmerama.data.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUser {

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
            ToastMessage.setToastMessage("Email missing");
            return false;
        }
        if(!matcher.matches()) {
            ToastMessage.setToastMessage("Email not valid");
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
            ToastMessage.setToastMessage("Password missing");
            return false;
        }
        if(password.length() < 6){
            ToastMessage.setToastMessage("Enter a password with at least 6 characters");
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
    public boolean verifyUserInput(String firstName, String lastName, String email, String password, String role){
        return verifyEmail(email) && verifyDetails(firstName, lastName, role) && verifyPassword(password);
    }

    private boolean verifyDetails(String firstName, String lastName, String role){
        if(firstName.trim().isEmpty()) {
            ToastMessage.setToastMessage("First name missing");
            return false;
        }
        else if(lastName.trim().isEmpty()){
            ToastMessage.setToastMessage("Last name missing");
            return false;
        }
        else if(role.trim().isEmpty()){
            ToastMessage.setToastMessage("Role missing");
            return false;
        }
        return true;
    }
}
