package com.example.miusapp.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.AddItemToCategory
import com.example.miusapp.DetailNavigationActivity
import com.example.miusapp.Model.SliderDetailItem
import com.example.miusapp.Model.SliderItem
import com.example.miusapp.R
import com.example.miusapp.Utils.prefs

class SliderDetailAdapter internal constructor(
    categoryTitle: String,
    detailItems: MutableList<SliderDetailItem>,
    context: Context,
    fragmentManager: FragmentManager
): RecyclerView.Adapter<SliderDetailAdapter.SliderDetailViewHolder>() {

    private var categoryTitle: String
    private val detailItems: List<SliderDetailItem>
    private var context: Context
    private var fragmentManager: FragmentManager

    init {
        this.detailItems = detailItems
        this.context = context
        this.fragmentManager = fragmentManager
        this.categoryTitle = categoryTitle
    }

    class SliderDetailViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView){
        val rvDetail: RecyclerView = itemView.findViewById(R.id.rv_slide_detail)
        val tvDetailTitle: TextView = itemView.findViewById(R.id.tv_slide_detail_title)
        var context: Context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderDetailViewHolder {
        return SliderDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.slide_detail_item, parent, false), context
        )
    }

    override fun onBindViewHolder(holder: SliderDetailViewHolder, position: Int) {
        prefs.myDesc = detailItems[position].title
        holder.tvDetailTitle.text = detailItems[position].title
        holder.rvDetail.adapter = SliderDetailRvAdapter(categoryTitle, detailItems[position].title, detailItems[position].items!!, context, fragmentManager)
    }

    override fun getItemCount(): Int {
        return detailItems.size
    }

}