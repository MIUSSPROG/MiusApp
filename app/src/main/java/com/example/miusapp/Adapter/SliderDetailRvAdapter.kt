package com.example.miusapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.AddItemToCategory
import com.example.miusapp.DetailNavigationActivity
import com.example.miusapp.Fragments.AddItemFragment
import com.example.miusapp.Fragments.EditDeleteItemFragment
import com.example.miusapp.Model.SliderDetailRvItem
import com.example.miusapp.Model.SliderRvItem
import com.example.miusapp.R

class SliderDetailRvAdapter internal constructor(
    categoryTitle: String,
    title: String,
    items: List<SliderDetailRvItem>,
    context: Context,
    fragmentManager: FragmentManager
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categoryTitle: String = categoryTitle
    private var title: String
    private var items: List<SliderDetailRvItem> = ArrayList()
    var context: Context
    private var fragmentManager: FragmentManager

    init {
        this.items = items
        this.context = context
        this.fragmentManager = fragmentManager
        this.title = title
    }


    class SliderDetailRvViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val desc: TextView = itemView.findViewById(R.id.tv_nav_detail_row_desc)
        private val rowNum: TextView = itemView.findViewById(R.id.tv_nav_detail_row_num)
        private var bottomSheet = EditDeleteItemFragment()

        @SuppressLint("ResourceType")
        fun bind(context: Context, categoryTitle: String, title: String, data: SliderDetailRvItem, position: Int, fragmentManager: FragmentManager){
            desc.text = data.desc
            rowNum.text = position.toString()
            rowNum.background = data.background
            itemView.setOnClickListener {
                Toast.makeText(context, "this -> $position", Toast.LENGTH_SHORT).show()
                if(!bottomSheet.isAdded){
                    val bundle = Bundle()
                    bundle.putString("itemAnswer", data.desc)
                    bundle.putString("itemId", data.questionId)
                    bundle.putString("title", title)
                    bundle.putString("categoryTitle", categoryTitle)
                    bottomSheet.arguments = bundle
                    bottomSheet.show(fragmentManager, "")
                }
//                val intent = Intent(context, AddItemToCategory::class.java)
//                intent.putExtra("itemTitle", data.desc)
//                context.startActivity(intent)
            }
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
                holder.bind(context, categoryTitle,  title, items[position], position + 1, fragmentManager)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}