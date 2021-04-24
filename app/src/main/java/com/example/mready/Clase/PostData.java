package com.example.mready.Clase;

public class PostData {

    private GetMessage data;

    public PostData(GetMessage data) {
        this.data = data;
    }

    public GetMessage getData() {
        return data;
    }

    public void setData(GetMessage data) {
        this.data = data;
    }
}
