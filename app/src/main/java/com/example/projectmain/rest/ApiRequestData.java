package com.example.projectmain.rest;

import com.example.projectmain.response.ResponseLogin;
import com.example.projectmain.response.ResponseModel;
import com.example.projectmain.response.ResponseTransaksiIn;
import com.example.projectmain.response.ResponseTransaksiOut;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiRequestData {
    // retrieve stock api
    //using response model


    @GET("Api/stock")
    Call<ResponseModel> Stock(@Header("Authorization") String authHeader);

    //USING RESPONSE TRANSACTION IN
    //retrieve MIGO API

    @GET("Api/migo")
    Call<ResponseTransaksiIn> getMigo(@Header("Authorization") String authHeaders);

    //USING RESPONSE TRANSACTION OUT
    //retrieve CONSUME API

    @GET("Api/Consume")
    Call<ResponseTransaksiOut> getConsume(@Header("Authorization") String authHeaders1);

    // post update stock api
    @FormUrlEncoded
    @PUT("Api/stocks/{id_stock}")
    Call<ResponseModel> updateStock(
            @Header("Authorization") String authHeader,
            @Field("id_stock") int id_stock,
            @Field("bin") String bin,
            @Field("mm") int mm,
            @Field("item") String item,
            @Field("available_stock") double available_stock,
            @Field("uom") String uom,
            @Field("gr_date") String gr_date
    );

    //post update migo api
    @FormUrlEncoded
    @PUT("Api/transactions_in")
    Call<ResponseTransaksiIn> updateMigo(
            @Header("Authorization") String authHeaders,
            @Field("no_doc") int no_doc,
            @Field("id_stock") int id_stock,
            @Field("material") String material,
            @Field("quantity") int quantity,
            @Field("uom") String uom,
            @Field("date") String date,
            @Field("shift") String shift,
            @Field(("nik")) String nik
    );

    //post update consumption api
    @FormUrlEncoded
    @PUT("Api/transactions_out")
    Call<ResponseTransaksiOut> updateConsumption(
            @Header("Authorization") String authHeaders,
            @Field("mat_doc") int mat_doc,
            @Field("id_stock") int id_stock,
            @Field("material") String material,
            @Field("quantity") int quantity,
            @Field("uom") String uom,
            @Field("date") String date,
            @Field("shift") String shift,
            @Field("nik") String nik
    );

    // post create migo
    @FormUrlEncoded
    @POST("Api/transactions_in")
    Call<ResponseTransaksiIn> postMigo(
            @Header("Authorization") String authHeaders,
            @Field("no_doc") int no_doc,
            @Field("id_stock") int id_stock,
            @Field("material") String material,
            @Field("quantity") int quantity,
            @Field("uom") String uom,
            @Field("date") String date,
            @Field("shift") String shift,
            @Field(("nik")) String nik

    );

    @FormUrlEncoded
    @POST("Api/transactions_out")
    Call<ResponseTransaksiOut> postConsumption(
            @Header("Authorization") String authHeaders1,
            @Field("mat_doc") int mat_doc,
            @Field("id_stock") int id_stock,
            @Field("material") String material,
            @Field("quantity") int quantity,
            @Field("uom") String uom,
            @Field("date") String date,
            @Field("shift") String shift,
            @Field("nik") String nik

    );

    //post register api
    @FormUrlEncoded
    @POST("Api/users")
    Call<ResponseLogin> register(
            @Header("Authorization") String authHeader,
            @Field("nik") String nik,
            @Field("username") String username,
            @Field("password") String password,
            @Field("phone") String phone
    );

    //use different api
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> loginUser(
            @Field("nik") String nik,
            @Field("password") String password
    );
//use auth api
    @FormUrlEncoded
    @POST("Api/auth")
    Call<ResponseLogin> login(
            @Header("Authorization") String authHeader,
            @Field("nik") String nik,
            @Field("password") String password
    );


    @DELETE("Api/transactions_in/{no_doc}")
    Call<ResponseTransaksiIn> deleteMigo(
            @Header("Authorization") String authHeader,
            @Path("no_doc") int no_doc
    );

    @DELETE("Api/transactions_out/{mat_doc}")
    Call<ResponseTransaksiOut> deleteConsume(
            @Header("Authorization") String authHeader,
            @Path("mat_doc") int mat_doc
    );

    @FormUrlEncoded
    @POST("deleteConsume.php")
    Call<ResponseTransaksiOut> deleteOut(
            @Field("mat_doc") int mat_doc
    );

    @FormUrlEncoded
    @POST("deleteMigo.php")
    Call<ResponseTransaksiIn> deleteIn(
            @Field("no_doc") int no_doc
    );

    @FormUrlEncoded
    @POST("deleteStock.php")
    Call<ResponseModel> deleted(
            @Field("id_stock") int id_stock
    );


   @DELETE("Api/stocks")
    Call<ResponseModel>deletedStock(
            @Header("Authorization") String authHeader,
            @Field("id_stock") int id_stock
    );

}
