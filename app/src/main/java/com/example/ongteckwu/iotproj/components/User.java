package com.example.ongteckwu.iotproj.components;

import java.io.Serializable;

/**
 * Created by ongteckwu on 4/12/16.
 */
public class User implements Serializable {
    String username;
    String password;
    String servername;

    public User(){
    }

    public User(String email, String pass){
        this.username = email;
        this.password = pass;
    }

    public User(String email, String pass, String serverName){
        this.username = email;
        this.password = pass;
        this.servername = serverName;
    }


    public String getName() {
        return username;
    }

    public String getpassword() {
        return password;
    }

    public String toString(){
        return "User(email=" + username +", password=" + password + ")" ;
    }

}