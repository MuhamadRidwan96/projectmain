package com.example.projectmain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaterialOut {

    @SerializedName("mat_doc")
    @Expose
    private int mat_doc;
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

    public MaterialOut(int mat_doc, int id_stock, String material, int quantity, String uom, String date, String shift,String nik) {
        this.mat_doc = mat_doc;
        this.id_stock = id_stock;
        this.material = material;
        this.quantity = quantity;
        this.uom = uom;
        this.date = date;
        this.shift = shift;
        this.nik = nik;
    }

    public int getMat_doc() {
        return mat_doc;
    }

    public void setMat_doc(int mat_doc) {
        this.mat_doc = mat_doc;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getNik() { return nik; }

    public void setNik(String nik) { this.nik = nik; }
}

