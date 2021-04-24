package com.example.mready.Clase;

import java.util.Date;

public class GetMessage {

    private long id;
    private String display_name;
    private long user_id;
    private String message;
    private String created_at;

    public GetMessage() {
    }

    public GetMessage(long id, String display_name, long user_id, String message, String created_at) {
        this.id = id;
        this.display_name = display_name;
        this.user_id = user_id;
        this.message = message;
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageReceived{");
        sb.append("id=").append(id);
        sb.append(", display_name='").append(display_name).append('\'');
        sb.append(", user_id=").append(user_id);
        sb.append(", message='").append(message).append('\'');
        sb.append(", created_at='").append(created_at).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
