package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class ResponseDto {

    @SerializedName("code")
    private int statusCode;

    @SerializedName("status")
    private String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", message ='" + statusMessage + '\'' +
                '}';
    }

}
