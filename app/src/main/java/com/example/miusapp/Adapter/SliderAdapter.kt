package com.example.miusapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.Model.SliderItem
import com.example.miusapp.R
class SliderAdapter internal constructor(
    sliderItems: MutableList<SliderItem>,
    context: Context
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){

    private val sliderItems: List<SliderItem>
    private var context: Context

    init {
        this.sliderItems = sliderItems
        this.context = context
    }

    class SliderViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView){
        private val textView: TextView = itemView.findViewById(R.id.tv_slide_item_title)
        private val rv: RecyclerView = itemView.findViewById(R.id.rvSliderItem)
        private var context: Context = context

        fun setSliderItem(sliderItem: SliderItem){
            textView.text = sliderItem.title
            textView.setBackgroundResource(sliderItem.background)

            val rvAdapter = SliderRvAdapter(sliderItem.items,sliderItem.title, sliderItem.background,  context)
            rv.adapter = rvAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.slide_item, parent, false), context)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
         holder.setSliderItem(sliderItems[position])
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }


}