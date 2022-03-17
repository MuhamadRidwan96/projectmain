package com.example.projectmain.response;

import com.example.projectmain.model.MaterialIn;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTransaksiIn {
    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    @Expose
    private List<MaterialIn> data;

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

    public List<MaterialIn> getData() {
        return data;
    }

    public void setData(List<MaterialIn> data) {
        this.data = data;
    }
}
