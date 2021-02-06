package com.example.simplelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;

public class LandingPageActivity extends AppCompatActivity {
    private TextView textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page_layout);

        Bundle bundle = this.getIntent().getExtras();
        String idNum = bundle.getString("idNum");

        //Start retrofit build
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);

        Call < List < Post >> call = jsonPlaceholderAPI.getPosts(); //API call; gets fake posts and info from jsonplaceholder

        call.enqueue(new Callback < List < Post >> () {
            @Override
            public void onResponse(Call < List < Post >> call, Response < List < Post >> response) {
                //If no response, display an error code on the landing page
                if (!response.isSuccessful()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "An Error Has Occurred. CODE: " + response.code(), Toast.LENGTH_LONG);
                    toast.show(); // display the Toast
                    return;
                }

                //Otherwise, Begin filtering poses by user info (check for correctness!)
                List < Post > posts = response.body();
                textViewResult = findViewById(R.id.text_view_result);
                textViewResult.append("Welcome, din_djarin! \n");

                for (Post p: posts) {
                    String content = "";
                    content += "Post ID: " + p.getID() + "\n";
                    content += "User ID: " + p.getUserId() + "\n";
                    content += "Post Title: " + p.getTitle() + "\n";
                    content += "Post Body: " + p.getText() + "\n\n";
                    if (p.getUserId().equals(idNum)) {
                        textViewResult = findViewById(R.id.text_view_result);
                        textViewResult.append(content);
                    }
                }

            }

            @Override
            public void onFailure(Call < List < Post >> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "An Error has occurred. Please try again later", Toast.LENGTH_LONG);
                toast.show(); // display the Toast
            }
        });
    }
}