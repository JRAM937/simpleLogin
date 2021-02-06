package com.example.simplelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {


    private String feed = ""; //holds the post feed to pass onto the landing page

    private final String correctUser = "din_djarin";
    private final String correctPass = "baby_yoda_ftw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = (Button) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get user input from the login page
                EditText userField = (EditText) findViewById(R.id.userid);
                EditText passField = (EditText) findViewById(R.id.password);
                EditText idField = (EditText) findViewById(R.id.ID);

                //Format the EditText as a string
                String userString = userField.getText().toString();
                String passString = passField.getText().toString();
                String userNum = idField.getText().toString();

                if (userString.equals(correctUser) && passString.equals(correctPass)) {
                    Intent i = new Intent (MainActivity.this, LandingPageActivity.class);
                    i.putExtra("idNum", userNum);
                    startActivity(i);
                } else if (!userString.equals(correctUser) && !passString.equals(correctPass)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Username & Password are Incorrect. Please Try Again", Toast.LENGTH_LONG);
                    toast.show(); // display the Toast
                } else if(!passString.equals(correctPass)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Password is Incorrect. Please Try Again", Toast.LENGTH_LONG);
                    toast.show(); // display the Toast
                } else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Username is Incorrect. Please Try Again", Toast.LENGTH_LONG);
                    toast.show(); // display the Toast
                }
            }
        });
    }

}