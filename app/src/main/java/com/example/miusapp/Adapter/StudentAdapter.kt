package com.example.miusapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miusapp.Model.Group
import com.example.miusapp.Model.Student
import com.example.miusapp.R
import com.example.miusapp.StudentDetailActivity

class StudentAdapter internal constructor(
    students: MutableList<Student>,
    context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var students: List<Student> = ArrayList()
    var context: Context

    init {
        this.students = students
        this.context = context
    }

    class StudentViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val studentName: TextView = itemView.findViewById(R.id.tvStudentName)
        val studentAge: TextView = itemView.findViewById(R.id.tvStudentAge)
        val studentPercentComplete: TextView = itemView.findViewById(R.id.tvStudentInfoPercentComplete)
        val pbPercentComplete: ProgressBar = itemView.findViewById(R.id.pbProgressComplete)

        @SuppressLint("ResourceType")
        fun bind(context: Context, data: Student, position: Int){
            studentName.text = data.name
            studentAge.text = data.age.toString() + " лет"
            studentPercentComplete.text = data.percentComplete.toString()
            pbPercentComplete.progress = data.percentComplete
            itemView.setOnClickListener {
                context.startActivity(Intent(context, StudentDetailActivity::class.java))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StudentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_student, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is StudentViewHolder -> {
                holder.bind(context, students[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }
}