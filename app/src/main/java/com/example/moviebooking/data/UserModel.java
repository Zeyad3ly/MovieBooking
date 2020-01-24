package com.example.moviebooking.data;

public class UserModel {
        private String name , password , email , id;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserModel(String name, String password, String email, String id) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
    }

    public UserModel(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}