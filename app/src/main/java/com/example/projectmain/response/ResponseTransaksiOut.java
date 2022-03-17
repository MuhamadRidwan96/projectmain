package com.example.projectmain.response;


import com.example.projectmain.model.MaterialOut;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseTransaksiOut {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    @Expose
    private List<MaterialOut> data;


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

    public List<MaterialOut> getData() {
        return data;
    }

    public void setData(List<MaterialOut> data) {
        this.data = data;
    }
}
