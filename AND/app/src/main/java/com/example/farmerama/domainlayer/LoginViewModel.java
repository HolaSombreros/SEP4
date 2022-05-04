package com.example.farmerama.domainlayer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.farmerama.datalayer.model.User;
import com.example.farmerama.datalayer.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel extends ViewModel {

    private final UserRepository repository;
    private List<User> users;
    private MutableLiveData<String> errorMessage;

    public LoginViewModel() {
        repository = UserRepository.getInstance();
        repository.retrieveAllEmployees();
        users =new ArrayList<>();
        errorMessage = new MutableLiveData<>();
        users.add(new User("Geana","geana@stefi.dk", "miawmiao", "Owner"));
    }

    public LiveData<String> getErrorMessage(){
        return errorMessage;
    }

    public boolean login(String email, String password) {
        verifyEmail(email);
        verifyPassword(password);

        //Verify if email and password correspond
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
        } else if( !((email.contains("@") && email.contains("."))) ) {
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

}
