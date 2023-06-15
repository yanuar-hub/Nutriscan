package com.example.nutriscan.api


import com.example.nutriscan.model.AddFoodResponse
import com.example.nutriscan.model.FindFoodResponse
import com.example.nutriscan.model.FoodListResponse
import com.example.nutriscan.model.LoginResponse
import com.example.nutriscan.model.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @GET("food")
    fun getfoodList(): Call<FoodListResponse>

    @GET("food/find")
    fun findFood(
        @Query("q")query: String
    ):Call<FindFoodResponse>

    @Multipart
    @POST("addfood")
    fun addfood(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): Call<AddFoodResponse>


}