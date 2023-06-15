package com.example.nutriscan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nutriscan.databinding.ItemRowScanBinding
import com.example.nutriscan.model.Food

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.UserViewHolder>() {

    private val list = ArrayList<Food>()
    private var onItemClickCallback: OnItemClickCallback?=null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(foods : ArrayList<Food>){
        list.clear()
        list.addAll(foods)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemRowScanBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(food)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(food.photo)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .transform(CircleCrop())
                    .into(ivScanResult)
                tvJumlahKalori.text = food.callories.toString()
                tvNamaObject.text=food.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemRowScanBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: Food)
    }
}