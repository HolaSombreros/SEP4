package com.example.farmerama.util;

import androidx.lifecycle.MutableLiveData;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class ValidationLoginRegister {
    private MutableLiveData<String> errorMessage;
    private List<User> users;
    private UserRepository userRepository;


    public ValidationLoginRegister(){
        errorMessage = new MutableLiveData<>();
        userRepository = UserRepository.getInstance();
        users = new ArrayList<>();
        //users.add(new User("Geana","geana@stefi.dk", "miawmiao", "Owner"));

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

    //Verify if email is entered correctly, if not, the error message is announcing
    private boolean verifyEmail(String email){
        if(email.trim().isEmpty()) {
            errorMessage.setValue("Email field cannot be empty");
            return false;
        } else if( !(email.contains("@") && email.contains(".")) ) {
            errorMessage.setValue("Email not valid");
            return false;
        }
        return true;
    }

    //Verify is password is entered
    private boolean verifyPassword(String password){
        if(password.trim().isEmpty()) {
            errorMessage.setValue("Password cannot be empty");
            return false;
        }
        return true;
    }

    //Verify is password is entered
    private boolean verifyPasswordRegister(String password){
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


    //Verify the data for register
    public boolean verifyRegister(String firstName, String lastName,String email, String password, String role){
        if(verifyEmail(email) && verifyDetails(firstName, lastName, role) && verifyPasswordRegister(password)){
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
