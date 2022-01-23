package com.example.angularjwtbe.dto.respone;

public class ResponeMessage {
    private String message;

    public ResponeMessage() {
    }

    public ResponeMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
