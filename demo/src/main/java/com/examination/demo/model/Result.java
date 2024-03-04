package com.examination.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result <D>{
    private String code;
    private String message;

    @JsonProperty("isSuccess")
    private boolean isSuccess=false;
    private D data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", isSuccess=" + isSuccess +
                ", data=" + data +
                '}';
    }
}
