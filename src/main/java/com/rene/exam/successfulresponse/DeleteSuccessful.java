package com.rene.exam.successfulresponse;

public class DeleteSuccessful {

    private int code;

    private String message;

    public DeleteSuccessful(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public DeleteSuccessful() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
