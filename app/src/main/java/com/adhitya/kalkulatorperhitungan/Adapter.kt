package com.adhitya.kalkulatorperhitungan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val dataset:MutableList<Data>)
    :RecyclerView.Adapter<Adapter.DataHolder>(){
    class DataHolder(view:View):RecyclerView.ViewHolder(view) {
        val textHitung = view.findViewById<TextView>(R.id.txtHitung)
        val textHasil = view.findViewById<TextView>(R.id.txtHasil)
        val btnHapus = view.findViewById<ImageView>(R.id.btn_hapus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_adapter,parent,false
        )
        return DataHolder(view)
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val data = dataset[position]
        holder.textHitung.text = dataset[position].text1
        holder.textHasil.text = dataset[position].text2
        holder.btnHapus.setOnClickListener {
            dataset.removeAt(position)
            notifyItemRangeRemoved(position,dataset.size)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}