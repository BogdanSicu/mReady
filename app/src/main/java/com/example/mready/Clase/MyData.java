package com.example.mready.Clase;

import java.util.ArrayList;
import java.util.List;

public class MyData {
    List<GetMessage> data;

    public MyData(List<GetMessage> listMessages) {
        this.data = listMessages;
    }

    public List<GetMessage> getData() {
        return data;
    }

    public void setData(List<GetMessage> data) {
        this.data = data;
    }

    public ArrayList<GetMessage> getMessageArrayList(){
        return new ArrayList<>(data);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MyData{");
        sb.append("data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
