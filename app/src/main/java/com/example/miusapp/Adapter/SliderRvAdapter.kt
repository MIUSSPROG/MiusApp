package com.example.miusapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.DetailNavigationActivity
import com.example.miusapp.Model.SliderRvItem
import com.example.miusapp.R

class SliderRvAdapter internal constructor(
    items: List<SliderRvItem>,
    title: String,
    background: Int,
    context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<SliderRvItem> = ArrayList()
    private var title: String
    private var background: Int
    var context: Context

    init {
        this.items = items
        this.context = context
        this.title = title
        this.background = background
    }

    class SliderRvViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val desc: TextView = itemView.findViewById(R.id.tv_desc_rv_item)
        val count: TextView = itemView.findViewById(R.id.tv_count_rv_item)

        @SuppressLint("ResourceType")
        fun bind(context: Context, data: SliderRvItem, title: String, background: Int){
            desc.text = data.desc
            count.text = data.count.toString()
            itemView.setOnClickListener {
                val intent = Intent(context, DetailNavigationActivity::class.java)
                intent.putExtra("desc", data.desc)
                intent.putExtra("title", title)
                intent.putExtra("background", background)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SliderRvViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SliderRvViewHolder -> {
                holder.bind(context, items[position], title, background)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}