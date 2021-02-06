package com.example.simplelogin;

import retrofit2.Call;

import retrofit2.http.GET;

import java.util.List;

public interface JsonPlaceholderAPI {

    @GET("posts")
    Call<List<Post>> getPosts();
}
