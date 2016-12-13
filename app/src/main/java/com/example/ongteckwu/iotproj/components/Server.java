package com.example.ongteckwu.iotproj.components;

import java.io.Serializable;

/**
 * Created by ongteckwu on 4/12/16.
 */
public class Server implements Serializable{
    String name;
    String password;

    public Server(){
    }

    public Server(String name, String pass){
        this.name = name;
        this.password = pass;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String toString(){
        return "Server(email=" + name +", password=" + password + ")" ;
    }

}
