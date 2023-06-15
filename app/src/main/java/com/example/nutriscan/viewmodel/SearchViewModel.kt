package com.example.nutriscan.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutriscan.api.ApiConfig
import com.example.nutriscan.api.ApiService
import com.example.nutriscan.model.Food
import com.example.nutriscan.model.FoodListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchViewModel : ViewModel() {
    val foodList = MutableLiveData<ArrayList<Food>>()

    fun loadFoodList(){
        ApiConfig.getApiService()
            .getfoodList()
            .enqueue(object :Callback<FoodListResponse>{
                override fun onResponse(
                    call: Call<FoodListResponse>,
                    response: Response<FoodListResponse>,
                ) {
                    if (response.isSuccessful){
                        foodList.postValue(response.body()?.data)
                    }else{
                        Log.e("Search Fragment", "onFailure: ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<FoodListResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        }
    fun getFood(): LiveData<ArrayList<Food>> {
        return foodList
    }
}