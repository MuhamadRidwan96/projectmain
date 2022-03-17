package com.example.projectmain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaterialIn {

    @SerializedName("no_doc")
    @Expose
    private int no_doc;
    @SerializedName("id_stock")
    @Expose
    private int id_stock;
    @SerializedName("material")
    @Expose
    private String material;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("uom")
    @Expose
    private String uom;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("shift")
    @Expose
    private String shift;
    @SerializedName("nik")
    @Expose
    private String nik;


    public int getNo_doc() {
        return no_doc;
    }

    public void setNo_doc(int no_doc) {
        this.no_doc = no_doc;
    }

    public int getId_stock() {
        return id_stock;
    }

    public void setId_stock(int id_stock) {
        this.id_stock = id_stock;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShift() { return shift; }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getNik() { return nik; }

    public void setNik(String nik) { this.nik = nik; }
}
