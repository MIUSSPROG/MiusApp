package com.example.miusapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.DetailNavigationActivity
import com.example.miusapp.Model.SliderDetailRvItem
import com.example.miusapp.Model.SliderRvItem
import com.example.miusapp.R

class SliderDetailRvAdapter internal constructor(
    items: List<SliderDetailRvItem>,
    context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<SliderDetailRvItem> = ArrayList()
    var context: Context

    init {
        this.items = items
        this.context = context
    }


    class SliderDetailRvViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val desc: TextView = itemView.findViewById(R.id.tv_nav_detail_row_desc)
        private val rowNum: TextView = itemView.findViewById(R.id.tv_nav_detail_row_num)

        @SuppressLint("ResourceType")
        fun bind(context: Context, data: SliderDetailRvItem, position: Int){
            desc.text = data.desc
            rowNum.text = position.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SliderDetailRvViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_item_detail, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SliderDetailRvViewHolder -> {
                holder.bind(context, items[position], position + 1)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}