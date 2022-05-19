package com.example.farmerama.data.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import retrofit2.Response;

public class ErrorReader<T> {

    public String errorReader(Response<T> response) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        String errorMessage = null;
        if(response.errorBody() != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
            try{
                while((errorMessage = bufferedReader.readLine()) != null) {
                    stringBuilder.append(errorMessage);
                }
                bufferedReader.close();
            }
            catch (Exception e) {

            }
        }
        return stringBuilder.toString();
    }

}
