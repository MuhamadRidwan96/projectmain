package com.example.projectmain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelData {
    @Expose
    @SerializedName("id_stock")
    private int id_stock;
    @Expose
    @SerializedName("bin")
    private String bin;
    @Expose
    @SerializedName("mm")
    private int mm;
    @Expose
    @SerializedName("item")
    private String item;
    @Expose
    @SerializedName("available_stock")
    private int available_stock;
    @Expose
    @SerializedName("uom")
    private String uom;
    @Expose
    @SerializedName("gr_date")
    private String gr_date;

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public int getMm() {
        return mm;
    }

    public void setMm(int mm) {
        this.mm = mm;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getAvailable_stock() {
        return available_stock;
    }

    public void setAvailable_stock(int available_stock) {
        this.available_stock = available_stock;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getGr_date() {
        return gr_date;
    }

    public void setGr_date(String gr_date) {
        this.gr_date = gr_date;
    }
}
