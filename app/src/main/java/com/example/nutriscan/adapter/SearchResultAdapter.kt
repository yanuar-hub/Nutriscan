package com.example.nutriscan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nutriscan.R
import com.example.nutriscan.model.FoodListResponse

class SearchResultAdapter(val food : ArrayList<FoodListResponse.Food>)
    : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_scan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = food.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = food[position]
        holder.tvKalori.text=food.callories.toString()
        holder.tvDeskripsi.text=food.name
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val imgPhoto : ImageView = view.findViewById(R.id.iv_scan_result)
        val tvKalori: TextView = view.findViewById(R.id.tv_jumlah_kalori)
        val tvDeskripsi: TextView = view.findViewById(R.id.tv_nama_object)
    }

    fun setData(data:ArrayList<FoodListResponse.Food>){
        food.clear()
        food.addAll(data)
        notifyDataSetChanged()
    }
}