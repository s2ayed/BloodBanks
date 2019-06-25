package com.example.bloodbank.data.api;


import com.example.bloodbank.data.model.blood_types.BloodTypes;
 import com.example.bloodbank.data.model.governorates.Governorates;
 import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.new_password.NewPassword;
import com.example.bloodbank.data.model.register.Register;
import com.example.bloodbank.data.model.reset.Reset;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {

    @POST("login")
    @FormUrlEncoded
    Call<Login> onLogin(@Field("phone")String phone, @Field("password")String password);

    @POST("register")
    @FormUrlEncoded
    Call<Register> onRegister(@Field("name") String name, @Field("email")String email
            , @Field("birth_date")String birth_date, @Field("city_id")int city_id
            , @Field("phone")String phone, @Field("donation_last_date")String donation_last_date
            , @Field("password")String password, @Field("password_confirmation") String password_confirmation,
                              @Field("blood_type_id")int blood_type_id);

    @POST("reset-password")
    @FormUrlEncoded
        Call<Reset> resetPassword(@Field("phone")String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> newPassword(@Field("password") String password, @Field("password_confirmation")String password_confirmation
            , @Field("pin_code")String pin_code, @Field("phone")String phone );

    @GET("governorates")
    Call<Governorates> getGovernorate ();

    @GET("cities")
    Call<Governorates> getCities(@Query("governorate_id")int governorate_id);


    @GET("blood-types")
    Call<BloodTypes> getBlood_types();
}
