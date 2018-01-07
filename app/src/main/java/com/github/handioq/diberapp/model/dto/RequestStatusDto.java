package com.github.handioq.diberapp.model.dto;

import com.google.gson.annotations.SerializedName;

public class RequestStatusDto {

    public RequestStatusDto(String status) {
        this.status = status;
    }

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RequestStatusDto{" +
                "status='" + status + '\'' +
                '}';
    }

}
