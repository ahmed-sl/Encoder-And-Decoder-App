package com.example.encoderanddecoder

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.encoderanddecoder.databinding.ItemRowBinding

class rvAdapter (val codes: ArrayList<Pharse>): RecyclerView.Adapter<rvAdapter.ItemeHolder>(){
    class ItemeHolder (val bind: ItemRowBinding): RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemeHolder {
        return ItemeHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: ItemeHolder, position: Int) {
        val code = codes[position]

        holder.bind.apply {
            textView.text=code.txt
            if(!code.origin){textView.setTextColor(Color.BLUE)}
        }
    }

    override fun getItemCount() = codes.size

    fun update() {
        notifyDataSetChanged()
    }

}