package com.example.miusapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.DetailNavigationActivity
import com.example.miusapp.Model.Group
import com.example.miusapp.Model.SliderRvItem
import com.example.miusapp.R

class DiagnosticMapAdapter internal constructor(
    items: List<Group>,
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<Group> = ArrayList()
    var context: Context

    init {
        this.items = items
        this.context = context
    }

    class DiagnosticMapViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val groupPos: TextView = itemView.findViewById(R.id.tvGroupNumber)
        val groupName: TextView = itemView.findViewById(R.id.tvGroupName)
        val groupAge: TextView = itemView.findViewById(R.id.tvGroupAge)
        val groupDateStart: TextView = itemView.findViewById(R.id.tvGroupDateStart)
        val groupDateEnd: TextView = itemView.findViewById(R.id.tvGroupDateEnd)
        val groupStudentCount: TextView = itemView.findViewById(R.id.tvGroupStudentCount)
        val formEducation: TextView = itemView.findViewById(R.id.tvGroupFormEducation)

        @SuppressLint("ResourceType")
        fun bind(context: Context, data: Group, position: Int){
            groupPos.text = (position + 1).toString() + " группа"
            groupName.text = data.name
            groupAge.text = data.ageFrom.toString() + "-" + data.ageTo.toString() + " лет"
            groupDateStart.text = data.dateStart + "-"
            groupDateEnd.text = data.dateEnd
            if (data.form == "Очно"){
                formEducation.setTextColor(Color.BLACK)
                formEducation.setBackgroundResource(R.drawable.rounded3)
            }
            else{
                formEducation.setTextColor(Color.WHITE)
                formEducation.setBackgroundResource(R.drawable.rounded1)
            }
            formEducation.text = data.form
            groupStudentCount.text = data.students.size.toString()

            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DiagnosticMapAdapter.DiagnosticMapViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_diagnostic, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is DiagnosticMapAdapter.DiagnosticMapViewHolder -> {
                holder.bind(context, items[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}