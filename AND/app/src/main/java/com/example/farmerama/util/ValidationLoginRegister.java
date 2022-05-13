package com.example.farmerama.util;

import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationLoginRegister {
    private MutableLiveData<String> errorMessage;
    private List<User> users;
    private UserRepository userRepository;


    public ValidationLoginRegister(){
        errorMessage = new MutableLiveData<>();
        userRepository = UserRepository.getInstance();
        users = new ArrayList<>();
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public boolean verifyLogin(String email, String password){
        users = userRepository.getAllEmployees().getValue();
        if(!verifyEmail(email)) return verifyEmail(email);
        if(!verifyPassword(password)) return verifyPassword(password);

        for (User user:users) {
            if(user.getPassword().equals(password) && user.getEmail().equals(email)) {
                return true;
            }
            else {
                errorMessage.setValue("Email and password combination is not correct");
                return false;
            }
        }
        return false;
    }

    /**
     * Verify if email is entered correctly, if not, the error message is announcing
     * @param email
     * @return
     */
    private boolean verifyEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
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
    public boolean verifyRegister(String firstName, String lastName,String email, String password, String role){
        if(verifyEmail(email) && verifyDetails(firstName, lastName, role) && verifyPassword(password)){
            return true;
        }
        return false;
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
