package com.bignerdranch.android.rwcreateuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModel();
        Button createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewUser();
            }
        });
    }

    private void createNewUser() {
        String name = ((EditText) findViewById(R.id.etName)).getText().toString();
        String email = ((EditText) findViewById(R.id.etEmail)).getText().toString();
        user = new User("", name, email, "Active", "Male");
        viewModel.createNewUser(user);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getCreateUserObserver().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if(userResponse == null) {
                    Toast.makeText(MainActivity.this, "failed to create new user", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Successfully created new user", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}