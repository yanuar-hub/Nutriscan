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

data class FoodListResponse(
    @SerializedName("data")
    var data:ArrayList<Food> = arrayListOf(),
    @SerializedName("count")
    var count: Int?= null,
    @SerializedName("totalPages")
    var totalPages: Int?= null,
    @SerializedName("currentPage" )
    var currentPage: Int?= null)

data class Food(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("photo")
    var photo: String? = null,
    @SerializedName("portion")
    var portion: Int?= null,
    @SerializedName("unit")
    var unit: String? = null,
    @SerializedName("callories")
    var callories : Int?= null,
    @SerializedName("category")
    var category  : String? = null
)

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