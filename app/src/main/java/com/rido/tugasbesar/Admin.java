package com.rido.tugasbesar;

public class Admin {
    public String username;
    public String email;
    public String password;

    public Admin() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Admin(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
