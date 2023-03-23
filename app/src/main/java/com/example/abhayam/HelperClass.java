package com.example.abhayam;

public class HelperClass {
    String name, username, password, parentPhNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public HelperClass(String name, String username, String password, String parentPhNo) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.parentPhNo = parentPhNo;
    }

    public HelperClass() {
    }



}
