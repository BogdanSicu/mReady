package com.example.mready.Clase;

import java.io.Serializable;

public class DataUser implements Serializable {
    private UserAndToken data;

    public DataUser() {
    }

    public DataUser(UserAndToken data) {
        this.data = data;
    }

    public UserAndToken getData() {
        return data;
    }

    public void setData(UserAndToken data) {
        this.data = data;
    }
}
