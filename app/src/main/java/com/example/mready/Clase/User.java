package com.example.mready.Clase;

import java.io.Serializable;

public class User implements Serializable {

     private long id;
     private String username;
     private String display_name;
     private String created_by;

    public User(long id, String username, String display_name, String created_by) {
        this.id = id;
        this.username = username;
        this.display_name = display_name;
        this.created_by = created_by;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
