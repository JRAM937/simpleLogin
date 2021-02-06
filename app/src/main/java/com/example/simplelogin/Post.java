package com.example.simplelogin;

import com.google.gson.annotations.SerializedName;

public class Post {
    private String userId;
    private int postID;
    private String postTitle;
    private String password;

    @SerializedName("body")
    private String text;

    public String getUserId() {
        return userId;
    }

    public int getID() {
        return postID;
    }

    public String getPassword(){
        return password;
    }

    public String getTitle() {
        return postTitle;
    }

    public String getText() {
        return text;
    }
}
