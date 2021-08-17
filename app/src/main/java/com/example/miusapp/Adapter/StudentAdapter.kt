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
    groupId: String,
    context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var students: List<Student> = ArrayList()
    private var groupId: String
    var context: Context

    init {
        this.students = students
        this.groupId = groupId
        this.context = context
    }

    class StudentViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val studentName: TextView = itemView.findViewById(R.id.tvStudentName)
        val studentAge: TextView = itemView.findViewById(R.id.tvStudentAge)
        val studentPercentComplete: TextView = itemView.findViewById(R.id.tvStudentInfoPercentComplete)
        val pbPercentComplete: ProgressBar = itemView.findViewById(R.id.pbProgressComplete)

        @SuppressLint("ResourceType")
        fun bind(context: Context, data: Student, position: Int, groupId: String){
            studentName.text = data.name
            studentAge.text = data.age.toString() + " лет"
            val completion = calculateCompletion(data)
            studentPercentComplete.text = completion.toString()
            pbPercentComplete.progress = completion
            itemView.setOnClickListener {
                val intent = Intent(context, StudentDetailActivity::class.java)
                intent.putExtra("studentId", data.id)
                intent.putExtra("studentName", data.name)
                intent.putExtra("groupId", groupId)
                context.startActivity(intent)
            }
        }

        private fun calculateCompletion(item: Student) : Int{
            var count = 0
            if (item.theorTrain1First != 0){
                count++
            }
            if (item.theorTrain1Second != 0){
                count++
            }
            if (item.theorTrain2First != 0){
                count++
            }
            if (item.theorTrain2Second != 0){
                count++
            }
            if (item.practicTrain1First != 0){
                count++
            }
            if (item.practicTrain1Second != 0){
                count++
            }
            if (item.practicTrain2First != 0){
                count++
            }
            if (item.practicTrain2Second != 0){
                count++
            }
            if (item.practicTrain3First != 0){
                count++
            }
            if (item.practicTrain3Second != 0){
                count++
            }
            if (item.generalTrain1First != 0){
                count++
            }
            if (item.generalTrain1Second != 0){
                count++
            }
            if (item.generalTrain2First != 0){
                count++
            }
            if (item.generalTrain2Second != 0){
                count++
            }
            if (item.generalTrain3First != 0){
                count++
            }
            if (item.generalTrain3Second != 0){
                count++
            }
            if (item.generalTrain4First != 0){
                count++
            }
            if (item.generalTrain4Second != 0){
                count++
            }
            if (item.generalTrain5First != 0){
                count++
            }
            if (item.generalTrain5Second != 0){
                count++
            }
            if (item.generalTrain6First != 0){
                count++
            }
            if (item.generalTrain6Second != 0){
                count++
            }
            if (item.generalTrain7First != 0){
                count++
            }
            if (item.generalTrain7Second != 0){
                count++
            }
            if (item.generalTrain8First != 0){
                count++
            }
            if (item.generalTrain8Second != 0){
                count++
            }
            if (item.generalTrain9First != 0){
                count++
            }
            if (item.generalTrain9Second != 0){
                count++
            }
            if (item.generalTrain10First != 0){
                count++
            }
            if (item.generalTrain10Second != 0){
                count++
            }
            if (item.generalTrain11First != 0){
                count++
            }
            if (item.generalTrain11Second != 0){
                count++
            }

            return (count*100)/32
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StudentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_student, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is StudentViewHolder -> {
                holder.bind(context, students[position], position, groupId)
            }
        }
    }


    override fun getItemCount(): Int {
        return students.size
    }
}