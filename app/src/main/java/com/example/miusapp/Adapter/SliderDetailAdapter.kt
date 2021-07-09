package com.example.miusapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.Model.SliderDetailItem
import com.example.miusapp.Model.SliderItem
import com.example.miusapp.R

class SliderDetailAdapter internal constructor(
    detailItems: MutableList<SliderDetailItem>,
    context: Context
): RecyclerView.Adapter<SliderDetailAdapter.SliderDetailViewHolder>() {

    private val detailItems: List<SliderDetailItem>
    private var context: Context

    init {
        this.detailItems = detailItems
        this.context = context
    }

    class SliderDetailViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView){
        val rvDetail: RecyclerView = itemView.findViewById(R.id.rv_slide_detail)
        var context: Context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderDetailViewHolder {
        return SliderDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.slide_detail_item, parent, false), context
        )
    }

    override fun onBindViewHolder(holder: SliderDetailViewHolder, position: Int) {
        holder.rvDetail.adapter = SliderDetailRvAdapter(detailItems[position].items, context)
    }

    override fun getItemCount(): Int {
        return detailItems.size
    }

}