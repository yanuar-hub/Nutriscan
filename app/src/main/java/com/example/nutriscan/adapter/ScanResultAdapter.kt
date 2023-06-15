package com.example.nutriscan.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.nutriscan.R
import com.example.nutriscan.model.Objek

class ScanResultAdapter (private val listScan: ArrayList<Objek>) : RecyclerView.Adapter<ScanResultAdapter.ListViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_scan, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listScan.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (img,kalori,deskripsi) =listScan[position]
        holder.imgPhoto.setImageResource(img)
        holder.tvKalori.text=kalori
        holder.tvDeskripsi.text=deskripsi
    }

    class ListViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val imgPhoto : ImageView = itemView.findViewById(R.id.iv_scan_result)
        val tvKalori: TextView = itemView.findViewById(R.id.tv_jumlah_kalori)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tv_nama_object)
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Objek)
    }
}