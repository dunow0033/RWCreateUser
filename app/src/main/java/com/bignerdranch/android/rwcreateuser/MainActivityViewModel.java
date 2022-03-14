package com.bignerdranch.android.rwcreateuser;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<UserResponse> createNewUserLiveData;

    public MainActivityViewModel() {
        createNewUserLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserResponse> getCreateUserObserver() {
        return createNewUserLiveData;
    }

    public void createNewUser(User user) {
        RetroServiceInterface retroServiceInterface = RetroInstance.getRetroInstance().create(RetroServiceInterface.class);
        Call<UserResponse> call = retroServiceInterface.createUser(user);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    createNewUserLiveData.postValue(response.body());
                } else {
                    createNewUserLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                createNewUserLiveData.postValue(null);
            }
        });
    }
}