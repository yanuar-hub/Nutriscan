package com.example.nutriscan.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class LoginResponse (
    @field:SerializedName("userid")
    val userId: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("token")
    val token: String
)

data class RegisterResponse (
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String
)

data class FoodListResponse (val data:ArrayList<Food>){
    data class Food(
    val id: String,
    val name: String,
    val photo: String,
    val portion: Int,
    val unit: String,
    val callories: Int,
    val category: String
    )
}

data class FindFoodResponse (
    val food : ArrayList<FoodListResponse>
)

data class AddFoodResponse (
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String
)

data class LogoutResponse (
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String
)